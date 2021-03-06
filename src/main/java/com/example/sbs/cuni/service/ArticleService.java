package com.example.sbs.cuni.service;

import java.util.List;
import java.util.Map;

import com.example.sbs.cuni.dto.Article;
import com.example.sbs.cuni.dto.Board;

public interface ArticleService {
	List<Article> getArticles();

	Article getArticle(int id);

	Map<String, Object> deleteArticle(int id);

	List<Article> getArticles(String boardCode);

	Board getBoard(String boardCode);

	Map<String, Object> writeArticle(Map<String, Object> param);

	Board getBoard(int boardId);

	Map<String, Object> modifyArticle(Map<String, Object> param);

	Map<String, Object> getArticleModifyAbailable(int id, int loginedMemberId);

	Map<String, Object> getArticleDeleteAbailable(int id, int loginedMemberId);
}
