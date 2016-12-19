package com.example.a.myapplication.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.a.myapplication.MainActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.activity.MessageActvity;
import com.example.a.myapplication.activity.ProductDetailsActivity;
import com.example.a.myapplication.activity.screen.ScreenActivity;
import com.example.a.myapplication.adapter.MyRecyclerCommonAdapter;
import com.example.a.myapplication.bean.RecommendModel;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.ScreenUtils;
import com.example.a.myapplication.view.MyLoadMoreRecyclerView;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static android.graphics.Color.TRANSPARENT;


/**
 * 发现
 */
public class FindFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    View view;
    @InjectView(R.id.title_text)
    protected TextView titleText;

    @InjectView(R.id.right)
    protected ImageView tightImg;


    @InjectView(R.id.product_text)
    protected TextView productText;

    @InjectView(R.id.product_line)
    protected View productLine;

    @InjectView(R.id.style_text)
    protected TextView styleText;

    @InjectView(R.id.style_line)
    protected View styleLine;


    @InjectView(R.id.swipeLayout)
    protected SwipeRefreshLayout swipeLayout;

    @InjectView(R.id.rv)
    protected MyLoadMoreRecyclerView rv;

    List<String> mDatas = new ArrayList<String>();
    private RecommendModel entity = new RecommendModel();
    private MyRecyclerCommonAdapter<RecommendModel.GankEntity> adapter;

    private int startIndex = 2;   //下标

    private int screenWidth;

    private final int SCREEN_REQUESTCODE = 0x0001;  //筛选携带参数返回值

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_find, container, false);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.inject(this, view);
        initTitleView();
        initData();
        setSeleoct(1);
    }

    /***/
    private void initData() {
    }

    /**
     * 设置标题
     */
    private void initTitleView() {
        titleText.setText("设计师");
        tightImg.setImageResource(R.drawable.icon_screen);


        ArrayList<RecommendModel.GankEntity> entities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            entities.add(new RecommendModel.GankEntity());
            mDatas.add("推荐" + i);
        }
        entity.setList(entities);

        screenWidth = ScreenUtils.getScreenWidthPX(getActivity());
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

    @OnClick({R.id.right, R.id.message_icon, R.id.product_layout, R.id.style_layout})
    public void OnClic(View v) {
        switch (v.getId()) {

            case R.id.right://筛选
                CommonUtils.startIntent(((MainActivity) getActivity()), ScreenActivity.class, SCREEN_REQUESTCODE);
                break;
            case R.id.message_icon://消息
                CommonUtils.startIntent(getActivity(), MessageActvity.class);
                break;
            case R.id.product_layout://求单品
                setSeleoct(1);
                break;

            case R.id.style_layout://求风格
                setSeleoct(2);
                break;
        }
    }


    private void setSeleoct(int i) {
        switch (i) {
            case 1:
                productText.setTextColor(getContext().getResources().getColor(R.color.black_text));
                productLine.setBackgroundResource(R.color.black);

                styleLine.setBackgroundDrawable(new ColorDrawable(TRANSPARENT));
                styleText.setTextColor(getResources().getColor(R.color.black_transparency_text));
                break;
            case 2:
                styleText.setTextColor(getContext().getResources().getColor(R.color.black_text));
                styleLine.setBackgroundResource(R.color.black);

                productLine.setBackgroundDrawable(new ColorDrawable(TRANSPARENT));
                productText.setTextColor(getResources().getColor(R.color.black_transparency_text));
                break;
        }
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
        adapter = new MyRecyclerCommonAdapter<RecommendModel.GankEntity>(getActivity(), R.layout.item_welfare_list, entity.getList()) {
            @Override
            protected void convert(final ViewHolder holder, RecommendModel.GankEntity gankEntity, int position) {
//                ((TextView) holder.getView(R.id.tvShowTime)).setText("asdsadsadasd");

                if (position == 0) {
                    holder.getView(R.id.upimg).setVisibility(View.VISIBLE);
                    holder.getView(R.id.rl_root).setVisibility(View.GONE);
                } else {
                    holder.getView(R.id.upimg).setVisibility(View.GONE);
                    holder.getView(R.id.rl_root).setVisibility(View.VISIBLE);
                }


                Glide.with(getActivity())
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
                        CommonUtils.startIntent(getActivity(), ProductDetailsActivity.class);
//                              HttpResult result1 = new HttpResult();
//                              result1.setResults(entity);
//                                Intent intent = new Intent(getActivity(), ImageShowActivity.class);
//                                intent.putExtra("pos", position);
//                                intent.putExtra("datas", result1);
//                                startActivity(intent);
//                        getActivity().overridePendingTransition(R.anim.alpha_in,
//                                R.anim.alpha_out);
                    }
                });

                holder.getView(R.id.upimg).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "我要提问了",1000).show();
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
}
