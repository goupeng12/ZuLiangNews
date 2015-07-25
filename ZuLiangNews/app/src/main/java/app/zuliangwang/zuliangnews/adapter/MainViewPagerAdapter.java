package app.zuliangwang.zuliangnews.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.HttpGet;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.show.api.ShowApiRequest;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpRequestHandler;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.zuliangwang.zuliangnews.R;
import app.zuliangwang.zuliangnews.api.NewsApi;
import app.zuliangwang.zuliangnews.model.Root;
import app.zuliangwang.zuliangnews.ui.fragment.ItemNewsFragment;

/**
 * Created by zuliangwang on 15/7/20.
 */
public class MainViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<String> mTitles;
    private List<ItemNewsFragment> itemNewsFragmentList;
    private ItemNewsFragment mFragment;
    private Context mContext;
    private Map<Integer,Root> rootMap;
    private Gson gson;

    public MainViewPagerAdapter(FragmentManager fm, List<ItemNewsFragment> fragments, List<String> titles, Context context) {
        super(fm);
        itemNewsFragmentList = fragments;
        mTitles = titles;
        mContext = context;
    }

    @Override
    public Fragment getItem(final int position) {
        return itemNewsFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mContext.getResources().getStringArray(R.array.news_item).length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }


}
