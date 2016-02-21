<html>
	<head>
		<%@ page errorPage="errorpg.jsp"
			import="java.sql.*, 
		javax.sql.*, 
		java.io.*"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf8">
	</head>
	<body>
		<h1>JDBC Test</h1>
	
		<% 
		
		// JDNI 를 사용하지 않는 DB 연결 예제를 위한 소스입니다.
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection conn = 
					DriverManager.getConnection("jdbc:mysql://localhost/eunjeongbae", "eunjeongbae" , "DB 비밀번호"); 
			
			out.println("<h3>연결 성공</h3>");
			
		} catch(Exception e){
			out.println("<h3>연결 실패</h3>");
			out.println(e.getMessage());
		}
		%>
	</body>
</html>


