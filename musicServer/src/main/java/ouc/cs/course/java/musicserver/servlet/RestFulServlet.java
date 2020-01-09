package ouc.cs.course.java.musicserver.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestFulServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置请求状态码
        request.setCharacterEncoding("UTF-8");
        // 设置响应状态码
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置请求状态码
        request.setCharacterEncoding("UTF-8");
        // 设置响应状态码
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
    }
}
