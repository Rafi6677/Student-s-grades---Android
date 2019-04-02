package com.example.ocenystudenta.IndividualGradesActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ocenystudenta.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndividualGradesActivity extends AppCompatActivity {

    private ListView  list;
    private GradesAdapter adapter;
    private int gradesQuantity;
    private List<Integer> quantityList;
    private Map<Integer, Integer> grades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_grades);

        Bundle bundle = getIntent().getExtras();

        grades = new HashMap<>();
        quantityList = new ArrayList<>();

        gradesQuantity = Integer.valueOf(bundle.getString("gradesQuantity"));

        for(int i=0; i<gradesQuantity; i++) {
            quantityList.add(i);
            grades.put(i, 5);
        }

        try {
            gradesQuantity = savedInstanceState.getInt("gradesQuantity");

            for(int i=0; i<gradesQuantity; i++) {
                grades.put(i, savedInstanceState.getInt(String.valueOf(i)));
            }
        }
        catch (NullPointerException e) { }

        list = findViewById(R.id.list);
        adapter = new GradesAdapter(this, quantityList, grades);
        list.setAdapter(adapter);

        Button readyButton = findViewById(R.id.readyButton);
        readyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countAverageGrade();
            }
        });
    }

    private void countAverageGrade() {
        int sum = 0;

        for (Integer value : grades.values())
            sum += value;

        float averageGrade = (float)sum/gradesQuantity;

        Bundle bundle = new Bundle();
        bundle.putFloat("AverageGrade", averageGrade);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        for (Map.Entry<Integer, Integer> entry : grades.entrySet()) {
            outState.putInt(entry.getKey().toString(), entry.getValue());
        }

        outState.putInt("gradesQuantity", grades.size());

        super.onSaveInstanceState(outState);
    }
}

