package in.uday.Service;

import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.uday.Constants.AppConstants;
import in.uday.Model.City;
import in.uday.Model.Country;
import in.uday.Model.State;
import in.uday.Model.User;
import in.uday.Repo.CityRepo;
import in.uday.Repo.CountryRepo;
import in.uday.Repo.StateRepo;
import in.uday.Repo.UserRepo;
import in.uday.Utils.EmailUtils;
import in.uday.bindings.LoginFrom;
import in.uday.bindings.RegisterForm;
import in.uday.bindings.RestPwdFrom;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private CountryRepo countryRepo;

	@Autowired
	private StateRepo stateRepo;

	@Autowired
	private CityRepo cityRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private EmailUtils emailUtils;

	Random random = new Random();

	@Override
	public Map<Integer, String> getCountries() {
		return countryRepo.findAll().stream().collect(Collectors.toMap(Country::getCountryId, Country::getCountryName));
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		return stateRepo.findByCountryId(countryId).stream()
				.collect(Collectors.toMap(State::getStateId, State::getStateName));
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		return cityRepo.findByStateId(stateId).stream().collect(Collectors.toMap(City::getCityId, City::getCityName));
	}

	@Override
	public User getUser(String userEmail) {
		return userRepo.findByUserEmail(userEmail);
	}

	@Override
	public boolean saveUser(RegisterForm formObject) {

		formObject.setUserpwd(generateRandomPwd());
		User userEntity = new User();
		userEntity.setPwdUpdated("NO");
		BeanUtils.copyProperties(formObject, userEntity);
		userRepo.save(userEntity);

		String subject = "YOUR ACCOUNT IS CREATED-  ";
		String body = "<h1>Your Password: " + formObject.getUserpwd() + "<h1>";

		return emailUtils.sendEmail(subject, body, formObject.getUserEmail());
	}

	private String generateRandomPwd() {
		return random.ints(5, 0, AppConstants.alphanumericCharacters.length())
				.mapToObj(AppConstants.alphanumericCharacters::charAt)
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
	}

	@Override
	public User login(LoginFrom formObject) {
		return userRepo.findByUserEmailAndUserpwd(formObject.getEmail(), formObject.getPwd());
	}

	@Override
	public boolean resetPassword(RestPwdFrom formObject) {
		return userRepo.findById(formObject.getUserId()).map(user -> {
			user.setUserpwd(formObject.getNewPwd());
			user.setPwdUpdated("YES");
			userRepo.save(user);
			return true;
		}).orElse(false);
	}

}
