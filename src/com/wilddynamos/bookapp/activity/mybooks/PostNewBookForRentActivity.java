package com.wilddynamos.bookapp.activity.mybooks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import com.wilddynamos.bookapp.R;
import com.wilddynamos.bookapp.activity.MultiWindowActivity;


public class PostNewBookForRentActivity extends Activity {
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mybooks_edit_my_book_for_rent);
		TextView tv1 = (TextView)findViewById(R.id.editMyBookForRentTitle);
		tv1.setText("Post a New Book For Rent");
		Button b1 = (Button)findViewById(R.id.edit_my_book_for_rent_saveButton);
		b1.setText("Post");
		ShowSpinner showSpinner1 = new ShowSpinner((Spinner)findViewById(R.id.rentPriceUnitSelection));
		ShowSpinner showSpinner2 = new ShowSpinner((Spinner)findViewById(R.id.rentDurationUnitSelection));
		showSpinner1.setSpinner(this);
		showSpinner1.setListener();
		showSpinner2.setSpinner(this);
		showSpinner2.setListener();
	}
	

	/* post button*/
	public void save(View view){
		Intent intent = new Intent(this, MultiWindowActivity.class);
		intent.putExtra(MultiWindowActivity.TAB_SELECT, 1);
		startActivity(intent);
	}
	
	/* cancel button*/
	public void cancel(View view){
		Intent intent = new Intent(this, MultiWindowActivity.class);
		intent.putExtra(MultiWindowActivity.TAB_SELECT, 1);
		startActivity(intent);
	}
} 

