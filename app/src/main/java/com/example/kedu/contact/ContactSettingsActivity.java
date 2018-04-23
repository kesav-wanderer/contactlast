package com.example.kedu.contact;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

public class ContactSettingsActivity extends AppCompatActivity {

        private ImageButton list, map, settings;
        private RadioGroup radio;
        SharedPreferences pref = null;
        private RadioButton rb1, rb2, rb3;
        ScrollView r1;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_contact_settings);
            initMapButton();
            initSettingsButton();
            initListButton();

            initColors();
            initColorsbyClick();
        }

        private void initListButton() {
            list = (ImageButton) findViewById(R.id.imageButtonList);
            list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ContactSettingsActivity.this, ContactListActivity.class);
                    intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
        }

        private void initMapButton() {
            map = (ImageButton) findViewById(R.id.imageButtonMap);
            map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ContactSettingsActivity.this, ContactMapActivity.class);
                    intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
        }

        private void initSettingsButton() {
            settings = (ImageButton) findViewById(R.id.imageButtonSettings);
             }

        public void initColors() {
            rb1 = (RadioButton) findViewById(R.id.radioButton1);
            rb3 = (RadioButton) findViewById(R.id.radioButton2);
            rb2 = (RadioButton) findViewById(R.id.radioButton3);

            String color = getSharedPreferences("colorpreferences", Context.MODE_PRIVATE).getString("colorfield", "green");

            r1 = (ScrollView) findViewById(R.id.sv);


            if (color.equalsIgnoreCase("green")) {
                rb1.setChecked(true);
                r1.setBackgroundResource(R.color.green);
            } else if (color.equalsIgnoreCase("yellow")) {
                rb2.setChecked(true);
                r1.setBackgroundResource(R.color.yellow);
            } else if (color.equalsIgnoreCase("blue")) {
                rb3.setChecked(true);
                r1.setBackgroundResource(R.color.blue);
            }
        }


        public void initColorsbyClick(){
            radio = (RadioGroup)findViewById(R.id.radiogroup);
            radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    pref = getSharedPreferences("colorpreferences", Context.MODE_PRIVATE);
                    rb1 = (RadioButton) findViewById(R.id.radioButton1);
                    rb3 = (RadioButton) findViewById(R.id.radioButton2);
                    rb2 = (RadioButton) findViewById(R.id.radioButton3);

                    if (rb1.isChecked()) {
                        pref.edit().putString("colorfield", "green").commit();
                        r1.setBackgroundResource(R.color.green);
                    } else if (rb2.isChecked()) {
                        pref.edit().putString("colorfield", "yellow").commit();
                        r1.setBackgroundResource(R.color.yellow);
                    } else if(rb3.isChecked()){
                        pref.edit().putString("colorfield", "blue").commit();
                        r1.setBackgroundResource(R.color.blue);

                    }
                }
            });

    }
}