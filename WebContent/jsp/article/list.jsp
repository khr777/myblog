<%@ include file="/jsp/part/head.jspf"%>
<%@ page import="com.sbs.java.blog.dto.Article"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 카테고리별 게시물들 -->
<%
	List<Article> articles = (List<Article>)request.getAttribute("articles");
%>
<!-- 페이지 개수 -->
<%
int totalPage = (int)request.getAttribute("totalPage");
%>



<!--  /s/article/list?cateItemId=1&page=1  -->
<nav class="cateItem-menu-box-1">
	<ul class="cateItem-menu">
		<li><a href="${pageContext.request.contextPath}/s/article/list?cateItemId=1&page=1">일상</a></li>
		<li><a href="${pageContext.request.contextPath}/s/article/list?cateItemId=2&page=1">IT : java</a></li>
		<li><a href="${pageContext.request.contextPath}/s/article/list?cateItemId=3&page=1">IT : html/css/js</a></li>
		<li><a href="${pageContext.request.contextPath}/s/article/list?cateItemId=4&page=1">IT : sql</a></li>
		<li><a href="${pageContext.request.contextPath}/s/article/list?cateItemId=5&page=1">IT : 기타</a></li>
		<li><a href="${pageContext.request.contextPath}/s/article/list?cateItemId=6&page=1">이거저거</a></li>
	</ul>
</nav>


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


<div class="paging-box">
	<% for ( int i = 1; i <= totalPage; i++ ) { %>
	<div class="paging-num-box">
		<a href="${pageContext.request.contextPath}/s/article/list?cateItemId=${param.cateItemId}&page=<%=i%>">[ <%=i%> ]</a>
	</div>
	<% 
		} 
	%>
</div>







<%@ include file="/jsp/part/foot.jspf"%>