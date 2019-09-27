package com.example.mvvm.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mvvm.R;
import com.example.mvvm.databinding.ActivityAddNoteBinding;

public class AddNoteActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE =
            "com.example.mvvm.view.activity.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.example.mvvm.view.activity.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY =
            "com.example.mvvm.view.activity.EXTRA_PRIORITY";
    public static final String EXTRA_ID =
            "com.example.mvvm.view.activity.EXTRA_ID";

    private ActivityAddNoteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_note);

        init();
    }

    private void init() {

        binding.numberPicker.setMinValue(0);
        binding.numberPicker.setMaxValue(20);

        ActionBar actionBar = getSupportActionBar();
        Intent intent = getIntent();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
            if (intent.hasExtra(EXTRA_ID)) {
                setTitle("Edit Note");
                binding.titleET.setText(intent.getStringExtra(EXTRA_TITLE));
                binding.descriptionET.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
                binding.numberPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY,1));
            } else {
                setTitle("Add Note");
            }
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveId:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void saveNote() {
        String title = binding.titleET.getText().toString().trim();
        String description = binding.descriptionET.getText().toString().trim();
        int priority = binding.numberPicker.getValue();

        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Please fill up", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID,-1);
        if (id != -1){
            data.putExtra(EXTRA_ID,id);
        }

        setResult(RESULT_OK, data);
        finish();
    }
}
