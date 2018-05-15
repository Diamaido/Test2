package com.example.a5498.doityourself;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Map<String, Integer> Datamap = new HashMap<>();
    String username;
    int pwd;

    TextView msg;
    EditText un;
    TextView tx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button login = findViewById(R.id.LoginButton);





        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 msg = findViewById(R.id.MessTextview);
                 un = findViewById(R.id.Usernametextview);
                 tx = findViewById(R.id.PasswordTextview);
                username = un.getText().toString();
                pwd = Integer.parseInt(tx.getText().toString());

                  Getdata log = new Getdata(username,pwd);
                  log.execute("");
            }
        });


    }






    private class Getdata extends AsyncTask<String,String, String>{
            String a;
            int b;

        private  Getdata(String a, int b){
           this.a = a;
           this.b = b;

        }


        protected  void onPreExecute(){
            msg.setText("Logging in.......");
        }


        @Override
        protected String doInBackground(String... strings) {


            Connection con = null;
            Statement sta = null;
            ResultSet re = null;

            try {
                con = DriverManager.getConnection("jdbc:mysql://10.0.2.2:3306/testdb", "root", "Ka930304");
                sta = con.createStatement();


                 re = sta.executeQuery("Select * From login");


                while (re.next()){
                    String temp= re.getString("Username");
                    int temp1= re.getInt("password");

                    Datamap.put(temp,temp1);
                }
                re.close();
                sta.close();
                con.close();

            } catch (SQLException e) {
                e.printStackTrace();

            }

            return null;
        }

        @Override
        protected void onPostExecute(String ms){
          if(Datamap.containsKey(a) && Datamap.containsValue(b)){
              msg.setText("Login Success");
             Intent it = new Intent(getApplicationContext(),Nextpage.class);
             startActivity(it);
          }else{
              msg.setText("Login Failed");
          }

        }




    }


    }












