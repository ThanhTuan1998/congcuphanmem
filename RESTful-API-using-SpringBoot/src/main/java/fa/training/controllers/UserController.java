package fa.training.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fa.training.exceptions.ResourceNotFoundException;
import fa.training.repositories.UserReponsitory;
import fa.training.resful.entities.User;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	private UserReponsitory userReponsitory;

	@GetMapping("/list")
	public List<User> getAllUsers() {
		return userReponsitory.findAll();
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		User user = userReponsitory.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found on:" + userId));
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("/add")
	public User create(@Validated @RequestBody User user) {
		return userReponsitory.save(user);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<User> update(@PathVariable(value = "id") Long userId,
			@Validated @RequestBody User userDetails) throws ResourceNotFoundException {
		User user = userReponsitory.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found on:" + userId));
		user.setPassword(userDetails.getPassword());
		final User updateUser = userReponsitory.save(user);
		return ResponseEntity.ok(updateUser);
	}

	@DeleteMapping("/delete/{id}")
	public Map<String, Boolean> delete(@PathVariable(value = "id") Long userId) throws Exception {
		User user = userReponsitory.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found on:" + userId));
		userReponsitory.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@PostMapping("/signin")
	public ResponseEntity<User> signIn(@Validated @RequestBody User u) {
		User user = userReponsitory.findByUsernameAndPassword(u.getUsername(), u.getPassword());
		if (user == null) {
			return ResponseEntity.ok(null);
		}
		return ResponseEntity.ok(user);
	}
}
