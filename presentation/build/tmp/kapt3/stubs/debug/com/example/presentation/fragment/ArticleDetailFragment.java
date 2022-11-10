package com.example.presentation.fragment;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0011H\u0002J\"\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0016H\u0016J\b\u0010\u001a\u001a\u00020\u0011H\u0002J\u0010\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u0018H\u0002J\f\u0010\u001d\u001a\u00020\u0011*\u00020\u0002H\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r\u00a8\u0006\u001e"}, d2 = {"Lcom/example/presentation/fragment/ArticleDetailFragment;", "Lcom/example/presentation/base/BaseFragment;", "Lcom/example/presentation/databinding/FragmentArticleDetailBinding;", "()V", "article", "Lcom/example/presentation/model/Article;", "navController", "Landroidx/navigation/NavController;", "navHost", "Landroidx/navigation/fragment/NavHostFragment;", "topNewsRepository", "Lcom/example/presentation/repository/TopNewsRepository;", "getTopNewsRepository", "()Lcom/example/presentation/repository/TopNewsRepository;", "topNewsRepository$delegate", "Lkotlin/Lazy;", "checkSavedArticle", "", "initSet", "onCreateAnimation", "Landroid/view/animation/Animation;", "transit", "", "enter", "", "nextAnim", "setListenerEvent", "setSaveIconVisible", "isSaveStatus", "onCreateView", "presentation_debug"})
public final class ArticleDetailFragment extends com.example.presentation.base.BaseFragment<com.example.presentation.databinding.FragmentArticleDetailBinding> {
    private com.example.presentation.model.Article article;
    private androidx.navigation.NavController navController;
    private androidx.navigation.fragment.NavHostFragment navHost;
    private final kotlin.Lazy topNewsRepository$delegate = null;
    
    public ArticleDetailFragment() {
        super(0);
    }
    
    @java.lang.Override()
    public void onCreateView(@org.jetbrains.annotations.NotNull()
    com.example.presentation.databinding.FragmentArticleDetailBinding $this$onCreateView) {
    }
    
    private final com.example.presentation.repository.TopNewsRepository getTopNewsRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.animation.Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return null;
    }
    
    private final void initSet() {
    }
    
    private final void checkSavedArticle() {
    }
    
    private final void setSaveIconVisible(boolean isSaveStatus) {
    }
    
    private final void setListenerEvent() {
    }
}