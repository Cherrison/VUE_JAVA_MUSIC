package ouc.cs.course.java.musicserver.dao;

import ouc.cs.course.java.musicserver.model.Range;

import java.sql.SQLException;
import java.util.List;

public interface RangeDao {
    public List<Range> getRange() throws SQLException;
}
