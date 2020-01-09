package ouc.cs.course.java.musicserver.service;

import ouc.cs.course.java.musicserver.dao.RangeDao;
import ouc.cs.course.java.musicserver.dao.impl.RangeDaoImpl;
import ouc.cs.course.java.musicserver.model.Range;

import java.sql.SQLException;
import java.util.List;

public class RangeService {

    private RangeDao rangeDao = new RangeDaoImpl();

    public List<Range> getRange() throws SQLException {
        return rangeDao.getRange();
    }
}
