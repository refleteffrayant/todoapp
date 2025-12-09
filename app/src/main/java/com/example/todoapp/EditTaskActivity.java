package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class EditTaskActivity extends AppCompatActivity {
    private EditText editTextTitle;
    private EditText editTextDescription;
    private Button buttonSave;
    private Button buttonCancel;
    private DatabaseHelper databaseHelper;
    private Task currentTask;
    private boolean isEditing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        databaseHelper = new DatabaseHelper(this);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        buttonSave = findViewById(R.id.buttonSave);
        buttonCancel = findViewById(R.id.buttonCancel);

        int taskId = getIntent().getIntExtra("task_id", -1);

        if (taskId != -1) {
            isEditing = true;
            for (Task task : databaseHelper.getAllTasks()) {
                if (task.getId() == taskId) {
                    currentTask = task;
                    break;
                }
            }

            if (currentTask != null) {
                editTextTitle.setText(currentTask.getTitle());
                editTextDescription.setText(currentTask.getDescription());
            }
        } else {
            currentTask = new Task();
        }

        buttonSave.setOnClickListener(v -> {
            String title = editTextTitle.getText().toString().trim();
            String description = editTextDescription.getText().toString().trim();

            if (!title.isEmpty()) {
                currentTask.setTitle(title);
                currentTask.setDescription(description);

                if (isEditing) {
                    databaseHelper.updateTask(currentTask);
                } else {
                    databaseHelper.addTask(currentTask);
                }

                setResult(RESULT_OK);
                finish();
            } else {
                editTextTitle.setError("Введите заголовок");
            }
        });

        buttonCancel.setOnClickListener(v -> {
            finish();
        });
    }
}