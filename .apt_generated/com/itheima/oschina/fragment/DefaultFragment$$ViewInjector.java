// Generated code from Butter Knife. Do not modify!
package com.itheima.oschina.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class DefaultFragment$$ViewInjector {
  public static void inject(Finder finder, final com.itheima.oschina.fragment.DefaultFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131034195, "field 'tv_content'");
    target.tv_content = (android.widget.TextView) view;
  }

  public static void reset(com.itheima.oschina.fragment.DefaultFragment target) {
    target.tv_content = null;
  }
}
