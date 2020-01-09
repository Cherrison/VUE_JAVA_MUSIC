package ouc.cs.course.java.musicserver.servlet;

import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet("/logout")
public class LogoutServlet extends RestFulServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("userId");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        Writer out = response.getWriter();
        JSONObject res = new JSONObject();

        response.setStatus(200);
        res.put("message", "退出成功");

        out.write(res.toString());
        out.flush();
    }
}
