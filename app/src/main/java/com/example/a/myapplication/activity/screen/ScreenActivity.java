package com.example.a.myapplication.activity.screen;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.ScreenBrandModel;
import com.example.a.myapplication.bean.ScreenCategoryTowModel;
import com.example.a.myapplication.bean.ScreenColorModel;
import com.example.a.myapplication.bean.ScreenFashionModel;
import com.example.a.myapplication.bean.ScreenStyleModel;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.view.TitleView1;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/9.
 * 筛选
 */
public class ScreenActivity extends BaseActivity {

    private final int BRAND = 0x0001;
    public final static int CATEGORY = 0x0002;
    private final int FASHION = 0x0003;
    private final int COLOR = 0x0004;
    private final int STYLE = 0x0005;

    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    @InjectView(R.id.category_icon)
    protected ImageView categoryIcon;

    @InjectView(R.id.category_text)
    protected TextView categoryText;


    @InjectView(R.id.fashion_icon)
    protected ImageView fashionIcon;

    @InjectView(R.id.fashion_text)
    protected TextView fashionText;


    @InjectView(R.id.color_icon)
    protected ImageView colorIcon;

    @InjectView(R.id.color_text)
    protected TextView colorText;


    @InjectView(R.id.brand_icon)
    protected ImageView brandIcon;

    @InjectView(R.id.brand_text)
    protected TextView brandText;


    @InjectView(R.id.style_icon)
    protected ImageView styleIcon;

    @InjectView(R.id.style_text)
    protected TextView styleText;


    private String brands = "";//品牌id /
    private String categorys = "";//品类id   /
    private String colors = "";//颜色id  /
    private String populars = "";//流行id/
    private String idols = "";//风格偶像id  /

    @Override
    protected int getLayoutID() {
        return R.layout.activity_screen;
    }

