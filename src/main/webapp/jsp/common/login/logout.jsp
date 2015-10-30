<%@ page import="com.free.module.core.config.FreeReservedWordConfig" %>
<%@ page import="com.free.module.common.model.vo.UserInfoVo" %>
<%
request.getSession().removeAttribute(FreeReservedWordConfig.USER_INFO_KEY);
%>
<script type="text/javascript">location.href = "/free?mission=CM0000001";</script>