package com.example.presentation.base;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u000f\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0004J\u0011\u0010\u000f\u001a\u00020\u0010*\u00028\u0000H\u0016\u00a2\u0006\u0002\u0010\u000bR\u001c\u0010\u0007\u001a\u00028\u0000X\u0086.\u00a2\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0016"}, d2 = {"Lcom/example/presentation/base/BaseActivity;", "VDB", "Landroidx/databinding/ViewDataBinding;", "Landroidx/appcompat/app/AppCompatActivity;", "layoutRes", "", "(I)V", "binding", "getBinding", "()Landroidx/databinding/ViewDataBinding;", "setBinding", "(Landroidx/databinding/ViewDataBinding;)V", "Landroidx/databinding/ViewDataBinding;", "getLayoutRes", "()I", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "showToast", "msg", "", "presentation_debug"})
public class BaseActivity<VDB extends androidx.databinding.ViewDataBinding> extends androidx.appcompat.app.AppCompatActivity {
    private final int layoutRes = 0;
    public VDB binding;
    
    public BaseActivity(@androidx.annotation.LayoutRes()
    int layoutRes) {
        super();
    }
    
    public final int getLayoutRes() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final VDB getBinding() {
        return null;
    }
    
    public final void setBinding(@org.jetbrains.annotations.NotNull()
    VDB p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    public void onCreate(@org.jetbrains.annotations.NotNull()
    VDB $this$onCreate) {
    }
    
    protected final void showToast(@org.jetbrains.annotations.NotNull()
    java.lang.String msg) {
    }
}