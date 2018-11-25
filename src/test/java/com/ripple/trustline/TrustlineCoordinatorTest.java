package com.ripple.trustline;

import org.junit.Test;

import java.util.ArrayList;
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
        c.post(TransactionParams.build("Alice", "Bob", 150));
        c.post(TransactionParams.build("Bob", "Alice", 50));

        assertEquals(Integer.valueOf(-100), alice.calculateBalance());
        assertEquals(Integer.valueOf(100), bob.calculateBalance());


    }
}
