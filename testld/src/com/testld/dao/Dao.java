package com.testld.dao;


import com.testld.model.Fld;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Dao {

    Logger log = Logger.getLogger(Dao.class);

    public Connection getConn() {
        Connection conn = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx
                    .lookup("java:comp/env/jdbc/mysql");
            conn = ds.getConnection();

        } catch (Exception e) {
            log.fatal(e.getMessage());
        }
        return conn;
    }

    public boolean isExist(String sql) {
        try (Connection conn = getConn()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs.next();
        } catch (SQLException e) {
            log.error(e.getMessage());
            return false;
        }

    }

    public boolean isExist(String sql, Object[] args) {
        try (Connection conn = getConn()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);
            }
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            log.error(e.getMessage());
            return false;
        }

    }

    public boolean update(String sql) {

        try (Connection conn = getConn()) {
            Statement stmt = conn.createStatement();
            int cnt = stmt.executeUpdate(sql);
            return cnt > 0;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public boolean update(String sql, Object[] args) {

        try (Connection conn = getConn()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);
            }
            int cnt = pstmt.executeUpdate();
            return cnt > 0;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public List<Fld> getFld(String sql) {
        List<Fld> list = new ArrayList<Fld>();
        try (Connection conn = getConn()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Fld fld = new Fld();
                fld.setFlda(rs.getInt("flda"));
                fld.setFldb(rs.getString("fldb"));
                fld.setFldc(rs.getFloat("fldc"));
                fld.setFldd(rs.getString("fldd"));
                list.add(fld);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return list;
    }


}
