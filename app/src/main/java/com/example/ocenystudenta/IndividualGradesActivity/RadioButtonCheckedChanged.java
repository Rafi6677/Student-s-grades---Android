package com.example.ocenystudenta.IndividualGradesActivity;

import android.app.Activity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

public class RadioButtonCheckedChanged implements RadioGroup.OnCheckedChangeListener{

    private Activity context;
    private List<Integer> gradesList;

    public RadioButtonCheckedChanged(Activity context, List<Integer> gradesList)
    {
        this.context = context;
        this.gradesList = gradesList;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton radioButton = (RadioButton)context.findViewById(checkedId);

        int value = Integer.valueOf(radioButton.getText().toString());
        int row = ((Integer) group.getTag()).intValue();

        gradesList.add(row, value);
    }
}