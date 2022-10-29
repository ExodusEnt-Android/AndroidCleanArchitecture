package com.example.presentation.model;

import java.lang.System;

@kotlinx.parcelize.Parcelize()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B.\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\u0015\b\u0002\u0010\u0007\u001a\u000f\u0012\u0004\u0012\u00028\u0000\u0018\u00010\b\u00a2\u0006\u0002\b\t\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0011\u001a\u00020\u0004H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0006H\u00c6\u0003J\u0016\u0010\u0013\u001a\u000f\u0012\u0004\u0012\u00028\u0000\u0018\u00010\b\u00a2\u0006\u0002\b\tH\u00c6\u0003J:\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0015\b\u0002\u0010\u0007\u001a\u000f\u0012\u0004\u0012\u00028\u0000\u0018\u00010\b\u00a2\u0006\u0002\b\tH\u00c6\u0001J\t\u0010\u0015\u001a\u00020\u0006H\u00d6\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\u0006H\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u0004H\u00d6\u0001J\u0019\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0006H\u00d6\u0001R\u001e\u0010\u0007\u001a\u000f\u0012\u0004\u0012\u00028\u0000\u0018\u00010\b\u00a2\u0006\u0002\b\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006!"}, d2 = {"Lcom/example/presentation/model/BaseDataModel;", "T", "Landroid/os/Parcelable;", "status", "", "totalResults", "", "articles", "", "Lkotlinx/parcelize/RawValue;", "(Ljava/lang/String;ILjava/util/List;)V", "getArticles", "()Ljava/util/List;", "getStatus", "()Ljava/lang/String;", "getTotalResults", "()I", "component1", "component2", "component3", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "presentation_debug"})
public final class BaseDataModel<T extends java.lang.Object> implements android.os.Parcelable {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String status = null;
    private final int totalResults = 0;
    @org.jetbrains.annotations.Nullable()
    private final java.util.List<T> articles = null;
    public static final android.os.Parcelable.Creator<com.example.presentation.model.BaseDataModel> CREATOR = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.presentation.model.BaseDataModel<T> copy(@org.jetbrains.annotations.NotNull()
    java.lang.String status, int totalResults, @org.jetbrains.annotations.Nullable()
    java.util.List<? extends T> articles) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    public BaseDataModel(@org.jetbrains.annotations.NotNull()
    java.lang.String status, int totalResults, @org.jetbrains.annotations.Nullable()
    java.util.List<? extends T> articles) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStatus() {
        return null;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final int getTotalResults() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<T> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<T> getArticles() {
        return null;
    }
    
    @java.lang.Override()
    public int describeContents() {
        return 0;
    }
    
    @java.lang.Override()
    public void writeToParcel(@org.jetbrains.annotations.NotNull()
    android.os.Parcel parcel, int flags) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
    public static final class Creator implements android.os.Parcelable.Creator<com.example.presentation.model.BaseDataModel> {
        
        public Creator() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final com.example.presentation.model.BaseDataModel<?> createFromParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel in) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final com.example.presentation.model.BaseDataModel<?>[] newArray(int size) {
            return null;
        }
    }
}