package com.example.sbs.cuni.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sbs.cuni.dto.Article;
import com.example.sbs.cuni.dto.Board;
import com.example.sbs.cuni.service.ArticleService;

@Controller
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("article/list")
	public String showList(Model model, String boardCode) {
		Board board = articleService.getBoard(boardCode);
		List<Article> articles = articleService.getArticles(boardCode);
		
		model.addAttribute("articles", articles);
		model.addAttribute("board", board);
		
		return "article/list";
	}
	
	@RequestMapping("article/detail")
	public String showDetail(Model model, int id) {
		Article article = articleService.getArticle(id);
		
		model.addAttribute("article", article);
		
		return "article/detail";
	}
	
	@RequestMapping("article/modify")
	public String showModify(Model model, int id, HttpServletRequest request) {
		int loginedMemberId = (int) request.getAttribute("loginedMemberId");
		
		Map<String, Object> articleModifyAvailableRs = articleService.getArticleModifyAbailable(id, loginedMemberId);
		
		if (((String) articleModifyAvailableRs.get("resultCode")).startsWith("F-")) {
			model.addAttribute("alertMsg", articleModifyAvailableRs.get("msg"));
			model.addAttribute("historyBack", true);
			
			return "common/redirct";
		}
		
		Article article = articleService.getArticle(id);
		
		model.addAttribute("article", article);
		
		return "article/modify";
	}
	
	@RequestMapping("article/doModify")
	public String doModify(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request) {
		int loginedMemberId = (int) request.getAttribute("loginedMemberId");
		
		int id = Integer.parseInt((String) param.get("id"));
		
		Map<String, Object> articleModifyAvailableRs = articleService.getArticleModifyAbailable(id, loginedMemberId);
		
		if (((String) articleModifyAvailableRs.get("resultCode")).startsWith("F-")) {
			model.addAttribute("alertMsg", articleModifyAvailableRs.get("msg"));
			model.addAttribute("historyBack", true);
			
			return "common/redirct";
		}
		
		Map<String, Object> rs = articleService.modifyArticle(param);
		
		
		String msg = (String) rs.get("msg");
		String redirectUrl = "/article/detail?id=" + id;
		
		model.addAttribute("alertMsg", msg);
		model.addAttribute("locationReplace", redirectUrl);
		
		return "common/redirct";
	}
	
	@RequestMapping("article/doDelete")
	public String doDelete(Model model, int id, HttpServletRequest request) {
		int loginedMemberId = (int) request.getAttribute("loginedMemberId");
		
		Map<String, Object> articleDeleteAvailableRs = articleService.getArticleDeleteAbailable(id, loginedMemberId);
		
		if (((String) articleDeleteAvailableRs.get("resultCode")).startsWith("F-")) {
			model.addAttribute("alertMsg", articleDeleteAvailableRs.get("msg"));
			model.addAttribute("historyBack", true);
			
			return "common/redirct";
		}
		
		Article article = articleService.getArticle(id);
		
		int boardId = article.getBoardId();
		
		Board board = articleService.getBoard(boardId);
		
		String boardCode = board.getCode();
		
		Map<String, Object> rs = articleService.deleteArticle(id);
		
		String msg = (String) rs.get("msg");
		String redirectUrl = "/article/list?boardCode=" + boardCode;
		
		model.addAttribute("alertMsg", msg);
		model.addAttribute("locationReplace", redirectUrl);
		
		return "common/redirct";
	}
	
	@RequestMapping("article/write")
	public String showWrite(Model model, String boardCode, HttpServletRequest request) {
		
		Board board = articleService.getBoard(boardCode);
		
		model.addAttribute("board", board);
		
		return "article/write";
	}
	
	@RequestMapping("article/doWrite")
	public String doWrite(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request) {
		int loginedMemberId = (int) request.getAttribute("loginedMemberId");
		
		param.put("memberId", loginedMemberId);
		
		Map<String, Object> rs = articleService.writeArticle(param);
		
		int boardId = Integer.parseInt((String) param.get("boardId"));
		
		Board board = articleService.getBoard(boardId);
		
		String msg = (String) rs.get("msg");
		String redirectUrl = "/article/list?boardCode=" + board.getCode();
		
		model.addAttribute("alertMsg", msg);
		model.addAttribute("locationReplace", redirectUrl);
		
		return "common/redirct";
	}
	
}
