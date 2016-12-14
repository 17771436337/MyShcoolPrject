package com.example.a.myapplication.view;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a.myapplication.BaseView;
import com.example.a.myapplication.R;


public class BottomView extends BaseView {

    private RelativeLayout mBottomLayout[];

    private ImageView icon[];

    private TextView text[];


    private OnItemClickListener mItemClickListener;

    public BottomView(Context context) {
        super(context);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mItemClickListener = onItemClickListener;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.view_bottom;
    }

    @Override
    protected void initView() {
        mBottomLayout = new RelativeLayout[4];
        mBottomLayout[0] = (RelativeLayout) mView.findViewById(R.id.b1_layout);
        mBottomLayout[1] = (RelativeLayout) mView.findViewById(R.id.b2_layout);

        mBottomLayout[2] = (RelativeLayout) mView.findViewById(R.id.b3_layout);
        mBottomLayout[3] = (RelativeLayout) mView.findViewById(R.id.b4_layout);


        icon = new ImageView[4];
        icon[0] = (ImageView) mView.findViewById(R.id.icon1);
        icon[1] = (ImageView) mView.findViewById(R.id.icon2);

        icon[2] = (ImageView) mView.findViewById(R.id.icon3);
        icon[3] = (ImageView) mView.findViewById(R.id.icon4);

        text = new TextView[4];
        text[0] = (TextView) mView.findViewById(R.id.text1);
        text[1] = (TextView) mView.findViewById(R.id.text2);

        text[2] = (TextView) mView.findViewById(R.id.text3);
        text[3] = (TextView) mView.findViewById(R.id.text4);

        for (int i = 0; i < 4; i++) {
            final int p = i;
            mBottomLayout[i].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(null, null, p, 0);
                    }

                    onSelect(p);
                }
            });
        }
        onSelect(0);
    }

    public void onSelect(int position) {

        for (int i = 0; i < 4; i++) {
            text[i].setTextColor(mContext.getResources()
                    .getColor(R.color.black_text));
        }
        text[position].setTextColor(mContext.getResources().getColor(
                R.color.black_transparency_text));

        icon[0].setBackgroundResource(R.drawable.icon_tab1_unselect);
        icon[1].setBackgroundResource(R.drawable.icon_tab2_unselect);
        icon[2].setBackgroundResource(R.drawable.icon_tab3_unselect);
        icon[3].setBackgroundResource(R.drawable.icon_tab4_unselect);

        switch (position) {
            case 0:
                icon[0].setBackgroundResource(R.drawable.icon_tab1_select);
                break;
            case 1:
                icon[1].setBackgroundResource(R.drawable.icon_tab2_select);
                break;
            case 2:
                icon[2].setBackgroundResource(R.drawable.icon_tab3_select);
                break;
            case 3:
                icon[3].setBackgroundResource(R.drawable.icon_tab4_select);
                break;

            default:
                break;
        }
    }


}
