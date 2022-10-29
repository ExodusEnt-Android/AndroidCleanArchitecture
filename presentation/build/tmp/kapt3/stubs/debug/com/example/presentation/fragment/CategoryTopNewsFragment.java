package com.example.presentation.fragment;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 %2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001%B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u001bH\u0002J\"\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u00072\u0006\u0010!\u001a\u00020\rH\u0016J\b\u0010\"\u001a\u00020\u001bH\u0002J\b\u0010#\u001a\u00020\u001bH\u0002J\f\u0010$\u001a\u00020\u001b*\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lcom/example/presentation/fragment/CategoryTopNewsFragment;", "Lcom/example/presentation/base/BaseFragment;", "Lcom/example/presentation/databinding/FragmentTopNewsBinding;", "()V", "categoryString", "", "isAlreadyInitialized", "", "navController", "Landroidx/navigation/NavController;", "navHost", "Landroidx/navigation/fragment/NavHostFragment;", "page", "", "rcyScrollLState", "Landroid/os/Parcelable;", "topNewsList", "", "Lcom/example/presentation/model/Article;", "topNewsListAdapter", "Lcom/example/presentation/adapter/TopNewsListAdapter;", "getTopNewsListAdapter", "()Lcom/example/presentation/adapter/TopNewsListAdapter;", "setTopNewsListAdapter", "(Lcom/example/presentation/adapter/TopNewsListAdapter;)V", "totalResult", "getTopNewsList", "", "initSet", "onCreateAnimation", "Landroid/view/animation/Animation;", "transit", "enter", "nextAnim", "setListenerEvent", "setToolbar", "onCreateView", "Companion", "presentation_debug"})
public final class CategoryTopNewsFragment extends com.example.presentation.base.BaseFragment<com.example.presentation.databinding.FragmentTopNewsBinding> {
    private androidx.navigation.NavController navController;
    private androidx.navigation.fragment.NavHostFragment navHost;
    private int totalResult = -1;
    private int page = 1;
    private android.os.Parcelable rcyScrollLState;
    public com.example.presentation.adapter.TopNewsListAdapter topNewsListAdapter;
    private final java.util.List<com.example.presentation.model.Article> topNewsList = null;
    private boolean isAlreadyInitialized = false;
    private java.lang.String categoryString = "";
    @org.jetbrains.annotations.NotNull()
    public static final com.example.presentation.fragment.CategoryTopNewsFragment.Companion Companion = null;
    public static final int DEFAULT_LIST_SIZE = -1;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TOTAL_RESULT = "totalResult";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PAGE = "page";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ARTICLE_LIST = "article_list";
    
    public CategoryTopNewsFragment() {
        super(0);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.presentation.adapter.TopNewsListAdapter getTopNewsListAdapter() {
        return null;
    }
    
    public final void setTopNewsListAdapter(@org.jetbrains.annotations.NotNull()
    com.example.presentation.adapter.TopNewsListAdapter p0) {
    }
    
    @java.lang.Override()
    public void onCreateView(@org.jetbrains.annotations.NotNull()
    com.example.presentation.databinding.FragmentTopNewsBinding $this$onCreateView) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.animation.Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return null;
    }
    
    private final void setListenerEvent() {
    }
    
    private final void setToolbar() {
    }
    
    private final void initSet() {
    }
    
    private final void getTopNewsList() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/example/presentation/fragment/CategoryTopNewsFragment$Companion;", "", "()V", "ARTICLE_LIST", "", "DEFAULT_LIST_SIZE", "", "PAGE", "TOTAL_RESULT", "presentation_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}