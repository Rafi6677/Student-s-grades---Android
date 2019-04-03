package com.example.ocenystudenta.MainActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ocenystudenta.IndividualGradesActivity.IndividualGradesActivity;
import com.example.ocenystudenta.R;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Button gradesButton;
    EditText nameInput;
    EditText surnameInput;
    EditText gradesQuantityInput;
    TextView averageGradeInfo;

    public boolean isNameCorrect;
    public boolean isSurnameCorrect;
    public boolean isGradesQuantityCorrect;

    private float averageGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setVariables();
        setEditTextListeners();
        prepareGradesButton();
    }

    private void setVariables() {
        isNameCorrect = false;
        isSurnameCorrect = false;
        isGradesQuantityCorrect = false;

        gradesButton = (Button)findViewById(R.id.gradesButton);
        nameInput = (EditText)findViewById(R.id.nameInput);
        surnameInput = (EditText)findViewById(R.id.surnameInput);
        gradesQuantityInput = (EditText)findViewById(R.id.gradesQuantityInput);
        averageGradeInfo = (TextView)findViewById(R.id.averageGradeInfo);
    }

    private void setEditTextListeners() {
        nameInput.addTextChangedListener(new TextChangedListener(this, "name"));
        nameInput.setOnFocusChangeListener(new TextLeaveListener(this, "name"));

        surnameInput.addTextChangedListener(new TextChangedListener(this, "surname"));
        surnameInput.setOnFocusChangeListener(new TextLeaveListener(this, "surname"));

        gradesQuantityInput.addTextChangedListener(new TextChangedListener(this, "gradesQuantity"));
        gradesQuantityInput.setOnFocusChangeListener(new TextLeaveListener(this, "gradesQuantity"));
    }

    private void prepareGradesButton() {
        if(isNameCorrect && isSurnameCorrect && isGradesQuantityCorrect)
            gradesButton.setVisibility(View.VISIBLE);

        gradesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondActivity = new Intent(MainActivity.this, IndividualGradesActivity.class);
                secondActivity.putExtra("gradesQuantity", MainActivity.this.gradesQuantityInput.getText().toString());
                startActivityForResult(secondActivity, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
        {
            Bundle bundle = data.getExtras();

            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);

            averageGrade = bundle.getFloat("AverageGrade");
            averageGradeInfo.setText("Średnia ocen: " + df.format(averageGrade));

            prepareEndingActivityButton(averageGrade);
        }
    }

    private void prepareEndingActivityButton(float averageGrade) {
        boolean isAverageGradeOk = true;
        System.out.println(averageGrade);

        if(averageGrade >= 3)
            gradesButton.setText("Super :)");
        else {
            gradesButton.setText("Tym razem mi nie poszło...");
            isAverageGradeOk = false;
        }

        final boolean finalIsAverageGradeOk = isAverageGradeOk;

        gradesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                if(finalIsAverageGradeOk) {
                    dialog.setTitle("Gratulacje!")
                            .setMessage("Otrzymujesz zaliczenie!");
                }
                else {
                    dialog.setTitle("Niestety.")
                            .setMessage("Wysyłam podanie o zaliczenie warunkowe.");
                }

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                });

                dialog.create();
                dialog.show();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("name", nameInput.getText().toString());
        outState.putString("surname", surnameInput.getText().toString());
        outState.putString("gradesQuantity", gradesQuantityInput.getText().toString());
        outState.putFloat("averageGrade", averageGrade);
        outState.putString("averageGradeText", averageGradeInfo.getText().toString());

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        nameInput.setText(savedInstanceState.getString("name"));
        surnameInput.setText(savedInstanceState.getString("surname"));
        gradesQuantityInput.setText(savedInstanceState.getString("gradesQuantity"));
        averageGradeInfo.setText(savedInstanceState.getString("averageGradeText"));

        float averageGrade = savedInstanceState.getFloat("averageGrade");

        if(!averageGradeInfo.getText().toString().isEmpty()) {
            prepareEndingActivityButton(averageGrade);
        }
    }
}
