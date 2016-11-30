package com.scit.samiemad.emart.ui;

import com.scit.samiemad.emart.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

	}

	public void clickBuy(View v) {
		startActivity(new Intent(this, MarketSelectActivity.class));
	}

	public void clickCategory(View v) {
		Intent i = new Intent(this, ItemSelectActivity.class);
		String s = v.getTag().toString();
		i.putExtra("EXTRA_MARKET_NAME", s);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
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
