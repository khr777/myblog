<%@ include file="/jsp/part/head.jspf"%>
<%@ page import="com.sbs.java.blog.dto.Article"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
List<Article> articles = (List<Article>) request.getAttribute("articles");
%>




<style>
.table-box {
	margin-top: 2%;
}

.table-box>table {
	width: 100%;
	border-collapse: collapse;
}

.table-box>table th, .table-box>table td {
	border: 1px solid black;
}

.article-list-box-1 td {
	text-align: center;
	padding: 10px;
}

.article-name-box {
	width:13%;
	margin-top:4%;
}

.article-icon {
	position: absolute;
	top: 100px;
}
</style>


<div class="con">
	<img class="article-icon" alt=""
		src="../../resourse/img/article-icon.jpg">
	<div class="article-name-box flex flex-jc-sb">
		<i class="fas fa-list-ol" style="font-size: 2.5rem;"></i>
		<h1 class="article-name">Articles</h1>
	</div>
</div>

<div class="con table-box article-list-box-1">
	<table>
		<colgroup>
			<col width="50" />
			<col width="150" />
			<col width="150" />
			
		</colgroup>
		<thead>
			<tr>
				<th>ID</th>
				<th>등록날짜</th>
				<th>갱신날짜</th>
				<th>제목</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (Article article : articles) {
			%>

			<tr>
				<td><%=article.getId()%></td>
				<td><%=article.getRegDate()%></td>
				<td><%=article.getUpdateDate()%></td>
				<!--  article.getId()가 없어도 일단 실행이 된다????? -->
				<!-- <td><a href="./detail?id=<%=article.getId()%>"><%=article.getTitle()%></a></td>  -->
				<td><a href="./detail?id=<%=article.getId()%>"><%=article.getTitle()%></a></td>
			</tr>
			<%
				}
			%>

		</tbody>
	</table>
</div>


<%@ include file="/jsp/part/foot.jspf"%>