package com.music.musicstore.controllers;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.music.musicstore.dao.UserDAO;
import com.music.musicstore.pojo.User;
import com.music.musicstore.validator.UserValidator;

@Controller
@RequestMapping(value = "register")
public class RegisterUserController {
	@Autowired
	UserValidator uservalidator;

	@Autowired
	UserDAO userdao;

	// Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(uservalidator);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String formView(ModelMap model, User user) {
		model.addAttribute("user", user);
		return "register";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String successView(@Validated @ModelAttribute("user") User user, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return "register"; // the are validation errors, go to the form view
		}
		// no errors, so go to the success view
		if (userdao.getUser(user.getEmail()) != null) {
			request.setAttribute("error", "Email address already present!!");
			return "register";
		}
		HttpSession session = request.getSession();
		user.setPassword(String.valueOf(user.getPassword().hashCode()));
		user = userdao.create(user);
		Random rand = new Random();
		int randomNum1 = rand.nextInt(5000000);
		int randomNum2 = rand.nextInt(5000000);
		try {
			String str = "http://localhost:8080/musicstore/validateemail.htm?email=" + user.getEmail() + "&key1="
					+ randomNum1 + "&key2=" + randomNum2;
			session.setAttribute("key1", randomNum1);
			session.setAttribute("key2", randomNum2);
			sendEmail(user.getEmail(), "Click on this link to activate your account : " + str);
		} catch (Exception e) {
			System.out.println("Email cannot be sent");
		}

		request.setAttribute("registration_success",
				"Registration Successful!! Please check your email for activation link!!");
		return "home";
	}

	public void sendEmail(String useremail, String message) {
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("wt.musicstore@gmail.com", "wt-mstr@123"));
			email.setSSLOnConnect(true);
			email.setFrom("no-reply@msis.neu.edu"); // This user email does not
													// exist
			email.setSubject("Password Reminder");
			email.setMsg(message); // Retrieve email from the DAO and send this
			email.addTo(useremail);
			email.send();
		} catch (EmailException e) {
			System.out.println(e);
		}
	}

	@RequestMapping(value = "resendemail.htm", method = RequestMethod.POST)
	public String resendEmail(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String useremail = (String) request.getAttribute("username");
		Random rand = new Random();
		int randomNum1 = rand.nextInt(5000000);
		int randomNum2 = rand.nextInt(5000000);
		try {
			String str = "http://localhost:8080/musicstore/validateemail.htm?email=" + useremail + "&key1=" + randomNum1
					+ "&key2=" + randomNum2;
			session.setAttribute("key1", randomNum1);
			session.setAttribute("key2", randomNum2);
			sendEmail(useremail, "Click on this link to activate your account : " + str);
		} catch (Exception e) {
			System.out.println("Email cannot be sent");
		}

		return "user-created";
	}

}
