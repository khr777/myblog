<%@ include file="/jsp/part/head.jspf"%>
<%@ page import="com.sbs.java.blog.dto.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

Article article = (Article)request.getAttribute("article");
%>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/highlight.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/styles/default.min.css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
 
 
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	

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

	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.min.css" />

	<script
		src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>

	<script
		src="https://uicdn.toast.com/editor-plugin-code-syntax-highlight/latest/toastui-editor-plugin-code-syntax-highlight-all.min.js"></script>

	<link rel="stylesheet"
		href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<title>Modify</title>


<div class="container">
<table class="table table-bordered">
    <thead>
        <caption> 게시물 수정</caption>
        <div class="emoji">
			<a href="https://www.emojiengine.com/ko/" target="_blank">
				😵 emoji 이동
			</a>
		</div>
    </thead>
    <tbody>                     <!--   form에  -    method="post"  -  를 뺐더니 한글깨짐 해결되었음   -->
        <form method="post" name="form1" action="${pageContext.request.contextPath}/s/article/modifyOk"  encType="application/x-www-form-urlencoded">
        	
        	<tr>
                <th>카테고리 번호: </th>
					<input type="hidden" name="id" value="<%=article.getId()%>" />
					<td><input type="text" placeholder="카테고리 번호를 입력하세요. " name="cateItemId" value = "<%=article.getCateItemId()%>" class="form-control"/></td>
            </tr>
            <tr>
                <th>제목: </th>
                <td><input type="text" placeholder="제목을 입력하세요. " name="title" value ="<%=article.getTitle()%>" class="form-control"/></td>
            </tr>
            <tr>
                <th>내용: </th>
                <td><textarea cols="10" placeholder="내용을 입력하세요. " name="body"  class="form-control" style="height:600px;"><%=article.getBody()%></textarea></td>
            </tr>
                     
            <tr>
                <td colspan="2">
<!--                 	<input type="button" value="등록" onclick="sendData()" class="pull-right"/> -->
                    	<button type="submit">저장</button>
<!--                     	<button type="button" value="입력완료" onClick="form1.action='detail?id={}';form1.submit();">입력완료</button> -->
                    	<button type="submit" onclick="location.href='detail?id=${param.id}'" >뒤로가기</button>	
                </td>
            </tr>
        </form>
    </tbody>
</table>
</div>

<style>
.container {
	margin-top:100px;
}
</style>






<%@ include file="/jsp/part/foot.jspf"%>