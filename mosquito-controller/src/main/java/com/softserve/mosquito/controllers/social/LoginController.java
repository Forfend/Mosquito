package com.softserve.mosquito.controllers.social;//package com.softserve.mosquito.controllers;


import com.softserve.mosquito.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@Autowired
	FacebookProvider facebookProvider;

	@Autowired
	GoogleProvider googleProvider;

	@Autowired
	LinkedInProvider linkedInProvider;

	@RequestMapping(value = "/facebook", method = RequestMethod.GET)
	public String loginToFacebook(Model model) {
		return facebookProvider.getFacebookUserData(model, new User());	}

	@RequestMapping(value = "/google", method = RequestMethod.GET)
	public String loginToGoogle(Model model) {
		return googleProvider.getGoogleUserData(model, new User());
	}

	@RequestMapping(value = "/linkedin", method = RequestMethod.GET)
	public String helloFacebook(Model model) {
		return linkedInProvider.getLinkedInUserData(model, new User());
	}

	@RequestMapping(value = { "/","/login" })
	public String login() {
		return "login";
	}

}
