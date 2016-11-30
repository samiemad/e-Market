package com.scit.samiemad.emart.ui;

import com.scit.samiemad.emart.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ItemSelectActivity extends AppCompatActivity {

	String market;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_item_select);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		market = getIntent().getStringExtra("EXTRA_MARKET_NAME");
		setTitle(market);
	}

	public void clickItem(View v) {
		Intent i = new Intent(this, ItemInfoActivity.class);
		i.putExtra("EXTRA_ITEM", (String) v.getTag());
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.item_select, menu);
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
