package com.example.admin.databaselogin.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.admin.databaselogin.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class Login extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener{

   SignInButton login;

    GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 007;
    Boolean out = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        login = (SignInButton) findViewById(R.id.sign_in_button);

        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();

        googleApiClient = new GoogleApiClient
                .Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        login.setOnClickListener(this);
        Log.e("abcd", String.valueOf(out));
        out = getIntent().getBooleanExtra("logout1", false);
        Log.e("abcd1", String.valueOf(out));
        if (out){
            Log.d("signout", String.valueOf(out));
            signOut();
        }else{
            Log.d("signout", "sign else");
        }





    }
    public void login(){
        Intent signInIntent =  Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }

    private void signOut() {

        Log.e("Signout", "asojas");
        if (googleApiClient.isConnected()) {
            Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(

                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {
                            //updateUI(false);
                            Log.e("Signout11", "asojas");

                            Toast.makeText(Login.this, "You have Logged Out!", Toast.LENGTH_LONG).show();

                        }
                    });
        }
        else{
            googleApiClient.connect();
            googleApiClient.registerConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                @Override
                public void onConnected(@Nullable Bundle bundle) {

                    //FirebaseAuth.getInstance().signOut();
                    if(googleApiClient.isConnected()) {
                        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                            @Override
                            public void onResult(@NonNull Status status) {
                                if (status.isSuccess()) {
                                    Log.d("signout", "User Logged out");
                                    googleApiClient.disconnect();
                                    //finish();
                                    //Intent intent = new Intent(Login.this, LoginActivity.class);
                                    //startActivity(intent);
                                    //finish();
                                }
                            }
                        });
                    }
                }

                @Override
                public void onConnectionSuspended(int i) {
                    Log.d("signout", "Google API Client Connection Suspended");
                }
            });
            Log.d("signout12", "out12");
        }
        //googleApiClient.disconnect();
    }

    /*public void signOutFromGplus() {
        if (googleApiClient.isConnected()) {
            this.AccountApi.clearDefaultAccount(googleApiClient);
            googleApiClient.disconnect();
            //Toast.makeText(this, getString(R.string.logout_status) , Toast.LENGTH_LONG).show();
            //MyLog.info(getString(R.string.logout_status));
        }
    }
*/

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.sign_in_button:
                login();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);


        }
    }



    @Override
    public void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d("done", "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            //handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.

            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {

                    //handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()){
            GoogleSignInAccount signInAccount = result.getSignInAccount();

            String email = signInAccount.getEmail();

            Intent sendData = new Intent(Login.this, home.class);

            sendData.putExtra("email", email);

            startActivity(sendData);
            finish();

        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("on Connection Failed  ", "onConnectionFailed:" + connectionResult);


    }

    @Override
    public void onBackPressed() {
        Log.d("back", String.valueOf(out));
        //System.exit(0);
        moveTaskToBack(true);
//        if (out){
//            Log.d("back1", String.valueOf(out));
//            System.exit(0);
//        }else{
//
//        }




    }
}
