package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;


//Import of useful components and resources
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> userID = new ArrayList<String>();  // Create Array List userID to store the ID of users
    private ArrayList<String> userName = new ArrayList<String>(); //Create Array List userName to store the name of users

    private Button buttonSum;
    private EditText edittext1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the ID of respective component from XML
        buttonSum = (Button) findViewById(R.id.button1);
        edittext1 = (EditText) findViewById(R.id.editText1);

        // Add in ID and name of student into ArrayList of userID and userName
        userID.add("10101");
        userName.add("Steven");

        userID.add("17845");
        userName.add("Sofi");

        userID.add("20157");
        userName.add("31202");

        userID.add("36834");
        userName.add("Wani");


        addListeneronButton();
    }


    public void addListeneronButton() {

        buttonSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                for(int i = 0; i<userID.size();i++) {     // Check if the ID of student in the Arraylist of userID
                    if (edittext1.getText().toString().equals(userID.get(i))) { //if the ID of student in the ArrayList of userID

                        // Displaying Login Successfully and the name of student respectively
                        Toast.makeText(getApplicationContext(),
                                "Login Sucessfully \n  Welcome "+userName.get(i), Toast.LENGTH_SHORT).show();

                        //Intent applied to move to next activity which is Calculation_Input
                        Intent intent1 = new Intent(MainActivity.this ,Calculation_Input.class);
                        startActivity(intent1);

                    }
                }
            }
        });

    }
}