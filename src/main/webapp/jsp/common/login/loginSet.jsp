<%@ page import="com.free.module.common.model.vo.UserInfoVo" %>
<%@ page import="com.free.module.core.config.FreeReservedWordConfig" %>
<%
UserInfoVo userInfoVo = (UserInfoVo)request.getAttribute("result");
if( userInfoVo != null ){
    request.getSession().setAttribute(FreeReservedWordConfig.USER_INFO_KEY, userInfoVo);
}
%>
<script type="text/javascript">location.href = "/free?mission=CM0000001";</script>