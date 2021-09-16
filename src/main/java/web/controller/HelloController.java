package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

	private UserService userService;

	@Autowired
	public HelloController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/")
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm Spring MVC application");
		messages.add("5.2.0 version by sep'19 ");
		List<User>  userList = userService.getAllUsers();
		model.addAttribute("userList", userList);
		return "index";
	}

	@GetMapping(value = "/createUserAddForm")
	public String createAddUserForm (User user) {
		return "createUserAddForm";
	}

	@PostMapping(value = "/userCreate")
	public String createUser(User user) {
		userService.saveUser(user);
		return "redirect:/";
	}

	@GetMapping(value = "deleteUser/{id}")
	public String deleteUser(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
		return "redirect:/";
	}

	@GetMapping(value = "/updateUser/{id}")
	public String updateUser(@PathVariable("id") Long id, Model model) {
		User user = userService.getUserById(id);
		model.addAttribute("user",user);
		return "/userUpdate";
	}

	@PostMapping(value ="/user-update")
	public String updateUserForm(User user) {
		userService.updateUser(user);
		return "redirect:/";
	}
}