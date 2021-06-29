<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="${cateItemName}"></c:set>
<%@ include file="/jsp/part/head.jspf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<nav class="cateItem-menu-box-1  visible-on-md-up ">
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
		<li><a
			href="${pageContext.request.contextPath}/s/article/list?cateItemId=7&page=1">공부
				계획</a></li>
	</ul>
</nav>


<select class="mobile-cateItem-move visible-on-sm-down "
	onchange="location.href=this.value">
	<option>카테고리</option>
	<option value="${pageContext.request.contextPath}/s/article/list">전체</option>
	<option
		value="${pageContext.request.contextPath}/s/article/list?cateItemId=1&page=1">일상</option>
	<option
		value="${pageContext.request.contextPath}/s/article/list?cateItemId=2&page=1">IT
		: java, jsp</option>
	<option
		value="${pageContext.request.contextPath}/s/article/list?cateItemId=3&page=1">IT
		: html/css/js</option>
	<option
		value="${pageContext.request.contextPath}/s/article/list?cateItemId=4&page=1">IT
		: sql</option>
	<option
		value="${pageContext.request.contextPath}/s/article/list?cateItemId=5&page=1">IT
		: 기타</option>
	<option
		value="${pageContext.request.contextPath}/s/article/list?cateItemId=6&page=1">이거저거</option>
</select>

<style>
</style>






<div class="con article-list-box-1 ">

	<c:forEach items="${articles}" var="article">
		<div class="list-content">
			<a
				href="./detail?id=${article.id}&cateItemId=${param.cateItemId}&page=${param.page}"
				class="">
				<div class="list-title">${article.title}</div>
				<div class="list-body-box">
					<div class="list-body">
						<%-- 						<%=article.getSummary()%> --%>
						${article.getSummary()}
					</div>
				</div> <br>
				<div class="list-id-regDate-box flex">
					<div class="list-id">
						<img src="../../resource/img/no.PNG" alt="" style="width: 30px;"
							style="block" /> ${article.id}
					</div>
					<div class="list-regDate">
						<img src="../../resource/img/date.PNG" alt="" style="width: 50px;"
							style="block" /> ${article.regDate}
					</div>
					<div class="list-writer">
						작성자 :
						<%-- 						<%=article.getExtra().get("writer") %> --%>
						${article.extra.writer}
					</div>
				</div>
				<div class="list-updateDate" style="display: none;">${article.updateDate}</div>
			</a> <br>
		</div>
	</c:forEach>
	<div class="search">
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
		</div>
	</div>

	<div class="paging-box">
		<c:forEach var="i" begin="1" end="${totalPage}" step="1">
<%-- 			<c:if test="${totalPage != 0 }"> --%>
<!-- 				<input type="button" value="다음"/> -->
<%-- 			</c:if> --%>
<!-- 			<!-- 현재 페이지current 이면 빨강. 삼항연산자?? --> 
			<div class="paging-num-box ${i == cPage ? 'current' : ''}">
				<a
					href="?cateItemId=${param.cateItemId}&searchKeywordType=${param.searchKeywordType}&searchKeywordTypeBody=${param.searchKeywordTypeBody}&searchKeyword=${param.searchKeyword}&page=${i}">${i}</a>
			</div>
			<!-- ${pageContext.request.contextPath}/s/article/list 여기까지 생략해도 작동된다. -->
		</c:forEach>
	</div>

	<style>
.page-title {
	margin-top: -30px;
}
</style>

	<%@ include file="/jsp/part/foot.jspf"%>