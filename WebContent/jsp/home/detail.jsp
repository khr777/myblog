<%@ include file="/jsp/part/head.jspf"%>
<%@ page import="com.sbs.java.blog.dto.Article"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
	Article article = (Article) request.getAttribute("article");
%>

<div class="con table-box article-detail-box-1">
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>등록날짜</th>
				<th>갱신날짜</th>
				<th>제목</th>
				<th>내용</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><%=article.getId()%></td>
				<td><%=article.getRegDate()%></td>
				<td><%=article.getUpdateDate()%></td>
				<td><%=article.getTitle()%></td>
				<td><%=article.getBody()%></td>
			</tr>
		</tbody>
	</table>
</div>


<div id="origin1">
	# <%=article.getBody()%> 
	## <%=article.getBody()%>
</div>
<div id="viewer1"></div>


<%@ include file="/jsp/part/foot.jspf"%>