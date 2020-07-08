<%@ include file="/jsp/part/head.jspf"%>
<%@ page import="com.sbs.java.blog.dto.Article"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 카테고리별 게시물들 -->
<%
	List<Article> articles = (List<Article>) request.getAttribute("articles");
int totalPage = (int) request.getAttribute("totalPage"); //getAttribute는 Object 타입을 return 한다.
int paramPage = (int) request.getAttribute("page");
//int cateItemId = (int)request.getAttribute("cateItemId");
int totalCount = (int) request.getAttribute("totalCount");
String cateItemName = (String) request.getAttribute("cateItemName");
//String search = (String)request.getAttribute("search");
%>



<!--  /s/article/list?cateItemId=1&page=1  -->

<nav class="cateItem-menu-box-1">
	<ul class="cateItem-menu">
		<li><a href="${pageContext.request.contextPath}/s/article/list">전체</a>
		</li>
		<li><a
			href="${pageContext.request.contextPath}/s/article/list?cateItemId=1&page=1">일상</a></li>
		<li><a
			href="${pageContext.request.contextPath}/s/article/list?cateItemId=2&page=1">IT
				: java, jsp</a></li>
		<li><a
			href="${pageContext.request.contextPath}/s/article/list?cateItemId=3&page=1">IT
				: html/css/js</a></li>
		<li><a
			href="${pageContext.request.contextPath}/s/article/list?cateItemId=4&page=1">IT
				: sql</a></li>
		<li><a
			href="${pageContext.request.contextPath}/s/article/list?cateItemId=5&page=1">IT
				: 기타</a></li>
		<li><a
			href="${pageContext.request.contextPath}/s/article/list?cateItemId=6&page=1">이거저거</a></li>
	</ul>

</nav>







<div class="con article-list-box-1 ">

	<%
		//if ( cateItemId == 0 && search == null) {
	%>
	<!--<div class="new-article">NEW 게시물</div> -->
	<%
		// }
	%>
	<%
		//if ( search != null ) {
	%>
	<!-- <div class="new-article">검색 결과</div>	 -->
	<%
		//}
	%>



	<%
		for (Article article : articles) {
	%>


	
	<div class="list-content">
		<a href="./detail?id=<%=article.getId()%>"  class="">
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
	<div class="search-box ">
		<!-- method="get"은 생략 가능하다. 무엇인지 찾아보기. method="get"-->
		<form action=" ${pageContext.request.contextPath}/s/article/list">

			<input type="hidden" name="page" value="1" />
			<!-- 검색하면 page를 모두 0으로 초기화해야 하니까..? -->
			<input type="hidden" name="cateItemId" value="${param.cateItemId}" />
			<!--param~ el?이라고 한다. 오른쪽 예시의 줄인 표현이다.  <%//request.getParame~로 바로 할 수 있지만 %> %> -->
			<input type="hidden" name="searchKeywordType" value="title" />
			<!-- title, body 할 수 있지만 지금은 title만 -->
			<input type="hidden" name="searchKeywordTypeBody" value="body" /> <input
				type="text" name="searchKeyword" value="${param.searchKeyword}"
				class="box" />

			<button type="submit" class="search-button">검색</button>

		</form>
	</div>

	<div class="cateItem-content">
		<div class="con total-count">총 게시물 수 : ${totalCount}</div>
		<div class="cateItemName "><%=cateItemName%></div>

		<div class="con doWrite">
			<a
				href="${pageContext.request.contextPath}/s/article/listWrite?cateItemId=${param.cateItemId}&page=${page}">글쓰기</a>
		</div>
	</div>

</div>


<div class="paging-box">
	<%
		for (int i = 1; i <= totalPage; i++) {
	%>
	<!-- 현재 페이지current 이면 빨강. 삼항연산자?? -->
	<div class="paging-num-box <%=i == paramPage ? "current" : ""%>">
		<%
			//if ( cateItemId != 0 ) {
		%>
		<a
			href="?cateItemId=${param.cateItemId}&searchKeywordType=${param.searchKeywordType}&searchKeywordTypeBody=${param.searchKeywordTypeBody}&searchKeyword=${param.searchKeyword}&page=<%=i%>"><%=i%></a>
	</div>
	<!-- ${pageContext.request.contextPath}/s/article/list 여기까지 생략해도 작동된다. -->
	<%
		}
	%>
	<%
		//}
	%>
</div>




<%@ include file="/jsp/part/foot.jspf"%>