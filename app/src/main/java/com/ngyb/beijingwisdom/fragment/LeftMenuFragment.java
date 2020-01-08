package com.ngyb.beijingwisdom.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ngyb.beijingwisdom.R;
import com.ngyb.beijingwisdom.constant.Constant;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/1/7 15:56
 */
public class LeftMenuFragment extends Fragment {
    private OnLeftMenuChangeListener onLeftMenuChangeListener;
    private static final String[] menuTitles = {"新闻", "专题", "组图", "互动"};
    private int lastSelectedPosition = 0;
    private ListView listView;
    private static final String TAG = "LeftMenuFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_left_menu, null);
        init(root);
        return root;
    }

    private void init(View root) {
        initView(root);
        initAdapter();
        initListener();
    }

    private void initListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (onLeftMenuChangeListener != null) {
                    onLeftMenuChangeListener.onMenuSwitch(i, i != lastSelectedPosition);
                }
                if (i == lastSelectedPosition) {
                    return;
                }
                View child = adapterView.getChildAt(i);
                child.setEnabled(true);
                View lastChild = adapterView.getChildAt(lastSelectedPosition);
                lastChild.setEnabled(false);
                lastSelectedPosition = i;
            }
        });
    }

    private void initAdapter() {
        listView.setAdapter(baseAdapter);
    }

    private void initView(View root) {
        listView = root.findViewById(R.id.list_view);
    }

    public interface OnLeftMenuChangeListener {
        void onMenuSwitch(int position, boolean isSwitch);
    }

    public void setOnLeftMenuChangeListener(OnLeftMenuChangeListener onLeftMenuChangeListener) {
        this.onLeftMenuChangeListener = onLeftMenuChangeListener;
    }

    private BaseAdapter baseAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return menuTitles.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder vh = null;
            if (view == null) {
                view = View.inflate(getContext(), R.layout.view_menu_item, null);
                vh = new ViewHolder(view);
                view.setTag(vh);
            } else {
                vh = (ViewHolder) view.getTag();
            }
            vh.tv.setText(menuTitles[i]);
            Log.e(TAG, "getView: " + i);
            vh.tv.setEnabled(i == 0);
            return view;
        }

        class ViewHolder {
            private final TextView tv;

            ViewHolder(View view) {
                tv = view.findViewById(R.id.tv);
            }
        }
    };

}

