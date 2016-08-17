package org.kingsunitedway.testapp20160808.Controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import org.kingsunitedway.testapp20160808.Model.mainMenuListItem;
import org.kingsunitedway.testapp20160808.R;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by kingsUnitedWay on 8/15/16.
 */
public class MainMenuGridViewAdapter3 extends RecyclerView.Adapter<MainMenuGridViewAdapter3.MainMenuViewHolder> {

    public static final String TAG = MainMenuGridViewAdapter3.class.getSimpleName();

    private Context mContext;
    private ArrayList<mainMenuListItem> mMainMenuListItems;
    private FirebaseStorage mFirebaseStorage = FirebaseStorage.getInstance();
    private StorageReference mStorageReference = mFirebaseStorage.getReferenceFromUrl("gs://project-4595746698222237897.appspot.com/");
    private int mDevicePixelWidth;

    public MainMenuGridViewAdapter3(Context context, ArrayList<mainMenuListItem> mainMenuListItems, int devicePixelWidth){

        this.mMainMenuListItems = mainMenuListItems;
        this.mContext = context;
        this.mDevicePixelWidth = devicePixelWidth;

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
        public ImageView mMainMenuIcon;


        public MainMenuViewHolder(View itemView) {
            super(itemView);

            mMainMenuText = (TextView) itemView.findViewById(R.id.main_menu_text);
            mMainMenuIcon = (ImageView) itemView.findViewById(R.id.main_menu_icon);





        }

        public void bindMainMenu(final mainMenuListItem mainMenuListItem){




            //Set Image

                //Call firebase
                StorageReference pathReference = mStorageReference.child("mainmenu/"+ mainMenuListItem.getMainMenuIcon());


            //Load images
            ///Download to memory
                final long ONE_MEGABYTE = 1024 * 1024;
                pathReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        // Data for "mainmenu/" is returned, use this as needed
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        mMainMenuIcon.setImageBitmap(bitmap);


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
                        Log.e(TAG, "icon not loaded from Firebase");
                    }
                });
            ///Download to memory

            mMainMenuText.setText(mainMenuListItem.getMainMenuLabel());
            if (mDevicePixelWidth > 1500){
                mMainMenuText.setTextSize(24);
            }

            itemView.setBackgroundColor(Color.parseColor("#" + mainMenuListItem.getMainMenuHex()));



        }

    }


}
