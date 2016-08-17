package org.kingsunitedway.testapp20160808.Controller;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import org.kingsunitedway.testapp20160808.Model.mainMenuListItem;
import org.kingsunitedway.testapp20160808.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private DatabaseReference mDatabase;
    private ArrayList<mainMenuListItem> mMainMenuListItems = new ArrayList<mainMenuListItem> ();
    private MainMenuGridViewAdapter3 mMainMenuGridViewAdapter3;
    private int mDevicePixelWidth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayMetrics();
        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://211.firebaseio.com/MainMenu/USCAKings-Tulare/");
        mDatabase.addValueEventListener(mainMenuListener);
        initViews();




    }

    private void displayMetrics() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        mDevicePixelWidth = displayMetrics.widthPixels;
//        int deviceHeight = displayMetrics.heightPixels;

    }


    //Firebase
    ValueEventListener mainMenuListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {


            Log.v(TAG, "Erase arraylist");
            mMainMenuListItems.clear();

            ArrayList mainMenuSnapshot = (ArrayList) dataSnapshot.getValue();


            for (Object Item : mainMenuSnapshot) {

                Map<String, String> mainMenuSnapshotItem = (Map<String, String>) Item;


                if (mainMenuSnapshotItem.get("hidden").equals("false")) {

                    String order = mainMenuSnapshotItem.get("order");
                    String label = mainMenuSnapshotItem.get("label");
                    String icon = mainMenuSnapshotItem.get("icon");
                    String hex = mainMenuSnapshotItem.get("hex");

                    mainMenuListItem mainMenuObject = new mainMenuListItem(order, label, icon, hex);

                    mMainMenuListItems.add(mainMenuObject);

                }


            }




            Collections.sort(mMainMenuListItems, new Comparator<mainMenuListItem>() {
                @Override
                public int compare(mainMenuListItem order1, mainMenuListItem order2) {
                    return (order1.getMainMenuOrder().compareTo(order2.getMainMenuOrder()));
                }
            });
            mMainMenuGridViewAdapter3.notifyDataSetChanged();

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // Getting Post failed, log a message
            Log.w("loadPost:onCancelled", databaseError.toException());

        }

    };

    //learn2crack implementation
    private void initViews(){

        int menuColumns;

        if (mDevicePixelWidth < 500){
            menuColumns = 2;
        }else if (mDevicePixelWidth > 1500){
            menuColumns = 4;
        }else{
            menuColumns = 3;
        }

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.main_menu_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager (getApplicationContext(),menuColumns);
        recyclerView.setLayoutManager(layoutManager);

        mMainMenuGridViewAdapter3 = new MainMenuGridViewAdapter3(getApplicationContext(), mMainMenuListItems, mDevicePixelWidth);
        recyclerView.setAdapter(mMainMenuGridViewAdapter3);

    }





}
