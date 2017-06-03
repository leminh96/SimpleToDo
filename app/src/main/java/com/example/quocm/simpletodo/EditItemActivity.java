package com.example.quocm.simpletodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {
    private EditText edit_item_textbox;
    private Button save_button;
    private Button delete_button;
    private String[] separated;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        edit_item_textbox = (EditText) findViewById(R.id.edit_item_textbox);
        save_button = (Button) findViewById(R.id.save_button);
        delete_button = (Button) findViewById(R.id.delete_button);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Package");
        String t = bundle.getString("Works");
        if (t != null)
        {
            separated = t.split("_");
            edit_item_textbox.setText(separated[0]);
        }
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = edit_item_textbox.getText().toString() + "_" + separated[1];
                sendToMain(t, 1);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = "Delete_" + separated[1];
                sendToMain(t, 2);
            }
        });
    }
    public void sendToMain(String value, int resultcode) {
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle.putString("Edited", value);
        intent.putExtra("Package", bundle);
        setResult(resultcode, intent);
        finish();
    }
}
