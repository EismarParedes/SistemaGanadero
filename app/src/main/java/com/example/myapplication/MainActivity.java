package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton = findViewById(R.id.button_open_home);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNextAct();
            }
        });
    }

    private void openNextAct() {
        Intent intent = new Intent(this, DrawerNavig.class);
        Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}
