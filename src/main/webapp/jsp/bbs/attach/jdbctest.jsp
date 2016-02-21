<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="javax.naming.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <%@ page errorPage="errorpg.jsp"
            import="java.sql.*, 
        javax.sql.*, 
        java.io.*, 
        javax.naming.InitialContext, 
        javax.naming.Context"%>
        <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    </head>
    <body>
        <h1>JDBC JNDI Resource Test</h1>
    <%
    String strDataSource = config.getInitParameter("jdbc/CUBRIDDS");   //jdbc/ds_name
    System.out.println("strDataSource : " + strDataSource);
       InitialContext ic = null;
       DataSource ds = null;
       Context ec  =  null;
       try {
        ic = new InitialContext();
        ec  = (Context)ic.lookup("java:/comp/env/jdbc/CUBRIDDS");
        out.println("<h3>연결 성공</h3>");
       } catch(NamingException ne) {
           out.println("<h3>연결 실패</h3>");
           out.println(ne.getMessage());
        throw ne;
       }
       
       /*
       try {
        ds = (DataSource)ec.lookup(strDataSource);   ////strDataSource="jdbc/ds_name"
       } catch(NamingException ne) {
        throw ne;
       } catch(ClassCastException cce) {
        throw cce;
       }*/
       /*
        try {
            InitialContext initCtx = new InitialContext();
            ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/"); 
            Connection conn = ds.getConnection(); 
            
            out.println("<h3>연결 성공</h3>");
            
        } catch(Exception e){
            out.println("<h3>연결 실패</h3>");
            out.println(e.getMessage());
        }*/
        %>
    </body>
</html>