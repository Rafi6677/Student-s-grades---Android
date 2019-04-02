package com.example.ocenystudenta.IndividualGradesActivity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.ocenystudenta.R;

import java.util.List;
import java.util.Map;

public class GradesAdapter extends ArrayAdapter<Integer> {

    private Map<Integer, Integer> grades;
    private Activity context;

    GradesAdapter(Activity context, List<Integer> quantityList, Map<Integer, Integer> gradesList) {
        super(context, R.layout.single_row, quantityList);
        this.context = context;
        this.grades = gradesList;
    }

    @Override
    public View getView(int rowNumber, View rowToRecycle, ViewGroup parent) {
        View row = null;
        RadioGroup radioGroup = null;
        TextView label = null;

        if(rowToRecycle == null) {
            LayoutInflater pump = context.getLayoutInflater();
            row = pump.inflate(R.layout.single_row, null);

            label = row.findViewById(R.id.label);
            radioGroup = row.findViewById(R.id.radioGroup);

            radioGroup.setTag(rowNumber);
            radioGroup.setOnCheckedChangeListener(new RadioButtonCheckedChanged(context, grades));
        }
        else {
            row = rowToRecycle;
            radioGroup = (RadioGroup)row.findViewById(R.id.radioGroup);
            label = (TextView)row.findViewById(R.id.label);
            radioGroup.setTag(rowNumber);
        }

        label.setText("Ocena "+(rowNumber+1)+": ");
        return row;
    }
}
