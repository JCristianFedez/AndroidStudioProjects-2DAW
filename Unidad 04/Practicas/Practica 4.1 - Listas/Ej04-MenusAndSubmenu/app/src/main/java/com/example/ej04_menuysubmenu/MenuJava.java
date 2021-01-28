package com.example.ej04_menuysubmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuJava extends AppCompatActivity {

    private static final int MnOp1 = 1;
    private static final int MnOp2 = 2;
    private static final int MnOp3 = 3;
    private static final int MnOp2_1 = 21;
    private static final int MnOp2_2 = 22;
    private Button btnMenuJava;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_java);

        btnMenuJava = (Button) findViewById(R.id.btnMenuJava);


        btnMenuJava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE,MnOp1,Menu.NONE,"Menu 1 desde Java");
        SubMenu smnu = menu.addSubMenu(Menu.NONE,MnOp2,Menu.NONE,"Menu 2 desde Java");
        smnu.add(Menu.NONE,MnOp2_1,Menu.NONE,"Menu 2.1");
        smnu.add(Menu.NONE,MnOp2_2,Menu.NONE,"Menu 2.2");
        menu.add(Menu.NONE,MnOp3,Menu.NONE,"Menu 3 desde Java");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case MnOp1:
                Toast.makeText(this, "Menu 1 Seleccionado", Toast.LENGTH_SHORT).show();
                return true;

            case MnOp2:
                Toast.makeText(this, "Menu 2 Seleccionado", Toast.LENGTH_SHORT).show();
                return true;

            case MnOp2_1:
                Toast.makeText(this, "Sub-Menu 2.1 Seleccionado", Toast.LENGTH_SHORT).show();
                return true;

            case MnOp2_2:
                Toast.makeText(this, "Sub-Menu 2.2 Seleccionado", Toast.LENGTH_SHORT).show();
                return true;

            case MnOp3:
                Toast.makeText(this, "Menu 3 Seleccionado", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}