package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnTaskClickListener {
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private List<Task> taskList;
    private DatabaseHelper databaseHelper;
    private Button buttonAddTask;
    private Button buttonDeleteCompleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        recyclerView = findViewById(R.id.recyclerView);
        buttonAddTask = findViewById(R.id.buttonAddTask);
        buttonDeleteCompleted = findViewById(R.id.buttonDeleteCompleted);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskList = new ArrayList<>();
        adapter = new TaskAdapter(taskList, this);
        recyclerView.setAdapter(adapter);

        buttonAddTask.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EditTaskActivity.class);
            startActivityForResult(intent, 1);
        });

        buttonDeleteCompleted.setOnClickListener(v -> {
            databaseHelper.deleteCompletedTasks();
            loadTasks();
        });

        loadTasks();
    }

    private void loadTasks() {
        taskList.clear();
        taskList.addAll(databaseHelper.getAllTasks());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onTaskClick(int position) {
        Task task = taskList.get(position);
        Intent intent = new Intent(this, ViewTaskActivity.class);
        intent.putExtra("task_id", task.getId());
        startActivityForResult(intent, 2);
    }

    @Override
    public void onTaskComplete(int position, boolean isCompleted) {
        Task task = taskList.get(position);
        task.setCompleted(isCompleted);
        databaseHelper.updateTask(task);
    }

    @Override
    public void onTaskDelete(int position) {
        Task task = taskList.get(position);
        databaseHelper.deleteTask(task.getId());
        loadTasks();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            loadTasks();
        }
    }
}