<%@ page import="java.util.*" %>
<%@ page import="javax.naming.*" %>
<%@ page errorPage="errorpg.jsp"
    import="java.sql.*, 
    javax.sql.*, 
    java.io.*, 
    javax.naming.InitialContext, 
    javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>회원 목록</title>
</head>
<body>


    MEMBER 테이블의 내용
    <table width="100%" border="1">
        <tr>
            <td>이름</td>
            <td>아이디</td>
            <td>이메일</td>
        </tr>
        <%
        InitialContext ic = null;
        Context c = null;
        Context ec  =  null;
        DataSource ds = null;
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
            try {
             ic = new InitialContext();
             ec  = (Context)ic.lookup("java:/comp/env");
             ds = (DataSource) ec.lookup("jdbc/CUBRIDDS");
             
             conn = ds.getConnection();
             stmt = conn.createStatement();
             String sql = "SELECT 1 name, 2 age, 3 old";
             rs = stmt.executeQuery(sql);
             while (rs.next()) {%>
             <tr>
                 <td><%=rs.getString("name")%></td>
                 <td><%=rs.getString("age")%></td>
                 <td><%=rs.getString("old")%></td>
             </tr><%
             }
             out.println("<h3>연결 성공</h3>");
            } catch(NamingException ne) {
                out.println("<h3>연결 실패</h3>");
                out.println(ne.getMessage());
             throw ne;
            } finally {

                if (rs != null)
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                    }

                if (stmt != null)
                    try {
                        stmt.close();
                    } catch (SQLException ex) {
                    }

                if (conn != null)
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                    }
            }
        %>
    </table>
</body>
</html>
