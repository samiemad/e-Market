package com.scit.samiemad.emart.ui;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.scit.samiemad.emart.R;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ItemInfoActivity extends AppCompatActivity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_info);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(getResources().getColor(R.color.primary_dark));
		}
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		setTitle(getIntent().getStringExtra("EXTRA_ITEM"));

	}

	public void clickOrder(View v) {
		new LocationDialog(this).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.item_info, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		return super.onOptionsItemSelected(item);
	}

	int PLACE_PICKER_REQUEST = 1;

	public void pickPlace() {
		PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

		try {
			startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
		} catch (Exception e) {
			AlertDialog dlg = new AlertDialog.Builder(this).create();
			dlg.setTitle(R.string.error_play_services_title);
			dlg.setMessage(getString(R.string.error_play_services_info));
			dlg.setIcon(android.R.drawable.ic_dialog_alert);
			dlg.show();
			e.printStackTrace();
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == PLACE_PICKER_REQUEST) {
			if (resultCode == RESULT_OK) {
				Place place = PlacePicker.getPlace(data, this);
				// String toastMsg = String.format("Place: %s",
				// place.getName());
				// Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
				orderComplete(place.getName().toString());
			}
		}
	}

	public void orderComplete(String place) {
		String msg = getString(R.string.receptDialog_info);
		msg = msg.replace("*", place);
		new ReceptDialog(this, msg).show();
	}
}
