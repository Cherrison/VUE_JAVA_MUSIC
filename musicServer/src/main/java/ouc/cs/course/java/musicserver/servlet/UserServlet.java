package ouc.cs.course.java.musicserver.servlet;

import ouc.cs.course.java.musicserver.util.ResponseRestFul;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test")
public class UserServlet extends RestFulServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ResponseRestFul rst = new ResponseRestFul(response);

		rst.res(200, "update", "服务器维护中");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
