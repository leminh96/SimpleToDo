package com.example.quocm.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity{
    private EditText add_item;
    private Button add_button;
    private ListView todo_listview;
    List<String> todo_list;
    ArrayAdapter<String> arrayAdapter;
    String[] works = new String[] {
            "Homework",
            "Wash clothes"
    };
    private int pos_edit = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add_item = (EditText) findViewById(R.id.add_item_textbox);
        add_button = (Button) findViewById(R.id.add_button);
        todo_listview = (ListView) findViewById(R.id.my_listview);
        todo_list = new ArrayList<String>(Arrays.asList(works));
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todo_list);
        todo_listview.setAdapter(arrayAdapter);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = add_item.getText().toString();
                if (!t.isEmpty()) {
                    todo_list.add(t);
                    arrayAdapter.notifyDataSetChanged();
                    add_item.setText("");
                }
                else {
                    Toast toast=Toast.makeText(MainActivity.this, "This field is required",   Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        todo_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EditItemActivity.class);
                Bundle bundle = new Bundle();
                String t = todo_listview.getItemAtPosition(position).toString() + "_" + position;
                bundle.putString("Works", t);
                intent.putExtra("Package", bundle);
                startActivityForResult(intent, 2);
            }
        });
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            String t = bundle.getString("Edited").toString();
//            String[] separated = t.split("_");
//            int i = Integer.parseInt(separated[1]);
//            todo_list.add(separated[0]);
//            arrayAdapter.notifyDataSetChanged();
//        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(data == null){
            return;
        }
        if (requestCode == 2){
            Bundle bundle = data.getBundleExtra("Package");
            String t = bundle.getString("Edited");
            String[] separated = t.split("_");
            int i = Integer.parseInt(separated[1]);
            if (resultCode == 1) {
                todo_list.set(i, separated[0]);
            }
            else if (resultCode == 2){
                todo_list.remove(i);
            }
            arrayAdapter.notifyDataSetChanged();
        }
    }
}
