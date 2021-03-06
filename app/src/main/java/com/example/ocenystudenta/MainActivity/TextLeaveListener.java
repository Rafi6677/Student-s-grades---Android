package com.example.ocenystudenta.MainActivity;

import android.view.View;
import android.widget.Toast;

public class TextLeaveListener implements View.OnFocusChangeListener {

    private MainActivity mainActivity;
    private String id;

    TextLeaveListener(MainActivity mainActivity, String id) {
        this.mainActivity = mainActivity;
        this.id = id;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(!hasFocus) {
            String text = "";
            String message;
            int number = 0;

            switch (id) {
                case "name":
                    text = mainActivity.nameInput.getText().toString();
                    message = "imię";
                    break;
                case "surname":
                    text = mainActivity.surnameInput.getText().toString();
                    message = "nazwisko";
                    break;
                case "gradesQuantity":
                    if(!mainActivity.gradesQuantityInput.getText().toString().isEmpty())
                        number = Integer.valueOf(mainActivity.gradesQuantityInput.getText().toString());
                    message = "liczba ocen";
                    break;
                default:
                    text = "";
                    message = "";
                    break;
            }

            if((id.equals("name")
                    || id.equals("surname"))
                    && (!checkText(text)
                    || text.isEmpty())) {
                Toast toast = Toast.makeText(mainActivity, "Pole '"+ message +"' zawiera nieprawidłowe znaki", Toast.LENGTH_SHORT);
                toast.show();
            }
            else if((id.equals("gradesQuantity")
                    && (number < 5 || number > 15))
                    || (id.equals("gradesQuantity")
                    && mainActivity.gradesQuantityInput.getText().toString().isEmpty())) {
                Toast toast = Toast.makeText(mainActivity, "Pole '"+ message +"' musi się zawierać w przedziale 5-15", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    private boolean checkText(String text) {
        for(char c : text.toCharArray())
            if(Character.isDigit(c))
                return false;

        return true;
    }
}
