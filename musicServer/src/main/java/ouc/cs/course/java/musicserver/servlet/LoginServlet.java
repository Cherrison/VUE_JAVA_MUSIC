package ouc.cs.course.java.musicserver.servlet;

import net.sf.json.JSONObject;
import ouc.cs.course.java.musicserver.model.User;
import ouc.cs.course.java.musicserver.service.UserService;
import ouc.cs.course.java.musicserver.util.json.JsonReader;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 设置请求状态码
		request.setCharacterEncoding("UTF-8");
		// 设置响应状态码
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST");

		Writer out = response.getWriter();
		JSONObject resJson = new JSONObject();

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		if (username != null) {
			// 登录成功返回200状态码表示登录成功
			response.setStatus(200);
			resJson.put("message", username + "已登录");
			out.write(resJson.toString());
			System.out.println("用户: "+ username + "已登录");
//			response.sendRedirect("index/index.html");
			out.flush();
		} else {
			// 如果未登录就返回401 状态码表示没有进行授权
			response.setStatus(401);
			resJson.put("message", "未登录");
			out.write(resJson.toString());
			out.flush();
//			response.sendRedirect("user/login.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService userService = new UserService();
		User user = null;
		// 设置请求状态码
		request.setCharacterEncoding("UTF-8");
		// 设置响应状态码
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");

		Writer out = response.getWriter();
		JSONObject resJson = new JSONObject();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
    System.out.println("Finding username"+ username + " password" + password);
		try {
			user = userService.getOne(username, password);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Find username"+ username + " password" + password);
		if (user!=null) {
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("userId", user.getId());

			response.setStatus(200);
			resJson.put("message", "登录成功");
			out.write(resJson.toString());
//			response.sendRedirect("index/index.html");
			out.flush();
		} else {
			response.setStatus(422);
			resJson.put("message", "用户名或密码错误");
			out.write(resJson.toString());
			out.flush();
		}
	}

}
