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
	CateItem cateItem = (CateItem) request.getAttribute("cateItem");
	int beforeId = (int)request.getAttribute("beforeId");
	int afterId = (int)request.getAttribute("afterId");
	int cateItemId = (int)request.getAttribute("cateItemId");
%>

<div class="con">
	<div class="detail-box-1 absolute-center absolute-middle">
		<div class="detail-title"><%=article.getTitle()%></div>
		<br>
		<div class="data-box-1 ">
			<div class="writeData">
				<div class="id ">
					번호 :
					<%=article.getId()%></div>
				<div class="date">
					등록일 :
					<%=article.getRegDate()%></div>
				<div class="updateDate visible-on-md-up">
					수정일 :
					<%=article.getUpdateDate()%></div>
				<div class="writer">
					작성자 :
					<%=article.getExtra().get("writer")%></div>
				<div class="cateItemName">
					카테고리 :
					<%=cateItem.getName()%></div>
			</div>
			<!-- 카테고리 게시물 접속했을 때, 목록 클릭하면 최신 게시물을 불러왔음. 카테고리 게시물로 이동하게 수정한 코드 -->
			<a href="list?cateItemId=${param.cateItemId}&page=${param.page}"
				class="back-icon list-icon"><i class="fas fa-arrow-left">목록
			</i></a> <a
				href="${pageContext.request.contextPath}/s/article/modify?id=<%=article.getId()%>"
				class="back-icon  modify-icon"><i class="fas fa-edit">수정
			</i></a>	

		</div>
		<div class="editor-box">
			<script type="text/x-template" id="origin1" style="display: none;"><%=article.getBody()%></script>
			<div id="viewer1"></div>
			<script>
				var editor1__initialValue = $('#origin1').html().trim(); // trim() 추가했음. 
				var editor1 = new toastui.Editor({
					el : document.querySelector("#viewer1"),
					viewer : true,
					initialValue : editor1__initialValue,
					plugins : [ toastui.Editor.plugin.codeSyntaxHighlight,
							youtubePlugin, replPlugin, codepenPlugin ]
				});
			</script>
			<br />
			<div class="move-button">
				<%if ( beforeId > 0 ) { %>
					<input class="before" value="이전" type="button" onclick="location.href='detail?id=<%=beforeId%>&cateItemId=<%=cateItemId%>'"></button> <!--  둘 중에 뭘해도 값은  -->	
				<% } %>
				<%if ( afterId != -1 ) { %>
					<input class="after" value="다음" type="button" onclick="location.href='detail?id=<%=afterId%>&cateItemId=<%=cateItemId%>'"></button> <!--  둘 중에 뭘해도 값은  -->
				<%} %>
				
			</div>
			<div class="delete-button">
				<button type="submit"
					onclick="location.href='delete?id=${param.id}'"
					style="position: absolute; left: 80%; top: 30%;">삭제</button>
			</div>
		</div>
	</div>
</div>










<style>
.editor-box {
	
}

.move-button {
	position: absolute;
	buttom: 0;
	
}

.delete-button {
	position: absolute;
	buttom: 0;
	right:4%;
	width: 100px;
}


.toast-youtube-embed {
  background-color:red;
}

.toast-youtube-embed {
  position:relative;
}

.ratio-16-9::after {
  content:"";
  display:block;
  padding-top:56.25%;
}

.ratio-1-1::after {
  content:"";
  display:block;
  padding-top:100%;
}


.abs-full {
  position:absolute;
  top:0;
  left:0;
  width:100%;
  height:100%;
}
</style>

<%@ include file="/jsp/part/foot.jspf"%>