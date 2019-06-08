package com.music.musicstore.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.music.musicstore.dao.UserDAO;
import com.music.musicstore.pojo.User;

@Controller
public class UserController {

	@Autowired
	UserDAO userdao;

	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public String login() {
		return "home";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/home")
	public String home() {
		return "home";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/homepage")
	public String homepage(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		System.out.println("here");
		if (user == null) {
			return "redirect:./home";
		} else {
			if (user.getRole().equals(User.UserRole.ADMIN.getRole())) {
				return "admin-view";
			} else if (user.getRole().equals(User.UserRole.ARTIST.getRole())) {
				return "artist-view";
			} else {
				return "subscriber-view";
			}
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/home")
	public String loginandRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		System.out.println("inside user login");
		String email = (String) request.getAttribute("email");
		String password = (String) request.getAttribute("password");
		System.out.println(password);
		password = String.valueOf(password.hashCode());
		User user = userdao.login(email, password);
		if (user == null) {
			request.setAttribute("login_error", "Invalid Credentials");
			return "home";
		} else {
			if (user.getStatus() == 0) {
				request.setAttribute("login_error",
						"Please activate the account using the link sent to your email first!!");
				return "home";
			}
			session.setAttribute("user", user);
			if (user.getRole().equals(User.UserRole.ADMIN.getRole())) {
				return "admin-view";
			} else if (user.getRole().equals(User.UserRole.ARTIST.getRole())) {
				return "artist-view";
			} else {
				return "subscriber-view";
			}
		}
	}

	@RequestMapping(value = "validateemail.htm", method = RequestMethod.GET)
	public String validateEmail(HttpServletRequest request, ModelMap map) {

		System.out.println("Hererererererere");
		// The user will be sent the following link when the use registers
		// This is the format of the email
		// http://hostname:8080/musicstore/validateemail.htm?email=useremail&key1=<random_number>&key2=<body
		// of the email that when user registers>
		HttpSession session = request.getSession();
		String email = (String) request.getParameter("email");
		int key1 = Integer.parseInt((String) request.getParameter("key1"));
		int key2 = Integer.parseInt((String) request.getParameter("key2"));
		System.out.println(session.getAttribute("key1"));
		System.out.println(session.getAttribute("key2"));

		if ((Integer) (session.getAttribute("key1")) == key1 && ((Integer) session.getAttribute("key2")) == key2) {
			try {
				System.out.println("HI________");
				boolean updateStatus = userdao.updateUser(email);
				if (updateStatus) {
					return "home";
				} else {
					return "error";
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			map.addAttribute("errorMessage", "Link expired , generate new link");
			map.addAttribute("resendLink", true);
			return "error";
		}

		return "home";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutUser(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		return "home";
	}

}
