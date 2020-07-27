package com.sbs.java.blog.controller;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.dto.ArticleReply;
import com.sbs.java.blog.dto.CateItem;
import com.sbs.java.blog.dto.Member;
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
			return doActionList();
		case "detail":
			return doActionDetail();
		case "editor":
			return doActionEditor();
		case "aboutMe":
			return doActionAboutMe();
		case "modify":
			return doActionModify();
		case "doDelete":
			return doActionDoDelete();
		case "write":
			return doActionWrite();
		case "doWrite":
			return doActionDoWrite();
		case "doModify":
			return doActionDoModify();
		case "doWriteReply": // 댓글 작성
			return doActionDoWriteReply();
		case "modifyReply": // 댓글 수정 페이지 이동
			return doActionModifyReply();
		case "doModifyReply": // 댓글 수정 후 저장하기 위한 페이지
			return doActionDoModifyReply();
		case "doDeleteReply":
			return doActionDoDeleteReply();
		}
		return "";
	}

	private String doActionModifyReply() {

		if (Util.empty(req, "id")) {
			return "html:id를 입력해주세요.";
		}

		if (Util.isNum(req, "id") == false) { // false라면. 정수가 아니라면. false를 빠트렸었음.
			return "html:id를 정수로 입력해주세요.";
		}

		int id = Util.getInt(req, "id"); // 댓글 번호

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		ArticleReply articleReply = articleService.getArticleReply(id);
		req.setAttribute("articleReply", articleReply);
		
		Article article = articleService.getForPrintArticle(articleReply.getArticleId(), loginedMemberId);
		req.setAttribute("article", article);

		return "article/modifyReply.jsp";
	}

	private String doActionDoDeleteReply() {

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");
		int replyId = 0;

		if (!Util.empty(req, "replyId") && Util.isNum(req, "replyId")) { // 댓글 번호
			replyId = Util.getInt(req, "replyId");
		}
		int id = 0;

		if (!Util.empty(req, "id") && Util.isNum(req, "id")) { // 게시물 번호
			id = Util.getInt(req, "id");
		}

		Map<String, Object> getReplyCheckRsDeleteAvailable = articleService.getReplyCheckRsDeleteAvailable(replyId,
				loginedMemberId);
		if (Util.isSuccess(getReplyCheckRsDeleteAvailable) == false) {
			return "html:<script> alert('" + getReplyCheckRsDeleteAvailable.get("msg")
					+ "'); history.back(); </script>";
		}

		

		articleService.deleteArticleReply(replyId);
		String redirectUri = Util.getString(req, "redirectUri", "list");

		return "html:<script> alert('댓글이 삭제되었습니다.'); location.replace('" + redirectUri + "'); </script>";
	}

	private String doActionDoModifyReply() {
		
		if (Util.empty(req, "id")) {
			return "html:id를 입력해주세요.";
		}
		if (Util.isNum(req, "id") == false) {
			return "html:id를 정수로 입력해주세요.";
		}
		
		
		int	id = Util.getInt(req, "articleReplyId");  // 댓글 번호     
		String body = Util.getString(req, "body");
 		
		System.out.println("id : " +  id);
		
		int loginedMemberId =(int)req.getAttribute("loginedMemberId");
		
		 Map<String, Object> getReplyCheckRsModifyAvailable = articleService.getReplyCheckRsModifyAvailable(id, loginedMemberId);
		
		 if ( Util.isSuccess(getReplyCheckRsModifyAvailable) == false ) { 
			 return "html:<script> alert('" + getReplyCheckRsModifyAvailable.get("msg") + "'); history.back(); </script>"; }
		
		 
		 
		 articleService.modifyArticleReply(id, body); 
		 
		 
		 
		int articleId = 0; // 게시물 id

		if (!Util.empty(req, "id") && Util.isNum(req, "id")) {
			articleId = Util.getInt(req, "id");
		}

		
		String redirectUri = Util.getString(req, "redirectUri", "list");
		
		redirectUri = Util.getNewUri(redirectUri, "lastWorkArticleReplyId", id + "");
		
		
		

		return "html:<script> alert('" + articleId + "번 게시물의 " + id + "댓글을 수정하셨습니다.'); location.replace('" + redirectUri + "'); </script>";
	}

	
	private String doActionDoWriteReply() {

		if (Util.empty(req, "articleId")) {
			return "html:articleId를 입력해주세요.";
		}

		if (Util.isNum(req, "articleId") == false) { // false라면. 정수가 아니라면. false를 빠트렸었음.
			return "html:articleId를 정수로 입력해주세요.";
		}

		int articleId = Util.getInt(req, "articleId");

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");
		String body = req.getParameter("body");

		String redirectUri = Util.getString(req, "redirectUri");
		System.out.println(redirectUri);

		int id = articleService.writeArticleReply(body, articleId, loginedMemberId); // 댓글 저장하는 메서드 (id는 댓글의 id를 의미)

		redirectUri = Util.getNewUri(redirectUri, "lastWorkArticleReplyId", id + "");

		return "html:<script> alert('" + articleId + "번 게시물 댓글을 작성하셨습니다.'); location.replace('" + redirectUri
				+ "'); </script>";
	}

	private String doActionDoModify() {

		if (Util.empty(req, "id")) {
			return "html:id를 입력해주세요.";
		}

		if (Util.isNum(req, "id") == false) { // false라면. 정수가 아니라면. false를 빠트렸었음.
			return "html:id를 정수로 입력해주세요.";
		}

		int id = Util.getInt(req, "id");

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		Map<String, Object> getCheckRsModifyAvailableRs = articleService.getCheckRsModifyAvailable(id, loginedMemberId);

		if (Util.isSuccess(getCheckRsModifyAvailableRs) == false) {
			return "html:<script> alert('" + getCheckRsModifyAvailableRs.get("msg") + "'); history.back(); </script>";
		}

		String title = req.getParameter("title");
		String body = req.getParameter("body");
		int cateItemId = Util.getInt(req, "cateItemId");
		int displayStatus = Util.getInt(req, "displayStatus");

		articleService.modifyArticle(id, cateItemId, displayStatus, title, body);

		return "html:<script> alert('" + id + "번 게시물이 수정되었습니다.'); location.replace('detail?id=" + id + "'); </script>";

	}

	private String doActionModify() {

		int id = 0;

		if (Util.empty(req, "id")) {
			return "html:id를 입력해주세요.";
		}
		if (Util.isNum(req, "id") == false) {
			return "html:id를 정수로 입력해주세요.";
		}

		id = Util.getInt(req, "id");

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		if (!Util.empty(req, "id") && Util.isNum(req, "id")) { // 게시물 번호

			id = Util.getInt(req, "id");

		}

		Article article = articleService.getForPrintArticle(id, loginedMemberId);

		req.setAttribute("article", article);
		return "article/modify.jsp";
	}

	private String doActionDoWrite() {
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		// int loginedMemberId = Util.getInt(req, "memberId");
		int cateItemId = Util.getInt(req, "cateItemId");
		int displayStatus = Util.getInt(req, "displayStatus");

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		int id = articleService.write(cateItemId, displayStatus, title, body, loginedMemberId);

		return "html:<script> alert('" + id + "번 게시물이 생성되었습니다.'); location.replace('list'); </script>";
		// 자바스크립트로써 페이지 이동 명령. 꼭 이렇게해야 한다. 그러지 않으면 뒤로가기?로 또 글을 생성하게 된다. history에서 남기지 말고
		// 이동해야 한다. 그래서 replace!
	}

	// 게시물 작성 신청 폼을 한번 보여주는 정도(용도)의 메서드.
	private String doActionWrite() {

		return "article/write.jsp";
	}

	private String doActionDoDelete() {

		if (Util.empty(req, "id")) {
			return "html:id를 입력해주세요.";
		}

		if (Util.isNum(req, "id") == false) { // false라면. 정수가 아니라면. false를 빠트렸었음.
			return "html:id를 정수로 입력해주세요.";
		}

		int id = Util.getInt(req, "id");

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		Map<String, Object> getCheckRsDeleteAvailableRs = articleService.getCheckRsDeleteAvailable(id, loginedMemberId);

		if (Util.isSuccess(getCheckRsDeleteAvailableRs) == false) {
			return "html:<script> alert('" + getCheckRsDeleteAvailableRs.get("msg") + "'); history.back(); </script>";
		}

		articleService.deleteArticle(id);

		String redirectUri = Util.getString(req, "redirectUri", "list");

		return "html:<script> alert('" + id + "번 게시물이 삭제되었습니다.'); location.replace('" + redirectUri + "'); </script>";
	}

	private String doActionAboutMe() {
		return "article/aboutMe.jsp";
	}

	private String doActionEditor() {

		return "article/editor.jsp";
	}

	private String doActionDetail() {
		if (Util.empty(req, "id")) {
			return "html:id를 입력해주세요.";
		}
		if (Util.isNum(req, "id") == false) {
			return "html:id를 정수로 입력해주세요.";
		}

		int id = Util.getInt(req, "id");

		int cateItemId = 0;

		if (!Util.empty(req, "cateItemId") && Util.isNum(req, "cateItemId")) { // cateItemId가 없지 않고 숫자가 맞으면

			cateItemId = Util.getInt(req, "cateItemId");

		}

		articleService.increaseHit(id);

		// 게시물 작성자만 삭제버튼 보이게 하기 위한 현재 접속자 확인용
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		// 이 article은 그냥(평범한) article이 아니다.
		Article article = articleService.getForPrintArticle(id, loginedMemberId); // sql 쿼리에 작성해놓은 정보들만이 아닌 부가적으로 추가한
																					// 자잘한 (항목 추가한)작성자 등
		// 항목 모두 불러오는 메서드 네임
		int beforeId = articleService.getForPageMoveBeforeArticle(id, cateItemId);
		int afterId = articleService.getForPageMoveAfterArticle(id, cateItemId);
		CateItem cateItem = articleService.getCateItem(article.getCateItemId());
		Member member = memberService.getMemberFromMemberId(article.getMemberId());
		req.setAttribute("member", member);
		req.setAttribute("beforeId", beforeId);
		req.setAttribute("afterId", afterId);
		req.setAttribute("article", article);
		req.setAttribute("cateItem", cateItem);
		req.setAttribute("cateItemId", cateItemId);
		// getArticleRepliesForDetail(id);
		List<ArticleReply> articleReplies = articleService.getForPrintArticleReplies(id, loginedMemberId);
		req.setAttribute("articleReplies", articleReplies);

		// return "article/detail.jsp";

		// 작성자명이 게시물 상세보기에서 잠깐 필요한데 필드에까지 추가할 필요가 없다. 그래서 extra__를 이용해서 사용한다. sql에는 영향을
		// 주지 않는 듯. 확인해보기.
		return "article/detail.jsp";
		// 다시 한번 설명! 자질구래한 것들을 모아놓는 것이 dto의 extra 변수이다.
	}

	private String doActionList() {
		
		long startTime = System.nanoTime();   // 최초에 한번 

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
		int totalCount = articleService.getForPrintListArticlesCount(cateItemId, searchKeywordType,
				searchKeywordTypeBody, searchKeyword);
		int totalPage = (int) Math.ceil(totalCount / (double) itemsInAPage);

		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("cPage", page);

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		List<Article> articles = articleService.getForPrintListArticles(loginedMemberId, page, cateItemId, itemsInAPage,
				searchKeywordType, searchKeywordTypeBody, searchKeyword);
		req.setAttribute("articles", articles);
		
		
		long endTime = System.nanoTime();
		long estimatedTime = endTime - startTime;
		// nano seconds to seconds 
		double seconds = estimatedTime / 1000000000.0;
		System.out.println("seconds : " + seconds );
		
		
		return "article/list.jsp";
	}

	@Override // 부모인 controller의 추상 메서드를 상속받아 controllerName을 임의로 정해서 return해준다.
	public String getControllerName() {
		return "article";
	}
}
