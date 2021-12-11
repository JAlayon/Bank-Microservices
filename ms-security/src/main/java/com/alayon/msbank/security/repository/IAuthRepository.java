package com.alayon.msbank.security.repository;

import com.alayon.msbank.security.model.AccessModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthRepository extends CrudRepository<AccessModel, Long> {

}
