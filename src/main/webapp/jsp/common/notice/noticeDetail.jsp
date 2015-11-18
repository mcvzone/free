<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.free.module.core.config.FreeReservedWordConfig" %>
<%@ page import="com.free.module.common.model.vo.NoticeVo" %>
<%@ page import="java.util.*"%>
<%

NoticeVo noticeVo = (NoticeVo)request.getAttribute(FreeReservedWordConfig.MISSION_RESULT);

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

if( noticeVo != null ){
	sSeq = noticeVo.getSeq();
	sTitle = noticeVo.getTitle();
	sStartDate = noticeVo.getStart_date();
	sEndDate = noticeVo.getEnd_date();
	sWriter = noticeVo.getWriter();
	sDescription = noticeVo.getDescription();
	sRedCount = noticeVo.getRed_count();
	sUseYn = noticeVo.getUse_yn();
	attachs = noticeVo.getAttachs();
}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항-상세</title>
<link href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<script type="text/javascript" src="/resources/common/js/common.util.js"></script>
<script type="text/javascript">
var myUpload = new AXUpload5();

var fnObj = {
    pageStart: function(){
        fnObj.upload.init();
    },
    upload: {
        init: function(){
            myUpload.setConfig({
                targetID:"AXUpload5",
                targetButtonClass:"Green",
                uploadFileName:"fileData",
                file_types:"*/*",  //audio/*|video/*|image/*|MIME_type (accept)
                dropBoxID:"uploadQueueBox",
                queueBoxID:"uploadQueueBox", // upload queue targetID
                //queueBoxAppendType:"(prepend || append)",
                // html 5를 지원하지 않는 브라우저를 위한 swf upload 설정 원치 않는 경우엔 선언 하지 않아도 됩니다. ------- s
                flash_url : pageObj.host+"lib/swfupload.swf",
                flash9_url : pageObj.host+"lib/swfupload_fp9.swf",
                flash_file_types:"*.*",
                flash_file_types_description:"all",

                // --------- e
                onClickUploadedItem: function(){ // 업로드된 목록을 클릭했을 때.
                    //trace(this);
                    window.open(this.uploadedPath.dec() + this.saveName.dec(), "_blank", "width=500,height=500");
                },

                uploadMaxFileSize:(10*1024*1024), // 업로드될 개별 파일 사이즈 (클라이언트에서 제한하는 사이즈 이지 서버에서 설정되는 값이 아닙니다.)
                uploadMaxFileCount:10, // 업로드될 파일갯수 제한 0 은 무제한
                uploadUrl:"fileUpload.php",
                uploadPars:{userID:'tom', userName:'액시스'},
                deleteUrl:"fileDelete.php",
                deletePars:{userID:'tom', userName:'액시스'},

                buttonTxt:"파일올리기",

                fileKeys:{ // 서버에서 리턴하는 json key 정의 (id는 예약어 사용할 수 없음)
                    name:"name",
                    type:"type",
                    saveName:"saveName",
                    fileSize:"fileSize",
                    uploadedPath:"uploadedPath",
                    thumbPath:"thumbUrl" // 서버에서 키값을 다르게 설정 할 수 있다는 것을 확인 하기 위해 이름을 다르게 처리한 예제 입니다.
                },

                onbeforeFileSelect: function(){
                    trace(this);
                    return true;
                },

                onUpload: function(){
                    //trace(this);
                    trace(myUpload.uploadedList);
                    //trace("onUpload");
                },
                onComplete: function(){
                    //trace(this);
                    //trace("onComplete");
                    $("#uploadCancelBtn").get(0).disabled = true; // 전송중지 버튼 제어
                },
                onStart: function(){
                    //trace(this);
                    //trace("onStart");
                    $("#uploadCancelBtn").get(0).disabled = false; // 전송중지 버튼 제어
                },
                onDelete: function(){
                    trace(this);
                    //trace("onDelete");
                },
                onError: function(errorType, extData){
                    if(errorType == "html5Support"){
                        //dialog.push('The File APIs are not fully supported in this browser.');
                    }else if(errorType == "fileSize"){
                        //trace(extData);
                        alert("파일사이즈가 초과된 파일을 업로드 할 수 없습니다. 업로드 목록에서 제외 합니다.\n("+extData.name+" : "+extData.size.byte()+")");
                    }else if(errorType == "fileCount"){
                        alert("업로드 갯수 초과 초과된 아이템은 업로드 되지 않습니다.");
                    }
                }
            });
            // changeConfig

            // 서버에 저장된 파일 목록을 불러와 업로드된 목록에 추가 합니다. ----------------------------- s
            var url = "fileListLoad.php";
            var pars = "dummy="+axf.timekey();
            new AXReq(url, {pars:pars, onsucc:function(res){
                if(!res.error){
                    myUpload.setUploadedList(res);
                }else{
                    alert(res.msg.dec());
                }
            }});
            // 서버에 저장된 파일 목록을 불러와 업로드된 목록에 추가 합니다. ----------------------------- e

        },
        printMethodReturn: function(method, type){
            var list = myUpload[method](type);
            trace(list);
            toast.push(Object.toJSON(list));
        },
        changeOption: function(){
            // 업로드 갯수 등 업로드 관련 옵션을 동적으로 변경 할 수 있습니다. 
            myUpload.changeConfig({
                /*
                uploadUrl:"uploadFile.asp",
                uploadPars:{userID:'tom', userName:'액시스'},
                deleteUrl:"deleteFile.asp",
                deletePars:{userID:'tom', userName:'액시스'},
                */
                uploadMaxFileCount:10
            }); 
            
        }
    }       
};


