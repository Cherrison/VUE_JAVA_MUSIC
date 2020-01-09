package ouc.cs.course.java.musicserver.dao;

import java.sql.SQLException;
import java.util.List;

import ouc.cs.course.java.musicserver.model.Music;
import ouc.cs.course.java.musicserver.model.MusicSheet;
import ouc.cs.course.java.musicserver.model.User;

public interface UserDao {

	public int insert(User user) throws SQLException;

	public User findById(int id) throws SQLException;
	
	public User findByName(String name) throws SQLException;
	
	public User findOne(String name, String passMd5Value) throws SQLException;

	public List<User> findAll() throws SQLException;
}
