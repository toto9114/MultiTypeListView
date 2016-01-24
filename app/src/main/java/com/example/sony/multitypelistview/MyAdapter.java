package com.example.sony.multitypelistview;

import android.content.ClipData;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sony on 2016-01-22.
 */
public class MyAdapter extends BaseAdapter {
    List<SectionData> items = new ArrayList<SectionData>();
 //   List<MultiType> total = new ArrayList<MultiType>();
    private static final String TAG = "MyAdapter";
    private static final int IDS_VIEW_TYPE_COUNT = 2;
    private static final int TYPE_SECTION = 0;
    private static final int TYPE_ITEM = 1;

    public void add(String sectionTitle, String itemTitle) {
        SectionData section = null;
        for (SectionData s : items) {
            if (s.title.equals(sectionTitle)) {
                section = s;
                break;
            }
        }
        if (section == null) {
            section = new SectionData();
            section.title = sectionTitle;
            items.add(section);
        }
        if (!TextUtils.isEmpty(itemTitle)) {
            ItemData item = new ItemData();
            item.title = itemTitle;
            section.itemlist.add(item);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        for (int i = 0; i < items.size(); i++) {
            SectionData section = items.get(i);
            if (position < 1) {
                return TYPE_SECTION;
            }
            position--;
            int itemCount = section.itemlist.size();
            if (position < itemCount) {
                return TYPE_ITEM;
            }
            position -= itemCount;
        }
        return super.getItemViewType(position);

    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getViewTypeCount() {
        return IDS_VIEW_TYPE_COUNT;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(TAG, "getViewPosition..:" + position);
        switch (getItemViewType(position)) {
            case TYPE_SECTION: {
                Log.i(TAG, "section");
                SectionView view;
                if (convertView == null) {
                    view = new SectionView(parent.getContext());
                } else {
                    view = (SectionView) convertView;
                }
                for (int i = 0; i < items.size(); i++) {
                    if (position < 1) {
                        Log.i(TAG, "Group Position: " + position + ", data: "+ items.get(i).title);
                        view.setTitle(items.get(i));
                        return view;
                    }
                    position--;
                    int childCount = items.get(i).itemlist.size();
                    position -= childCount;
                }
            }
            case TYPE_ITEM:
            default:{
                ItemView view;
                if (convertView == null) {
                    view = new ItemView(parent.getContext());
                } else {
                    view = (ItemView) convertView;
                }
                for (int i = 0; i < items.size(); i++) {
                    position--;
                    int childCount = items.get(i).itemlist.size();
                    if (position < childCount) {
                        ItemData data = items.get(i).itemlist.get(position);
                        Log.i(TAG, "Child Position:  " + position + ", data: "+data.title);
                        view.setTitle(data);
                        return view;
                    }
                    position -= childCount;
                }

            }
        }
        return null;
    }
}

