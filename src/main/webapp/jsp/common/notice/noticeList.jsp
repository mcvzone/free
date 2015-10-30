<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.free.module.core.config.FreeReservedWordConfig" %>
<%@ page import="com.free.module.common.model.vo.NoticeVo" %>
<%@ page import="java.util.*" %>
<%
List<NoticeVo> notices = (List<NoticeVo>)request.getAttribute(FreeReservedWordConfig.MISSION_RESULT_KEY);
StringBuffer sbData = null;
if( notices != null ){
    System.out.println("notices : " + notices.size());
    sbData = new StringBuffer();

    for( NoticeVo notice : notices ){
        sbData.append("{seq:"+notice.getSeq()+
        		   ", title:'"+notice.getTitle()+
                   "', writer:'"+notice.getWriter()+
                   "', reg_date:'"+notice.getReg_date()+"'},");
    }
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항</title>
<script type="text/javascript">
/**
 * Require Files for AXISJ UI Component...
 * Based        : jQuery
 * Javascript   : AXJ.js, AXGrid.js, AXInput.js, AXSelect.js
 * CSS          : AXJ.css, AXGrid.css, AXButton.css, AXInput.css, AXSelector.css
 */
var pageID = "viewMode";
var myGrid = new AXGrid();
var itemSum = 0;

var fnObj = {
    pageStart: function(){

        myGrid.setConfig({
            targetID : "AXGridTarget",
            theme : "AXGrid",
            viewMode: "grid", // ["grid","icon","mobile"]
            //viewMode: "mobile",

            // 브라우저 사이즈에 따른 changeGridView 설정
            mediaQuery: {
                mx:{min:0, max:600}, dx:{min:600}
            },
            colGroup : [
                {key:"seq", label:"번호", width:"100", align:"center", formatter:"money"},
                {key:"title", label:"제목", width:"500"},
                {key:"writer", label:"작성자", width:"100", align:"center"},
                {key:"reg_date", label:"작성일", width:"*", align:"center", formatter:"date"}
            ],

            body : {
                onclick: function(){
                	alert(this.item.seq);
                	document.mainForm.seq.value = this.item.seq;
                	document.mainForm.submit();
                    //toast.push(Object.toJSON({index:this.index, item:this.item}));
                    //alert(this.list);
                    //alert(this.page);
                },
                ondblclick: function(){
                    //toast.push(Object.toJSON({index:this.index, item:this.item}));
                    //alert(this.list);
                    //alert(this.page);
                }
            },
            
            view: {
                label:true,
                column: [ // col 은 4
                    {key:"seq", label:"번호", col:4, addClass:"underLine"},
                    {key:"title", label:"제목", col:2, align:"right"},
                    {key:"writer", label:"작성자", col:2, align:"center"},
                    {key:"reg_date", label:"작성일", col:null, align:"center"},
                    {key:"start_date", label:"공지시작일", align:"center"},
                    {key:"end_date", label:"공지종료일", align:"center"},
                    {key:"use_yn", label:"공지여부", align:"center"}
                ],
                buttons: [
                    {addClass:"", onclick:function(){
                    }},
                    {addClass:"", onclick:function(){
                    }}
                ]
            },
            page:{
                paging:true,
                pageNo:1,
                pageSize:100
            }
        });

        var list = [<%=sbData.toString().substring(0, sbData.toString().length()-1)%>];
        myGrid.setList(list);
        /*
        myGrid.setList({
            ajaxUrl:"loadGrid.php", ajaxPars:"", onLoad:function(){
                //trace(this);
            }
        });*/

    },
    otherFunction: function(reqThis){
        toast.push(reqThis.buttonItem.label+"//"+Object.toJSON(reqThis.item));
    },
    changeView: function(viewMode){
        if(viewMode == "grid"){
            myGrid.changeGridView({
                viewMode:viewMode
            });
        }else if(viewMode == "icon"){
            myGrid.changeGridView({
                viewMode:viewMode,
                view: {
                    // 속성이 없을때 예외 처리 마지막에 구현
                    width:"200",
                    height:"300",
                    img: {left:"10", top:"10", width:"179", height:"180",style:"border:1px solid #ccc;"},
                    label:{left:"10", top:"200", width:"180", height:"20"},
                    description: {left:"10", top:"225", width:"180", height:"65", style:"color:#888;"},
                    buttons: {
                        left:"5", top:"5", width:"180", height:"20", style:"",
                        items:[
                            {label:"but1", style:"", addClass:"AXButton Green", onclick:function(){
                                fnObj.otherFunction(this);
                            }},
                            {label:"but2", style:"", addClass:"AXButton", onclick:function(){
                                fnObj.otherFunction(this);
                            }}
                        ]
                    },
                    format: function(){
                        return {
                            imgsrc : this.item.img,
                            label : this.item.title,
                            description : this.item.writer+", "+this.item.no+" / " + (this.item.desc || "")
                        }
                    }
                }
            });
        }else if(viewMode == "mobile"){
            myGrid.changeGridView({
                viewMode:viewMode,
                view: {
                    column: [ // col 은 4
                        {key:"title", label:"제목", col:4, addClass:"underLine"},
                        {key:"seq", label:"번호", col:2, align:"right"},
                        {key:"writer", label:"작성자", col:2, align:"center"},
                        {key:"reg_date", label:"작성일", col:null, align:"center"}
                    ]
                }
            });
            myGrid.changeGridView({
                viewMode:viewMode
            });
        }

    }
};

jQuery(document.body).ready(function() {
    fnObj.pageStart();
});
</script>
</head>
<body>
<form action="/free?mission=CM0000006" method="post" name="mainForm">
    <input type="hidden" name="seq">
</form>
<div id="AXGridTarget"></div>
</body>
</html>