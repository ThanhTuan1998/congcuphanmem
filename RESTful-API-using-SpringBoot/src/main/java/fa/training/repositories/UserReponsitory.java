package fa.training.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fa.training.resful.entities.User;
@Repository
public interface UserReponsitory extends JpaRepository<User, Long>{
	public User findByUsernameAndPassword(String username, String password);
}
