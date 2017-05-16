// Generated code from Butter Knife. Do not modify!
package com.itheima.oschina.base;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class BaseListFragment$$ViewInjector {
  public static void inject(Finder finder, final com.itheima.oschina.base.BaseListFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131034215, "field 'mListView'");
    target.mListView = (android.widget.ListView) view;
    view = finder.findRequiredView(source, 2131034185, "field 'mErrorLayout'");
    target.mErrorLayout = (com.itheima.oschina.ui.empty.EmptyLayout) view;
  }

  public static void reset(com.itheima.oschina.base.BaseListFragment target) {
    target.mListView = null;
    target.mErrorLayout = null;
  }
}
