package com.sbs.java.blog.controller;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RequestWrapper;

import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.dto.ArticleReply;
import com.sbs.java.blog.dto.CateItem;
import com.sbs.java.blog.util.Util;

public class ArticleController extends Controller {

	public ArticleController(Connection dbConn, String actionMethodName, HttpServletRequest req,
			HttpServletResponse resp) {
		super(dbConn, actionMethodName, req, resp);

	}

	// 오버라이딩을 통해서 부모의 메서드를 불러왔고, super를 통해서 부모가 먼저 할 일이 있으면 하세요. 그리고 제 일을 하겠습니다. 라는
	// 의미.
	public void beforeAction() {
		// [ 이 메서드는 게시물 컨트롤러의 모든 액션이 실행되기 전에 실행된다. ]
		// [ 필요없다면 지워도 된다. ]
		super.beforeAction();
	}

	// 상속받은 아이는 부모의 추상메서드를 무조건(싫어도) 상속받아서 구현해야 한다.
	public String doAction() {
		switch (actionMethodName) {
		case "list":
			return doActionList(req, resp);
		case "detail":
			return doActionDetail(req, resp);
		case "editor":
			return doActionEditor(req, resp);
		case "aboutMe":
			return doActionAboutMe(req, resp);
		case "modify":
			return doActionModify(req, resp);
		case "delete":
			return doActionDelete(req, resp);
		case "write":
			return doActionWrite(req, resp);
		case "doWrite":
			return doActionDoWrite(req, resp);
		case "doModify":
			return doModify(req, resp);
		case "doArticleReply":
			return doActionArticleReply(req, resp);
		case "doReplyModify":
			return doActionDoReplyModify(req,resp);
		case "doReplyDelete":
			return doActionDoReplyDelete(req,resp);
		}
		return "";
	}
	
	private String doActionDoReplyDelete(HttpServletRequest req, HttpServletResponse resp) {
		int replyId = 0;

		if (!Util.empty(req, "replyId") && Util.isNum(req, "replyId")) { // cateItemId가 없지 않고 숫자가 맞으면
			replyId = Util.getInt(req, "replyId");
		}
		System.out.println(replyId);
		int id = 0;

		if (!Util.empty(req, "id") && Util.isNum(req, "id")) { // cateItemId가 없지 않고 숫자가 맞으면
			id = Util.getInt(req, "id");
		}
		
		articleService.articleReplyDelete(replyId);
		

		return "html:<script> alert('작성하신 댓글을 삭제했습니다.'); location.replace('detail?id=" + id + "'); </script>";
	}

	private String doActionDoReplyModify(HttpServletRequest req, HttpServletResponse resp) {
		int replyId = 0;

		if (!Util.empty(req, "replyId") && Util.isNum(req, "replyId")) { // cateItemId가 없지 않고 숫자가 맞으면
			replyId = Util.getInt(req, "replyId");
		}
		
		int articleId = 0;

		if (!Util.empty(req, "articleId") && Util.isNum(req, "articleId")) { // cateItemId가 없지 않고 숫자가 맞으면
			articleId = Util.getInt(req, "articleId");
		}
		String body = req.getParameter("body");
		
		articleService.articleReplyModify(replyId,body);
		
		return "html:<script> alert('댓글을 수정했습니다.'); location.replace('detail?id=" + articleId + "');  </script>";
	}

	private String doActionArticleReply(HttpServletRequest req, HttpServletResponse resp) {
		String body = req.getParameter("body");
		int articleId = 0;
		if (!Util.empty(req, "articleId") && Util.isNum(req, "articleId")) { // articleId가 없지 않고 숫자가 맞으면
			articleId = Util.getInt(req, "articleId");
		}
	
		int articleReplyId = articleService.getArticleReply(body, articleId);
		
		
		
		return "html:<script> alert('" + articleId + "번 게시물 댓글을 작성하셨습니다.'); location.replace('detail?id=" + articleId + "'); </script>";
	}

