package com.alayon.msbank.movements.service;

import com.alayon.msbank.movements.models.MovementModel;
import com.alayon.msbank.movements.repository.IMovementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "movement")
@Service
public class MovementService implements IMovementService {

    @Autowired
    IMovementRepository movementRepository;

    Logger logger = LoggerFactory.getLogger(MovementService.class);

    @Override
    public MovementModel add(final MovementModel movement) {
        return movementRepository.save(movement);
    }

    @Override
    @Cacheable(cacheNames = { "movement" }, key = "#accountId")
    public Iterable<MovementModel> findByAccountId(final Integer accountId) {

        logger.info("SERVICE: Get Find By AccountId: {}", accountId);

        return movementRepository.findByAccountId(accountId);
    }

}
