package in.uday.Utils;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.uday.Model.City;
import in.uday.Model.Country;
import in.uday.Model.State;
import in.uday.Repo.CityRepo;
import in.uday.Repo.CountryRepo;
import in.uday.Repo.StateRepo;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private CountryRepo countryrepo;

	@Autowired
	private StateRepo staterepo;

	@Autowired
	private CityRepo cityrepo;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		countryrepo.deleteAll();

		staterepo.deleteAll();

		cityrepo.deleteAll();

		Country c1 = new Country(1, "INDIA");
		Country c2 = new Country(2, "US");

		countryrepo.saveAll(Arrays.asList(c1, c2));

		State s1 = new State(1, "AP", 1);
		State s2 = new State(2, "TS", 1);

		State s3 = new State(3, "Texas", 2);
		State s4 = new State(4, "New Jersey", 2);

		staterepo.saveAll(Arrays.asList(s1, s2, s3, s4));

		City city1 = new City(1, "KRNL", 1);
		City city2 = new City(2, "VIZAG", 1);

		City city3 = new City(3, "HYD", 2);
		City city4 = new City(4, "NZMD", 2);

		City city5 = new City(5, "TEXAS-1", 3);
		City city6 = new City(6, "TEXAS-2", 3);

		City city7 = new City(7, "NJ-1", 4);
		City city8 = new City(8, "NJ-2", 4);

		cityrepo.saveAll(Arrays.asList(city1, city2, city3, city4, city5, city6, city7, city8));

	}

}
