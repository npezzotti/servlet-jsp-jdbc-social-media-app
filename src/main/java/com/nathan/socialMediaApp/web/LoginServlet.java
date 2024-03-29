package com.nathan.socialMediaApp.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.nathan.socialMediaApp.model.User;
import com.nathan.socialMediaApp.service.UserServiceImpl;
import com.nathan.socialMediaApp.util.AuthUtils;
import com.nathan.socialMediaApp.util.HibernateUtil;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserServiceImpl userServiceImpl = new UserServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Session session = HibernateUtil.getSession();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = userServiceImpl.getUserByEmail(email, session);

		if (user != null && AuthUtils.authenticate(user, password)) {

			HttpSession oldSession = request.getSession(false);
			if (oldSession != null) {
				oldSession.invalidate();
			}

			HttpSession newSession = request.getSession(true);
			newSession.setMaxInactiveInterval(10 * 60);
			newSession.setAttribute("user", user);
			response.sendRedirect(request.getContextPath() + "/posts");
		} else {
			String error = "Email or password invalid.";
			request.setAttribute("error", error);
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp");
			dispatcher.forward(request, response);
		}
		
		session.close();

	}

}
