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
	
	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}
	
	public User update(User obj) {
		// newObj recebe os mesmmos dados que o user com o id igual ao obj passado como parâmetro.
		User newObj = findById(obj.getId()); 
		
		// newObj tem os valores do user no banco de dados com o id igual ao obj passado como parâmetro.
		// obj tem os novos valores que a requisição enviou. 
		updateData(newObj, obj);
		return userRepository.save(newObj);
	}
	
	// Armazena os valores do User obj (name, email) no User newObj, sem trocar o id.
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	// Método que recebe um UserDTO e retorna os dados desse UserDTO em um novo User como retorno. 
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}

