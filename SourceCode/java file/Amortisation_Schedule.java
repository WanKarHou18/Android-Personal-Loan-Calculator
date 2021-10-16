package com.example.testing;

//import useful components and resources
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class Amortisation_Schedule extends AppCompatActivity {


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

        // Declare Linear Layout ll
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL); //Set orientation of ll to vertical
        ll.setVerticalScrollBarEnabled(true); //Add scroll bar to ll

        //Display the payment number , Interest Paid and Principal Paid for each month line by line
       for(int i=0 ;i<=Period;i++) {
           StringBuilder str = new StringBuilder();
           if(i==0) {
               str.append("   Payment Number   " + "  InterestPaid   " + "   PrincipalPaid   ");
           }
           else{
               str.append("            "+Number.get(i-1)+"           "+"              "+balance.get(i-1)+"           "+"              "+PrincipalPaid.get(i-1)+"           ");
           }

            //Declare TextView
           TextView textview = new TextView(this);
            TextView  textview1 = new TextView(this);

            //Set the colour of text to BLACK
            textview.setTextColor(Color.BLACK);
            textview1.setTextColor(Color.BLACK);

            //Append string to textView
           textview.setText(str);
           textview1.setText("_______________________________________________________________");

           //Linear Layout ,ll add the TextView to it
           ll.addView(textview);
           ll.addView(textview1);
           // Set ContentView dynamically
           this.setContentView(ll);
       }

    }
}