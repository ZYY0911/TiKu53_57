package com.example.tiku53_57.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tiku53_57.R;
import com.example.tiku53_57.bean.YHGL;
import com.example.tiku53_57.util.MyScrollView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @LogIn Name zhangyingyu
 * @Create by 张瀛煜 on 2020-08-30 at 15:30
 */
public class YHGLAdapter extends ArrayAdapter<YHGL> {
    private int width;

    public YHGLAdapter(@NonNull Context context, @NonNull List<YHGL> objects, int width) {
        super(context, 0, objects);
        this.width = width;
    }

    public interface OnClickItem {
        void click(int position, int lx);
    }

    private OnClickItem clickItem;

    public void setClickItem(OnClickItem clickItem) {
        this.clickItem = clickItem;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.yhgl_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final YHGL yhgl = getItem(position);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.WRAP_CONTENT);
        holder.layout.setLayoutParams(params);
        holder.scrollView.setOnScrollListener(new MyScrollView.OnScrollListener() {
            @Override
            public void onScroll(int scrollY) {
                if (!yhgl.isIs()) {
                    if (scrollY > 10) {
                        clickItem.click(position, 3);
                    }
                }
            }
        });

        holder.scrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (yhgl.isIs()) {
                    holder.scrollView.fullScroll(View.FOCUS_RIGHT);
                } else {
                    holder.scrollView.fullScroll(View.FOCUS_LEFT);
                }
            }
        }, 500);

        holder.tvCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItem.click(position, 1);
            }
        });
        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItem.click(position, 2);
            }
        });
        holder.tvDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItem.click(position,4);
            }
        });
        holder.tvCollect.setText(yhgl.isSc() ? "已收藏" : "收藏");
        holder.ivPhoto.setImageResource(yhgl.getSex().equals("男") ? R.mipmap.touxiang_2 : R.mipmap.touxiang_1);
        holder.tvUsername.setText("用户名：" + yhgl.getUsername());
        holder.tvName.setText("姓名：" + yhgl.getName());
        holder.tvTel.setText("电话：" + yhgl.getTel());
        holder.tvType.setText(yhgl.getRoot());
        return convertView;
    }

    static
    class ViewHolder {
        @BindView(R.id.iv_photo)
        ImageView ivPhoto;
        @BindView(R.id.tv_username)
        TextView tvUsername;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_tel)
        TextView tvTel;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_details)
        TextView tvDetails;
        @BindView(R.id.layout)
        LinearLayout layout;
        @BindView(R.id.tv_collect)
        TextView tvCollect;
        @BindView(R.id.tv_delete)
        TextView tvDelete;
        @BindView(R.id.menu)
        LinearLayout menu;
        @BindView(R.id.scroll_view)
        MyScrollView scrollView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
