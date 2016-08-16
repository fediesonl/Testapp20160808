package org.kingsunitedway.testapp20160808.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private DatabaseReference mDatabase;
    private ArrayList<mainMenuListItem> mMainMenuListItems = new ArrayList<mainMenuListItem> ();
    private MainMenuGridViewAdapter3 mMainMenuGridViewAdapter3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://211.firebaseio.com/MainMenu/USCAKings-Tulare/");
        mDatabase.addValueEventListener(mainMenuListener);
        initViews();




    }

// Set up Grid View adapter
    private void setUpGridViewAdapter(){
        //GridView set up
//        GridView gridview = (GridView) findViewById(R.id.gridview);
//        gridview.setAdapter(new MainMenuGridViewAdapter(this, mMainMenuListItems));
//        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.v(TAG, "" + position);
//            }
//        });

    }

    //Firebase
    ValueEventListener mainMenuListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

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


            //setUpGridViewAdapter();
            mMainMenuGridViewAdapter3.notifyDataSetChanged();
            Log.v(TAG, mMainMenuListItems.size() + "");


        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // Getting Post failed, log a message
            Log.w("loadPost:onCancelled", databaseError.toException());

        }
    };

    //learn2crack implementation
    private void initViews(){

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.main_menu_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager (getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        mMainMenuGridViewAdapter3 = new MainMenuGridViewAdapter3(getApplicationContext(), mMainMenuListItems);
        recyclerView.setAdapter(mMainMenuGridViewAdapter3);

    }



}
