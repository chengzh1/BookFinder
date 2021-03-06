package com.wilddynamos.bookapp.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wilddynamos.bookapp.R;
import com.wilddynamos.bookapp.ws.remote.Connection;
import com.wilddynamos.bookapp.ws.remote.action.Signup;

public class SignupActivity extends Activity {
	
	private EditText email;
	private EditText name;
	private EditText password;
	private EditText confirmation;
	private Button signup;
	
	private Handler handler = new Handler() {
    	@Override
    	public void handleMessage(Message msg){
    		
    		if(msg.what == -1)
    			Toast.makeText(SignupActivity.this, "Failed...", Toast.LENGTH_SHORT).show();
    		else if(msg.what == -2)
    			Toast.makeText(SignupActivity.this, "Email exists!", Toast.LENGTH_SHORT).show();
    		else if (msg.what == 1) {
    			new Thread() {
    				@Override
    				public void run() {
    					Connection.login(email.getEditableText().toString(), 
    							password.getEditableText().toString());
    				}
    			}.start();
    			
    			Toast.makeText(SignupActivity.this, "Welcome, new friend!", Toast.LENGTH_SHORT).show();
    			signUp();
    		} else
    			Toast.makeText(SignupActivity.this, "What happened?", Toast.LENGTH_SHORT).show();
    	}
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        
        email = (EditText) findViewById(R.id.userEmail);
        name = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.userPassword);
        confirmation = (EditText) findViewById(R.id.userPasswordConfirm);
        
        signup = (Button) findViewById(R.id.signupButton);
        
        signup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (password.getEditableText().toString().equals(confirmation.getEditableText().toString())) 
				new Signup(SignupActivity.this,
						  email.getEditableText().toString(),
						  name.getEditableText().toString(),
						  password.getEditableText().toString())
					.start();
				else Toast.makeText(SignupActivity.this, "Password confirmation not match!", Toast.LENGTH_LONG).show();
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    } 
    
    public void signUp(){
    	/*Post List is a fragment activity in MultiWindowActivity*/
    	Intent intent = new Intent(this, MultiWindowActivity.class);
    	startActivity(intent);
    }
    
    public Handler getHandler() {
    	return handler;
    }
}
