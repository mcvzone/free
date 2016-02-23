package com.free.module.core.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnection {
    private InitialContext ic = null;
    private Context c = null;
    private Context ec = null;
    private DataSource ds = null;
//    ResultSet rs = null;
//    Statement stmt = null;
    protected Connection conn = null;

    public DBConnection() throws Exception {
        try {
            ic = new InitialContext();
            ec = (Context) ic.lookup("java:/comp/env");
            ds = (DataSource) ec.lookup("jdbc/CUBRIDDS");

            conn = ds.getConnection();
            //stmt = conn.createStatement();
            //String sql = "SELECT 1 name, 2 age, 3 old";
            //rs = stmt.executeQuery(sql);
        } catch (Exception e) {
//            out.println("<h3>연결 실패</h3>");
//            out.println(ne.getMessage());
            throw e;
        } finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
    }
}
