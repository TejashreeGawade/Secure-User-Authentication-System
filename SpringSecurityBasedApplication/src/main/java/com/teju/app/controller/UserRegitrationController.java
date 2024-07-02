package com.teju.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teju.app.dto.UserRegitrationDto;
import com.teju.app.service.UserService;

@Controller
@RequestMapping("/registration")
public class UserRegitrationController {
	
	private UserService userService;
	
//	public UserRegitrationController() {
//		
//	}

	public UserRegitrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	//
	@ModelAttribute("user")
    public UserRegitrationDto userRegistrationDto() {
        return new UserRegitrationDto();
    }
	
	@GetMapping
	//just to show registration page
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping
	//here usr object containes a form data and we are binding it with dto object
	public String registerUserAccount(@ModelAttribute("user") UserRegitrationDto userRegitrationDto) {
		userService.save(userRegitrationDto);
		return "redirect:/registration?successs";
	}

}
