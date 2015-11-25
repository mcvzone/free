<%@ page import="com.free.module.common.model.vo.UserInfoVo" %>
<%@ page import="com.free.module.core.config.WordConfig" %>
<%
UserInfoVo userInfoVo = (UserInfoVo)request.getAttribute("result");
if( userInfoVo != null ){
    request.getSession().setAttribute(WordConfig.USER_INFO, userInfoVo);
}
%>
<script type="text/javascript">location.href = "/free?mission=CM0000001";</script>