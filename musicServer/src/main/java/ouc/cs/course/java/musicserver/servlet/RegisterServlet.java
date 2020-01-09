package ouc.cs.course.java.musicserver.servlet;

import net.sf.json.JSONObject;
import ouc.cs.course.java.musicserver.model.User;
import ouc.cs.course.java.musicserver.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends RestFulServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = new UserService();

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        Writer out = response.getWriter();
        JSONObject resJson = new JSONObject();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User(username, password);
        try {
            int userId = userService.create(user);
           if(userId==0){
                response.setStatus(422);
                resJson.put("message", "用户已存在");
                out.write(resJson.toString());
                out.flush();
            }else if(userId!=-1){
               response.setStatus(200);
               resJson.put("message", "注册成功");
               request.getSession().setAttribute("username", username);
               resJson.put("userId", userId);
               out.write(resJson.toString());
               out.flush();
           }else{
                response.setStatus(422);
                resJson.put("message", "注册失败");
                out.write(resJson.toString());
                out.flush();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