    @Override
    protected void initView() {
        initTitle();
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
        view.setTitleText("筛选", "完成");
        view.setTitleOnClickListeneRight(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("brand", brands);
                intent.putExtra("categorys", categorys);
                intent.putExtra("colors", colors);
                intent.putExtra("populars", populars);
                intent.putExtra("idols", idols);
                setResult(1000, intent);
                finish();
            }
        });
    }

    @OnClick({R.id.brand_layout, R.id.category_layout, R.id.fashion_layout, R.id.color_layout, R.id.style_layout})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.brand_layout: //品牌
                CommonUtils.startIntent(this, ScreenBrandActivity.class, BRAND);
                break;
            case R.id.category_layout: //品类
                CommonUtils.startIntent(this, ScreenCategoryActivity.class, CATEGORY);
                break;
            case R.id.fashion_layout: //流行
                CommonUtils.startIntent(this, ScreenFashionActivity.class, FASHION);
                break;
            case R.id.color_layout: //颜色
                CommonUtils.startIntent(this, ScreenColorActivity.class, COLOR);
                break;
            case R.id.style_layout: //风格偶像
                CommonUtils.startIntent(this, ScreenStyleActivity.class, STYLE);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("onActivityResult", requestCode + "onActivityResult" + resultCode);
        if (resultCode == 0x0001) {
            if (requestCode == CATEGORY) {//品类/
                ScreenCategoryTowModel towMode = (ScreenCategoryTowModel) data.getSerializableExtra("category");
                String string = "";
                if (towMode != null) {
                    categoryText.setVisibility(View.VISIBLE);
                    categoryIcon.setVisibility(View.GONE);
                    for (int i = 0; i < towMode.getO().size(); i++) {
                        string = string + towMode.getO().get(i).getName() + ",";
                        categorys = categorys + towMode.getO().get(i).getId() + ",";
//                       string=  categorys.substring(0,string.length()-1);
                    }
                    if (!TextUtils.isEmpty(brands) && brands.length() > 0) {
                        categorys = categorys.substring(0, categorys.length() - 1);
                    } else {
                        categoryText.setVisibility(View.GONE);
                        categoryIcon.setVisibility(View.VISIBLE);
                    }
                    if (!TextUtils.isEmpty(brands) && brands.length() > 0) {
                        string = string.substring(0, string.length() - 1);
                    }
                    categoryText.setText(string);
                } else {
                    categoryText.setVisibility(View.GONE);
                    categoryIcon.setVisibility(View.VISIBLE);
                }
            }
            if (requestCode == FASHION) {//流行
                ScreenFashionModel towMode = (ScreenFashionModel) data.getSerializableExtra("fashion");
                String string = "";
                if (towMode != null) {
                    fashionIcon.setVisibility(View.GONE);
                    fashionText.setVisibility(View.VISIBLE);
                    for (int i = 0; i < towMode.getO().size(); i++) {
                        string = string + towMode.getO().get(i).getName() + ",";
                        populars = populars + towMode.getO().get(i).getId() + ",";
//                        populars=  populars.substring(0,populars.length()-1);
                    }
                    if (!TextUtils.isEmpty(brands) && brands.length() > 0) {
                        populars = populars.substring(0, populars.length() - 1);
                    } else {
                        fashionIcon.setVisibility(View.VISIBLE);
                        fashionText.setVisibility(View.GONE);
                    }
                    if (!TextUtils.isEmpty(brands) && brands.length() > 0) {
                        string = string.substring(0, string.length() - 1);
                    }
                    fashionText.setText(string);
                } else {
                    fashionIcon.setVisibility(View.VISIBLE);
                    fashionText.setVisibility(View.GONE);
                }


            }

            if (requestCode == COLOR) { //颜色
                ScreenColorModel towMode = (ScreenColorModel) data.getSerializableExtra("color");
                String string = "";
                if (towMode != null) {
                    colorIcon.setVisibility(View.GONE);
                    colorText.setVisibility(View.VISIBLE);
                    for (int i = 0; i < towMode.getO().size(); i++) {
                        string = string + towMode.getO().get(i).getName() + ",";
                        colors = colors + towMode.getO().get(i).getId() + ",";
//                        string=  brands.substring(0,string.length()-1);
                    }
                    if (!TextUtils.isEmpty(colors) && colors.length() > 0) {
                        colors = colors.substring(0, colors.length() - 1);
                    } else {
                        colorIcon.setVisibility(View.VISIBLE);
                        colorText.setVisibility(View.GONE);
                    }
                    if (!TextUtils.isEmpty(string) && string.length() > 0) {
                        string = string.substring(0, string.length() - 1);
                    }
                    colorText.setText(string);
                } else {
                    colorIcon.setVisibility(View.VISIBLE);
                    colorText.setVisibility(View.GONE);
                }

            }

            if (requestCode == BRAND) {//品牌 /
                ScreenBrandModel towMode = (ScreenBrandModel) data.getSerializableExtra("brand");
                String string = "";
                if (towMode != null) {
                    brandIcon.setVisibility(View.GONE);
                    brandText.setVisibility(View.VISIBLE);
                    for (int i = 0; i < towMode.getO().size(); i++) {
                        string = string + towMode.getO().get(i).getName() + ",";
                        brands = brands + towMode.getO().get(i).getId() + ",";

                    }

                    if (!TextUtils.isEmpty(brands) && brands.length() > 0) {
                        brands = brands.substring(0, brands.length() - 1);
                    } else {
                        brandIcon.setVisibility(View.VISIBLE);
                        brandText.setVisibility(View.GONE);
                    }
                    if (!TextUtils.isEmpty(string) && string.length() > 0) {
                        string = string.substring(0, string.length() - 1);
                    }
                    brandText.setText(string);
                } else {
                    brandIcon.setVisibility(View.VISIBLE);
                    brandText.setVisibility(View.GONE);
                }

            }

            if (requestCode == STYLE) {//风格偶像 /
                ScreenStyleModel towMode = (ScreenStyleModel) data.getSerializableExtra("style");
                String string = "";
                if (towMode != null) {
                    styleIcon.setVisibility(View.GONE);
                    styleText.setVisibility(View.VISIBLE);
                    for (int i = 0; i < towMode.getO().size(); i++) {
                        string = string + towMode.getO().get(i).getName() + ",";
                        idols = idols + towMode.getO().get(i).getId() + ",";

                    }
                    if (!TextUtils.isEmpty(string) && string.length() > 0) {
                        string = string.substring(0, string.length() - 1);
                    } else {
                        styleIcon.setVisibility(View.VISIBLE);
                        styleText.setVisibility(View.GONE);
                    }
                    if (!TextUtils.isEmpty(idols) && idols.length() > 0) {
                        idols = idols.substring(0, idols.length() - 1);
                    }


                    styleText.setText(string);
                } else {
                    styleIcon.setVisibility(View.VISIBLE);
                    styleText.setVisibility(View.GONE);
                }

            }
        }

    }
}
