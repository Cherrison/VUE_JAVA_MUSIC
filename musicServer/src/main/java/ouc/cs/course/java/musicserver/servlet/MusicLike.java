package ouc.cs.course.java.musicserver.servlet;

import ouc.cs.course.java.musicserver.service.MusicService;
import ouc.cs.course.java.musicserver.util.ResponseRestFul;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/musicLike")
public class MusicLike  extends RestFulServlet {
    private MusicService musicService = new MusicService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ResponseRestFul rst = new ResponseRestFul(response);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        String md5value = request.getParameter("md5value");
        String username = (String) request.getSession().getAttribute("username");
        System.out.println("md5"+md5value+"user"+username);
        if(username == null )
            username = "default";
        try {
            musicService.likeByMd5value(md5value, username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        rst.res(200,"成功喜欢了这首歌","OK");
    }
}
