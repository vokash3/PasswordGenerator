package com.example.passwordgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public int passLength = 8;
    TextView passField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        passField = (TextView)findViewById(R.id.passField);
    }

    public void generatePassword(View view){ // Button Method
        StringBuilder password = new StringBuilder();
        for(int i = 0; i < passLength; i++){
            if(Math.random()<0.7)
                password.append(getRandomChar());
            else
                password.append(getRandomNumber());
        }
        display(password.toString());
    }

    public String getRandomChar(){
        String[] character = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        String randomChar = character[(int)(Math.random()*character.length)];
        if(Math.random()<0.5d) return randomChar.toUpperCase();
        else
            return randomChar;
    }

    public String getRandomNumber(){
        String[] nums = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        return nums[(int)(Math.random()*nums.length)];
    }

    public void display(String string){
        passField.setText(string);
    }

    public void copyInBuffer(View view){
//        findViewById(R.id.copyButton).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        //Копируем в буфер обмена пароль при нажатии
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("", passField.getText().toString());
        clipboard.setPrimaryClip(clip);

        //Уведомляем при сохранении в буфер обмена
        Toast toast = Toast.makeText(getApplicationContext(), "Password has been copied!", Toast.LENGTH_SHORT);
        toast.show();
    }
}
