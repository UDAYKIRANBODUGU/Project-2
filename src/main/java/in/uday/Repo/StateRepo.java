package in.uday.Repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import in.uday.Model.State;

public interface StateRepo extends JpaRepository<State, Integer> {
    
    List<State> findByCountryId(Integer countryId);  
}
