// Generated code from Butter Knife. Do not modify!
package com.itheima.oschina.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class NewsAdapter$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final com.itheima.oschina.adapter.NewsAdapter.ViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131034212, "field 'source'");
    target.source = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131034226, "field 'comment_count'");
    target.comment_count = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131034230, "field 'tip'");
    target.tip = (android.widget.ImageView) view;
    view = finder.findRequiredView(source, 2131034232, "field 'link'");
    target.link = (android.widget.ImageView) view;
    view = finder.findRequiredView(source, 2131034231, "field 'description'");
    target.description = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131034211, "field 'time'");
    target.time = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131034210, "field 'title'");
    target.title = (android.widget.TextView) view;
  }

  public static void reset(com.itheima.oschina.adapter.NewsAdapter.ViewHolder target) {
    target.source = null;
    target.comment_count = null;
    target.tip = null;
    target.link = null;
    target.description = null;
    target.time = null;
    target.title = null;
  }
}
