package com.example.presentation.adapter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00162\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0016\u0017B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\fH\u0016J\u0018\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\fH\u0016J\u000e\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u0006R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n\u00a8\u0006\u0018"}, d2 = {"Lcom/example/presentation/adapter/TopNewsListAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/example/presentation/model/Article;", "Lcom/example/presentation/viewholder/TopNewArticleViewHolder;", "()V", "itemClickListener", "Lcom/example/presentation/adapter/TopNewsListAdapter$ItemClickListener;", "getItemClickListener", "()Lcom/example/presentation/adapter/TopNewsListAdapter$ItemClickListener;", "setItemClickListener", "(Lcom/example/presentation/adapter/TopNewsListAdapter$ItemClickListener;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setOnTopNewsItemClickListener", "Companion", "ItemClickListener", "presentation_debug"})
public final class TopNewsListAdapter extends androidx.recyclerview.widget.ListAdapter<com.example.presentation.model.Article, com.example.presentation.viewholder.TopNewArticleViewHolder> {
    public com.example.presentation.adapter.TopNewsListAdapter.ItemClickListener itemClickListener;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.presentation.adapter.TopNewsListAdapter.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.recyclerview.widget.DiffUtil.ItemCallback<com.example.presentation.model.Article> diffUtil = null;
    
    public TopNewsListAdapter() {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.presentation.adapter.TopNewsListAdapter.ItemClickListener getItemClickListener() {
        return null;
    }
    
    public final void setItemClickListener(@org.jetbrains.annotations.NotNull()
    com.example.presentation.adapter.TopNewsListAdapter.ItemClickListener p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.example.presentation.viewholder.TopNewArticleViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    public final void setOnTopNewsItemClickListener(@org.jetbrains.annotations.NotNull()
    com.example.presentation.adapter.TopNewsListAdapter.ItemClickListener itemClickListener) {
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.example.presentation.viewholder.TopNewArticleViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lcom/example/presentation/adapter/TopNewsListAdapter$ItemClickListener;", "", "onTopNewItemClick", "", "article", "Lcom/example/presentation/model/Article;", "presentation_debug"})
    public static abstract interface ItemClickListener {
        
        public abstract void onTopNewItemClick(@org.jetbrains.annotations.NotNull()
        com.example.presentation.model.Article article);
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/example/presentation/adapter/TopNewsListAdapter$Companion;", "", "()V", "diffUtil", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/example/presentation/model/Article;", "getDiffUtil", "()Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "presentation_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.recyclerview.widget.DiffUtil.ItemCallback<com.example.presentation.model.Article> getDiffUtil() {
            return null;
        }
    }
}