// Generated code from Butter Knife. Do not modify!
package com.itheima.oschina.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class NewsDetailFragment$$ViewInjector {
  public static void inject(Finder finder, final com.itheima.oschina.fragment.NewsDetailFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131034212, "field 'mTvSource'");
    target.mTvSource = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131034211, "field 'mTvTime'");
    target.mTvTime = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131034210, "field 'mTvTitle'");
    target.mTvTitle = (android.widget.TextView) view;
  }

  public static void reset(com.itheima.oschina.fragment.NewsDetailFragment target) {
    target.mTvSource = null;
    target.mTvTime = null;
    target.mTvTitle = null;
  }
}
