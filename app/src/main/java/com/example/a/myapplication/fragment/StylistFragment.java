package com.example.a.myapplication.fragment;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.a.myapplication.BaseFragment;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.StylistAdapter;
import com.example.a.myapplication.bean.StyListModel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.OnDataChangeListener;
import com.example.a.myapplication.util.UIUtils;
import com.example.a.myapplication.view.LoadingPager;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 造型师下Viewpage下
 */
public class StylistFragment  extends BaseFragment  implements OnDataChangeListener {
    @InjectView(R.id.swipe_target)
    protected RecyclerView fragment_stylist_rv;
    @InjectView(R.id.swipeToLoadLayout)
    protected SwipeToLoadLayout fragment_stylist_sl;
    View view;
    int page=1;
    public static final String ARGS_PAGE = "args_page";
    private int mPage;
    StylistAdapter adapter;
    StyListModel styListModel;
    public static StylistFragment mCurrentFragment;
    public boolean isChangeData;
    public String id;
    Map<String,String> parm;
    String mUrl;
    public static StylistFragment newInstance(int page,String url,String id) {
        Bundle args = new Bundle();
        args.putInt(ARGS_PAGE, page);
        args.putString("URL", url);
        args.putString("id", id);
        StylistFragment fragment = new StylistFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARGS_PAGE);
        mUrl = getArguments().getString("URL");
        id = getArguments().getString("id");
        parm= CommonUtils.getMapParm();
        if(!"".equals(id)){
            parm.put("id",id);
        }

    }

    @Override
    protected View onLoadSuccessView() {
        view = View.inflate(getActivity(), R.layout.fragment_stylist, null);
        ButterKnife.inject(this, view);
        init();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCurrentFragment=this;
    }


    @Override
    protected LoadingPager.LoadedResult onLoadingData() {
        return initData();
    }

    public void init() {
        fragment_stylist_rv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        //设置adapter
        adapter = new StylistAdapter(mUrl);
        fragment_stylist_rv.setAdapter(adapter);
        adapter.getDateList().addAll(styListModel.getO());
        adapter.notifyDataSetChanged();
        //设置item之间的间隔
        SpacesItemDecoration decoration = new SpacesItemDecoration(500);
        //initItemAnimator(fragment_stylist_rv);
        fragment_stylist_rv.addItemDecoration(decoration);
        fragment_stylist_sl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                UIUtils.post(new Runnable() {
                    @Override
                    public void run() {
                        fragment_stylist_sl.setRefreshing(false);
                        EventBus.getDefault().post("onRefresh");
                    }
                });
            }
        });
        fragment_stylist_sl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                fragment_stylist_sl.setLoadingMore(false);
            }
        });
    }
    public LoadingPager.LoadedResult initData() {
        try{
            page=1;
            initDate();
            if(null==styListModel.getO()||styListModel.getO().size()==0){
                return LoadingPager.LoadedResult.EMPTY;
            }
            return LoadingPager.LoadedResult.SUCCESS;
        }catch (Exception e){
            return LoadingPager.LoadedResult.ERROR;
        }
    }
    public StyListModel initDate() {
        parm.put("pagination", String.valueOf(page));
        String result=OkHttpUtil.getInstance().addRequestNoCallPost(mUrl, parm);
        styListModel= new Gson().fromJson(result, StyListModel.class);
        if(page==1&&mUrl.equals(Config.QITTMELIST)){
            StyListModel.OBean oBean=   new StyListModel.OBean();
            oBean.setImg(Config.NATIVE);
            styListModel.getO().add(0,oBean);
        }

        if(isChangeData){
            EventBus.getDefault().post("isGetData");
        }
        return  styListModel;

    }
    @Override
    public void onDataChagne(Map<String, String> parm) {
        this.parm=parm;
        this.isChangeData=true;
        EventBus.getDefault().post("onDataChagne");
    }


    public void notifyDataRefres(){
        page=1;
        initData();
        if(null!=adapter){
            adapter.getDateList().clear();
            adapter.getDateList().addAll(styListModel.getO());
            adapter.notifyDataSetChanged();
        }

        fragment_stylist_sl.setRefreshing(false);
    }
    public void notifyDataLoad(){
        if(page==1){
            adapter.getDateList().clear();
        }
        adapter.getDateList().addAll(styListModel.getO());
        adapter.notifyDataSetChanged();
        fragment_stylist_sl.setLoadingMore(false);
    }
    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);
        if("onRefresh".equals(obj.toString())){
            page=1;
            initDate();
        }
        if("onLoadMore".equals(obj.toString())){
            fragment_stylist_sl.setLoadingMore(false);
        }
        if("isGetData".equals(obj.toString())){
            adapter.getDateList().clear();
            if(null!=styListModel.getO()){
                adapter.getDateList().addAll(styListModel.getO());
            }
            adapter.notifyDataSetChanged();
            isChangeData=false;
        }

    }

    @Override
    public void onEventBackgroundThread(Object obj) throws Exception {
        super.onEventBackgroundThread(obj);
        if("onDataChagne".equals(obj.toString())){
            initDate();
        }
    }

    public static class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = 5;
            outRect.right = 5;
            outRect.top = 0;
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.bottom = 20;
            }
            if (parent.getChildAdapterPosition(view) == 1) {
                outRect.bottom = 20;
            }
        }
    }
    private void initItemAnimator(RecyclerView recyclerView) {
        recyclerView.setItemAnimator(new DefaultItemAnimator()); // 默认动画
    }
}
