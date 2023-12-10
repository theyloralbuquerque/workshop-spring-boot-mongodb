package com.theylor.mongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.theylor.mongo.domain.User;
import com.theylor.mongo.dto.UserDTO;
import com.theylor.mongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() { // O retorno será uma Lista de UserDTO.
		List<User> list = userService.findAll();
		
		// Conversão dos objetos User contidos em list para objetos UserDTO.
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) { // O retorno será um UserDTO.
		User obj = userService.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) { // O retorno Void.
		User obj = userService.fromDTO(objDto);
		obj = userService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build(); // Código 201, created.
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		userService.delete(id);
		return ResponseEntity.noContent().build(); // Código 204, no content.
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable String id, @RequestBody UserDTO objDto) { // O retorno Void.
		User obj = userService.fromDTO(objDto);
		obj.setId(id);
		obj = userService.update(obj);
		return ResponseEntity.noContent().build(); // Código 204, no content.
	}
}
