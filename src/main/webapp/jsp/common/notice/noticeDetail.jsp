<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.free.module.core.config.FreeReservedWordConfig" %>
<%@ page import="com.free.module.core.config.FreePathConfig" %>
<%@ page import="com.free.module.common.model.vo.NoticeVo" %>
<%@ page import="java.util.*"%>
<%

Map<String, Object> mResult = (Map<String, Object>)request.getAttribute(FreeReservedWordConfig.MISSION_RESULT);
List<NoticeVo> notices = null;
NoticeVo noticeDesc = null;
if(mResult != null ){
    notices = (List<NoticeVo>)mResult.get("list");
    noticeDesc = (NoticeVo)mResult.get("desc");
}

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

StringBuffer sbData = null;
if( notices != null ){
    sbData = new StringBuffer();
    NoticeVo notice = null;
    for( int i=0; i<notices.size() && i < 3; i++ ){
    	notice = notices.get(i);
        sbData.append("{seq:"+notice.getSeq()+
                   ", title:'"+notice.getTitle()+
                   "', writer:'"+notice.getWriter()+
                   "', reg_date:'"+notice.getReg_date()+"'},");
        
    }
}

if( noticeDesc != null ){
	sSeq = noticeDesc.getSeq();
	sTitle = noticeDesc.getTitle();
	sStartDate = noticeDesc.getStart_date();
	sEndDate = noticeDesc.getEnd_date();
	sWriter = noticeDesc.getWriter();
	sDescription = noticeDesc.getDescription();
	sRedCount = noticeDesc.getRed_count();
	sUseYn = noticeDesc.getUse_yn();
	attachs = noticeDesc.getAttachs();
}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항-상세</title>
<script type="text/javascript" src="/resources/common/js/common.util.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var columns = [{key:"seq", label:"번호", width:"100", align:"center", formatter:"money"},
                    {key:"title", label:"제목", width:"500"},
                    {key:"writer", label:"작성자", width:"100", align:"center"},
                    {key:"reg_date", label:"작성일", width:"*", align:"center", formatter:"date"}];
	var datas = [<%=sbData.toString().substring(0, sbData.toString().length()-1)%>];
	
	grid(columns, datas, true);
});
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
<div id="id-grid"></div>
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