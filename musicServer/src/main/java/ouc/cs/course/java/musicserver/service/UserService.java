package ouc.cs.course.java.musicserver.service;

import ouc.cs.course.java.musicserver.dao.UserDao;
import ouc.cs.course.java.musicserver.dao.impl.UserDaoImpl;
import ouc.cs.course.java.musicserver.model.User;

import java.sql.SQLException;

public class UserService {
    UserDao userDao = new UserDaoImpl();

    public UserService(){}

    public int create(User user) throws SQLException{
        if(findByName(user.getName())!=null) {
            return 0;
        }else{
            return userDao.insert(user);
        }
    }

    public User getOne(String name, String passMd5Value) throws SQLException{
        return userDao.findOne(name, passMd5Value);
    }

    public User findByName(String name) throws SQLException {
        // TODO Auto-generated method stub
        return userDao.findByName(name);
    }
}