package com.github.motassemja.overcome;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.github.motassemja.overcome.db.FeelingsDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO Make singleton
        // FeelingsDatabase db = Room.databaseBuilder(getApplicationContext(), FeelingsDatabase.class, "feelings-db").build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_parent_control) {
            // TODO handle parent control
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
