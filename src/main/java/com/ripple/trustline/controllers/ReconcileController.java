package com.ripple.trustline.controllers;

import com.ripple.trustline.exceptions.ParticipantDoesNotExist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/trustline/reconcile")

public class ReconcileController {

    Logger logger = LoggerFactory.getLogger(ReconcileController.class);

    @Autowired
    private TrustlineCoordinator coordinator;

    @PostMapping
    public @ResponseBody
    void requestPayment(@RequestBody String source, HttpServletResponse response){

        try {
            coordinator.reconcile(source);
        } catch (ParticipantDoesNotExist e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (Exception e){
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        }
    }





}
