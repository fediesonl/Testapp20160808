package org.kingsunitedway.testapp20160808.Controller;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.kingsunitedway.testapp20160808.Model.mainMenuListItem;

import java.util.ArrayList;

/**
 * Created by kingsUnitedWay on 8/9/16.
 */
public class MainMenuGridViewAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<mainMenuListItem> mMainMenuListItems;

    public MainMenuGridViewAdapter(Context c, ArrayList<mainMenuListItem> mainMenuListItems){
        mContext = c;
        mMainMenuListItems = mainMenuListItems;
    }

    @Override
    public int getCount() {
        return mMainMenuListItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

//        ///Image View
//        ImageView imageView;
//        if (convertView == null) {
//            // if it's not recycled, initialize some attributes
//            imageView = new ImageView(mContext);
//            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setPadding(8, 8, 8, 8);
//        } else {
//            imageView = (ImageView) convertView;
//        }
//
//        imageView.setImageResource(mThumbIds[position]);
//        return imageView;
//        ///End Image View


        ///Text View
        TextView textView;
        if(convertView == null){
            // if it's not recycled, initialize some attributes
            textView = new TextView(mContext);
            textView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setPadding(8, 8, 8, 8);

        }else{

            textView = (TextView) convertView;

        }

        textView.setText(mMainMenuListItems.get(position).getMainMenuLabel());
        return textView;

        ///End Text View



    }



}