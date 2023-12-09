package com.theylor.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.theylor.mongo.domain.User;

public interface UserRepository extends MongoRepository<User, String> {

}
