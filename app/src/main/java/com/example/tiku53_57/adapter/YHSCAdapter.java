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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @LogIn Name zhangyingyu
 * @Create by 张瀛煜 on 2020-08-30 at 22:33
 */
public class YHSCAdapter extends ArrayAdapter<YHGL> {
    public YHSCAdapter(@NonNull Context context, @NonNull List<YHGL> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.wdsc_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        YHGL yhgl =getItem(position);
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

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
