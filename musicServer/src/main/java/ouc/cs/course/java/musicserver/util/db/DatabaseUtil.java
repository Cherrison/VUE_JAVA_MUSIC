package ouc.cs.course.java.musicserver.util.db;

import javax.net.ssl.SSLException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 数据库操作工具类
 */
public class DatabaseUtil {

//	public static String URL;
//	private static String USERNAME;
//	private static String PASSWORD;
//  private static ResourceBundle rb = ResourceBundle.getBundle("ouc.cs.course.java.musicserver.util.db.db-config");
//
//	private DatabaseUtil() {
//	}
//
//	static {
//		URL = rb.getString("jdbc.url");
//		USERNAME = rb.getString("jdbc.username");
//		PASSWORD = rb.getString("jdbc.password");
//    String DRIVER = rb.getString("jdbc.driver");
//
//		try {
//			Class.forName(DRIVER);
//		} catch (ClassNotFoundException ignored) {
//
//		}
//	}

//	public static Connection getConnection() {
//		Connection conn = null;
//		try {
//			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//		} catch (SQLException e) {
//
//			System.out.println("Get Database Connection failed.");
//		}
//		return conn;
//	}
//  com.mysql.cj.jdbc.Driver
	public static void close(ResultSet rs, Statement stat, Connection conn) {

		try {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

  public static Connection getConnection() {
    Connection con = null;
    Properties props = new Properties();
    FileInputStream in = null;
    /*
     * 采用系统属性获取当前用户HOME目录
     */
    try {
      Class.forName("com.mysql.jdbc.Driver");
      in = new FileInputStream(System.getProperty("user.dir") + File.separator
        + "db.conf");
      props.load(in);
    } catch (IOException | ClassNotFoundException ex) {
      Logger lgr = Logger.getLogger(DatabaseUtil.class.getName());
      lgr.log(Level.SEVERE, ex.getMessage(), ex);
    } finally {
      try {
        if (in != null) {
          in.close();
        }
      } catch (IOException ex) {
        Logger lgr = Logger.getLogger(DatabaseUtil.class.getName());
        lgr.log(Level.SEVERE, ex.getMessage(), ex);
      }
    }
    String url = "jdbc:mysql://127.0.0.1:3306/musicserver?useUnicode=true&characterEncoding=utf-8";
    String user = "oucmusic";
    String passwd = "oucmusic";
    try {
      con = DriverManager.getConnection(url, user, passwd);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return con;
  }
}
