package com.example.takenotebyjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.takenotebyjava.databinding.ActivityAddNoteBinding;
import com.example.takenotebyjava.databinding.ActivityMainBinding;

public class AddEditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_ID
            = "com.example.takenotebyjava.EXTRA_ID";
    public static final String EXTRA_TITLE
            = "com.example.takenotebyjava.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION
            = "com.example.takenotebyjava.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY
            = "com.example.takenotebyjava.EXTRA_PRIORITY";

    ActivityAddNoteBinding binding;
    EditText etTitle, etDescription;
    NumberPicker numberPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        etTitle = binding.etTitle;
        etDescription = binding.etDescription;
        numberPicker = binding.numberPicker;

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            etTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            etDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY,1));
        }else{
            setTitle("Add Note");
        }
    }

    private void saveNote() {
        String title = etTitle.getText().toString();
        String description = etDescription.getText().toString();
        int priority = numberPicker.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(this, "Please insert a title & description...", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if(id != -1){
            data.putExtra(EXTRA_ID,id);
        }
        setResult(RESULT_OK, data);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.saveNote:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}