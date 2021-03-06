package com.anew.krungsriauto.crudesqlitenewapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.anew.krungsriauto.crudesqlitenewapplication.models.SpeakThai;

public class DetailActivity extends AppCompatActivity {
    private TextView txtSpeak, txtSolve;
    private Button btnEdit, btnDelete;

    private DBHelper mHelper;
    private String id;

    private SpeakThai mSpeakThai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // enabling action bar app icon and behaving it as toggle button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mHelper = new DBHelper(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString(SpeakThai.Column.ID);
        }

        setContentView(R.layout.activity_detail);


        //findView
        txtSpeak = findViewById(R.id.showTxtSpeak);
        txtSolve = findViewById(R.id.showTxtSolve);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);



        // set text
        mSpeakThai = mHelper.getTextThai(id);
        txtSpeak.setText(mSpeakThai.getTextSpeak());
        txtSolve.setText(mSpeakThai.getTextSolve());


        ButtonEdit();
        ButtonDelete();
    }

    private void ButtonDelete() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder =
                        new AlertDialog.Builder(DetailActivity.this);
                builder.setTitle(getString(R.string.alert_title));
                builder.setMessage(getString(R.string.alert_message));

                builder.setPositiveButton(getString(android.R.string.ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                mHelper.deleteFriend(id);

                                Toast.makeText(getApplication(),
                                        "Deleted", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        });

                builder.setNegativeButton(getString(android.R.string.cancel), null);

                builder.show();

            }
        });
    }

    private void ButtonEdit() {
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent updateIntent = new Intent(DetailActivity.this,
                        AddFriendActivity.class);

                updateIntent.putExtra(SpeakThai.Column.ID, mSpeakThai.getId());
                updateIntent.putExtra(SpeakThai.Column.TEXT_SPEAK, mSpeakThai.getTextSpeak());
                updateIntent.putExtra(SpeakThai.Column.TEXT_SOLVE, mSpeakThai.getTextSolve());


                startActivity(updateIntent);

                overridePendingTransition(android.R.anim.fade_in,
                        android.R.anim.fade_out);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);

        return super.onOptionsItemSelected(item);
    }
}
