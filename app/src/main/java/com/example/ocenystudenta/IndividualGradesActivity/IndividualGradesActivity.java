package com.example.ocenystudenta.IndividualGradesActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ocenystudenta.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndividualGradesActivity extends AppCompatActivity {

    private ListView list;
    private Button readyButton;
    private int gradesQuantity;
    private List<Integer> quantityList;
    private Map<Integer, Integer> grades;
    private GradesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_grades);

        Bundle bundle = getIntent().getExtras();
        gradesQuantity = Integer.valueOf(bundle.getString("gradesQuantity"));

        quantityList = new ArrayList<>();
        grades = new HashMap<>();

        for(int i=0; i<gradesQuantity; i++)
        {
            quantityList.add(i);
        }

        list = (ListView)findViewById(R.id.list);
        adapter = new GradesAdapter(this, quantityList, grades);
        list.setAdapter(adapter);

        readyButton = (Button)findViewById(R.id.readyButton);
        readyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countAverageGrade();
            }
        });
    }

    private void countAverageGrade()
    {

        int sum = 0;
        int counter = 0;

        for (Integer value : grades.values())
        {
            sum += value;
            counter++;
        }

        if(counter != gradesQuantity)
            Toast.makeText(this, "Wypełnij wszystkie pola!", Toast.LENGTH_SHORT).show();
        else
        {
            float averageGrade = (float)sum/gradesQuantity;

            Bundle bundle=new Bundle();
            bundle.putFloat("AverageGrade", averageGrade);
            Intent intent=new Intent();
            intent.putExtras(bundle);
            setResult(RESULT_OK,intent);
            finish();
        }
    }
}

