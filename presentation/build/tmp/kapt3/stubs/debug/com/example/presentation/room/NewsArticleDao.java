package com.example.presentation.room;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\'J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\'J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\nH\'\u00a8\u0006\r"}, d2 = {"Lcom/example/presentation/room/NewsArticleDao;", "", "deleteSavedArticle", "", "publishedAt", "", "title", "url", "loadSavedNewsArticles", "", "Lcom/example/presentation/model/Article;", "setSavedArticle", "article", "presentation_debug"})
public abstract interface NewsArticleDao {
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void setSavedArticle(@org.jetbrains.annotations.NotNull()
    com.example.presentation.model.Article article);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM newsArticleTable")
    public abstract java.util.List<com.example.presentation.model.Article> loadSavedNewsArticles();
    
    @androidx.room.Query(value = "DELETE FROM newsArticleTable WHERE publishedAt = :publishedAt AND title = :title AND url = :url")
    public abstract void deleteSavedArticle(@org.jetbrains.annotations.NotNull()
    java.lang.String publishedAt, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String url);
}