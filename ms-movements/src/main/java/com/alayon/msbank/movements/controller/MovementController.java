package com.alayon.msbank.movements.controller;

import com.alayon.msbank.movements.models.MovementModel;
import com.alayon.msbank.movements.service.IMovementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movement")
public class MovementController {

    private static final Logger logger = LoggerFactory.getLogger(MovementController.class);

    @Autowired
    IMovementService movementService;

    @GetMapping ("/{accountId}")
    public ResponseEntity<?> getByAccountId (@PathVariable Integer accountId){

        logger.info("Get By AccountId: {}", accountId);

        Iterable<MovementModel> transaction= movementService.findByAccountId(accountId);
        return ResponseEntity.ok(transaction);
    }
}
