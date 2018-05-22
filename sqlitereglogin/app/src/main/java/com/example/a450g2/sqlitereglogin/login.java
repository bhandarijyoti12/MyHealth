package com.example.a450g2.sqlitereglogin;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.jar.Attributes;

public class login extends AppCompatActivity {
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Button _btnlogin;
    EditText _username, _Password;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        openHelper= new DatabaseHelper(this);
        db= openHelper.getReadableDatabase();
        _btnlogin=(Button)findViewById(R.id.btnlogin);
        _username=(EditText)findViewById(R.id.username);
        _Password=(EditText)findViewById(R.id.Password);
        _btnlogin.setOnClickListener(view -> {
            String username=_username.getText().toString();
            String Password= _Password.getText().toString();
            cursor=db.rawQuery(String.format(" SELECT * FROM %s WHERE %s=? AND %s=? ", DatabaseHelper.TABLE_NAME, DatabaseHelper.COL_2, DatabaseHelper.COL_3), new String[]{username,Password});
            if (cursor!=null){
                if(cursor.getCount()>0){
                    cursor.moveToNext();
                    Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Error In Login", Toast.LENGTH_LONG).show();

                }
            }
            _btnlogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(login.this,homeActivity.class);
                    startActivity(intent);
                }
            });

        });

    }





}
