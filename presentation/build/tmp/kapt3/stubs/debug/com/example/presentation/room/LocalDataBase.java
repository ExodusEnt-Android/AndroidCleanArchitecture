package com.example.presentation.room;

import java.lang.System;

@androidx.room.Database(entities = {com.example.presentation.model.Article.class}, version = 5, exportSchema = false)
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u00a8\u0006\u0006"}, d2 = {"Lcom/example/presentation/room/LocalDataBase;", "Landroidx/room/RoomDatabase;", "()V", "getNewsArticleDao", "Lcom/example/presentation/room/NewsArticleDao;", "Companion", "presentation_debug"})
public abstract class LocalDataBase extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.presentation.room.LocalDataBase.Companion Companion = null;
    private static com.example.presentation.room.LocalDataBase instance;
    
    public LocalDataBase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.presentation.room.NewsArticleDao getNewsArticleDao();
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/example/presentation/room/LocalDataBase$Companion;", "", "()V", "instance", "Lcom/example/presentation/room/LocalDataBase;", "destroyInstance", "", "getInstance", "context", "Landroid/content/Context;", "presentation_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.Synchronized()
        public final synchronized com.example.presentation.room.LocalDataBase getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        public final void destroyInstance() {
        }
    }
}