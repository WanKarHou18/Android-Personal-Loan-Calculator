package com.example.testing;

//import useful component  and resources
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Calculation_Input extends AppCompatActivity {

    //Declare components involved
    private EditText editText1;
    private EditText editText2;
    private  EditText editText3;
    private Button cal_button;

    //Declared and initialized the loan ,interest rate and period
    double loan = 0.00; //loan
    double interest = 0.00;//interest rate
    int period  = 0;//period




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calculation__input);

        //Get the id for components
        editText1 = (EditText)  findViewById(R.id.editText1);
        editText2 = (EditText)  findViewById(R.id.editText2);
        editText3 = (EditText)  findViewById(R.id.editText3);
        cal_button = (Button) findViewById(R.id.button1);


        addListeneronButton() ; // Get input from editText and display value of input


    }

    public void addListeneronButton()
    {


        cal_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get the input(data type : String) from editText and initialized it in variable
                String loan_str= editText1.getText().toString();  //Amount of loan
                String interest_str = editText2.getText().toString();//Interest Rate (anum)
                String  period_str = editText3.getText().toString();//Loan Period(month)

                //Covert those data type of input(data type:String) to Double or int , then copy it to variables respectively
                 loan=Double.parseDouble(loan_str);
                 interest=Double.parseDouble(interest_str);
                 period=Integer.parseInt(period_str);

                //Display the input for amount of loan, interest rate (anum),loan period(month)
                Toast.makeText(getApplicationContext(),"You had enter :\n"+"Loan Value RM"+loan+"\nInterest Rate(% per anum) : "+interest+"%"+"\nLoan Periods(months): "+period,Toast.LENGTH_LONG).show();

               //Intent was applied to move to next activity which was  Display_info
                Intent intent2 = new Intent(Calculation_Input.this ,Display__Info.class);

                // loan , interest rate (anum) and loan period was also copy to next activity through intent
               intent2.putExtra("loan", loan_str);
                intent2.putExtra("Interest", interest_str);
                intent2.putExtra("Period",period_str);
                startActivity(intent2);



            }
        });

    }
}