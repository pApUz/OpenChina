package com.itheima.oschina.fragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.itheima.oschina.AppContext;
import com.itheima.oschina.R;
import com.itheima.oschina.api.remote.OSChinaApi;
import com.itheima.oschina.bean.Comment;
import com.itheima.oschina.bean.CommentList;
import com.itheima.oschina.bean.Entity;
import com.itheima.oschina.bean.FavoriteList;
import com.itheima.oschina.bean.News;
import com.itheima.oschina.bean.News.Relative;
import com.itheima.oschina.bean.NewsDetail;
import com.itheima.oschina.emoji.OnSendClickListener;
import com.itheima.oschina.ui.DetailActivity;
import com.itheima.oschina.ui.empty.EmptyLayout;
import com.itheima.oschina.util.StringUtils;
import com.itheima.oschina.util.TDevice;
import com.itheima.oschina.util.UIHelper;
import com.itheima.oschina.util.URLsUtils;
import com.itheima.oschina.util.XmlUtils;

public class NewsDetailFragment extends BaseDetailFragment implements
        OnSendClickListener {

    protected static final String TAG = NewsDetailFragment.class
            .getSimpleName();
    private static final String NEWS_CACHE_KEY = "news_";
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.tv_source)
    TextView mTvSource;
    @InjectView(R.id.tv_time)
    TextView mTvTime;
    private int mNewsId;
    private News mNews;

    @Override
    public View onCreateView(LayoutInflater inflater,
            @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_detail, container,
                false);
        mCommentCount = getActivity().getIntent().getIntExtra("comment_count",
                0);
        mNewsId = getActivity().getIntent().getIntExtra("news_id", 0);
        ButterKnife.inject(this, view);
        initViews(view); 
        return view;
    }

    private void initViews(View view) {
        mEmptyLayout = (EmptyLayout) view.findViewById(R.id.error_layout);
//        ((DetailActivity) getActivity()).toolFragment
//                .setCommentCount(mCommentCount);
        mWebView = (WebView) view.findViewById(R.id.webview);
        UIHelper.initWebView(mWebView);
    }

    @Override
    protected String getCacheKey() {
        return new StringBuilder(NEWS_CACHE_KEY).append(mNewsId).toString();
    }

    @Override
    protected void sendRequestData() {
        mEmptyLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
        OSChinaApi.getNewsDetail(mNewsId, mHandler);
    }

    @Override
    protected Entity parseData(InputStream is) throws Exception {
        return XmlUtils.toBean(NewsDetail.class, is).getNews();
    }

    @Override
    protected Entity readData(Serializable seri) {
        return (News) seri;
    }

    @Override
    protected void onCommentChanged(int opt, int id, int catalog,
            boolean isBlog, Comment comment) {
        if (id == mNewsId && catalog == CommentList.CATALOG_NEWS && !isBlog) {
            if (Comment.OPT_ADD == opt && mNews != null) {
                mNews.setCommentCount(mNews.getCommentCount() + 1);
            }
        }
    }

    @Override
    protected void executeOnLoadDataSuccess(Entity entity) {
        mNews = (News) entity;
        fillUI();
        fillWebViewBody();
        ((DetailActivity) getActivity()).setCommentCount(mNews
                .getCommentCount());
    }

    private void fillUI() {
        mTvTitle.setText(mNews.getTitle());
        mTvSource.setText(mNews.getAuthor());
        mTvSource.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                UIHelper.showUserCenter(getActivity(), mNews.getAuthorId(),
//                        mNews.getAuthor());
            }
        });
//        2015-9-29 17:04:31  -> 2个月前
        mTvTime.setText(StringUtils.friendly_time(mNews.getPubDate()));
        notifyFavorite(mNews.getFavorite() == 1);
    }

    @Override
    public int getCommentCount() {
        return mNews.getCommentCount();
    }

    private void fillWebViewBody() {

        StringBuffer body = new StringBuffer();
        body.append(UIHelper.setHtmlCotentSupportImagePreview(mNews.getBody()));

        body.append(UIHelper.WEB_STYLE).append(UIHelper.WEB_LOAD_IMAGES);

        // 更多关于***软件的信息
        String softwareName = mNews.getSoftwareName();
        String softwareLink = mNews.getSoftwareLink();
        if (!StringUtils.isEmpty(softwareName)
                && !StringUtils.isEmpty(softwareLink))
            body.append(String
                    .format("<div id='oschina_software' style='margin-top:8px;color:#FF0000;font-weight:bold'>更多关于:&nbsp;<a href='%s'>%s</a>&nbsp;的详细信息</div>",
                            softwareLink, softwareName));

        // 相关新闻
        if (mNews != null && mNews.getRelatives() != null
                && mNews.getRelatives().size() > 0) {
            String strRelative = "";
            for (Relative relative : mNews.getRelatives()) {
                strRelative += String.format(
                        "<a href='%s' style='text-decoration:none'>%s</a><p/>",
                        relative.url, relative.title);
            }
            body.append("<p/><div style=\"height:1px;width:100%;background:#DADADA;margin-bottom:10px;\"/>"
                    + String.format("<br/> <b>相关资讯</b> <div><p/>%s</div>",
                            strRelative));
        }
        body.append("<br/>");
        
        try {
        	System.out.println("save begin! :" + mNewsId);
			File file = new File(Environment.getExternalStorageDirectory(), 
					String.format("/OSChina/html/%d.html", mNewsId));
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(body.toString().getBytes());
			fos.close();
			System.out.println("save success! :" + file.getPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, body.toString(), "text/html",
                    "utf-8", null);
        }
    }

    @Override
    protected void onFavoriteChanged(boolean flag) {
        mNews.setFavorite(flag ? 1 : 0);
        saveCache(mNews);
    }

    @Override
    protected int getFavoriteTargetId() {
        return mNews != null ? mNews.getId() : -1;
    }

    @Override
    protected int getFavoriteTargetType() {
        return mNews != null ? FavoriteList.TYPE_NEWS : -1;
    }

    @Override
    protected String getShareTitle() {
        return mNews != null ? mNews.getTitle()
                : getString(R.string.share_title_news);
    }

    @Override
    protected String getShareContent() {
        return mNews != null ? StringUtils.getSubString(0, 55,
                getFilterHtmlBody(mNews.getBody())) : "";
    }

    @Override
    protected String getShareUrl() {
        return mNews != null ? URLsUtils.URL_MOBILE + "news/" + mNews.getId()
                : null;
    }

    @Override
    public void onClickSendButton(Editable str) {
        if (!TDevice.hasInternet()) {
            AppContext.showToastShort(R.string.tip_network_error);
            return;
        }
        if (!AppContext.getInstance().isLogin()) {
//            UIHelper.showLoginActivity(getActivity());
            return;
        }
        if (TextUtils.isEmpty(str)) {
            AppContext.showToastShort(R.string.tip_comment_content_empty);
            return;
        }
//        showWaitDialog(R.string.progress_submit);
        OSChinaApi.publicComment(CommentList.CATALOG_NEWS, mNewsId, AppContext
                .getInstance().getLoginUid(), str.toString(), 0,
                mCommentHandler);
    }

    @Override
    public void onResume() {
        super.onResume();
//        ((DetailActivity) getActivity()).toolFragment.hideReportButton();
    }

    @Override
    public void onClickFlagButton() {}

    @Override
    public void onclickWriteComment() {
        super.onclickWriteComment();
//        if (mNews != null)
//            UIHelper.showComment(getActivity(), mNewsId,
//                    CommentList.CATALOG_NEWS);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.refresh_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        sendRequestData();
        return super.onOptionsItemSelected(item);
    }
}
