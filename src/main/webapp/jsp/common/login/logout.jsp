<%@ page import="com.free.module.core.config.WordConfig" %>
<%@ page import="com.free.module.common.model.vo.UserInfoVo" %>
<%
request.getSession().removeAttribute(WordConfig.USER_INFO);
%>
<script type="text/javascript">location.href = "/free?mission=CM0000001";</script>