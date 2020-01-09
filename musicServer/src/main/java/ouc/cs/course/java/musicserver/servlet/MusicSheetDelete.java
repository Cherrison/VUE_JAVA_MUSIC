package ouc.cs.course.java.musicserver.servlet;

import ouc.cs.course.java.musicserver.service.MusicSheetService;
import ouc.cs.course.java.musicserver.service.MusicSheetToMusicService;
import ouc.cs.course.java.musicserver.util.ResponseRestFul;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/delSheet")
public class MusicSheetDelete extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ResponseRestFul rst = new ResponseRestFul(response, true);
        int sheetId = Integer.parseInt(request.getParameter("sheetId"));
        int isSuccess = 0;
        MusicSheetService musicSheetService = new MusicSheetService();
        MusicSheetToMusicService musicSheetToMusicService = new MusicSheetToMusicService();
        try {
            musicSheetToMusicService.deleteBySheetId(sheetId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            isSuccess = musicSheetService.deleteById(sheetId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (isSuccess != 0)
            rst.res(200, "删除成功!");
        else {
            rst.res(503, "删除失败!", "服务器资源不存在!");
        }
    }
}