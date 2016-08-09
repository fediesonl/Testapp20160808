package org.kingsunitedway.testapp20160808;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.kingsunitedway.testapp20160808.Controller.MainMenuGridViewAdapter;
import org.kingsunitedway.testapp20160808.Model.mainMenuListItem;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private DatabaseReference mDatabase;
    private ArrayList<mainMenuListItem> mMainMenuListItems = new ArrayList<mainMenuListItem> ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://211.firebaseio.com/MainMenu/USCAKings-Tulare/");
        mDatabase.addValueEventListener(mainMenuListener);

        //GridView set up
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new MainMenuGridViewAdapter(this, mMainMenuListItems));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v(TAG, "" + position);
            }
        });


    }

// Set up adapter



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

            Log.v(TAG, mMainMenuListItems.size() + "");

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // Getting Post failed, log a message
            Log.w("loadPost:onCancelled", databaseError.toException());

        }
    };





}
