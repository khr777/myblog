<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="아이디 찾기"></c:set>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<c:if test="${jsAlertMsg != null }">
	<script>
		alert('${jsAlertMsg}');
	</script>
</c:if>
<c:if test="${jsAlertMsg2 != null }">
	<script>
		alert('${jsAlertMsg2}');
	</script>
</c:if>
<c:if test="${jsHistoryBack}">
	<script>
		history.back();
	</script>
</c:if>
<c:if test="${isLocationReplace != null  }">
	<script>
		location.replace('${isLocationReplace}');
	</script>
</c:if>
<c:if test="${jsLocationHref != null  }">
	<script>
		location.href = '${jsLocationHref}';
	</script>
</c:if>
<c:if test="${redirectUri != null  }">   
	<script>
		location.replace('${redirectUri}');
	</script>
</c:if>
