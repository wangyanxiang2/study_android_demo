package com.user.study_android_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean ledon = false;
    private Button button =null ;
    private CheckBox checkBoxLed1 = null;
    private CheckBox checkBoxLed2 = null;

    class myButtonListener implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            ledon =!ledon;
            if(ledon) {
                button.setText("已经全部点亮");
                checkBoxLed1.setChecked(true);
                checkBoxLed2.setChecked(true);
            }
            else{
                button.setText("已经全部熄灭");
                checkBoxLed1.setChecked(false);
                checkBoxLed2.setChecked(false);
            }
        }
    }
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.LED1:
                if (checked){
                    Toast.makeText(getApplicationContext(),"LED1 已点亮",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"LED1 已熄灭",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.LED2:
                if (checked){
                    Toast.makeText(getApplicationContext(),"LED2 已点亮",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"LED2 已熄灭",Toast.LENGTH_LONG).show();
                }
                break;

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.BUTTON);
        checkBoxLed1 = (CheckBox)  findViewById(R.id.LED1);
        checkBoxLed2 = (CheckBox)  findViewById(R.id.LED2);

        button.setOnClickListener(new myButtonListener());
    }
}
