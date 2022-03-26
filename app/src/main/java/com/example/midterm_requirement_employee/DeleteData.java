package com.example.midterm_requirement_employee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteData extends AppCompatActivity {


    EditText id;
    Button deleteData, viewData,backTohome;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_data);

        id = findViewById(R.id.id_text);

        deleteData = findViewById(R.id.deleteDataButton);
        viewData = findViewById(R.id.viewDataButton);
        backTohome = findViewById(R.id.backTohome);

        DB = new DBHelper(this);

        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = id.getText().toString();
                SQLiteDatabase DB = getApplicationContext().openOrCreateDatabase("Userdata.db", Context.MODE_PRIVATE,null);
                Cursor res = DB.rawQuery("Select * from Userdetails where id ='"+input+"'", null);
                if (res.getCount() == 0){
                    Toast.makeText(getApplicationContext(),"No Record",Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("ID :"+res.getString(0)+"\n");
                    buffer.append("Name :"+res.getString(1)+"\n");
                    buffer.append("Color :"+res.getString(2)+"\n");
                    buffer.append("Price :"+res.getString(3)+"\n");
                    buffer.append("Quantity :"+res.getString(4)+"\n");

                }
                Toast.makeText(getApplicationContext(),"Search by ID Result \n\n"+buffer.toString(),Toast.LENGTH_SHORT).show();
            }
        });

        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = id.getText().toString();
                SQLiteDatabase DB = getApplicationContext().openOrCreateDatabase("Userdata.db", Context.MODE_PRIVATE,null);
                DB.execSQL("delete from Userdetails where id ='"+input+"'");

                Toast.makeText(DeleteData.this,"Deleted Successfully",Toast.LENGTH_SHORT).show();

            }
        });
        backTohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });


    }
}