package com.example.ocenystudenta.MainActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

public class TextChangedListener implements TextWatcher {

    private MainActivity mainActivity;
    private String id;

    TextChangedListener(MainActivity mainActivity, String id) {
        this.mainActivity = mainActivity;
        this.id = id;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) { }

    @Override
    public void afterTextChanged(Editable s) {
        switch(id) {
            case "name":
                mainActivity.isNameCorrect = validateEditText(mainActivity.nameInput.getText().toString());
                break;
            case "surname":
                mainActivity.isSurnameCorrect = validateEditText(mainActivity.surnameInput.getText().toString());
                break;
            case "gradesQuantity":
                mainActivity.isGradesQuantityCorrect = validateEditText(mainActivity.gradesQuantityInput.getText().toString(),true);
                break;
        }

        if(mainActivity.isNameCorrect && mainActivity.isSurnameCorrect && mainActivity.isGradesQuantityCorrect)
            mainActivity.gradesButton.setVisibility(View.VISIBLE);
        else
            mainActivity.gradesButton.setVisibility(View.INVISIBLE);

    }

    private boolean validateEditText(String text) {
        boolean isValid = true;

        for(char c : text.toCharArray())
            if(Character.isDigit(c))
                isValid = false;

        return !text.equals("") && isValid;
    }

    private boolean validateEditText(String text, boolean isNumber) {
        if(text.isEmpty())
            return false;
        else {
            int number = Integer.valueOf(text);

            return number >= 5 && number <= 15;
        }
    }
}
