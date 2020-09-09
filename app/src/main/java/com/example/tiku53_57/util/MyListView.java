package com.example.tiku53_57.util;

import android.content.Context;
import android.icu.util.Measure;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tiku53_57.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @LogIn Name zhangyingyu
 * @Create by 张瀛煜 on 2020-08-28 at 15:33
 */
public class MyListView extends ListView {
    private View headerView;
    private ViewHolder holder;
    private int headerHeight;

    public MyListView(Context context) {
        super(context);
        initHeader();
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHeader();
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initHeader();
    }

    private void initHeader() {
        headerView = View.inflate(getContext(), R.layout.reflash_hade, null);
        holder = new ViewHolder(headerView);
        headerView.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        headerHeight = headerView.getMeasuredHeight();
        headerView.setPadding(0, -headerHeight, 0, 0);
        addHeaderView(headerView);
    }

    private int downY, moveY;
    private boolean isUpdate;
    public static final int DOWN_REFRESH = 1;
    public static final int RELEASE_REFRESH = 2;
    public static final int REFRESHING = 3;
    private int currentStateHeader = DOWN_REFRESH;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!isUpdate) {
                    moveY = (int) ev.getY();
                    int instance = moveY - downY;
                    holder.ivImage.setVisibility(VISIBLE);
                    holder.pbBar.setVisibility(GONE);
                    if (instance > 0 && getFirstVisiblePosition() == 0
                            && currentStateHeader != REFRESHING) {
                        int topPadding = -headerHeight + instance;
                        if (topPadding < 20) {
                            currentStateHeader = DOWN_REFRESH;
                            holder.tvTip.setText("下拉刷新");
                            holder.ivImage.animate().rotation(0).setDuration(500);
                        } else if (topPadding > 20) {
                            currentStateHeader = RELEASE_REFRESH;
                            holder.tvTip.setText("即将刷新");
                            holder.ivImage.animate().rotation(180).setDuration(500);
                        }
                        headerView.setPadding(0, instance, 0, 0);
                    }else {
                        currentStateHeader=-1;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (currentStateHeader == RELEASE_REFRESH) {
                    currentStateHeader = REFRESHING;
                    headerView.setPadding(0, 0, 0, 0);
                    holder.tvTip.setText("正在刷新");
                    isUpdate = true;
                    holder.ivImage.setVisibility(GONE);
                    holder.pbBar.setVisibility(VISIBLE);
                    if (onRefreshListener != null) {
                        onRefreshListener.refresh();
                    }
                } else if (currentStateHeader == DOWN_REFRESH) {
                    headerView.setPadding(0, -headerHeight, 0, 0);
                }
                break;

        }
        return super.onTouchEvent(ev);
    }

    public interface OnRefreshListener {
        void refresh();
    }

    private OnRefreshListener onRefreshListener;

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }


    public void finishRefresh() {
        holder.tvTip.setText("刷新完成");
        holder.pbBar.setVisibility(GONE);
        holder.ivImage.setVisibility(VISIBLE);
        holder.ivImage.setImageResource(R.mipmap.shuxinwanche);
        holder.ivImage.animate().rotation(0).setDuration(100);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                headerView.setPadding(0, -headerHeight, 0, 0);
                currentStateHeader = DOWN_REFRESH;
                holder.ivImage.setImageResource(R.mipmap.jiantou);
                isUpdate = false;
            }
        }, 2000);
    }

    static
    class ViewHolder {
        @BindView(R.id.tv_tip)
        TextView tvTip;
        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.pb_bar)
        ProgressBar pbBar;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
