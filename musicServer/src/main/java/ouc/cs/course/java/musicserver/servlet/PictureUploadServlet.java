package ouc.cs.course.java.musicserver.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import ouc.cs.course.java.musicserver.util.ResponseRestFul;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ouc.cs.course.java.musicserver.util.Md5.MD5;

@WebServlet("/uploadPicture")
public class PictureUploadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ResponseRestFul rst = new ResponseRestFul(response);
        String uuid = ""; // 根据图片名称的md5生产uuid
        String realPath = getServletContext().getInitParameter("pictureFilePath");
        Map<String, String> fieldNames = new HashMap<String, String>();
        //  获取请求参数中的每一项, 存放在list中
        DiskFileItemFactory fc = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(fc);
        try {
            // 进行解析, 将每对分割线中的内容封装在FileItem 对象中
            List<FileItem> list = upload.parseRequest(request);
            // 进行遍历 判断是普通项还是上传项
            for(FileItem fileItem : list){
                // 如果是普通项存放在map中
                if(fileItem.isFormField()){
                    System.out.println("普通项:  " + fileItem.getFieldName() + " " + fileItem.getString());
                    fieldNames.put(fileItem.getFieldName(), fileItem.getString());
                }else{
                    // 获取输入流
                    InputStream is = fileItem.getInputStream();
                    // 上传项, 新建一个同名文件
                    String filename = fileItem.getName();
                    String extname = filename.substring(filename.lastIndexOf(".") + 1);
                    uuid = MD5(filename);

                    File file = new File(realPath, uuid +"."+ extname);

                    OutputStream os = new FileOutputStream(file);
                    IOUtils.copy(is, os);
                    IOUtils.closeQuietly(is);
                    IOUtils.closeQuietly(os);
                    System.out.println("文件:  " + fileItem.getFieldName() + " " + fileItem.getName());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        rst.res(200, "上传成功!", "OK", uuid);
    }
}
