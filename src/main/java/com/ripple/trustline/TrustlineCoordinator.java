package com.ripple.trustline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


// This class coordinates transactions between two injected TrustlineService implementations
// in production configuration (alice/bob) the assumption is that there are 2 participants
// however implementation is ready for extending it to more participants

@Service
public class TrustlineCoordinator {

    Logger logger = LoggerFactory.getLogger(TrustlineCoordinator.class);

    private Map<String, TrustlineService> participants;

    @Autowired
    public void setParticipants(List<TrustlineService> l){
        logger.info("participants: ");
        participants = l.stream().collect(Collectors.toMap(TrustlineService::name, Function.identity()));
        if (participants.size() != 2){
            logger.error("there should be 2 participants configured");
            System.exit(-1);
        }

        participants.forEach((k,v) -> logger.info(k + " " + v));
    }

    public void post(@NotNull TransactionParams txParams) {

        Transaction tx = Transaction.build(txParams);
        logger.info("TrustlineCoordinator post: " + tx);

        try {
            // prepare
            participants.get(txParams.from).requestTransaction(tx.reverse());
            participants.get(txParams.to).requestTransaction(tx);
        } catch (Exception e){
            logger.info(e.getMessage());
            // rollback
            participants.forEach((k, t) -> t.rollbackTransaction(tx.id));
            throw e;
        }

        // commit
        participants.forEach((k, t) -> t.commitTransaction(tx.id));

    }
}
