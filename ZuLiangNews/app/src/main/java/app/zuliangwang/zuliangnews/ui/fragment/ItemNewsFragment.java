package app.zuliangwang.zuliangnews.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.ClipData;
import android.content.Context;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.show.api.ShowApiRequest;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.zuliangwang.zuliangnews.R;
import app.zuliangwang.zuliangnews.adapter.MainRecyclerViewAdapter;
import app.zuliangwang.zuliangnews.adapter.MainViewPagerAdapter;
import app.zuliangwang.zuliangnews.api.NewsApi;
import app.zuliangwang.zuliangnews.model.Root;
import app.zuliangwang.zuliangnews.ui.activity.MainActivity;

/**
 * Created by zuliangwang on 15/7/19.
 */
public class ItemNewsFragment extends android.support.v4.app.Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private View mView;
    private RecyclerView recyclerView;
    private MainRecyclerViewAdapter recyclerViewAdapter;
    private Gson gson;
    private Root item;
    private Context mContext;
    private int numOfFragment;
    private android.support.v4.widget.SwipeRefreshLayout swipeRefreshLayout;
    private int page ;
    private LinearLayoutManager linearLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;





    public ItemNewsFragment(Context context,int numOfFragment) {
        super();
        mContext = context;
        this.numOfFragment = numOfFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("Fragment","onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        gson = new Gson();
        mView= inflater.inflate(R.layout.item_news_fragment, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.fragment_swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        Log.d("Fragment", "onCreateView");
        initRecyclerView();
        page = 1;
        initItem(numOfFragment,""+page);
        page++;
        return mView;
    }

    @Override
    public void onStart() {
        Log.d("Fragment","onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d("Fragment","onResume");
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment","onDestory");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Fragment","onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("Fragment","onDetach");
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) mView.findViewById(R.id.fragment_recyclerview);
        recyclerView.setLayoutManager( linearLayoutManager = new LinearLayoutManager(mContext));
        recyclerViewAdapter = new MainRecyclerViewAdapter(mContext);

                recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                    int firstItemPostion;
                    int lastVisibleItem;

                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        if (newState == RecyclerView.SCROLL_STATE_IDLE && firstItemPostion == 0) {
                        }
                        if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem+1 == recyclerViewAdapter.getItemCount()){
                            initItem(numOfFragment,page+"");
                            page++;
                        }
                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        firstItemPostion = linearLayoutManager.findFirstVisibleItemPosition();
                        lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    }
                });


    }





    private void initItem(final int des,String page) {
        TextHttpResponseHandler handler = new TextHttpResponseHandler() {
            @Override
            public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
            }

            @Override
            public void onSuccess(int i, Header[] headers, String s) {
                item= gson.fromJson(s, Root.class);
                Log.d("czx",s);
            }
        };
        onRefresh();
        ShowApiRequest request = new ShowApiRequest(NewsApi.baseNewsUrl, NewsApi.appid, NewsApi.secret);
        request.setResponseHandler(handler);
        request.addTextPara("channelId", "");
        request.addTextPara("showapi_timestamp", NewsApi.getiCurTime());
        request.addTextPara("channelName", "");
        request.addTextPara("title", getResources().getStringArray(R.array.news_item)[des]);
        Log.d("page23",page);
        request.addTextPara("page", page);
        request.post();


    }

    public ItemNewsFragment() {
        super();
    }


    public RecyclerView getRecyclerView() {
        return recyclerView;
    }


    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        Log.d("Refresh", "begin");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerViewAdapter.setRoot(item);
                recyclerView.setAdapter(recyclerViewAdapter);
                swipeRefreshLayout.setRefreshing(false);
                recyclerViewAdapter.notifyDataSetChanged();
                Toast.makeText(mContext,"刷新完毕！",Toast.LENGTH_SHORT).show();
            }
        },2000);

    }


}
