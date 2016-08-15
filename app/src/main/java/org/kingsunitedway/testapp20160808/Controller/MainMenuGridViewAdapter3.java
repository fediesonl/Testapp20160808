package org.kingsunitedway.testapp20160808.Controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.kingsunitedway.testapp20160808.Model.mainMenuListItem;
import org.kingsunitedway.testapp20160808.R;

import java.util.ArrayList;

/**
 * Created by kingsUnitedWay on 8/15/16.
 */
public class MainMenuGridViewAdapter3 extends RecyclerView.Adapter<MainMenuGridViewAdapter3.MainMenuViewHolder> {

    private Context mContext;
    private ArrayList<mainMenuListItem> mMainMenuListItems;

    public MainMenuGridViewAdapter3(Context context, ArrayList<mainMenuListItem> mainMenuListItems){

        this.mMainMenuListItems = mainMenuListItems;
        this.mContext = context;

    }


    @Override
    public MainMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_menu_grid_item, parent, false);
        return new MainMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainMenuViewHolder holder, int position) {
        holder.bindMainMenu(mMainMenuListItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mMainMenuListItems.size();
    }



    public class MainMenuViewHolder extends RecyclerView.ViewHolder {

        public TextView mMainMenuText;

        public MainMenuViewHolder(View itemView) {
            super(itemView);

            mMainMenuText = (TextView) itemView.findViewById(R.id.main_menu_text);

        }

        public void bindMainMenu(mainMenuListItem mainMenuListItem){

            mMainMenuText.setText(mainMenuListItem.getMainMenuLabel());

        }

    }


}
