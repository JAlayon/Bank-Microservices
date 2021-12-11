package com.alayon.msbank.movements.service;

import com.alayon.msbank.movements.models.MovementModel;

public interface IMovementService {
    public MovementModel add (MovementModel movement);
    public Iterable<MovementModel> findByAccountId (Integer accountId);
}

