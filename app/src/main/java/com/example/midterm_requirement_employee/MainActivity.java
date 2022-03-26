package com.example.midterm_requirement_employee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, color, price, quantity;
    Button insertData, updateData, deleteData, viewData;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name_text);
        color = findViewById(R.id.color_text);
        price = findViewById(R.id.price_text);
        quantity = findViewById(R.id.quantityET);


        insertData = findViewById(R.id.insertDataButton);
        updateData = findViewById(R.id.updateDataButton);
        deleteData = findViewById(R.id.deleteDataButton);
        viewData = findViewById(R.id.viewDataButton);



        DB = new DBHelper(this);


        insertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String colorTXT = color.getText().toString();
                String priceTXT = price.getText().toString();
                String quantityTXT = quantity.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(nameTXT, colorTXT, priceTXT, quantityTXT);
                if(checkinsertdata==true)
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }        });


        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String colorTXT = price.getText().toString();
                String priceTXT = color.getText().toString();
                String quantityTXT = quantity.getText().toString();


                Boolean checkupdatedata = DB.updateuserdata(nameTXT, colorTXT, priceTXT, quantityTXT);

                if(checkupdatedata)
                    Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }        });

        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DeleteData.class);
                startActivity(i);
            }
        });

        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
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

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Worker Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });


    }}

