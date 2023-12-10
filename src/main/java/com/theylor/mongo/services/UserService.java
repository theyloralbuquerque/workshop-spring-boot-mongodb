package com.theylor.mongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theylor.mongo.domain.User;
import com.theylor.mongo.repository.UserRepository;
import com.theylor.mongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = userRepository.findById(id);
		
		// retorna o obj ou lança uma exceção que é uma ObjectNotFoundException.
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado")); 
	}
}

