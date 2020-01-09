package ouc.cs.course.java.musicserver.util;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class ResponseRestFul {
    private HttpServletResponse response;

    public ResponseRestFul(HttpServletResponse response) {
        this.response = response;
    }

    /**
     * 增加是否允许跨域选项
     * @param response
     * @param isAllowAccess
     */
    public ResponseRestFul(HttpServletResponse response, Boolean isAllowAccess) {
        this.response = response;
        if (isAllowAccess) {
            this.response.setHeader("Access-Control-Allow-Origin", "*");
            this.response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        }
    }

    /**
     * 返回状态码 状态信息
     *
     * @param code
     * @param message
     * @throws IOException
     */
    public void res(int code, String message) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        Writer out = response.getWriter();
        JSONObject res = new JSONObject();

        response.setStatus(code);
        res.put("message", message);

        out.write(res.toString());
        out.flush();

        Object ob = new Object();
    }

    /**
     * 返回状态码 状态信息 报错信息
     *
     * @param code
     * @param message
     * @param errMsg
     * @throws IOException
     */
    public void res(int code, String message, String errMsg) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        Writer out = response.getWriter();
        JSONObject res = new JSONObject();

        response.setStatus(code);

        res.put("message", message);
        res.put("errMsg", errMsg);

        if (code == 302) {
            response.setHeader("Location", "https://www.baidu.com");
        }

        out.write(res.toString());
        out.flush();
    }

    /**
     * 返回状态码 状态信息 报错信息uuid
     *
     * @param code
     * @param message
     * @param errMsg
     * @param uuid
     * @throws IOException
     */
    public void res(int code, String message, String errMsg, String uuid) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        Writer out = response.getWriter();
        JSONObject res = new JSONObject();

        response.setStatus(code);

        res.put("message", message);
        res.put("errMsg", errMsg);
        res.put("uuid", uuid);
        out.write(res.toString());
        out.flush();
    }
}
