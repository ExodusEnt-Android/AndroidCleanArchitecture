package com.example.presentation.fragment;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u001b2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001bB\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0011H\u0002J\u0012\u0010\u0013\u001a\u00020\u00112\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0015H\u0016J\b\u0010\u0018\u001a\u00020\u0011H\u0002J\b\u0010\u0019\u001a\u00020\u0011H\u0002J\f\u0010\u001a\u001a\u00020\u0011*\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001c"}, d2 = {"Lcom/example/presentation/fragment/SavedFragment;", "Lcom/example/presentation/base/BaseFragment;", "Lcom/example/presentation/databinding/FragmentSavedBinding;", "()V", "navController", "Landroidx/navigation/NavController;", "navHost", "Landroidx/navigation/fragment/NavHostFragment;", "rcyScrollLState", "Landroid/os/Parcelable;", "topNewsListAdapter", "Lcom/example/presentation/adapter/TopNewsListAdapter;", "getTopNewsListAdapter", "()Lcom/example/presentation/adapter/TopNewsListAdapter;", "setTopNewsListAdapter", "(Lcom/example/presentation/adapter/TopNewsListAdapter;)V", "getSavedNewsList", "", "initSet", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSaveInstanceState", "outState", "setListenerEvent", "setToolbar", "onCreateView", "Companion", "presentation_debug"})
public final class SavedFragment extends com.example.presentation.base.BaseFragment<com.example.presentation.databinding.FragmentSavedBinding> {
    private androidx.navigation.NavController navController;
    private androidx.navigation.fragment.NavHostFragment navHost;
    private android.os.Parcelable rcyScrollLState;
    public com.example.presentation.adapter.TopNewsListAdapter topNewsListAdapter;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.presentation.fragment.SavedFragment.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PARAM_RCY_SCROLL_STATE = "param_rcy_scroll_state";
    
    public SavedFragment() {
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
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onCreateView(@org.jetbrains.annotations.NotNull()
    com.example.presentation.databinding.FragmentSavedBinding $this$onCreateView) {
    }
    
    private final void initSet() {
    }
    
    private final void setListenerEvent() {
    }
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    private final void getSavedNewsList() {
    }
    
    private final void setToolbar() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/example/presentation/fragment/SavedFragment$Companion;", "", "()V", "PARAM_RCY_SCROLL_STATE", "", "presentation_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}