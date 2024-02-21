package in.uday.Repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import in.uday.Model.City;

public interface CityRepo extends JpaRepository<City, Integer> {

    List<City> findByStateId(Integer stateId);  
}
