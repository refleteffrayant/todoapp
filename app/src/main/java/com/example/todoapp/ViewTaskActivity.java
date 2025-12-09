package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ViewTaskActivity extends AppCompatActivity {
    private TextView textViewTitle;
    private TextView textViewDescription;
    private Button buttonEdit;
    private Button buttonDelete;
    private Button buttonBack;
    private DatabaseHelper databaseHelper;
    private Task currentTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        databaseHelper = new DatabaseHelper(this);

        textViewTitle = findViewById(R.id.textViewTitle);
        textViewDescription = findViewById(R.id.textViewDescription);
        buttonEdit = findViewById(R.id.buttonEdit);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonBack = findViewById(R.id.buttonBack);

        int taskId = getIntent().getIntExtra("task_id", -1);

        if (taskId != -1) {
            for (Task task : databaseHelper.getAllTasks()) {
                if (task.getId() == taskId) {
                    currentTask = task;
                    break;
                }
            }

            if (currentTask != null) {
                textViewTitle.setText(currentTask.getTitle());
                textViewDescription.setText(currentTask.getDescription());
            }
        }

        buttonEdit.setOnClickListener(v -> {
            Intent intent = new Intent(ViewTaskActivity.this, EditTaskActivity.class);
            intent.putExtra("task_id", currentTask.getId());
            startActivityForResult(intent, 1);
        });

        buttonDelete.setOnClickListener(v -> {
            databaseHelper.deleteTask(currentTask.getId());
            setResult(RESULT_OK);
            finish();
        });

        buttonBack.setOnClickListener(v -> {
            finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            setResult(RESULT_OK);
            finish();
        }
    }
}