package com.tuto.mareu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ColorSpinnerAdapter extends BaseAdapter {

    private Context context;
    private int[] colorList;


    public ColorSpinnerAdapter(Context context, int[] colorList) {
        this.context = context;
        this.colorList = colorList;
    }

    @Override
    public int getCount() {
        return colorList.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View rootView = LayoutInflater.from(context)
                .inflate(R.layout.item_color, viewGroup, false);
        ImageView image = rootView.findViewById(R.id.colorItem);
        image.setImageResource(colorList[position]);
        return rootView;
    }
}
