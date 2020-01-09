package ouc.cs.course.java.musicserver.servlet;

import ouc.cs.course.java.musicserver.service.MusicService;
import ouc.cs.course.java.musicserver.util.ResponseRestFul;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/musicPlay")
public class MusicPlay  extends RestFulServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ResponseRestFul rst = new ResponseRestFul(response);
        MusicService musicService = new MusicService();
        String md5value = request.getParameter("md5value");
        String name = request.getParameter("name");
        if(name!=null){
            try {
                musicService.playByNameAndMd5value("online:" + name,md5value);
                System.out.println("Play Online");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            try {
                musicService.playByMd5value(md5value);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        rst.res(200,"播放成功","OK");
    }
}