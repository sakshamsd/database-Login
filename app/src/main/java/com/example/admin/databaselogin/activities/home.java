package com.example.admin.databaselogin.activities;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.databaselogin.R;
import com.example.admin.databaselogin.adapter.Recyclerview_adapter;
import com.example.admin.databaselogin.model.API_client;
import com.example.admin.databaselogin.model.AddResult;
import com.example.admin.databaselogin.model.StudentResult;
import com.example.admin.databaselogin.model.dbhelper;
import com.example.admin.databaselogin.interfaces.API_interface;
import com.example.admin.databaselogin.model.student_test;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//import okhttp3.ResponseBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class home extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener{

    TextView mail;
    TextView id, name, address;
    Button signout;
    FloatingActionButton add_student;
    dbhelper dbhelper;
    RecyclerView recyclerView;
    Recyclerview_adapter rec_adapter;

    List<student_test> list;

    API_interface api_interface;



    GoogleApiClient googleApiClient;
    SwipeRefreshLayout swipe;

    Context context;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dbhelper = new dbhelper(this);

        /*swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                refreshItems();
            }
        });*/


        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();

        googleApiClient = new GoogleApiClient
                .Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        googleApiClient.isConnected();

        /*//For displaying sqlite data
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        //Log.e("data123", dbhelper.getStudentInfo().get(0).getName());
        rec_adapter = new recyclerview_adapter(getApplicationContext(), dbhelper.getStudentInfo());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(rec_adapter);
*/

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        rec_adapter = new Recyclerview_adapter(getApplicationContext(), list);

        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(rec_adapter);


        get();
//






        id = (TextView) findViewById(R.id.txt_id);
        name = (TextView) findViewById(R.id.txt_name);
        address = (TextView) findViewById(R.id.txt_address);
        mail = (TextView) findViewById(R.id.email);
        signout = (Button) findViewById(R.id.signout);
        add_student = (FloatingActionButton) findViewById(R.id.add);




        Intent intent = getIntent();

        String email;
        email = intent.getStringExtra("email");

        mail.setText(email);


        signout.setOnClickListener(this);

        add_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_student();

            }
        });


    }



            public void logout(){
        Log.d("signout", "home");
        Intent logout = new Intent(home.this, Login.class);
        logout.putExtra("logout1", true);
        startActivity(logout);
        finish();
    }







        /*public void display_student(){
            dbhelper = new dbhelper(this);
            ArrayList<student_info> list = dbhelper.getStudentInfo();
             for (int i = 0; i < list.size(); i++) {
            final student_info info = list.get(i);

        }
}*/







    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void get(){

        api_interface = new API_client().getClient().create(API_interface.class);




        Call<StudentResult>call = api_interface.getList();

        call.enqueue(new Callback<StudentResult>() {
            @Override
            public void onResponse(Call<StudentResult> call, Response<StudentResult> response) {
                StudentResult result = response.body();
                List<student_test> students = result.data;

                Log.d("response", response.body().toString());



                Log.e("Response", String.valueOf(response.body()));
                 for (int i = 0; i < students.size(); i++) {
                            student_test item = students.get(i);
                            list.add(item);

                            Log.d("display", String.valueOf(list.get(0)));
                        }


                rec_adapter.notifyDataSetChanged();
                //Log.d("retrofitttt", response+"");
            }

            @Override
            public void onFailure(Call<StudentResult> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"Failure", Toast.LENGTH_SHORT ).show();
                Log.d("failure", t.toString());

            }
        });

    }






    public void save_student(){
        final Dialog save_dialog = new Dialog(this);
        View view =save_dialog.getLayoutInflater().from(this).inflate(R.layout.add_student,null);

        dbhelper = new dbhelper(this);

        final EditText id, name, address;
        Button save;

        id = (EditText) view.findViewById(R.id.id);
        name = (EditText) view.findViewById(R.id.name);
        address = (EditText) view.findViewById(R.id.address);

        save_dialog.setTitle("Add Student");

        save = (Button) view.findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String student_id = id.getText().toString();
                final String student_name = name.getText().toString();
                final String student_address = address.getText().toString();
                final String added_by = mail.getText().toString();




                //insert into sqlite

                final student_test student_test = new student_test(student_id, student_name, student_address, added_by);
                long id = dbhelper.insertStudent(student_test);

                student_test.setId("s" + String.valueOf(id));

                Log.d("student", student_test.getId());
                Log.d("student", student_test.getName());
                Log.d("student", student_test.getAddress());
                Log.d("student", student_test.getAdded_by());




                //insert into server

                api_interface = new API_client().getClient().create(API_interface.class);

                Call<ResponseBody> call= api_interface.insertDetails(student_id, student_name
                ,student_address, added_by);
                Log.d("details", student_id);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String responseBody = "";
                        try {
                            responseBody = response.body().string();
                        } catch (Exception ex) {

                        }

                        Log.d("onResponse", "" + response.code() +
                              "  response body "  + response.body() +
                                " responseError " + response.errorBody() + " responseMessage " +
                                response.message());
                        Log.d("response", responseBody);
                        Log.d("inserted data:", student_id+","+student_name+","+added_by);

                        list.add(student_test);
                        rec_adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        Log.d("onFailure", t.toString());


                    }
                });

                save_dialog.dismiss();


            }

        });



        save_dialog.setContentView(view);
        save_dialog.show();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation");
        builder.setMessage("Are you sure want to exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logout();
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();

    }
    /*List<student_info> update;
    void refreshItems() {
        update = dbhelper.showAllMembers();
        for(student_info all: update){
            Log.d("usernameVIA",mem.getUsername());
            Log.d("passwordVIA",mem.getLastname());
        }
        onItemsLoadComplete();
    }

    void onItemsLoadComplete() {
        rec_adapter = new recyclerview_adapter(update);
        recyclerView.setAdapter(rec_adapter);
        *//*RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());*//*
        rec_adapter.notifyDataSetChanged();
        Toast.makeText(this, "hunai parxa", Toast.LENGTH_SHORT).show();
        // Update the adapter and notify data set changed
        // ...

        // Stop refresh animation
        swipe.setRefreshing(false);
    }*/





    public String gen10DigitNumber() {
        Random rng = new Random();
        int val = rng.nextInt(2100000000);
        String number = String.format("%010d", val);
        return number;
    }





    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.signout:
                logout();
        }
    }
}
