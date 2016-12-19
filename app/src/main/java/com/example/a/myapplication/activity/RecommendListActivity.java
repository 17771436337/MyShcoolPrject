package com.example.a.myapplication.activity;

import android.graphics.Bitmap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.adapter.MyRecyclerCommonAdapter;
import com.example.a.myapplication.bean.RecommendModel;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.ScreenUtils;
import com.example.a.myapplication.view.MyLoadMoreRecyclerView;
import com.example.a.myapplication.view.TitleView1;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/9.
 * 推荐列表
 */
public class RecommendListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @InjectView(R.id.swipeLayout)
    protected SwipeRefreshLayout swipeLayout;

    @InjectView(R.id.rv)
    protected MyLoadMoreRecyclerView rv;

    List<String> mDatas = new ArrayList<String>();
    private RecommendModel entity = new RecommendModel();
    private MyRecyclerCommonAdapter<RecommendModel.GankEntity> adapter;

    private int startIndex = 2;   //下标

    private int screenWidth;

    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    @Override
    protected int getLayoutID() {

        return R.layout.activity_recommend;
    }

    @Override
    protected void initData() {

    }

    /**
     * 标题初始化
     */
    private void initTitle() {
        TitleView1 view = new TitleView1(this);
        titleView.addView(view.getView());
        view.setTitleText("推荐列表", "");
    }

    @Override
    protected void initView() {

        initTitle();
        ArrayList<RecommendModel.GankEntity> entities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            entities.add(new RecommendModel.GankEntity());
            mDatas.add("推荐" + i);
        }
        entity.setList(entities);

        screenWidth = ScreenUtils.getScreenWidthPX(this);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(staggeredGridLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setOnLoadDataListener(new MyLoadMoreRecyclerView.LoadData() {

            @Override
            public void onLoadMore() {
                getPicFromNet(startIndex);
            }
        });
        swipeLayout.setColorSchemeResources(R.color.colorPrimary, R.color.swipecolor);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.post(new Runnable() {

            @Override
            public void run() {
                swipeLayout.setRefreshing(true);

            }
        });

        onRefresh();

    }


    @Override
    public void onRefresh() {
        getPicFromNet(1);
    }

    /**
     * 网络请求
     */
    private void getPicFromNet(final int i) {

        if (adapter != null &&
                entity.getList() != null && entity.getList().size() > 0) {

            if (swipeLayout.isRefreshing()) {
                swipeLayout.setRefreshing(false);
            }
            if (i == 1) {
                startIndex = 2;
                entity.getList().clear();

                adapter.setDatas(entity.getList());
//                        DialogUtils.closeProgressDialog();
                adapter.notifyDataSetChanged();
                return;
            }


            startIndex++;
            adapter.setDatas(entity.getList());
//                    DialogUtils.closeProgressDialog();
            adapter.notifyDataSetChanged();
            return;
        }
        adapter = new MyRecyclerCommonAdapter<RecommendModel.GankEntity>(this, R.layout.item_welfare_staggered, entity.getList()) {
            @Override
            protected void convert(final ViewHolder holder, RecommendModel.GankEntity gankEntity, int position) {
//                ((TextView) holder.getView(R.id.tvShowTime)).setText("asdsadsadasd");
                Glide.with(RecommendListActivity.this)
                        .load("http://ww2.sinaimg.cn/large/610dc034jw1fa8n634v0vj20u00qx0x4.jpg")
                        .asBitmap()
                        .placeholder(R.drawable.pic_gray_bg)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(new SimpleTarget<Bitmap>(screenWidth / 2, screenWidth / 2) {
                                  @Override
                                  public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                      int width = resource.getWidth();
                                      int height = resource.getHeight();
                                      //计算高宽比
                                      int finalHeight = (screenWidth / 2) * height / width;
                                      ViewGroup.LayoutParams layoutParams = holder.getView(R.id.rl_root).getLayoutParams();
                                      layoutParams.height = finalHeight;
                                      ((ImageView) holder.getView(R.id.image)).setImageBitmap(resource);
                                  }
                              }
                        );
                holder.getView(R.id.card_view).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CommonUtils.startIntent(RecommendListActivity.this, ProductDetailsActivity.class);
//                              HttpResult result1 = new HttpResult();
//                              result1.setResults(entity);
//                                Intent intent = new Intent(getActivity(), ImageShowActivity.class);
//                                intent.putExtra("pos", position);
//                                intent.putExtra("datas", result1);
//                                startActivity(intent);
                        RecommendListActivity.this.overridePendingTransition(R.anim.alpha_in,
                                R.anim.alpha_out);
                    }
                });
                holder.getView(R.id.card_view).setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        switch (motionEvent.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                view.setScaleX(1.1f);
                                view.setScaleY(1.1f);
                                break;
                            case MotionEvent.ACTION_UP:
                            case MotionEvent.ACTION_CANCEL:
                                view.setScaleX(1f);
                                view.setScaleY(1f);
                                break;
                        }
                        return false;
                    }
                });
            }
        };
        if (swipeLayout.isRefreshing()) {
            swipeLayout.setRefreshing(false);
        }
        rv.setAdapter(adapter);
    }


//    @OnItemClick(R.id.rv)
//    protected void onItemClick(int position){
////        CommonUtils.startIntent(this,ProductDetailsActivity.class);
//
//    }
}
