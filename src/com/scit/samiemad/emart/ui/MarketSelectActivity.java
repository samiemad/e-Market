package com.scit.samiemad.emart.ui;

import com.scit.samiemad.emart.R;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MarketSelectActivity extends AppCompatActivity {

	// private String[] uris = { "geo:0,0?q=Orange+Mall+syria",
	// "geo:0,0?q=k+Mart+syria", "geo:0,0?q=city+center+syria" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_market_select);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	public void clickSelectMall(View v) {
		String s = (String) v.getTag();
		Intent i = new Intent(this, ItemSelectActivity.class);
		i.putExtra("EXTRA_MARKET_NAME", s);
		startActivity(i);
		finish();
	}

	public void clickShowOnMap(View v) {
		String s = "geo:0,0?q=" + (String) v.getTag() + " Syria";
		displayMap(Uri.parse(s));
	}

	private void displayMap(Uri uri) {
		Intent i = new Intent(Intent.ACTION_VIEW, uri);
		if (i.resolveActivity(getPackageManager()) != null) {
			startActivity(i);
		} else {
			Log.e("Uri maps", uri.toString() + " failed!");
			AlertDialog dlg = new AlertDialog.Builder(this).create();
			dlg.setTitle(R.string.error_starting_maps_title);
			dlg.setMessage(getString(R.string.error_starting_maps_info));
			dlg.setIcon(android.R.drawable.ic_dialog_alert);
			dlg.show();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.market_select, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		return super.onOptionsItemSelected(item);
	}

}
