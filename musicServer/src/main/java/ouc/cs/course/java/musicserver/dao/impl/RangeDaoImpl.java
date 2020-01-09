package ouc.cs.course.java.musicserver.dao.impl;

import ouc.cs.course.java.musicserver.dao.RangeDao;
import ouc.cs.course.java.musicserver.model.Music;
import ouc.cs.course.java.musicserver.model.Range;
import ouc.cs.course.java.musicserver.util.db.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class RangeDaoImpl implements RangeDao {
    @Override
    public List<Range> getRange() throws SQLException {
        List<Range> rangelist = new ArrayList<Range>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Range range = null;
        String sql = "select distinct name, times from music order by times desc limit 10 ";
        try {
            conn = DatabaseUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                range = new Range(rs.getString(1), rs.getInt(2));
                rangelist.add(range);
            }
        } catch (SQLException e) {
            throw new SQLException("query all data failed.");
        } finally {
            DatabaseUtil.close(rs, ps, conn);
        }
        return  rangelist;
    }
}
