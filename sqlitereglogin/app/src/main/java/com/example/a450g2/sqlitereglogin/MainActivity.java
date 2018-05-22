package com.example.a450g2.sqlitereglogin;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button _button3, _btnlogin;
    EditText _txtname,  _txtPhone, _txtPass, _txtemail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openHelper =new DatabaseHelper(this);
        _button3 = (Button)findViewById(R.id.button3);
        _txtname = (EditText)findViewById(R.id.txtname);
        _txtPass = (EditText)findViewById(R.id.txtPass);
        _txtemail = (EditText)findViewById(R.id.txtemail);
        _txtPhone = (EditText)findViewById(R.id.txtPhone);
        _btnlogin = (Button) findViewById(R.id.btnlogin);



         _button3.setOnClickListener((View v) -> {
             {

             String validate_email = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +

                        "\\@" +

                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +

                        "(" +

                        "\\." +

                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +

                        ")+";


                String Email = _txtemail.getText().toString();

                Matcher matcher= Pattern.compile(validate_email).matcher(Email);


                if (matcher.matches()){



                        db = openHelper.getWritableDatabase();
                        String name = _txtname.getText().toString();
                        String pass = _txtPass.getText().toString();
                        String email = _txtemail.getText().toString();
                        String Phone = _txtPhone.getText().toString();
                        insertdata(name, pass, email, Phone);
                        Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_LONG).show();

}
                else
                    {
                        Toast.makeText(getApplicationContext(), "Enter valid email address", Toast.LENGTH_SHORT).show();

                    }
                }




        });


        _btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,login.class);
                startActivity(intent);
            }
        });
    }
    public void insertdata (String name,String pass,String email, String Phone){
        ContentValues contentValues= new ContentValues();
        contentValues.put(DatabaseHelper.COL_2,name);
        contentValues.put(DatabaseHelper.COL_3,pass);
        contentValues.put(DatabaseHelper.COL_4,email);
        contentValues.put(DatabaseHelper.COL_5,Phone);
        long id= db.insert(DatabaseHelper.TABLE_NAME,null,contentValues);

    }

}

