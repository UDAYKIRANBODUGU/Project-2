package in.uday.Service;

import java.util.Map;

import in.uday.Model.User;
import in.uday.bindings.LoginFrom;
import in.uday.bindings.RegisterForm;
import in.uday.bindings.RestPwdFrom;

public interface UserService {

	Map<Integer, String> getCountries();

	Map<Integer, String> getStates(Integer countryId);

	Map<Integer, String> getCities(Integer stateId);

	User getUser(String userEmail);

	boolean saveUser(RegisterForm formObject);

	User login(LoginFrom formObject);

	boolean resetPassword(RestPwdFrom formObject);
}
