<%@ include file="/jsp/part/head.jspf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/highlight.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/styles/default.min.css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
 
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
 
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


<title>Write something else you want</title>
<div class="container">
<table class="table table-bordered">
    <thead>
        <caption> 게시물 작성하기</caption>
    </thead>
    <tbody>                     <!--   form에  -    method="post"  -  를 뺐더니 한글깨짐 해결되었음   -->
        <form name =form1 action="${pageContext.request.contextPath}/s/article/listWrite"  encType="multipart/form-data">
        	<tr>
                <th>공개 여부: </th>
                <td><input type="text" placeholder="공개여부 번호를 입력하세요. " name="displayStatus" value = "${param.displayStatus}"class="form-control"/></td>
            </tr>
        	<tr>
                <th>카테고리 번호: </th>
                <td><input type="text" placeholder="카테고리 번호를 입력하세요. " name="cateItemId" value = "${param.cateItemId}"class="form-control"/></td>
            </tr>
            <tr>
                <th>제목: </th>
                <td><input type="text" placeholder="제목을 입력하세요. " name="title" value ="${param.title}" class="form-control"/></td>
            </tr>
            <tr>
                <th>내용: </th>
                <td><textarea cols="10" placeholder="내용을 입력하세요. " name="body" value = "${param.body}" class="form-control" style="height:600px;"></textarea></td>
            </tr>
                     
            <tr>
                <td colspan="2">
<!-- 		                <input type="button" value="등록" onclick="sendData()" class="pull-right"/> -->
                    	<button type="submit"  id="save-button">저장</button>
                		<input type="button" value="입력완료" onClick="form1.action='list';form1.submit();">
						<button type="button" onclick="location.href='list?cateItemId=${param.cateItemId}&page=${param.page}'" >뒤로가기</button> 
                </td>

                	
            </tr>
        </form>
    </tbody>
</table>
</div>


<script>
	var save-button = el.document.Selector('save-button');

	

</script>










<style>
.container {
	margin-top:100px;
}
.article-write-box {
	position:absolute;
	top:50%;
	left:50%;
	transform:translateX(-50%) translateY(-50%);
	
}
.article-write-box .title-box {
	border:1px solid black;

}

.article-write-box .body-box {
	border:1px solid black;

}

.article-write-box form .title-box  input:nth-child(4) {
}

.article-write-box form .body-box input:nth-child(4) {
}



</style>




</body>
</html>







<%@ include file="/jsp/part/foot.jspf"%>