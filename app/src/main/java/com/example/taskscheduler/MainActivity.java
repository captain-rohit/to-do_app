package com.example.taskscheduler;
import android.view.View;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Dialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.text.Editable;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Dialog myDialog;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHandler db;
        db = new DBHandler(this,null,null,1);
        myDialog = new Dialog(this);
        ArrayList<HashMap<String,String>> tasklist = db.viewDB();
        lv = (ListView) findViewById(R.id.list_view);
        ListAdapter adapter = new SimpleAdapter(MainActivity.this,tasklist,R.layout.task_list,new String[]{"name"},new int[]{R.id.task_name});
        lv.setAdapter(adapter);
    }
    public EditText get_input(){
        EditText Item = (EditText) findViewById(R.id.task_name);
        return Item;
    }
    public void onclickAddTask(View view) {
        final DBHandler db;
        db = new DBHandler(this,null,null,1);
        Button btnCreate;
        myDialog.setContentView(R.layout.create_task);
        btnCreate = (Button) myDialog.findViewById(R.id.button);
        final EditText etNewItem = (EditText) myDialog.findViewById(R.id.task_name);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etNewItem.getText().toString().isEmpty()){
                    etNewItem.setError("Please Enter a new Task");
                }
                else{
                    String itemText = etNewItem.getText().toString();
                    models task = new models(itemText);
                    db.addTask(task);
                    myDialog.dismiss();
                }
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();

    }
}