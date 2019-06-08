package com.music.musicstore.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class SanitizeFilter
 */
public class SanitizeFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public SanitizeFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		try {
			System.out.println("here in filter");

			String email = request.getParameter("email");
			if (email != null) {
				email = email.replaceAll("[^\\dA-Za-z0-9@.-_]", "");
				System.out.println(email);
				request.setAttribute("email", email);
			}

			String password = request.getParameter("password");
			System.out.println(password);
			if (password != null) {
				password = password.replaceAll("[^\\dA-Za-z0-9@.-_]", "");
				System.out.println(password);
				request.setAttribute("password", password);
			}

		} catch (Exception e) {
			System.out.println(e);
			request.setAttribute("errormessage", e);
			// request.getRequestDispatcher("error.jsp").forward(request,
			// response);
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
