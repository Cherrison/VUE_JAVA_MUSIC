package ouc.cs.course.java.musicserver.servlet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import ouc.cs.course.java.musicserver.model.Range;
import ouc.cs.course.java.musicserver.service.RangeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/getRange")
public class GetRangeServlet extends RestFulServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        Writer out = response.getWriter();

        RangeService rangeService = new RangeService();
        List<Range> rangelist = new ArrayList<Range>();

        JSONObject res = new JSONObject();
        JSONArray songArr = new JSONArray();
        out.flush();
        try {
            rangelist = rangeService.getRange();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(Range range : rangelist){
            JSONObject song = null;
            song = new JSONObject();
            song.put("name", range.getName());
            song.put("times", range.getTimes());

            songArr.add(song.toString());
            System.out.println("Name: "+range.getName()+" times: "+range.getTimes());
        }

        response.setStatus(200);

        res.put("message", "获取成功!");
        res.put("rangeList", songArr.toString());

        // 返回排名查询列表
        out.write(res.toString());
        out.flush();
    }
}