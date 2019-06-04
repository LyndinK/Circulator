package com.example.circulator;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Locale;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {

    /*поля для отображения текста и результата*/
    TextView value1_view, value2_view, action_view, result_view;

    /*кнопки интерфейса*/
    Button button0, button1, button2, button3, button4, button5,
            button6, button7, button8, button9, button_clear, button_add,
            button_subtract, button_multiply, button_divide, button_equals;

    Switch sw;

    /*введённые значения*/
    String value1, value2;

    /*применяемая функция*/
    String action;

    /*результат*/;
    float result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*устанавливаем цвет бекграунда, ибо из activity_main он устанавливается криво*/
        getWindow().getDecorView().setBackgroundColor(Color.rgb(4, 4, 81));

        value1="";
        value2 ="";
        action = "";

        /*инициализация текстовых полей*/
        value1_view = findViewById(R.id.value1);
        value2_view = findViewById(R.id.value2);
        action_view = findViewById(R.id.applied_operation);
        result_view = findViewById(R.id.result);


        /*инициализация кнопочек*/
        button0 = findViewById(R.id.digit0);
        button1 = findViewById(R.id.digit1);
        button2 = findViewById(R.id.digit2);
        button3 = findViewById(R.id.digit3);
        button4 = findViewById(R.id.digit4);
        button5 = findViewById(R.id.digit5);
        button6 = findViewById(R.id.digit6);
        button7 = findViewById(R.id.digit7);
        button8 = findViewById(R.id.digit8);
        button9 = findViewById(R.id.digit9);
        button_clear = findViewById(R.id.action_clear);
        button_add = findViewById(R.id.action_plus);
        button_subtract = findViewById(R.id.action_minus);
        button_multiply = findViewById(R.id.action_multiply);
        button_divide = findViewById(R.id.action_divide);
        button_equals = findViewById(R.id.action_equals);

        /*для смены локали*/
        sw = findViewById(R.id.switch2);

        /*устанавливаем обработчики событий для кнопок с цифрами*/
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UpdateValues("1");
                UpdateVisuals();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UpdateValues("2");
                UpdateVisuals();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UpdateValues("3");
                UpdateVisuals();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UpdateValues("4");
                UpdateVisuals();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UpdateValues("5");
                UpdateVisuals();
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UpdateValues("6");
                UpdateVisuals();
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UpdateValues("7");
                UpdateVisuals();
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UpdateValues("8");
                UpdateVisuals();
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UpdateValues("9");
                UpdateVisuals();
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if ((value1.length() > 0 && action == "") || (value2.length() > 0 && action != ""))
                UpdateValues("0");
                UpdateVisuals();
            }
        });

        button_clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                value1="";
                value2 ="";
                action = "";
                result = 0;
                UpdateVisuals();
            }
        });

        button_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               action = "+";
               action_view.setText("+");
            }
        });

        button_subtract.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                action = "-";
                action_view.setText("-");
            }
        });

        button_multiply.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                action = "*";
                action_view.setText("*");
            }
        });

        button_divide.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                action = "/";
                action_view.setText("/");
            }
        });

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ChangeLocale("en");
                }
                else{
                    ChangeLocale("ru");
                }
            }
        });

        button_equals.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (value1.length() > 0 && value2.length() > 0){
                    switch(action){
                        case "+":
                            result = Integer.parseInt(value1) + Integer.parseInt(value2);
                            break;
                        case "-":
                            result = Integer.parseInt(value1) - Integer.parseInt(value2);
                            break;
                        case "*":
                            result = Integer.parseInt(value1) * Integer.parseInt(value2);
                            break;
                        case "/":
                            result = Integer.parseInt(value1) / Integer.parseInt(value2);
                            break;
                    }
                    value1 = Integer.toString(Math.round(result));
                    value2 = "";
                    action = "";
                    UpdateVisuals();
                }
            }
        });
    }

    /*обновляет значения текстбоксов*/
    public void UpdateVisuals(){
        value1_view.setText(value1);
        value2_view.setText(value2);
        action_view.setText(action);
        result_view.setText(""+ Math.round(result));
    }

    /*обновляет значения переменных*/
    public void UpdateValues(String val){
        if (action == ""){
            if (value1.length() > 2){
                alertDialog();
            }
            else {
                value1 += val;
            }
        } else {
            if (value2.length() > 2){
                alertDialog();
            }
            else {
                value2 += val;
            }
        }
    }

    /*предостерегает и оберегает программу от слишком нагруженных вычислений*/
    private void alertDialog() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage(R.string.errormsg);
        dialog.setTitle(R.string.errheader);
        dialog.setPositiveButton(R.string.erroption, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }

    private void ChangeLocale(String langcode){
        Resources res;
        Configuration config;
        res = this.getResources();
        config = new Configuration(res.getConfiguration());
        config.setLocale(Locale.forLanguageTag(langcode));
        res.updateConfiguration(config, res.getDisplayMetrics());
    }


}
