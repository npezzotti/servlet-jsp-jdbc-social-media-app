package com.nathan.userManagement.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nathan.userManagement.beans.Post;
import com.nathan.userManagement.service.PostServiceImpl;

@WebServlet(urlPatterns = { "/" })
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PostServiceImpl postServiceImpl = new PostServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Post> posts = postServiceImpl.getAllPosts();
		request.setAttribute("posts", posts);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/posts.jsp");
		dispatcher.forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
