package com.example.presentation;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.example.presentation.databinding.ActivityLoginBindingImpl;
import com.example.presentation.databinding.ActivityMainBindingImpl;
import com.example.presentation.databinding.ActivitySplashBindingImpl;
import com.example.presentation.databinding.FragmentArticleDetailBindingImpl;
import com.example.presentation.databinding.FragmentCategoriesBindingImpl;
import com.example.presentation.databinding.FragmentSavedBindingImpl;
import com.example.presentation.databinding.FragmentTopNewsBindingImpl;
import com.example.presentation.databinding.ItemNewsListBindingImpl;
import com.example.presentation.databinding.ToolbarBackBindingImpl;
import com.example.presentation.databinding.ToolbarMainBindingImpl;
import com.example.presentation.databinding.ToolbarTitleBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYLOGIN = 1;

  private static final int LAYOUT_ACTIVITYMAIN = 2;

  private static final int LAYOUT_ACTIVITYSPLASH = 3;

  private static final int LAYOUT_FRAGMENTARTICLEDETAIL = 4;

  private static final int LAYOUT_FRAGMENTCATEGORIES = 5;

  private static final int LAYOUT_FRAGMENTSAVED = 6;

  private static final int LAYOUT_FRAGMENTTOPNEWS = 7;

  private static final int LAYOUT_ITEMNEWSLIST = 8;

  private static final int LAYOUT_TOOLBARBACK = 9;

  private static final int LAYOUT_TOOLBARMAIN = 10;

  private static final int LAYOUT_TOOLBARTITLE = 11;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(11);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.presentation.R.layout.activity_login, LAYOUT_ACTIVITYLOGIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.presentation.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.presentation.R.layout.activity_splash, LAYOUT_ACTIVITYSPLASH);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.presentation.R.layout.fragment_article_detail, LAYOUT_FRAGMENTARTICLEDETAIL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.presentation.R.layout.fragment_categories, LAYOUT_FRAGMENTCATEGORIES);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.presentation.R.layout.fragment_saved, LAYOUT_FRAGMENTSAVED);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.presentation.R.layout.fragment_top_news, LAYOUT_FRAGMENTTOPNEWS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.presentation.R.layout.item_news_list, LAYOUT_ITEMNEWSLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.presentation.R.layout.toolbar_back, LAYOUT_TOOLBARBACK);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.presentation.R.layout.toolbar_main, LAYOUT_TOOLBARMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.presentation.R.layout.toolbar_title, LAYOUT_TOOLBARTITLE);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYLOGIN: {
          if ("layout/activity_login_0".equals(tag)) {
            return new ActivityLoginBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_login is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYMAIN: {
          if ("layout/activity_main_0".equals(tag)) {
            return new ActivityMainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYSPLASH: {
          if ("layout/activity_splash_0".equals(tag)) {
            return new ActivitySplashBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_splash is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTARTICLEDETAIL: {
          if ("layout/fragment_article_detail_0".equals(tag)) {
            return new FragmentArticleDetailBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_article_detail is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTCATEGORIES: {
          if ("layout/fragment_categories_0".equals(tag)) {
            return new FragmentCategoriesBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_categories is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSAVED: {
          if ("layout/fragment_saved_0".equals(tag)) {
            return new FragmentSavedBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_saved is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTTOPNEWS: {
          if ("layout/fragment_top_news_0".equals(tag)) {
            return new FragmentTopNewsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_top_news is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMNEWSLIST: {
          if ("layout/item_news_list_0".equals(tag)) {
            return new ItemNewsListBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_news_list is invalid. Received: " + tag);
        }
        case  LAYOUT_TOOLBARBACK: {
          if ("layout/toolbar_back_0".equals(tag)) {
            return new ToolbarBackBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for toolbar_back is invalid. Received: " + tag);
        }
        case  LAYOUT_TOOLBARMAIN: {
          if ("layout/toolbar_main_0".equals(tag)) {
            return new ToolbarMainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for toolbar_main is invalid. Received: " + tag);
        }
        case  LAYOUT_TOOLBARTITLE: {
          if ("layout/toolbar_title_0".equals(tag)) {
            return new ToolbarTitleBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for toolbar_title is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(1);

    static {
      sKeys.put(0, "_all");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(11);

    static {
      sKeys.put("layout/activity_login_0", com.example.presentation.R.layout.activity_login);
      sKeys.put("layout/activity_main_0", com.example.presentation.R.layout.activity_main);
      sKeys.put("layout/activity_splash_0", com.example.presentation.R.layout.activity_splash);
      sKeys.put("layout/fragment_article_detail_0", com.example.presentation.R.layout.fragment_article_detail);
      sKeys.put("layout/fragment_categories_0", com.example.presentation.R.layout.fragment_categories);
      sKeys.put("layout/fragment_saved_0", com.example.presentation.R.layout.fragment_saved);
      sKeys.put("layout/fragment_top_news_0", com.example.presentation.R.layout.fragment_top_news);
      sKeys.put("layout/item_news_list_0", com.example.presentation.R.layout.item_news_list);
      sKeys.put("layout/toolbar_back_0", com.example.presentation.R.layout.toolbar_back);
      sKeys.put("layout/toolbar_main_0", com.example.presentation.R.layout.toolbar_main);
      sKeys.put("layout/toolbar_title_0", com.example.presentation.R.layout.toolbar_title);
    }
  }
}
