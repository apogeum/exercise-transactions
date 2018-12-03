package com.ripple.trustline;

import com.ripple.trustline.controllers.TrustlineCoordinator;
import com.ripple.trustline.dao.TransactionParams;
import com.ripple.trustline.service.LocalTrustlineService;
import com.ripple.trustline.service.TrustlineService;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TrustlineCoordinatorTest {

    @Test
    public void testTrustlineCoordinator(){
        TrustlineService alice = new LocalTrustlineService("Alice");
        TrustlineService bob = new LocalTrustlineService("Bob");
        List<TrustlineService> participants = Arrays.asList(alice, bob);

        TrustlineCoordinator c = new TrustlineCoordinator();
        c.setParticipants(participants);
        c.post(TransactionParams.build("1","Alice", "Bob", 150));
        c.post(TransactionParams.build("2","Bob", "Alice", 50));

        assertEquals(Integer.valueOf(-100), alice.calculateBalance());
        assertEquals(Integer.valueOf(100), bob.calculateBalance());


    }
}
