<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.free.module.core.config.FreeReservedWordConfig" %>
<%@ page import="com.free.module.common.model.vo.NoticeVo" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항</title>
<link rel="stylesheet" type="text/css" href="/resources/axisj-1.0.19/ui/arongi/AXJ.min.css"/>
<script type="text/javascript" src="/resources/axisj-1.0.19/jquery/jquery.min.js"></script>
<script type="text/javascript" src="/resources/axisj-1.0.19/dist/AXJ.min.js"></script>
<script type="text/javascript" src="/resources/common/js/common.util.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    var columns = [{key:"seq", label:"번호", width:"100", align:"center", formatter:"money"},
                    {key:"title", label:"제목", width:"500"},
                    {key:"writer", label:"작성자", width:"100", align:"center"},
                    {key:"reg_date", label:"작성일", width:"*", align:"center", formatter:"date"}];
    
    gridAjax({
    	cols: columns,
    	paging: true,
    	event: function(){
    		document.mainForm.seq.value = this.item.seq;
            document.mainForm.submit();
    	},
    	ajaxInfo: {
                ajaxUrl : "/ajax",
                dataType: "json",
                ajaxPars: {
                    "mission":"CM0000007"
                },
                onLoad  : function(){
                    
                }
            }
    });
    
});
</script>
</head>
<body>
<form action="/free?mission=CM0000006" method="post" name="mainForm">
    <input type="hidden" name="seq">
</form>
<div id="id-grid"></div>
</body>
</html>