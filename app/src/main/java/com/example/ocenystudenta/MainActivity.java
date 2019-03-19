package com.example.ocenystudenta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button gradesButton;
    EditText nameInput;
    EditText surnameInput;
    EditText gradesQuantityInput;

    public boolean isNameCorrect;
    public boolean isSurnameCorrect;
    public boolean isGradesQuantityCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setVariables();
        setEditTextListeners();
        prepareButton();
    }

    private void setVariables()
    {
        isNameCorrect = false;
        isSurnameCorrect = false;
        isGradesQuantityCorrect = false;

        gradesButton = (Button)findViewById(R.id.gradesButton);
        nameInput = (EditText)findViewById(R.id.nameInput);
        surnameInput = (EditText)findViewById(R.id.surnameInput);
        gradesQuantityInput = (EditText)findViewById(R.id.gradesQuantityInput);
    }

    private void setEditTextListeners()
    {
        nameInput.addTextChangedListener(new TextChangedListener(this, "name"));
        nameInput.setOnFocusChangeListener(new TextLeaveListener(this, "name"));

        surnameInput.addTextChangedListener(new TextChangedListener(this, "surname"));
        surnameInput.setOnFocusChangeListener(new TextLeaveListener(this, "surname"));

        gradesQuantityInput.addTextChangedListener(new TextChangedListener(this, "gradesQuantity"));
        gradesQuantityInput.setOnFocusChangeListener(new TextLeaveListener(this, "gradesQuantity"));
    }

    private void prepareButton()
    {
        if(isNameCorrect && isSurnameCorrect && isGradesQuantityCorrect) gradesButton.setVisibility(View.VISIBLE);

        gradesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
