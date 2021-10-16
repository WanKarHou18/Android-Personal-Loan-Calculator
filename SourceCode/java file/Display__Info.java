package com.example.testing;

//import useful component and resources
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Display__Info extends AppCompatActivity {

    //Declare the components involved
    Button button2;
    Button button;
    Button button1;
    EditText edittext1;

    //ArrayList created to store Payment Number , Balance ,Interest Paid and Principal Paid
    ArrayList<Integer> Number = new ArrayList<Integer>();  // For payment Number
    ArrayList<Double> balance = new ArrayList<Double>(); //For balance
    ArrayList<Double> InterestPaid = new ArrayList<Double>(); // For InterestPaid
    ArrayList<Double> PrincipalPaid = new ArrayList<Double>(); // For Principal Paid

    //Initialized the variables with 0 or 0.00
    int count=0; // For Payment Number
    int Period = 0; //For Loan Period
    double Interest= 0.00 ; // For interest rate
    double loan = 0.00; //For loan
    double PrincipalPaid_sum=0.00; // For sum of principal paid calculated
    double Total_Interest = 0.00;//For total interest calculated
    double month_interest=0.00; //For monthly interest rate calculated

    double month_install = 0.00;   //For monthly installment
    double Interest_value=0.00;  // For the amount of interest rate
    double PrincipalPaid_value=0.00;//For the amount of principalpaid
    double balance_value= loan; // Initialized the amount of balance with amount of loan

    String loan_str;
    String Period_str;
    String Interest_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display___info);

        //Get the amount of loan , loan period(month) , interest rate(anum) from previous activity which was Calculation_Input
         Intent  intent = getIntent();
         loan_str = intent.getStringExtra("loan");
        Period_str = intent.getStringExtra("Period");
        Interest_str = intent.getStringExtra("Interest");

        //Covert those data type of input(data type:String) to Double
         loan = Double.parseDouble(loan_str);
         Period =Integer.parseInt(Period_str);
         Interest =Double.parseDouble(Interest_str);

         //Get the id for components involvded
        button = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button1);
        button2 =(Button) findViewById(R.id.button2);
        edittext1 =(EditText) findViewById(R.id.editText1);





        month_interest =Interest/1200 ; //Calculate monthly interest
        //Calculate monthly installment or monthly repayment
        month_install = (loan*month_interest*(Math.pow((1+month_interest),Period))/((Math.pow((1+month_interest),Period))-1));
        month_install =Math.round(month_install*100.0)/100.0 ;//Round off the monthly installment to 2 decimal placses

        //Calculate balance , interest paid(RM) and Principal Paid for each month ,then insert those value into ArrayList respectively
        //Loop will continue until its finish its counting till Number of Loan Period
        for(int i=0 ; i<=Period;i++)
        {
            //Calculate the Payment Number for this month
            count++;
            Number.add(count); // Addp payment number to Number list ;

            //Calculate the balance for this month
            balance_value = loan - PrincipalPaid_sum; //Calculate the balance by using amount of loan minus sum of principal paid
            balance_value = Math.round(balance_value*100.0)/100.0; //Round off the balance to 2 decimal places
            balance.add(balance_value);//Add the balance to ArrayList

            //Calculate the amount of interest for this month
            Interest_value =( balance.get(i))*month_interest;
            Interest_value = Math.round(Interest_value*100.0)/100.0;//Round off the amount of interest to 2 decimal places
            InterestPaid.add(Interest_value); //Add the amount of interest to Arrayist

            //Calculate the amount of principlalpaid for this month
            PrincipalPaid_value = month_install-InterestPaid.get(i);
            PrincipalPaid_value = Math.round(PrincipalPaid_value*100.0)/100.0;//Round off the Amount of Principal Paid to 2 decimal places
            PrincipalPaid.add(PrincipalPaid_value);// Add the amount of Principal Paid to ArrayList

            PrincipalPaid_sum= PrincipalPaid_sum+PrincipalPaid.get(i); //Calculate the total amount of principal paid

        }

        //Calculate the toal amount of Interest
        for(int i=0;i<=Period;i++)
        {
            Total_Interest = Total_Interest+InterestPaid.get(i);
        }

        findInterest();

    }

    public void findInterest()
    {


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int value1 = Integer.parseInt(edittext1.getText().toString());//Get the value of month from edittext and convert it to integer
                if((value1>0)&&(value1<=Period)) {  //if the value is between 1 to loan period, it will display message on the amount of interest in that month

                    if (value1 == 1) {
                        Toast.makeText(getApplicationContext(),
                                "Interest You need to pay in month of "+value1 + ": RM"+ InterestPaid.get(0)+"\n" , Toast.LENGTH_LONG).show();
                    } else {


                        Toast.makeText(getApplicationContext(),
                                "Interest You need to pay in\n month of "+value1 +":RM"+ InterestPaid.get(value1-1) + "\n" , Toast.LENGTH_LONG).show();
                    }
                }
                else //If the value of month input was not within the range of 1 to Loan Period,it will show message to remind user
                {

                        Toast.makeText(getApplicationContext(),
                                "Range of Input should be between 1 to " + Period, Toast.LENGTH_LONG).show();

                }

            }

        });

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                //Display the message on amount need to pay each month , total interest and the total payment
                Toast.makeText(getApplicationContext(),
                        "Amount paid each month : RM "+month_install+"\n Total Interest: RM "+Total_Interest+"\nTotal Payment : RM"+
                                (Total_Interest+loan), Toast.LENGTH_LONG).show();
            }

        });

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //Intent was applied to move to next activity which was Amortisation_Schedule
                Intent intent3 = new Intent(Display__Info.this ,Amortisation_Schedule.class);

                // loan , interest rate (anum) and loan period was also copy to next activity through intent
                intent3.putExtra("loan", loan_str);
                intent3.putExtra("Interest", Interest_str);
                intent3.putExtra("Period",Period_str);
                startActivity(intent3);



            }

        });
    }
}