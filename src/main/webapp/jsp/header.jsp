<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="com.free.module.core.config.FreeReservedWordConfig" %>
<%@ page import="com.free.module.common.model.vo.UserInfoVo" %>
<%
UserInfoVo userInfoVo = (UserInfoVo)request.getSession().getAttribute(FreeReservedWordConfig.USER_INFO_KEY);
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<html>
<head>
    <link rel="shortcut icon" href="/resources/axisj-1.0.19/ui/axisj.ico" type="image/x-icon" />
    <link rel="icon" href="/resources/axisj-1.0.19/ui/axisj.ico" type="image/x-icon" />
    <link rel="stylesheet" type="text/css" href="/resources/axisj-1.0.19/ui/arongi/AXJ.min.css"/>
    <script type="text/javascript" src="/resources/axisj-1.0.19/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/axisj-1.0.19/dist/AXJ.min.js"></script>
    <script type="text/javascript">
    var myMenu = new AXTopDownMenu();
    var myMenuForScript = new AXTopDownMenu();
    var fnObj = {
        pageStart: function(){

            myMenu.setConfig({
                menuBoxID:"menuBox",
                parentMenu:{
                    className:"parentMenu"
                },
                childMenu:{
                    className:"childMenu",
                    align:"center",
                    valign:"top",
                    margin:{top:0, left:0},
                    arrowClassName:"varrow2",
                    arrowMargin:{top:1, left:0}
                },
                childsMenu:{
                    className:"childsMenu",
                    align:"left",
                    valign:"top",
                    margin:{top:-4, left:0},
                    arrowClassName:"harrow",
                    arrowMargin:{top:13, left:1}
                },
                onComplete: function(){
                    //myMenu.setHighLightMenu(0);
                    //myMenu.setHighLightMenu([0,1,3,2]);
                    myMenu.setHighLightOriginID("ID1245");
                }
            });
        }
    }
    $(document).ready(fnObj.pageStart.delay(0));
    </script>
    
    <script type="text/javascript">

    </script>

    <style>
        .varrow1    {position:absolute;background: transparent url('/resources/axisj-1.0.19/ui/arongi/images/allow_menu1.gif') no-repeat;width:19px;height:10px;z-index:2;_margin-top:4px;}
        .varrow2    {position:absolute;background: transparent url('/resources/axisj-1.0.19/ui/arongi/images/allow_menu2.gif') no-repeat;width:19px;height:10px;z-index:2;_margin-top:4px;}
        .harrow     {position:absolute;background: transparent url('/resources/axisj-1.0.19/ui/arongi/images/allow_menu3.gif') no-repeat;width:9px;height:13px;z-index:2;}

        .AXMenuBox{position:relative;background: #3e4558;height:40px;line-height:40px;padding:0px 20px;min-width:700px;}
        .AXMenuBox a{
            transition-delay:0s;
            transition-timing-function:ease;
            transition-duration:0.3s;
            transition-property:all;
        }
        .AXMenuBox ul{list-style:none;margin:0px; padding:0px;}
        .AXMenuBox ul li{list-style:none;margin:0px; padding:0px;float:left;}

            .AXMenuBox .parentMenu{color:#fff;position:relative;font-size:14px;}
                .AXMenuBox .parentMenu a{
                    display:block;_display:inline-block;
                    color:#fff;text-decoration:none;
                    height:40px;line-height:40px;font-family:'Consolas';
                    padding:0px 10px;
                }
                .AXMenuBox .parentMenu a:hover{background:#9197a4;}
                .AXMenuBox .parentMenu a:focus{background:#9197a4;}
                .AXMenuBox .parentMenu a.on{background:#9197a4;}

            .AXMenuBox .childMenu{position:absolute;line-height:25px;font-size:12px;display:none;}
                .AXMenuBox .childMenu ul{
                    background:#ebebeb;border:1px solid #ccc;border-radius:5px;
                    padding:3px; margin:0px;display:block;position:relative;
                    box-shadow:0px 0px 3px #ccc;
                    }
                    .AXMenuBox .childMenu ul li{float:none;padding:0px; margin:0px;border-bottom:1px solid #ebebeb;}
                        .AXMenuBox .childMenu ul li a{
                            display:block;_width:100%;
                            padding:0px 10px;
                            height:30px;line-height:30px;
                            background:#424a5c;
                            border-radius:3px;
                            color:#fff; text-decoration: none;white-space:nowrap;
                            }
                        .AXMenuBox .childMenu ul li a:hover{background:#9197a4;}

                        .AXMenuBox .childMenu ul li a.on{background:#9197a4;}
                        .AXMenuBox .childMenu ul li a.expand{background-image:url(/resources/axisj-1.0.19/ui/arongi/images/rightArrows.png);background-repeat:no-repeat;background-position:100% 0px;padding-right:30px;}
                        .AXMenuBox .childMenu ul li a.expand:hover{background-image:url(/resources/axisj-1.0.19/ui/arongi/images/rightArrows.png);background-repeat:no-repeat;background-position:100% -30px;padding-right:30px;}

            .AXMenuBox .childsMenu{position:absolute;line-height:20px;font-size:12px;}
                .AXMenuBox .childsMenu ul{background:#ebebeb;border:1px solid #ccc;padding:3px; margin:0px;}
                    .AXMenuBox .childsMenu ul li{float:none;padding:0px; margin:0px;border-bottom:1px solid #ebebeb;}
                        .AXMenuBox .childsMenu ul li a{}

        .clear{clear:both;}
    </style>
</head>
<body>
<%if( userInfoVo != null ){%>
    <%=userInfoVo.getName()%> 님 어서오세요. <a href="/free?mission=CM0000004">Logout</a>
<%} else {%>
    <a href="/free?mission=CM0000002">Login</a>
<%}%>
<div style="height:50px;"></div>
<div id="AXPage">
    <!-- s.AXPageBody -->
    <div id="AXPageBody" class="SampleAXSelect">
        <div id="demoPageTabTarget" class="AXdemoPageTabTarget"></div>
        <div class="AXdemoPageContent">
            <div class="AXMenuBox" style="z-index:5;">
                <div id="menuBox">
                    <ul>
                        <li>
                            <div class="parentMenu">
                                <a href="/free?mission=CM0000005" id="ID1234">공지사항</a>
                            </div>
                        </li>
                        <li>
                            <div class="parentMenu">
                                <a href="#">게시판</a>
                                <div class="childMenu">
                                    <ul>
                                        <li><a href="http://www.mondo.co.kr" target="_blank">mondo.co.kr</a></li>
                                        <li><a href="#">AXISJ Simple</a></li>
                                        <li><a href="#">AXISJ Journey</a></li>
                                        <li><a href="#">AXWaterfall</a></li>
                                        <li><a href="#">Squall</a></li>
                                        <li>
                                            <a href="#" class="expand">tom@axisj.com</a>
                                            <div class="childsMenu">
                                                <ul>
                                                    <li><a href="#">Feature</a></li>
                                                    <li><a href="#">Design</a></li>
                                                    <li><a href="#">Application</a></li>
                                                    <li><a href="#" class="expand">Options</a>
                                                        <div class="childsMenu">
                                                            <ul>
                                                                <li><a href="#">Feature</a></li>
                                                                <li><a href="#">Design</a></li>
                                                                <li><a href="#" class="expand">Applications</a>
                                                                    <div class="childsMenu">
                                                                        <ul>
                                                                            <li><a href="#">Feature</a></li>
                                                                            <li><a href="#">Design</a></li>
                                                                            <li><a href="#">Applications</a></li>
                                                                            <li><a href="#">Options</a></li>
                                                                        </ul>
                                                                    </div>
                                                                </li>
                                                                <li>
                                                                    <a href="#">Options</a>

                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="parentMenu">
                                <a href="#">자료실</a>
                            </div>
                        </li>
                        <li>
                            <div class="parentMenu">
                                <a href="#">채팅방</a>
                            </div>
                        </li>
                        <li>
                            <div class="parentMenu">
                                <a href="#">admin</a>
                                <div class="childMenu">
                                    <ul>
                                        <li><a href="#">회원관리</a></li>
                                        <li><a href="#">8300297</a></li>
                                        <li><a href="#">AXInput</a></li>
                                        <li><a href="#">Jowrney</a></li>
                                        <li><a href="#">Stacey</a></li>
                                    </ul>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>