package com.nathan.socialMediaApp.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nathan.socialMediaApp.model.User;
import com.nathan.socialMediaApp.service.UserServiceImpl;


@WebServlet(urlPatterns = { "/user/delete" })
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserServiceImpl userServiceImpl = new UserServiceImpl();
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User userToDelete = userServiceImpl.getUserById(id);
		boolean deleted = userServiceImpl.deleteUser(userToDelete);
		if (deleted) {
			HttpSession session = request.getSession();
			session.removeAttribute("user");
			response.sendRedirect(request.getContextPath() + "/login");			
		} else {
//			TODO- validation
			response.sendRedirect(request.getContextPath());	
		}
	}
	
}
