package com.example.a.myapplication.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.a.myapplication.BaseActivity;
import com.example.a.myapplication.R;
import com.example.a.myapplication.activity.WheelPicker.DatePicker;
import com.example.a.myapplication.bean.BaseModel;
import com.example.a.myapplication.http.OkHttpUtil;
import com.example.a.myapplication.lib.view.LoopView;
import com.example.a.myapplication.util.CommonUtils;
import com.example.a.myapplication.util.Config;
import com.example.a.myapplication.util.PopupUtil;
import com.example.a.myapplication.util.Preference;
import com.example.a.myapplication.util.UIUtils;
import com.example.a.myapplication.view.TitleView1;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/8.
 * 更换资料
 */
public class MyProFileActivity extends BaseActivity {

    final static String IMAGE_FILE_NAME = "faceImage.jpg";

    /* 请求码 */
    private static final int IMAGE_REQUEST_CODE = 0;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int RESULT_REQUEST_CODE = 2;

    @InjectView(R.id.title_layout)
    protected RelativeLayout titleView;

    @InjectView(R.id.name_text)
    protected TextView name;

    @InjectView(R.id.sex_text)
    protected TextView sex;

    @InjectView(R.id.date_text)
    protected TextView date;

    @InjectView(R.id.head)
    protected ImageView head;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_myprofile;
    }

    @Override
    protected void initView() {
        VERSION();
        initTitle();
        name.setText(Preference.get(Config.NAME, ""));

        String str = Preference.get(Config.SEX, "");

        if (str.equals("1")) {
            str = "男";
        } else if (str.equals("2")) {
            str = "女";
        } else {
            str = "保密";
        }
        sex.setText(str);
        date.setText(Preference.get(Config.AGE, ""));

        Glide.with(UIUtils.getContext()).load(Config.hostImgString + Preference.get(Config.HEAD, "")).asBitmap().centerCrop().into(new BitmapImageViewTarget(head) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(UIUtils.getContext().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                head.setImageDrawable(circularBitmapDrawable);
            }
        });
    }


    /**
     * 标题初始化
     */
    private void initTitle() {
        TitleView1 view = new TitleView1(this);
        titleView.addView(view.getView());
        view.setTitleText("更换资料", "保存");
        view.setTitleOnClickListeneRight(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String ava = "";
                if (mBitmap != null) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    ava = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
                }
                Map<String, String> par = CommonUtils.getMapParm();
                par.put("userid", Preference.get(Config.ID, ""));
                if (!TextUtils.isEmpty(ava)) {
                    par.put("imgid", ava);
                }
                par.put("nickname", name.getText().toString());


                String str = sex.getText().toString();

                if (str.equals("男")) {
                    str = 1 + "";
                } else if (str.equals("女")) {
                    str = 2 + "";
                } else {
                    str = 0 + "";
                }

                par.put("sex", str);
                par.put("age", date.getText().toString());

                OkHttpUtil.getInstance().addRequestPost(Config.saveProfile, par, new OkHttpUtil.HttpCallBack<BaseModel>() {


                    @Override
                    public void onSuccss(BaseModel baseModel) {
                        EventBus.getDefault().post(baseModel);
                    }

                    @Override
                    public void onFailure(String error) {

                    }
                });
            }
        });
    }


    @Override
    public void onEventMainThread(Object obj) {
        super.onEventMainThread(obj);
        if (obj instanceof BaseModel) {
            BaseModel baseModel = (BaseModel) obj;
            Toast.makeText(this, baseModel.getM() + "", Toast.LENGTH_SHORT).show();
            if (baseModel.getC() == 1) {
                Preference.put(Config.NAME, name.getText().toString());

                String str = sex.getText().toString();

                if (str.equals("男")) {
                    str = 1 + "";
                } else if (str.equals("女")) {
                    str = 2 + "";
                } else {
                    str = 0 + "";
                }

                Preference.put(Config.SEX, str);
                Preference.put(Config.AGE, date.getText().toString());
                finish();
            }


        }
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.head_layout, R.id.name_layout, R.id.sex_layout, R.id.date_layout})
    protected void onClick(View v) {
        switch (v.getId()) {
            case R.id.head_layout://头像
                showDialog(this);
                break;
            case R.id.name_layout://姓名
                dialogName();
                break;
            case R.id.sex_layout://性别
                dialogSex();
                break;
            case R.id.date_layout://生日
                dialogAge();
                break;
        }
    }

    /**
     * 弹出生日
     */
    private void dialogAge() {
        final DatePicker datePicker = new DatePicker(this);
        datePicker.setRange(1900, 2017);
        datePicker.setSelectedItem(2017, 1, 1);

        datePicker.setOnDatePickListener(
                new DatePicker.OnYearMonthDayPickListener() {

                    @Override
                    public void onDatePicked(String year, String month, String day) {
                        date.setText(year + "-" + month + "-" + day);
                    }
                });
        datePicker.show();

    }

    /**
     * 弹出输入文字
     */
    private void dialogSex() {
        PopupUtil.showInBottom(this, R.layout.dialog_selecor, new PopupUtil.ShowListener() {
            @Override
            public void onShow(View view, final PopupWindow popupWindow) {
                final LoopView loopView = (LoopView) view.findViewById(R.id.sex);
                TextView no = (TextView) view.findViewById(R.id.no);
                TextView yes = (TextView) view.findViewById(R.id.yes);
                final ArrayList<String> strings = new ArrayList<String>();
                strings.add("男");
                strings.add("女");
                loopView.setItems(strings);
                loopView.setNotLoop();
                loopView.setTextSize(28);


                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sex.setText(strings.get(loopView.getSelectedItem()));
                        popupWindow.dismiss();
                    }
                });

                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
            }
        });
    }

    /**
     * 弹出输入文字
     */
    private void dialogName() {
        PopupUtil.showInCenter(this, R.layout.dialog_addname, new PopupUtil.ShowListener() {
            @Override
            public void onShow(View view, final PopupWindow popupWindow) {
                final EditText nameView = (EditText) view.findViewById(R.id.name_edit);
                TextView yes = (TextView) view.findViewById(R.id.yes);
                TextView no = (TextView) view.findViewById(R.id.no);

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(nameView.getText().toString())) {
                            Toast.makeText(MyProFileActivity.this, "请输入姓名", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        name.setText(nameView.getText().toString());
                        popupWindow.dismiss();
                    }
                });

                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
            }
        });
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


        if (requestCode == 123) {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // 检查该权限是否已经获取
                int i = ContextCompat.checkSelfPermission(this, permissions[0]);
                // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
                if (i != PackageManager.PERMISSION_GRANTED) {
                    // 提示用户应该去应用设置界面手动开启权限
                    showDialogTipUserGoToAppSettting();
                } else {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                }
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

    //----------------------------------

    public void VERSION() {
        // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // 检查该权限是否已经获取
            int i = ContextCompat.checkSelfPermission(this, permissions[0]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (i != PackageManager.PERMISSION_GRANTED) {
                // 如果没有授予该权限，就去提示用户请求
                showDialogTipUserRequestPermission();
            }
        }

    }


    // 要申请的权限
    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    private AlertDialog dialog;


    // 提示用户该请求权限的弹出框
    private void showDialogTipUserRequestPermission() {

        new AlertDialog.Builder(this)
                .setTitle("存储权限不可用")
                .setMessage("由于需要保存图片，为你存储个人照片；\n否则，您将无法正常使用相机")
                .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startRequestPermission();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setCancelable(false).show();
    }

    // 开始提交请求权限
    private void startRequestPermission() {
        ActivityCompat.requestPermissions(this, permissions, 321);
    }

    // 用户权限 申请 的回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 321) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    // 判断用户是否 点击了不再提醒。(检测该权限是否还可以申请)
                    boolean b = shouldShowRequestPermissionRationale(permissions[0]);
                    if (!b) {
                        // 用户还是想用我的 APP 的
                        // 提示用户去应用设置界面手动开启权限
                        showDialogTipUserGoToAppSettting();
                    } else
                        finish();
                } else {
                    Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    // 提示用户去应用设置界面手动开启权限

    private void showDialogTipUserGoToAppSettting() {

        dialog = new AlertDialog.Builder(this)
                .setTitle("存储权限不可用")
                .setMessage("请在-应用设置-权限-中，允许支付宝使用存储权限来保存用户数据")
                .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 跳转到应用设置界面
                        goToAppSetting();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setCancelable(false).show();
    }

    // 跳转到当前应用的设置界面
    private void goToAppSetting() {
        Intent intent = new Intent();

        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);

        startActivityForResult(intent, 123);
    }


}
