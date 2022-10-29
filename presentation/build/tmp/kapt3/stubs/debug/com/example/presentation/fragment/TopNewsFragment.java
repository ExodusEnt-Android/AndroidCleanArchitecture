package com.example.presentation.fragment;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u0000 \u001f2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001fB\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0019H\u0002J\b\u0010\u001b\u001a\u00020\u0019H\u0002J\b\u0010\u001c\u001a\u00020\u0019H\u0002J\b\u0010\u001d\u001a\u00020\u0019H\u0002J\f\u0010\u001e\u001a\u00020\u0019*\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/example/presentation/fragment/TopNewsFragment;", "Lcom/example/presentation/base/BaseFragment;", "Lcom/example/presentation/databinding/FragmentTopNewsBinding;", "()V", "isAlreadyInitialized", "", "navController", "Landroidx/navigation/NavController;", "navHost", "Landroidx/navigation/fragment/NavHostFragment;", "page", "", "rcyScrollLState", "Landroid/os/Parcelable;", "topNewsList", "", "Lcom/example/presentation/model/Article;", "topNewsListAdapter", "Lcom/example/presentation/adapter/TopNewsListAdapter;", "getTopNewsListAdapter", "()Lcom/example/presentation/adapter/TopNewsListAdapter;", "setTopNewsListAdapter", "(Lcom/example/presentation/adapter/TopNewsListAdapter;)V", "totalResult", "getTopNewsList", "", "initSet", "logout", "setListenerEvent", "setToolbar", "onCreateView", "Companion", "presentation_debug"})
public final class TopNewsFragment extends com.example.presentation.base.BaseFragment<com.example.presentation.databinding.FragmentTopNewsBinding> {
    public com.example.presentation.adapter.TopNewsListAdapter topNewsListAdapter;
    private int totalResult = -1;
    private int page = 1;
    private android.os.Parcelable rcyScrollLState;
    private boolean isAlreadyInitialized = false;
    private final java.util.List<com.example.presentation.model.Article> topNewsList = null;
    private androidx.navigation.NavController navController;
    private androidx.navigation.fragment.NavHostFragment navHost;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.presentation.fragment.TopNewsFragment.Companion Companion = null;
    public static final int DEFAULT_LIST_SIZE = -1;
    
    public TopNewsFragment() {
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
    
    private final void initSet() {
    }
    
    private final void setListenerEvent() {
    }
    
    private final void logout() {
    }
    
    private final void setToolbar() {
    }
    
    private final void getTopNewsList() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/example/presentation/fragment/TopNewsFragment$Companion;", "", "()V", "DEFAULT_LIST_SIZE", "", "presentation_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}