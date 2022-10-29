package com.example.presentation.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2 = {"Lcom/example/presentation/util/PreferenceManager;", "", "()V", "Companion", "presentation_debug"})
public final class PreferenceManager {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.presentation.util.PreferenceManager.Companion Companion = null;
    
    public PreferenceManager() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0001J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u0005J\u0016\u0010\r\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u001e\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0001\u00a8\u0006\u0010"}, d2 = {"Lcom/example/presentation/util/PreferenceManager$Companion;", "", "()V", "getPreference", "context", "Landroid/content/Context;", "key", "", "defaultValue", "isPreferenceExist", "", "removeAllPreference", "", "removePreference", "setPreference", "value", "presentation_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final void setPreference(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        java.lang.String key, @org.jetbrains.annotations.NotNull()
        java.lang.Object value) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Object getPreference(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        java.lang.String key, @org.jetbrains.annotations.Nullable()
        java.lang.Object defaultValue) {
            return null;
        }
        
        public final void removePreference(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        java.lang.String key) {
        }
        
        public final void removeAllPreference(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
        }
        
        public final boolean isPreferenceExist(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        java.lang.String key) {
            return false;
        }
    }
}