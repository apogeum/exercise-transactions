package com.ripple.trustline;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.validation.constraints.NotNull;

@Service
public class RemoteTrustlineService implements TrustlineService {

    @Value("${trustline.remote}")
    private String remoteTrustlineURL;

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

}
