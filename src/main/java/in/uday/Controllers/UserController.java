package in.uday.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import in.uday.Constants.AppConstants;
import in.uday.Model.User;
import in.uday.Props.AppProps;
import in.uday.Service.UserService;
import in.uday.bindings.LoginFrom;
import in.uday.bindings.RegisterForm;
import in.uday.bindings.RestPwdFrom;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AppProps props;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("login", new LoginFrom());
		return "index";
	}

	@PostMapping("/login")
	public String loginCheck(@ModelAttribute("login") LoginFrom login, Model model, HttpSession session) {
		User user = userService.login(login);
		if (user == null) {
			model.addAttribute(AppConstants.ERROR_MSG, props.getMessages().get("Invalid Details"));
			// Handle the case where user is null (optional: you may redirect to a login
			// page)
			return "error"; // Replace "error" with the actual error view name or redirect to a login page.
		}

		if ("NO".equals(user.getPwdUpdated())) {
			RestPwdFrom restPwdFrom = new RestPwdFrom();
			restPwdFrom.setUserId(user.getUserId());
			model.addAttribute("restPwd", restPwdFrom);
			return "resetPwd";
		}

		session.setAttribute("userId", user.getUserId());
		return "redirect:/dashboard";
	}

	@PostMapping("/updatePwd")
	public String updatePwd(@ModelAttribute("resetPwd") RestPwdFrom resetPwd, Model model) {
		boolean status = userService.resetPassword(resetPwd);

		if (status) {
			return "redirect:/dashboard";
		}

		model.addAttribute(AppConstants.ERROR_MSG, props.getMessages().get("Pwd update failed"));
		return "resetPwd";
	}

	@GetMapping("/register")
	public String loadRegisterPage(Model model) {
		RegisterForm registerForm = new RegisterForm();
		model.addAttribute("registerfrom", registerForm);

		Map<Integer, String> countries = userService.getCountries();
		model.addAttribute("countries", countries);

		return "register";
	}

	@GetMapping("/getstates")
	@ResponseBody
	public Map<Integer, String> getStates(@RequestParam("country_id") Integer countryId) {
		return userService.getStates(countryId);
	}

	@GetMapping("/getcities")
	@ResponseBody
	public Map<Integer, String> getCities(@RequestParam("State_id") Integer stateId) {
		return userService.getCities(stateId);
	}

	@PostMapping("/register")
	public String registerUser(@ModelAttribute("registerfrom") RegisterForm registerForm, Model model) {

		boolean saveUser = userService.saveUser(registerForm);

		if (saveUser) {
			model.addAttribute(AppConstants.SUCC_MSG, props.getMessages().get("Register Details Successful"));
		} else {
			model.addAttribute(AppConstants.ERROR_MSG, props.getMessages().get("Registration Failed"));
		}

		Map<Integer, String> countries = userService.getCountries();
		model.addAttribute("countries", countries);

		return "register";
	}
}
