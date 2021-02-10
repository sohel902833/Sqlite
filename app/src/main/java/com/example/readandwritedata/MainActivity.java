package com.example.readandwritedata;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    private EditText nameEdittext;
    private  EditText rollEdittext;
    private  EditText ageEdittext;
    private  EditText genderEdittext;

    private Button saveButton;
    String name,roll,age,gender;
    private  Button displaybutton;

    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDatabaseHelper=new MyDatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase=myDatabaseHelper.getWritableDatabase();


        //<-----------------------Finding here All the components----------------------------------------->



        nameEdittext=findViewById(R.id.nameEdittextid);
        rollEdittext=findViewById(R.id.rollEdittextid);
        ageEdittext=findViewById(R.id.ageEdittextid);
        genderEdittext=findViewById(R.id.genderEdittext);
        displaybutton=findViewById(R.id.displaydataButtonid);


        saveButton=findViewById(R.id.saveButtonid);

        saveButton.setOnClickListener(this);
        displaybutton.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        name=nameEdittext.getText().toString();
        roll=rollEdittext.getText().toString();
        gender=genderEdittext.getText().toString();
        age=ageEdittext.getText().toString();


                if(v.getId()==R.id.saveButtonid){
                        if(name.isEmpty() || roll.isEmpty() || gender.isEmpty() || age.isEmpty()){
                            Toast.makeText(this, "Please Fill The All Data", Toast.LENGTH_SHORT).show();
                        }else{
                            long getvalu=myDatabaseHelper.insertData(name,roll,age,gender);
                            if(getvalu==-1){
                                Toast.makeText(this, "Data Inserted Failed", Toast.LENGTH_SHORT).show();

                            }
                            else{
                                nameEdittext.setText("");
                                rollEdittext.setText("");
                                ageEdittext.setText("");
                                genderEdittext.setText("");
                                Toast.makeText(this, getvalu+" Data is Successfully Inserted", Toast.LENGTH_SHORT).show();
                            }
                        }


                }
                else if(v.getId()==R.id.displaydataButtonid){
                  Cursor cursor= myDatabaseHelper.displayalldata();
                  if(cursor.getCount()!=0){
                        StringBuffer stringBuffer=new StringBuffer();
                        while (cursor.moveToNext()){
                            stringBuffer.append("Id : "+cursor.getString(0)+"\n");
                            stringBuffer.append("Name : "+cursor.getString(1)+"\n");
                            stringBuffer.append("Roll : "+cursor.getString(2)+"\n");
                            stringBuffer.append("Age : "+cursor.getString(3)+"\n");
                            stringBuffer.append("Gender : "+cursor.getString(4)+"\n\n");
                        }
                        showData("Result Data",stringBuffer.toString());
                  }

                }
    }


    public void showData(String title,String resultSet){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(resultSet);
        builder.setCancelable(true);
        builder.show();

    }

}
