package com.sbs.java.blog.controller;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.dto.Article;
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
		case "doWrite":
			return doActionDoWrite(req, resp);
		case "aboutMe":
			return doActionAboutMe(req, resp);
		case "listWrite":
			return doActionListWrite(req, resp);
		case "modify":
			return doActionModify(req, resp);
		case "delete":
			return doActionDelete(req, resp);
		}

		return "";
	}

	private String doActionDelete(HttpServletRequest req, HttpServletResponse resp) {
		
		int id = 0;

		if (!Util.empty(req, "id") && Util.isNum(req, "id")) { // cateItemId가 없지 않고 숫자가 맞으면

			id = Util.getInt(req, "id");

		}

		articleService.articleDelete(id);
				
		
		
		
		
		return "article/delete.jsp";
	}

	private String doActionModify(HttpServletRequest req, HttpServletResponse resp) {
		
		int id = 0;

		if (!Util.empty(req, "id") && Util.isNum(req, "id")) { // cateItemId가 없지 않고 숫자가 맞으면

			id = Util.getInt(req, "id");

		}

		
		
		
		int cateItemId = 0;

		if (!Util.empty(req, "cateItemId") && Util.isNum(req, "cateItemId")) { // cateItemId가 없지 않고 숫자가 맞으면

			cateItemId = Util.getInt(req, "cateItemId");

		}
		
		
		String title = ""; // keywordType이 더 중요하니까 searchkeyword보다 위에 써주는게 낫다.

		if (!Util.empty(req, "title")) { // cateItemId가 없지 않고 숫자가 맞으면

			title = Util.getString(req, "title");

		}

		String body = "";

		if (!Util.empty(req, "body")) { 

			body = Util.getString(req, "body");

		}
		
		if ( body != null ) {
			articleService.ArticleModify(id, cateItemId, title, body);
			req.setAttribute("id", id);
			req.setAttribute("cateItemId", cateItemId);
			req.setAttribute("title", title);
			req.setAttribute("body", body);
		}
		
		
		
		

		return "article/modify.jsp";
	}

	private String doActionListWrite(HttpServletRequest req, HttpServletResponse resp) {

		// 샘이 만들어주신 검색 기능

		int displayStatus = 0;

		if (!Util.empty(req, "displayStatus") && Util.isNum(req, "displayStatus")) { // cateItemId가 없지 않고 숫자가 맞으면

			displayStatus = Util.getInt(req, "displayStatus");

		}

		int cateItemId = 0;

		if (!Util.empty(req, "cateItemId") && Util.isNum(req, "cateItemId")) { // cateItemId가 없지 않고 숫자가 맞으면

			cateItemId = Util.getInt(req, "cateItemId");

		}

		String title = ""; // keywordType이 더 중요하니까 searchkeyword보다 위에 써주는게 낫다.

		if (!Util.empty(req, "title")) { // cateItemId가 없지 않고 숫자가 맞으면

			title = Util.getString(req, "title");

		}

		String body = "";

		if (!Util.empty(req, "body")) { // cateItemId가 없지 않고 숫자가 맞으면

			body = Util.getString(req, "body");

		}
		req.setAttribute("displayStatus", displayStatus);
		req.setAttribute("cateItemId", cateItemId);
		req.setAttribute("title", title);
		req.setAttribute("body", body);

		articleService.doArticleWrite(displayStatus, cateItemId, title, body);

		return "article/listWrite.jsp";
	}

	private String doActionAboutMe(HttpServletRequest req, HttpServletResponse resp) {
		return "article/aboutMe.jsp";
	}

	private String doActionDoWrite(HttpServletRequest req, HttpServletResponse resp) {
		/*
		 * String title = req.getParameter("title"); String body =
		 * req.getParameter("body"); //★★★★★INTSERT 쿼리에 맞춰서 카테고리 번호 등 값을 넘겨주고 해당 카테고리에
		 * 글을 저장할 수 있도록 추가 구현해보기! /*String regDate = "NOW()"; String updateDate =
		 * "NOW()"; int cateItemId = Integer.parseInt(req.getParameter("cateItemId"));
		 */

		/*
		 * articleService.doWriteArticle(title, body); req.setAttribute("title", title);
		 * req.setAttribute("body", body);
		 * 
		 * 
		 * return "article/doWrite.jsp";
		 */
		return "article/doWrite.jsp";
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
		

		// 이 article은 그냥(평범한) article이 아니다.
		Article article = articleService.getForPrintArticle(id); // sql 쿼리에 작성해놓은 정보들만이 아닌 부가적으로 추가한 자잘한 (항목 추가한)작성자 등
																	// 항목 모두 불러오는 메서드 네임
		req.setAttribute("article", article);
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

		String searchKeyword = "";

		if (!Util.empty(req, "searchKeyword")) { // cateItemId가 없지 않고 숫자가 맞으면

			searchKeyword = Util.getString(req, "searchKeyword");

		}

		// 나중에 코드 지워버리기 / 카테고리별 게시물 리스트 //2020-07-03 영상에서 검색어 기능 추가하면서 없애셨음.
		/*
		 * int cateItemId = 0; int page = 1; if ( req.getParameter("cateItemId") != null
		 * ) { cateItemId = Integer.parseInt(req.getParameter("cateItemId"));
		 * 
		 * } if ( req.getParameter("page") != null ) { page =
		 * Integer.parseInt(req.getParameter("page")); }
		 */

		int itemsInAPage = 10; // 게시물 리스트에 보여줄 게시물 개수
		int totalCount = articleService.getForPrintListArticlesCount(cateItemId, searchKeywordType, searchKeyword);
		int totalPage = (int) Math.ceil(totalCount / (double) itemsInAPage);
		

		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("page", page);

		List<Article> articles = articleService.getForPrintListArticles(page, cateItemId, itemsInAPage,
				searchKeywordType, searchKeyword);
		req.setAttribute("articles", articles);

		// 혜련 검색 기능 (나중에 보완하거나 없애거나하기)
		/*
		 * String search = req.getParameter("search"); if ( search != null ) { articles
		 * = articleService.getSearchContents(search); req.setAttribute("articles",
		 * articles); }
		 * 
		 * req.setAttribute("search", search );
		 */

		// 최신 10개 게시물 리스트
		/*
		 * if ( search == null ) { if ( cateItemId == 0 ) { articles =
		 * articleService.getForPrintListNewArticles(); req.setAttribute("articles",
		 * articles);
		 * 
		 * } }
		 * 
		 * req.setAttribute("cateItemId", cateItemId);
		 */

		return "article/list.jsp";
	}
}
