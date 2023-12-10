package com.theylor.mongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theylor.mongo.domain.User;
import com.theylor.mongo.dto.UserDTO;
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
	
	public User insert(User obj) {
		return userRepository.insert(obj);
	}
	
	// Método que recebe um UserDTO e retorna os dados desse UserDTO em um novo User como retorno. 
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}

