package app.zuliangwang.zuliangnews.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.TextHttpResponseHandler;
import com.show.api.ShowApiRequest;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.zuliangwang.zuliangnews.R;
import app.zuliangwang.zuliangnews.adapter.MainRecyclerViewAdapter;
import app.zuliangwang.zuliangnews.adapter.MainViewPagerAdapter;
import app.zuliangwang.zuliangnews.api.NewsApi;
import app.zuliangwang.zuliangnews.model.Root;
import app.zuliangwang.zuliangnews.ui.fragment.ItemNewsFragment;


public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {


    private Toolbar mToolbar;
    private android.support.v4.widget.DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private FloatingActionButton mFloatingActionButton;
    private TabLayout mTabLayout;

    private ViewPager mViewPager;
    private String newsItemName[];
    private List<ItemNewsFragment> itemNewsFragmentList;
    private List<String> newsItemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDrawerToolBar();
        initFloatingActionButton();
        initTabLayout();
        initViewpager();


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

//        return super.onOptionsItemSelected(item);
        return mDrawerToggle.onOptionsItemSelected(item);
    }


    private void initDrawerToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawerlayout);

        mToolbar.setTitle("Toolbar");
        mToolbar.setBackgroundColor(Color.LTGRAY);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    private void initFloatingActionButton() {
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.main_floating_button);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(mDrawerLayout, "Test SnackBar", Snackbar.LENGTH_SHORT).setAction("Come!", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(MainActivity.this, rootMap.get(0).showapi_res_body.pagebean.contentlist.get(0).getDesc(), Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });
    }

    private void initTabLayout() {
        newsItemName = getResources().getStringArray(R.array.news_item);
        newsItemList = new ArrayList<String>();
        mTabLayout = (TabLayout) findViewById(R.id.main_tablayout);
        for (int i = 0; i < newsItemName.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(newsItemName[i]));
            newsItemList.add(newsItemName[i]);
        }

    }


    private void initViewpager() {
        itemNewsFragmentList = new ArrayList<ItemNewsFragment>();
        for (int i = 0; i < getResources().getStringArray(R.array.news_item).length; i++) {
            ItemNewsFragment fragment = new ItemNewsFragment(this,i);
            itemNewsFragmentList.add(fragment);
        }
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager(), itemNewsFragmentList, newsItemList, this);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);
        mViewPager.setOnPageChangeListener(this);
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageSelected(final int position) {
        Log.d("Fragment",""+position);
//        ((MainRecyclerViewAdapter)itemNewsFragmentList.get(position).getRecyclerView().getAdapter()).setRoot();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }








}
