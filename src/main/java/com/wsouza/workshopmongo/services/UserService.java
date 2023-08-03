package com.wsouza.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wsouza.workshopmongo.domain.User;
import com.wsouza.workshopmongo.dto.UserDTO;
import com.wsouza.workshopmongo.repository.UserRepository;
import com.wsouza.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
		User user = repo.findById(id).orElse(null);
		if (user == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		return user;
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		if(!(findById(id) == null)){
			
			repo.deleteById(id);
		}
	}
	public User fromDTO(UserDTO objDTO){
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}
}
