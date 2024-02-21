package in.uday.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.uday.Model.Country;

public interface CountryRepo extends JpaRepository<Country, Integer> {
	
	

}
