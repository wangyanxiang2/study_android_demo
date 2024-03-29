package com.user.study_android_demo;

import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import android.os.ILedService;
import android.os.ServiceManager;
public class MainActivity extends AppCompatActivity {

    private boolean ledon = false;
    private Button button =null ;
    private CheckBox checkBoxLed1 = null;
    private CheckBox checkBoxLed2 = null;
    private ILedService iLedService = null;
    class myButtonListener implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {

            ledon =!ledon;
            if(ledon) {
                button.setText("已经全部点亮");
                checkBoxLed1.setChecked(true);
                checkBoxLed2.setChecked(true);
                try {
                    iLedService.ledCtrl(0,1);
                    iLedService.ledCtrl(1,1);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            else{
                button.setText("已经全部熄灭");
                checkBoxLed1.setChecked(false);
                checkBoxLed2.setChecked(false);
                try {
                    iLedService.ledCtrl(0,0);
                    iLedService.ledCtrl(1,0);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        try {
            switch(view.getId()) {
                case R.id.LED1:
                    if (checked){
                        iLedService.ledCtrl(0,1);
                        Toast.makeText(getApplicationContext(),"LED1 已点亮",Toast.LENGTH_LONG).show();
                    }
                    else{
                        iLedService.ledCtrl(0,0);
                        Toast.makeText(getApplicationContext(),"LED1 已熄灭",Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.LED2:
                    if (checked){
                        iLedService.ledCtrl(1,1);
                        Toast.makeText(getApplicationContext(),"LED2 已点亮",Toast.LENGTH_LONG).show();
                    }
                    else{
                        iLedService.ledCtrl(1,0);
                        Toast.makeText(getApplicationContext(),"LED2 已熄灭",Toast.LENGTH_LONG).show();
                    }
                    break;

            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.BUTTON);
        iLedService =  ILedService.Stub.asInterface(
                ServiceManager.getService("led"));

        checkBoxLed1 = (CheckBox)  findViewById(R.id.LED1);
        checkBoxLed2 = (CheckBox)  findViewById(R.id.LED2);

        button.setOnClickListener(new myButtonListener());


    }
}
