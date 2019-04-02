package com.example.ocenystudenta.IndividualGradesActivity;

import android.app.Activity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;
import java.util.Map;

public class RadioButtonCheckedChanged implements RadioGroup.OnCheckedChangeListener{

    private Activity context;
    private Map<Integer, Integer> grades;

    RadioButtonCheckedChanged(Activity context, Map<Integer, Integer> gradesList) {
        this.context = context;
        this.grades = gradesList;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton radioButton = context.findViewById(checkedId);

        int value = Integer.valueOf(radioButton.getText().toString());
        int row = Integer.valueOf(group.getTag().toString());

        grades.put(row, value);

        System.out.println(grades);
    }
}