$(document).ready(function(){
	
	var form = document.mainForm;
	
	var columns = [{key:"seq", label:"번호", width:"100", align:"center", formatter:"money"},
                   {key:"title", label:"제목", width:"500"},
                   {key:"writer", label:"작성자", width:"100", align:"center"},
                   {key:"reg_date", label:"작성일", width:"*", align:"center", formatter:"date"}];
   
   gridAjax({
       cols: columns,
       paging: true,
       event: function(){
    	   form.action = "/free?mission=CM0000006";
           form.seq.value = this.item.seq;
           form.submit();
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
   
   $("#AXInputDateED").bindTwinDate({
       startTargetID: "AXInputDateST",
       handleLeft: 25,
       align: "right",
       valign: "bottom",
       separator: "/",
       buttonText: "선택",
       onChange: function () {
           //toast.push(Object.toJSON(this));
       },
       onBeforeShowDay: function (date) {
           if (date.getDay() == 3) {
               return { isEnable: false, title: "수요일 선택불가", className: "", style: "" };
           }
       }
   });
   
   $("button").click(function(){
	   if( "List" == this.innerText ){
		   form.action = "/free?mission=CM0000005";
	   } else if("Update"){
		   form.action = "/free?mission=CM0000005";
	   }
       form.submit();
   });
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
<form method="post" name="mainForm">
    <input type="hidden" name="seq">
    
    <div id="id-grid"></div>
    <div style="height:10px;"></div>
    <div id="id-read-header" style="width:100%;">
        <button type="button" class="AXButtonLarge Classic"><i class="fa fa-check-circle fa-lg"></i> list</button>
        <button type="button" class="AXButtonLarge Classic"><i class="fa fa-heart fa-lg"></i> delete</button>
    
        <table class="cls-read-table">
            <tr>
                <th>No</th><td><%=sSeq%></td>
                <th>공지기간</th><td>
                <input type="text" name="" id="AXInputDateST" class="AXInput W80" value="<%=sStartDate%>"/> ~ <input type="text" name="" id="AXInputDateED" class="AXInput W100" value="<%=sEndDate%>"/></td>
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
                <th width="80px">
                    <button type="button" class="AXButtonLarge Classic"><i class="fa fa-check-circle fa-lg"></i> add</button>
                </th>
            </tr>
        </table>
    </div>
    
    <div class="AXUpload5" id="AXUpload5"></div>

    <div class="H10"></div>

    <div id="uploadQueueBox" class="AXUpload5QueueBox" style="height:188px;"></div>

    <div class="H10"></div>

    <div>
        <input type="button" value="전송중지" class="AXButton" id="uploadCancelBtn" disabled="disabled" onclick="myUpload.cancelUpload();" />
        <input type="button" value="선택삭제" class="AXButton" onclick="myUpload.deleteSelect(null);" />
        <!--<input type="button" value="모두삭제" class="AXButton" onclick="myUpload.deleteSelect('all', 'withOutServer');" />-->
        <input type="button" value="모두삭제" class="AXButton" onclick="myUpload.deleteSelect('all');" />

        <input type="button" value="Get Object" class="AXButton" onclick="fnObj.upload.printMethodReturn('getUploadedList','object');" />
        <input type="button" value="Get Param" class="AXButton" onclick="fnObj.upload.printMethodReturn('getUploadedList','param');" />

        <input type="button" value="Get Select Object" class="AXButton" onclick="fnObj.upload.printMethodReturn('getSelectUploadedList','object')" />
        <input type="button" value="Get Select Param" class="AXButton" onclick="fnObj.upload.printMethodReturn('getSelectUploadedList','param')" />
    </div>
    
</form>
</body>
</html>