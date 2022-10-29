package com.example.presentation.room;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.presentation.model.Article;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class NewsArticleDao_Impl implements NewsArticleDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Article> __insertionAdapterOfArticle;

  private final SharedSQLiteStatement __preparedStmtOfDeleteSavedArticle;

  public NewsArticleDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfArticle = new EntityInsertionAdapter<Article>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `newsArticleTable` (`uid`,`author`,`content`,`description`,`publishedAt`,`title`,`url`,`urlToImage`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Article value) {
        stmt.bindLong(1, value.getUid());
        if (value.getAuthor() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getAuthor());
        }
        if (value.getContent() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getContent());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDescription());
        }
        if (value.getPublishedAt() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPublishedAt());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getTitle());
        }
        if (value.getUrl() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getUrl());
        }
        if (value.getUrlToImage() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getUrlToImage());
        }
      }
    };
    this.__preparedStmtOfDeleteSavedArticle = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM newsArticleTable WHERE publishedAt = ? AND title = ? AND url = ?";
        return _query;
      }
    };
  }

  @Override
  public void setSavedArticle(final Article article) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfArticle.insert(article);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteSavedArticle(final String publishedAt, final String title, final String url) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteSavedArticle.acquire();
    int _argIndex = 1;
    if (publishedAt == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, publishedAt);
    }
    _argIndex = 2;
    if (title == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, title);
    }
    _argIndex = 3;
    if (url == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, url);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteSavedArticle.release(_stmt);
    }
  }

  @Override
  public List<Article> loadSavedNewsArticles() {
    final String _sql = "SELECT * FROM newsArticleTable";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
      final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
      final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfPublishedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "publishedAt");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
      final int _cursorIndexOfUrlToImage = CursorUtil.getColumnIndexOrThrow(_cursor, "urlToImage");
      final List<Article> _result = new ArrayList<Article>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Article _item;
        final long _tmpUid;
        _tmpUid = _cursor.getLong(_cursorIndexOfUid);
        final String _tmpAuthor;
        if (_cursor.isNull(_cursorIndexOfAuthor)) {
          _tmpAuthor = null;
        } else {
          _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
        }
        final String _tmpContent;
        if (_cursor.isNull(_cursorIndexOfContent)) {
          _tmpContent = null;
        } else {
          _tmpContent = _cursor.getString(_cursorIndexOfContent);
        }
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        final String _tmpPublishedAt;
        if (_cursor.isNull(_cursorIndexOfPublishedAt)) {
          _tmpPublishedAt = null;
        } else {
          _tmpPublishedAt = _cursor.getString(_cursorIndexOfPublishedAt);
        }
        final String _tmpTitle;
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _tmpTitle = null;
        } else {
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        }
        final String _tmpUrl;
        if (_cursor.isNull(_cursorIndexOfUrl)) {
          _tmpUrl = null;
        } else {
          _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
        }
        final String _tmpUrlToImage;
        if (_cursor.isNull(_cursorIndexOfUrlToImage)) {
          _tmpUrlToImage = null;
        } else {
          _tmpUrlToImage = _cursor.getString(_cursorIndexOfUrlToImage);
        }
        _item = new Article(_tmpUid,_tmpAuthor,_tmpContent,_tmpDescription,_tmpPublishedAt,_tmpTitle,_tmpUrl,_tmpUrlToImage);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
