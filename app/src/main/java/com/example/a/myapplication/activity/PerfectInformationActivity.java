package com.example.a.myapplication.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.bean.BaseModel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.Preference;
import com.example.a.myapplication.util.UIUtils;
import com.example.a.myapplication.view.TitleView1;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Map;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/9.
 * 完善资料
 */
public class PerfectInformationActivity extends BaseActivity {

    final static String IMAGE_FILE_NAME = "faceImage.jpg";

    /* 请求码 */
    private static final int IMAGE_REQUEST_CODE = 0;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int RESULT_REQUEST_CODE = 2;

    DisplayImageOptions mOptions;

    @InjectView(R.id.head)
    protected ImageView head;


    @InjectView(R.id.name_edit)
    protected EditText nameEditText;


    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_perfect_information;
    }

    @Override
    protected void initView() {
        mOptions = UIUtils.getRoundedDisplayOptions(R.drawable.default_head);
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
        view.setTitleText("完善资料", "");
    }


    @OnClick({R.id.head, R.id.next})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.head:
                showDialog(this);

                break;
            case R.id.next:

                String name = nameEditText.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
                    return;
                }

                String ava = "";
                if (mBitmap != null) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    ava = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
                } else {
                    Toast.makeText(this, "请选择头像", Toast.LENGTH_SHORT).show();
                    return;
                }

                Map<String, String> par = CommonUtils.getMapParm();
                par.put("userid", Preference.get(Config.ID, ""));
                if (!TextUtils.isEmpty(ava)) {
                    par.put("imgid", ava);
                }
                par.put("nickname", name);


                OkHttpUtil.getInstance().addRequestPost(Config.saveProfile, par, new OkHttpUtil.HttpCallBack<BaseModel>() {


                    @Override
                    public void onSuccss(BaseModel baseModel) {
                        EventBus.getDefault().post(baseModel);
                    }

                    @Override
                    public void onFailure(String error) {

                    }
                });
                break;
        }
    }


    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);
        if (obj instanceof BaseModel) {
            BaseModel baseModel = (BaseModel) obj;
            Toast.makeText(this, baseModel.getM() + "", Toast.LENGTH_SHORT).show();
            if (baseModel.getC() == 1) {
                finish();
            }


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // 结果码不等于取消时候
        if (resultCode != RESULT_CANCELED) {

            switch (requestCode) {
                case IMAGE_REQUEST_CODE:
                    resizeImage(data.getData());

                    try {
                        mBitmap = MediaStore.Images.Media.getBitmap(
                                this.getContentResolver(), data.getData());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

//                    resizeImage(getImageUri());
//                    ImageLoader.getInstance().displayImage(
//                            data.getData().toString(), head, mOptions);

                    break;
                case CAMERA_REQUEST_CODE:
                    if (hasSdcard()) {
                        resizeImage(getImageUri());
                    } else {
                        Toast.makeText(this, "未找到存储卡，无法存储照片！",
                                Toast.LENGTH_LONG).show();
                    }

                    break;
                case RESULT_REQUEST_CODE:
                    if (data != null) {
                        // getImageToView(data);
                        showResizeImage(data);
                    }
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }


    //------------------------------

    Bitmap mBitmap;

    private static void showDialog(final Activity activity) {
        final String[] items = new String[]{"选择本地图片", "拍照"};

        new AlertDialog.Builder(activity)
                .setTitle("设置头像")
                .setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent galleryIntent = new Intent(
                                        Intent.ACTION_GET_CONTENT);
                                galleryIntent.addCategory(Intent.CATEGORY_OPENABLE);

                                galleryIntent.setType("image/*");
                                activity.startActivityForResult(galleryIntent,
                                        IMAGE_REQUEST_CODE);
                                break;
                            case 1:
                                Intent cameraIntent = new Intent(
                                        "android.media.action.IMAGE_CAPTURE");
                                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                                        .fromFile(new File(Environment
                                                .getExternalStorageDirectory(),
                                                IMAGE_FILE_NAME)));
                                cameraIntent.putExtra(
                                        MediaStore.EXTRA_VIDEO_QUALITY, 0);
                                activity.startActivityForResult(cameraIntent,
                                        CAMERA_REQUEST_CODE);
                                break;
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

    }

    public boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    public void resizeImage(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, RESULT_REQUEST_CODE);
    }

    private Uri getImageUri() {
        return Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                IMAGE_FILE_NAME));
    }

    private void showResizeImage(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            mBitmap = photo;
            Uri rUri = Uri.parse(MediaStore.Images.Media.insertImage(
                    getContentResolver(), mBitmap, null, null));
            ImageLoader.getInstance().displayImage(rUri.toString(),
                    head);
        }
    }
}
