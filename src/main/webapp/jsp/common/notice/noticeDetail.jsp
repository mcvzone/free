<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.free.module.core.config.FreeReservedWordConfig" %>
<%@ page import="com.free.module.core.config.FreePathConfig" %>
<%@ page import="com.free.module.common.model.vo.NoticeVo" %>
<%@ page import="java.util.*"%>
<%

NoticeVo notice = (NoticeVo)request.getAttribute(FreeReservedWordConfig.MISSION_RESULT_KEY);

String sSeq = "";
String sTitle = "";
String sStartDate = "";
String sEndDate = "";
String sWriter = "";
String sRegDate = "";
String sDescription = "";
String sRedCount = "";
String sUseYn = "";
List<String> attachs = null;

if( notice != null ){
	sSeq = notice.getSeq();
	sTitle = notice.getTitle();
	sStartDate = notice.getStart_date();
	sEndDate = notice.getEnd_date();
	sWriter = notice.getWriter();
	sDescription = notice.getDescription();
	sRedCount = notice.getRed_count();
	sUseYn = notice.getUse_yn();
    System.out.println("notice.getAttachs() : " + notice.getAttachs());
	attachs = notice.getAttachs();
}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항-상세</title>
<script type="text/javascript">
/**
 * Require Files for AXISJ UI Component...
 * Based        : jQuery
 * Javascript   : AXJ.js, AXGrid.js, AXInput.js, AXSelect.js
 * CSS          : AXJ.css, AXGrid.css, AXButton.css, AXInput.css, AXSelecto.css
 */
 var pageID = "ajax";

 var myGrid = new AXGrid();
 var itemSum = 0;
 var fnObj = {
     pageStart: function(){
         myGrid.setConfig({
             targetID : "id-top-grid",
             theme : "AXGrid",
             //viewMode: "grid", // ["grid","icon","mobile"]
             // 브라우저 사이즈에 따른 changeGridView 설정
             mediaQuery: {
                 mx:{min:0, max:600}, dx:{min:600}
             },
             colGroup : [
                 {key:"no", label:"번호", width:"40", align:"center", formatter:"money"},
                 {key:"title", label:"제목", width:"200"},
                 {key:"writer", label:"작성자", width:"90", align:"center"},
                 {key:"regDate", label:"작성일", width:"90", align:"center"},
                 {key:"desc", label:"비고", width:"*"}
             ],

             body : {
                 onclick: function(){
                     toast.push(Object.toJSON({index:this.index, item:this.item}));
                     //alert(this.list);
                     //alert(this.page);
                 }
             },
             page:{
                 paging:true,
                 pageNo:1,
                 pageSize:100,
                 status:{formatter: null}
             }
         });

         myGrid.setList({
             ajaxUrl:"loadGrid.php", ajaxPars:"param1=1&param2=2", onLoad:function(){
                 //trace(this);
             }
         });

     }
 };

 jQuery(document.body).ready(function(){fnObj.pageStart()});

</script>
<style type="text/css">
.cls-read-table {
    width: 100%;
}
.cls-read-table th{
    background-color: rgba(233, 244, 252, 1);
    text-align: left;
} 
.cls-read-table td{
    text-align: left;
}
</style>
</head>
<body>
<form action="/free?mission=CM0000006" method="post" name="mainForm">
    <input type="hidden" name="seq">
</form>
<div id="id-top-grid"></div>
<div style="height:10px;"></div>
<div id="id-read-header" style="width:100%;">
    <table class="cls-read-table">
        <tr>
            <th>No</th><td><%=sSeq%></td>
            <th>공지기간</th><td><%=sStartDate%> ~ <%=sEndDate%></td>
            <th>작성자</th><td><%=sWriter%></td>
            <th>조회수</th><td><%=sRedCount%></td>
        </tr>
        <tr>
            <th>제목</th><td colspan="7"><%=sTitle%></td>
        </tr>
    </table>
</div>
<div id="id-read-body">
    <table class="cls-read-table">
        <tr>
            <td height="300px"><%=sDescription%></td>
        </tr>
    </table>
    <table class="cls-read-table">
        <tr>
            <th width="100px">첨부파일</th>
            <td width="*">
            <%if( attachs!=null&&attachs.size()>0 ){%>
            <%  for( String attach : attachs ){%>
                <a href="/download?filename=<%=attach%>"><%=attach%></a><br/>
            <%  }%>
            <%}%>
            </td>
        </tr>
    </table>
</div>
</body>
</html>