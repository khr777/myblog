<%@ include file="/jsp/part/head.jspf"%>
<%@ page import="com.sbs.java.blog.dto.Article"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!-- 마크다운 뷰어에 필요한 코드.. (참고: 딱 필요한 것만 추리지 못함) -->

<!-- 하이라이트 라이브러리 추가, 토스트 UI 에디터에서 사용됨 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/highlight.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/styles/default.min.css">

<!-- 하이라이트 라이브러리, 언어 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/css.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/javascript.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/java.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/xml.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/php.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/php-template.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/sql.min.js"></script>

<!-- 코드 미러 라이브러리 추가, 토스트 UI 에디터에서 사용됨 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.min.css" />

<!-- 토스트 UI 에디터, 자바스크립트 코어 -->
<script
	src="https://uicdn.toast.com/editor/latest/toastui-editor-viewer.min.js"></script>

<!-- 토스트 UI 에디터, 신택스 하이라이트 플러그인 추가 -->
<script
	src="https://uicdn.toast.com/editor-plugin-code-syntax-highlight/latest/toastui-editor-plugin-code-syntax-highlight-all.min.js"></script>

<!-- 토스트 UI 에디터, CSS 코어 -->
<link rel="stylesheet"
	href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />

<!--  마크다운 뷰어 코드 끝 -->



<%
	Article article = (Article) request.getAttribute("article");
%>

<div class="con">
	<div class="detail-box-1 absolute-center absolute-middle">
		<div class="detail-title"><%=article.getTitle()%></div>
		<br>
		<div class="data-box-1 flex flex-jc-sb flex-ai-c">
			<div class="writeData flex">
				<div class="id visible-on-md-up">
					번호 :
					<%=article.getId()%></div>
				<div class="date">
					등록일 :
					<%=article.getRegDate()%></div>
				<div class="updateDate visible-on-md-up">
					수정일 :
					<%=article.getUpdateDate()%></div>
				<div class="writer">
					작성자 :<%=article.getExtra().get("writer")%></div>
			</div>
			<!-- 카테고리 게시물 접속했을 때, 목록 클릭하면 최신 게시물을 불러왔음. 카테고리 게시물로 이동하게 수정한 코드 -->
			<a
				href="${pageContext.request.contextPath}/s/article/list?cateItemId=<%=article.getCateItemId()%>"
				class="back-icon block"><i class="fas fa-arrow-left"> <span>목록</span>
			</i></a> <a
				href="${pageContext.request.contextPath}/s/article/modify?id=<%=article.getId()%>&cateItemId=<%=article.getCateItemId()%>&title=<%=article.getTitle()%>&body=<%=article.getBody()%>"
				class="back-icon block"><i class="fas fa-edit"> <span>수정</span>
			</i></a>

		</div>
		<div class="editor-box">
			<div id="origin1" style="display: none;"><%=article.getBody()%></div>
			<div id="viewer1"></div>
			<script>
				var editor1__initialValue = $('#origin1').html();
				var editor1 = new toastui.Editor({
					el : document.querySelector('#viewer1'),
					height : '600px',
					initialValue : editor1__initialValue,
					viewer : true,
					plugins : [ toastui.Editor.plugin.codeSyntaxHighlight ]
				});
			</script>
			<br />
			<div class="move-button">
				<button type="button"
					onclick="location.href='detail?id=${param.id-1}'">이전</button>
				<button type="button"
					onclick="location.href='detail?id=${param.id+1}'">다음</button>
			</div>
		</div>
	</div>
</div>
<button type="submit" onclick="location.href='delete?id=${param.id}'" style="position:absolute; left:80%; top:30%;">삭제</button>




<style>
.move-button {
	position:absolute;
	top:170px;
	left:0;
}
</style>

<%@ include file="/jsp/part/foot.jspf"%>