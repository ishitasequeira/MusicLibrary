package com.music.musicstore.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.music.musicstore.pojo.User;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String uri = request.getRequestURI();
		User user = (User) request.getSession().getAttribute("user");

		System.out.println(uri);

		if (uri.endsWith("login") || uri.endsWith("register") || uri.endsWith(".htm")) {
			if (user == null) {
				return true;
			} else {
				response.sendRedirect("./homepage");
				System.out.println("here1");
				return false;
			}
		} else {
			if (uri.endsWith("home")) {
				return true;
			} else {
				if (user == null) {
					response.sendRedirect("./login");
					return false;
				} else {
					System.out.println("here3");
					// response.sendRedirect("./homepage");
					if (uri.endsWith("musicstore/")) {
						response.sendRedirect("./login");
						return false;
					}
					return true;
				}
			}
		}

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
