package com.itheima.oschina.ui;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;

import com.itheima.oschina.R;
import com.itheima.oschina.bean.News;
import com.itheima.oschina.bean.NewsList;
import com.itheima.oschina.fragment.DefaultFragment;
import com.itheima.oschina.util.XmlUtils;

public class TestActivity extends FragmentActivity {

    private FragmentTabHost mTabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);

		// 原理
//		FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
//		DefaultFragment fragment = new DefaultFragment();
//		fragment.setArguments(args)
//		beginTransaction.replace(R.id.realtabcontent, fragment);
//		beginTransaction.commitAllowingStateLoss();
		
		//a. 设置内容显示区域: mTabHost.setup(context, getSupportFragmentManager(), R.id.realtabcontent); // 最后一个参数决定了Fragment被创建的区域(图中蓝色区域)
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        // b. 添加Tab选项: FragmentTabHost.addTab(TabSpec tabSpec, Class<?> clss, Bundle args); // tabSpec是包含选项图标View的对象,如当前选中的[综合]选项, class是Fragment对象, args初始化参数
        mTabHost.addTab(mTabHost.newTabSpec("simple").setIndicator("Simple"),
                DefaultFragment.class, getBundle("simple"));
        
        mTabHost.addTab(mTabHost.newTabSpec("contacts").setIndicator("Contacts"),
        		DefaultFragment.class, getBundle("contacts"));
        
        mTabHost.addTab(mTabHost.newTabSpec("custom").setIndicator("Custom"),
        		DefaultFragment.class, getBundle("custom"));
        
        mTabHost.addTab(mTabHost.newTabSpec("throttle").setIndicator("Throttle"),
        		DefaultFragment.class, getBundle("throttle"));
        
        
        // Xml解析
//        new Thread(){
//        	public void run() {
//        		
//                try {
//					DefaultHttpClient httpClient = new DefaultHttpClient();
//					HttpGet httpGet = new HttpGet("http://192.168.17.81:8080/oschina/list/news/page0.xml");
//					HttpResponse response = httpClient.execute(httpGet);
//					
//					if(response.getStatusLine().getStatusCode() == 200){
//						InputStream is = response.getEntity().getContent();
//						NewsList list = XmlUtils.toBean(NewsList.class, is);
//						System.out.println("newslist: " + list.getList().size());
//						is.close();
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//        	};
//        }.start();

	}

	private Bundle getBundle(String string) {
		Bundle bundle = new Bundle();
		bundle.putString("key", string);
		return bundle;
	}
	
}
