package com.suji.cram.model;


import com.suji.cram.db.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDao {
    
    public static void main(String[] args) throws SQLException {
        for (String allGroup : GroupDao.getAllGroups()) {
            System.out.println(allGroup);
        }
    }

    public static boolean addGroup(String group) throws SQLException {
        boolean isSaved = false;

        String sqlQuery = "insert into cram.groups values(?)";
        Connection con = DBUtil.getConn();
        PreparedStatement ps = con.prepareStatement(sqlQuery);
        ps.setString(1, group);

        isSaved = ps.executeUpdate() > 0;

        return isSaved;
    }

    public static String[] getAllGroups() throws SQLException {
        String sqlQuery = "select * from cram.groups order by gname";
        List<String> list = new ArrayList<String>();

        Connection con = DBUtil.getConn();
        PreparedStatement ps = con.prepareStatement(sqlQuery);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            list.add(rs.getString(1));
        }

        return list.toArray(new String[0]);

    }

}
