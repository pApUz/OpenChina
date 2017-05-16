package com.itheima.oschina.ui;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.itheima.oschina.R;
import com.itheima.oschina.widget.ActionBarDrawerToggle;
import com.itheima.oschina.widget.DrawerArrowDrawable;

/**
 * 侧滑菜单界面
 * 
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @created 2014年9月25日 下午6:00:05
 * 
 */
public class NavigationDrawerFragment extends Fragment implements
		OnClickListener {

	/**
	 * Remember the position of the selected item.
	 */
	private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

	/**
	 * A pointer to the current callbacks instance (the Activity).
	 */
	private NavigationDrawerCallbacks mCallbacks;

	/**
	 * Helper component that ties the action bar to the navigation drawer.
	 */
	private ActionBarDrawerToggle mDrawerToggle;

	private DrawerArrowDrawable drawerArrow;

	private DrawerLayout mDrawerLayout;
	private View mDrawerListView;
	private View mFragmentContainerView;

	private int mCurrentSelectedPosition = 0;
	private boolean mFromSavedInstanceState;

	@InjectView(R.id.menu_item_quests)
	View mMenu_item_quests;

	@InjectView(R.id.menu_item_opensoft)
	View mMenu_item_opensoft;

	@InjectView(R.id.menu_item_blog)
	View mMenu_item_blog;

	@InjectView(R.id.menu_item_gitapp)
	View mMenu_item_gitapp;

	@InjectView(R.id.menu_item_rss)
	View mMenu_item_rss;

	@InjectView(R.id.menu_item_setting)
	View mMenu_item_setting;

	@InjectView(R.id.menu_item_exit)
	View mMenu_item_exit;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (savedInstanceState != null) {
			mCurrentSelectedPosition = savedInstanceState
					.getInt(STATE_SELECTED_POSITION);
			mFromSavedInstanceState = true;
		}

		selectItem(mCurrentSelectedPosition);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mDrawerListView = inflater.inflate(R.layout.fragment_navigation_drawer,
				container, false);
		mDrawerListView.setOnClickListener(this);
		ButterKnife.inject(this, mDrawerListView);
		initView(mDrawerListView);
		initData();
		return mDrawerListView;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.menu_item_quests:
			break;
		case R.id.menu_item_opensoft:
			break;
		case R.id.menu_item_blog:
			break;
		case R.id.menu_item_gitapp:
			break;
		case R.id.menu_item_rss:
			break;
		case R.id.menu_item_setting:
			break;
		case R.id.menu_item_exit:
			break;
		default:
			break;

		}
		mDrawerLayout.postDelayed(new Runnable() {

			@Override
			public void run() {
				mDrawerLayout.closeDrawers();
			}
		}, 800);
	}

	public void initView(View view) {

		mMenu_item_rss.setOnClickListener(this);
		mMenu_item_opensoft.setOnClickListener(this);
		mMenu_item_blog.setOnClickListener(this);
		mMenu_item_quests.setOnClickListener(this);

		mMenu_item_setting.setOnClickListener(this);
		mMenu_item_exit.setOnClickListener(this);

		mMenu_item_gitapp.setOnClickListener(this);
	}

	public void initData() {
	}

	public boolean isDrawerOpen() {
		return mDrawerLayout != null
				&& mDrawerLayout.isDrawerOpen(mFragmentContainerView);
	}

	/**
	 * Users of this fragment must call this method to set up the navigation
	 * drawer interactions.
	 * 
	 * @param fragmentId
	 *            The android:id of this fragment in its activity's layout.
	 * @param drawerLayout
	 *            The DrawerLayout containing this fragment's UI.
	 */
	public void setUp(int fragmentId, DrawerLayout drawerLayout) {
		mFragmentContainerView = getActivity().findViewById(fragmentId);
		mDrawerLayout = drawerLayout;

		// set a custom shadow that overlays the main content when the drawer
		// opens
		// 给DrawerLayout设置阴影
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		// set up the drawer's list view with items and click listener

		ActionBar actionBar = getActionBar();
		// 设置返回按钮可以显示 (向左的小箭头)// 默认不显示
		actionBar.setDisplayHomeAsUpEnabled(true); 
		// 设置返回按钮可以点击
		actionBar.setHomeButtonEnabled(true);

		// 创建自定义返回按钮内容
		drawerArrow = new DrawerArrowDrawable(getActivity()) {
			@Override
			public boolean isLayoutRtl() {
				return false;
			}
		};

		// 创建v7包下的返回按钮ActionBarDrawerToggle, (封装DrawerArrowDrawable)
		mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout,
				drawerArrow, R.string.navigation_drawer_open,
				R.string.navigation_drawer_close) {

			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				getActivity().invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getActivity().invalidateOptionsMenu();
			}
		};

		mDrawerLayout.post(new Runnable() {
			@Override
			public void run() {
				// 同步开关状态
				mDrawerToggle.syncState();
			}
		});

		// 设置按钮监听, 点击之后, 或者拖拽过程中返回按钮可以实时更新其动画位置.
		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	public void openDrawerMenu() {
		mDrawerLayout.openDrawer(mFragmentContainerView);
	}

	private void selectItem(int position) {
		mCurrentSelectedPosition = position;
		if (mDrawerLayout != null) {
			mDrawerLayout.closeDrawer(mFragmentContainerView);
		}
		if (mCallbacks != null) {
			mCallbacks.onNavigationDrawerItemSelected(position);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mCallbacks = (NavigationDrawerCallbacks) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(
					"Activity must implement NavigationDrawerCallbacks.");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mCallbacks = null;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	private ActionBar getActionBar() {
		return ((ActionBarActivity) getActivity()).getSupportActionBar();
	}

	public static interface NavigationDrawerCallbacks {
		void onNavigationDrawerItemSelected(int position);
	}
}
