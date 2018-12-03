package com.ripple.trustline.service;

import com.ripple.trustline.dao.TransactionDAO;
import com.ripple.trustline.domain.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Order(value=2)
public class RemoteTrustlineService implements TrustlineService {

    @Value("${trustline.remote}")
    private String remoteTrustlineURL;

    @Value("${trustline.remoteCommitted}")
    private String remoteCommittedURL;

    @Value("${trustline.remoteAccount}")
    private String name;

    @Override
    public void requestTransaction(@NotNull Transaction tx) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<TransactionDAO> request = new HttpEntity<>(TransactionDAO.build(tx));
        restTemplate.postForLocation(remoteTrustlineURL, request);
    }

    @Override
    public void commitTransaction(@NotNull String txId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>(txId);
        restTemplate.put(remoteTrustlineURL, request);
    }

    @Override
    public void rollbackTransaction(@NotNull String txId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(remoteTrustlineURL + "/" + txId);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Integer calculateBalance() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public List<Transaction> getCommittedReverse() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Transaction>> response = restTemplate.exchange(
                remoteCommittedURL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Transaction>>(){});
        return response.getBody();

    }

    @Override
    public void setCommitted(List<Transaction> s) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<List<Transaction>> request = new HttpEntity<>(s);
        restTemplate.postForLocation(remoteCommittedURL, request);

    }

    @Override
    public boolean isTransactionCommitted(String id) {
        throw new RuntimeException("not implemented");
    }

}
