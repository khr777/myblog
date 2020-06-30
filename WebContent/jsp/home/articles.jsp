<%@ include file="/jsp/part/head.jspf"%>
<%@ page import="com.sbs.java.blog.dto.Article"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	List<Article> articles = (List<Article>) request.getAttribute("articles");
%>




<style>
.article-name-box {
	width: 13%;
	margin-top: 4%;
}

.article-icon {
	position: absolute;
	top: 100px;
}
</style>

<!--<div class="con">
	<img class="article-icon" alt=""
		src="../../resourse/img/article-icon.jpg">
	<div class="article-name-box flex flex-jc-sb">
		<i class="fas fa-list-ol" style="font-size: 2.5rem;"></i>
		<h1 class="article-name">Articles</h1>
	</div>
</div>  -->


<div class="con article-list-box-1 ">
	<%
		for (Article article : articles) {
	%>

	<div class="list-content">
		<a href="./detail?id=<%=article.getId()%> " class="">
			<div class="list-title"><%=article.getTitle()%></div>
			<div class="list-body-box">
				<div class="list-body"><%=article.getBody()%></div>
			</div> <br>
			<div class="list-id-regDate-box flex">
				<div class="list-id">
					<img src="../../resource/img/no.PNG" alt="" style="width: 30px;"
						style="block" />
					<%=article.getId()%>
				</div>
				<div class="list-regDate">
					<img src="../../resource/img/date.PNG" alt="" style="width: 50px;"
						style="block" />
					<%=article.getRegDate()%></div>
			</div>
			<div class="list-updateDate" style="display: none;"><%=article.getUpdateDate()%></div>
		</a> <br>
	</div>

	<%
		}
	%>

</div>


<%@ include file="/jsp/part/foot.jspf"%>