	private String doModify(HttpServletRequest req, HttpServletResponse resp) {
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		
		int id = 0;

		if (!Util.empty(req, "id") && Util.isNum(req, "id")) { // cateItemId가 없지 않고 숫자가 맞으면

			id = Util.getInt(req, "id");

		}
		
		int cateItemId = Util.getInt(req, "cateItemId");
		int displayStatus = Util.getInt(req, "displayStatus");
		
		
		
		/*    jsp 파일에서 script로 함수 추가하기 전 구상했던 코드(작동은 됐었음)
		if (title.length() == 0 && body.length() == 0 ) {
			return "html:<script> alert('제목과 내용을 입력바랍니다.'); history.back();  </script>";
		}
		else if ( title.length() == 0 ) {
			return "html:<script> alert('제목을 입력바랍니다.'); history.back(); </script>";
		}
		else if ( body.length() == 0 ) {
			return "html:<script> alert('내용을 입력바랍니다.'); history.back(); </script>";
		}*/
		
		articleService.articleModify(id, cateItemId, displayStatus, title, body);
				
		return "html:<script> alert('" + id + "번 게시물이 수정되었습니다.'); location.replace('detail?id=" + id + "'); </script>";
		
	}

	private String doActionModify(HttpServletRequest req, HttpServletResponse resp) {
		int id = 0;

		if (!Util.empty(req, "id") && Util.isNum(req, "id")) { // cateItemId가 없지 않고 숫자가 맞으면

			id = Util.getInt(req, "id");

		}
		
		Article article = articleService.getForPrintArticle(id);
		String cateItemName = "전체";

		if (article.getCateItemId() != 0) {
			CateItem cateItem = articleService.getCateItem(article.getCateItemId());
			cateItemName = cateItem.getName();
		}

		req.setAttribute("cateItemName", cateItemName);
		req.setAttribute("article", article);
		return "article/modify.jsp";
	}

	private String doActionDoWrite(HttpServletRequest req, HttpServletResponse resp) {
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		
		
		int cateItemId = Util.getInt(req, "cateItemId");
		int displayStatus = Util.getInt(req, "displayStatus");
		/*  jsp 파일에서 script로 함수 추가하기 전 구상했던 코드(작동은 됐었음)
		if (title.length() == 0 && body.length() == 0 ) {
			return "html:<script> alert('제목과 내용을 입력바랍니다.'); history.back();  </script>";
		}
		else if ( title.length() == 0 ) {
			return "html:<script> alert('제목을 입력바랍니다.'); history.back(); </script>";
		}
		else if ( body.length() == 0 ) {
			return "html:<script> alert('내용을 입력바랍니다.'); history.back(); </script>";
		} */
		
		int id = articleService.write(cateItemId, displayStatus, title, body);
		
				
		return "html:<script> alert('" + id + "번 게시물이 생성되었습니다.'); location.replace('list'); </script>";
		// 자바스크립트로써 페이지 이동 명령. 꼭 이렇게해야 한다. 그러지 않으면 뒤로가기?로 또 글을 생성하게 된다. history에서 남기지 말고 이동해야 한다. 그래서 replace! 
	}

	// 게시물 작성 신청 폼을 한번 보여주는 정도(용도)의 메서드.	
	private String doActionWrite(HttpServletRequest req, HttpServletResponse resp) {
		return "article/write.jsp";
	}
	
	

	private String doActionDelete(HttpServletRequest req, HttpServletResponse resp) {
		
		int id = 0;

		if (!Util.empty(req, "id") && Util.isNum(req, "id")) { // cateItemId가 없지 않고 숫자가 맞으면

			id = Util.getInt(req, "id");

		}
		
		int deleteId = articleService.articleDelete(id);
				
		
		
		
		
		return "html:<script> alert('" + id + "번 게시물이 삭제되었습니다.'); location.replace('list'); </script>";
	} 


	
	private String doActionListWrite(HttpServletRequest req, HttpServletResponse resp) {

		

		return "article/listWrite.jsp";
	}

	private String doActionAboutMe(HttpServletRequest req, HttpServletResponse resp) {
		return "article/aboutMe.jsp";
	}

	private String doActionEditor(HttpServletRequest req, HttpServletResponse resp) {
		
		return "article/editor.jsp";
	}

