package com.example.ocenystudenta.IndividualGradesActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ocenystudenta.R;

import java.util.ArrayList;
import java.util.List;

public class IndividualGradesActivity extends AppCompatActivity {

    private ListView list;
    private Button readyButton;
    private int gradesQuantity;
    private List<Integer> quantityList;
    private List<Integer> gradesList;
    private ArrayAdapter<Integer> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_grades);

        Bundle bundle = getIntent().getExtras();
        gradesQuantity = Integer.valueOf(bundle.getString("gradesQuantity"));

        quantityList = new ArrayList<>();
        gradesList = new ArrayList<>();

        for(int i=0; i<gradesQuantity; i++)
            quantityList.add(i);

        list = (ListView)findViewById(R.id.list);
        adapter = new GradesAdapter(this, gradesQuantity, quantityList, gradesList);
        list.setAdapter(adapter);

        readyButton = (Button)findViewById(R.id.readyButton);
        readyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obliczSrednia();
            }
        });
    }

    private void obliczSrednia()
    {
        int suma=0;
        for (Integer liczba : gradesList)
            suma+=liczba.intValue();
        Toast.makeText(this, "Średnia: "+((float)suma)/ gradesQuantity, Toast.LENGTH_LONG).show();
    }
}

