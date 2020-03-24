package com.example.hwadaptation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onbtnEnter(View view){
        Toast.makeText(this, "Список", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}
