package in.uday.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.uday.Model.User;



public interface UserRepo extends JpaRepository<User, Integer> {
	
public User findByUserEmail(String userEmail);
	
public User  findByUserEmailAndUserpwd(String userEmail, String userpwd);
}
