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

public class GradesAdapter extends ArrayAdapter<Integer> {

    private int gradesQuantity;
    private List<Integer> quantityList;
    private List<Integer> gradesList;
    private Activity context;

    public GradesAdapter(Activity context, int gradesQuantity, List<Integer> quantityList, List<Integer> gradesList) {
        super(context, R.layout.single_row, quantityList);
        this.gradesQuantity = gradesQuantity;
        this.context = context;
        this.quantityList = quantityList;
        this.gradesList = gradesList;
    }

    @Override
    public View getView(int rowNumber, View rowToRecycle, ViewGroup parent) {
        View row = null;
        RadioGroup radioGroup = null;
        TextView label = null;
        //tworzenie nowego wiersza
        if(rowToRecycle == null)
        {
            LayoutInflater pump = context.getLayoutInflater();
            //tworzenie komponentów (obiektów) na podstawie XMLa
            row = pump.inflate(R.layout.single_row, null);

            //znalezienie w wierszu pola tekstowego i ustawienie obsługi zdarzeń
            label = (TextView)row.findViewById(R.id.label);
            radioGroup = (RadioGroup)row.findViewById(R.id.radioGroup);

            radioGroup.setOnCheckedChangeListener(new RadioButtonCheckedChanged(context, gradesList));


            //zapisanie informacji jakiemu wierszowi odpowiada pole tekstowe
            radioGroup.setTag(rowNumber);
            //używanie wiersza „z odzysku”
        }
        else
        {
            row = rowToRecycle;
            radioGroup = (RadioGroup)row.findViewById(R.id.radioGroup);
            label = (TextView)row.findViewById(R.id.label);
            radioGroup.setTag(rowNumber);
        }
        //ustawienie w polu tekstowym wartości ze źródła danych
        label.setText("Ocena "+(rowNumber+1)+": ");
        return row;
    }
}