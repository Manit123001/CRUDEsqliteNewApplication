package com.anew.krungsriauto.crudesqlitenewapplication;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.anew.krungsriauto.crudesqlitenewapplication.models.SpeakThai;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DBHelper mHelper;
    List<String> textThai;
    ListView lv;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         lv = findViewById(R.id.simple_list_item_1);

        mHelper = new DBHelper(this);
        textThai = mHelper.getFriendList();

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, textThai);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent detail = new Intent(MainActivity.this, DetailActivity.class);

                String listName = textThai.get(i);

                int index = listName.indexOf(" ");
                String columnId = listName.substring(0, index);
                Log.d("s", columnId.toString());

                detail.putExtra(SpeakThai.Column.ID, columnId);
                startActivity(detail);
                overridePendingTransition(android.R.anim.fade_in,
                        android.R.anim.fade_out);

            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            Intent addFriend = new Intent(this, AddFriendActivity.class);

            startActivity(addFriend);

            overridePendingTransition(android.R.anim.fade_in,
                    android.R.anim.fade_out);
        }
        return super.onOptionsItemSelected(item);
    }

}
