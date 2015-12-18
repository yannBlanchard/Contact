package com.example.yablanch.contact;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Enumeration;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {
    File file;
    FileWriter fileWriter;

    Properties p = new Properties();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void OnClick(View v){
        String contact = "";
        String key;
        file = new File(getFilesDir(),"save");
        switch(v.getId()){
            case R.id.btnSave :
                EditText etName=(EditText)findViewById(R.id.fieldName);
                EditText etPrenom=(EditText)findViewById(R.id.fieldPrenom);
                EditText etNum=(EditText)findViewById(R.id.fieldNum);
                try{
                    contact = etNum.getText().toString() + "=" +
                             etName.getText().toString() + " " +
                            etPrenom.getText().toString() + "\n";
                    fileWriter = new FileWriter(file,true);
                    fileWriter.write(contact);
                    Toast.makeText(getApplicationContext(), contact, Toast.LENGTH_LONG).show();
                    etName.setText("");
                    etNum.setText("");
                    etPrenom.setText("");
                    fileWriter.close();
                }catch (Exception e) {}
                break;
            case R.id.btnShow :
                try {
                    contact = "";
                    FileInputStream fis =new FileInputStream(getFilesDir()+"/save");
                    p.load(fis);
                    fis.close();
                    for (Enumeration en = p.propertyNames(); en.hasMoreElements();) {
                        key = (String) en.nextElement();
                        contact = contact + key + " " + p.getProperty(key) + "\n";
                        Log.e("Key",file.getAbsolutePath());
                    }
                    Toast.makeText(getApplicationContext(), contact, Toast.LENGTH_LONG).show();

                }catch (Exception e){

                }
                break;
            default:

        }
    }
}