	private String doActionDetail(HttpServletRequest req, HttpServletResponse resp) {
		if (Util.empty(req, "id")) {
			return "html:id를 입력해주세요.";
		}
		if (Util.isNum(req, "id") == false) {
			return "html:id를 정수로 입력해주세요.";
		}
		
		int id = Util.getInt(req, "id");
		
		if (Util.empty(req, "id")) {
			return "html:id를 입력해주세요.";
		}
		
		int cateItemId = 0;
		
		if (!Util.empty(req, "cateItemId") && Util.isNum(req, "cateItemId")) { // cateItemId가 없지 않고 숫자가 맞으면

			cateItemId = Util.getInt(req, "cateItemId");

		}
		
		articleService.increaseHit(id);
		
		// 이 article은 그냥(평범한) article이 아니다.
		Article article = articleService.getForPrintArticle(id); // sql 쿼리에 작성해놓은 정보들만이 아닌 부가적으로 추가한 자잘한 (항목 추가한)작성자 등
																	// 항목 모두 불러오는 메서드 네임
		int beforeId = articleService.getForPageMoveBeforeArticle(id, cateItemId);
		int afterId = articleService.getForPageMoveAfterArticle(id, cateItemId);
		CateItem cateItem = articleService.getCateItem(article.getCateItemId());
		req.setAttribute("beforeId", beforeId);
		req.setAttribute("afterId", afterId);
		req.setAttribute("article", article);
		req.setAttribute("cateItem", cateItem);
		req.setAttribute("cateItemId", cateItemId);
		
		
		
		List<ArticleReply> articleReplies = articleService.getArticleRepliesForDetail(id);
		req.setAttribute("articleReplies", articleReplies);
		
		// return "article/detail.jsp";

		// 작성자명이 게시물 상세보기에서 잠깐 필요한데 필드에까지 추가할 필요가 없다. 그래서 extra__를 이용해서 사용한다. sql에는 영향을
		// 주지 않는 듯. 확인해보기.
		return "article/detail.jsp";
		// 다시 한번 설명! 자질구래한 것들을 모아놓는 것이 dto의 extra 변수이다.
	}

	

	private String doActionList(HttpServletRequest req, HttpServletResponse resp) {

		// 샘이 만들어주신 검색 기능

		int page = 1;

		if (!Util.empty(req, "page") && Util.isNum(req, "page")) {
			page = Util.getInt(req, "page");
		}

		int cateItemId = 0;
		
		if (!Util.empty(req, "cateItemId") && Util.isNum(req, "cateItemId")) { // cateItemId가 없지 않고 숫자가 맞으면

			cateItemId = Util.getInt(req, "cateItemId");

		}
		
		
		String cateItemName = "전체";

		if (cateItemId != 0) {
			CateItem cateItem = articleService.getCateItem(cateItemId);
			cateItemName = cateItem.getName();
		}
		req.setAttribute("cateItemName", cateItemName);

		String searchKeywordType = ""; // keywordType이 더 중요하니까 searchkeyword보다 위에 써주는게 낫다.

		if (!Util.empty(req, "searchKeywordType")) { // cateItemId가 없지 않고 숫자가 맞으면

			searchKeywordType = Util.getString(req, "searchKeywordType");

		}
		
		String searchKeywordTypeBody = "";
		
		if (!Util.empty(req, "searchKeywordTypeBody")) { // cateItemId가 없지 않고 숫자가 맞으면

			searchKeywordTypeBody = Util.getString(req, "searchKeywordTypeBody");

		}
		
		
		
		

		String searchKeyword = "";

		if (!Util.empty(req, "searchKeyword")) { // cateItemId가 없지 않고 숫자가 맞으면

			searchKeyword = Util.getString(req, "searchKeyword");

		}

		

		int itemsInAPage = 10; // 게시물 리스트에 보여줄 게시물 개수
		int totalCount = articleService.getForPrintListArticlesCount(cateItemId, searchKeywordType, searchKeywordTypeBody, searchKeyword);
		int totalPage = (int) Math.ceil(totalCount / (double) itemsInAPage);
		

		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("page", page);

		List<Article> articles = articleService.getForPrintListArticles(page, cateItemId, itemsInAPage,
				searchKeywordType, searchKeywordTypeBody, searchKeyword);
		req.setAttribute("articles", articles);

	

		return "article/list.jsp";
	}
}
