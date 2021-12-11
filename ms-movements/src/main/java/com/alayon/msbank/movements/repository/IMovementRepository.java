package com.alayon.msbank.movements.repository;

import com.alayon.msbank.movements.models.MovementModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovementRepository extends MongoRepository<MovementModel, String>  {
    @Query("{'accountId':?0}")
    public Iterable<MovementModel> findByAccountId(Integer accountId);
}
