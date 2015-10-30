<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<script type="text/javascript">
$(document).ready(function(){
});

function fLogin(){
	var f = document.mainForm;
	
	if( f.loginId.value == "" ){
		alert("input id.");
		return false;
	} else if( f.loginPw.value == "" ){
		alert("input pw.");
		return false;
	}
	
	document.mainForm.submit();
}
</script>
</head>
<body>
<form action="/free?mission=CM0000003" method="post" name="mainForm">
    <div>Login</div>
    <div>ID : <input type="text" name="loginId"></div>
    <div>PW : <input type="password" name="loginPw"></div>
</form>
    <div><button onclick="fLogin();">submit</button></div>
</body>
</html>