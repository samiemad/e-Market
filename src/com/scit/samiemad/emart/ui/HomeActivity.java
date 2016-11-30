package com.scit.samiemad.emart.ui;

import com.scit.samiemad.emart.R;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

	Toolbar toolbar;
	NavigationView navigationView;
	DrawerLayout drawerLayout;
	int currentSection = R.id.navHome;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		navigationView = (NavigationView) findViewById(R.id.navigation_view);
		navigationView.setItemIconTintList(null);
		navigationView.setNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {

			@Override
			public boolean onNavigationItemSelected(MenuItem item) {
				// if (item.isChecked())
				// item.setChecked(false);
				// else
				item.setChecked(true);

				drawerLayout.closeDrawers();
				return navigateSection(item.getItemId());
			}
		});

		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
				R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
			}
		};

		drawerLayout.setDrawerListener(toggle);

		toggle.syncState();
	}

	@Override
	protected void onResume() {
		navigationView.setCheckedItem(currentSection);
		navigateSection(currentSection);
		super.onResume();
	}

	protected boolean navigateSection(int sectionId) {
		switch (sectionId) {
		case R.id.navHome:
			currentSection = sectionId;
			HomeFragment home = new HomeFragment();
			getSupportFragmentManager().beginTransaction().replace(R.id.container, home).commit();
			return true;
		case R.id.navDeals:
			currentSection = sectionId;
			HomeFragment meats = new HomeFragment();
			getSupportFragmentManager().beginTransaction().replace(R.id.container, meats).commit();
			return true;
		case R.id.navFavs:
			currentSection = sectionId;
			HomeFragment foods = new HomeFragment();
			getSupportFragmentManager().beginTransaction().replace(R.id.container, foods).commit();
			return true;
		case R.id.navCart:
			currentSection = sectionId;
			CheckoutFragment checkout = new CheckoutFragment();
			getSupportFragmentManager().beginTransaction().replace(R.id.container, checkout).commit();
			return true;
		case R.id.navMyAccount:
			startActivity(new Intent(this, SettingsActivity.class));
			return true;
		case R.id.navLogout:
			startActivity(new Intent(this, LoginActivity.class));
			finish();
			return true;
		}
		return false;
	}

	public void clickCard(View v) {
		Toast.makeText(this, "clicked card " + v.getTag().toString(), Toast.LENGTH_LONG).show();
		Intent i = new Intent(this, ItemInfoActivity.class);
		i.putExtra("EXTRA_ITEM", (String) v.getTag());
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.action_cart:
			// TODO: open cart activity
			navigationView.setCheckedItem(R.id.navCart);
			navigateSection(R.id.navCart);
			return true;
		}
		return false;
	}

	private boolean exitWait = false;
	private static final long EXIT_DELAY = 2000;
	Toast exitToast;

	@Override
	public void onBackPressed() {
		if (drawerLayout.isDrawerOpen(navigationView)) {
			drawerLayout.closeDrawers();
		} else if (!exitWait) {
			exitToast = Toast.makeText(this, "Press back again to exit", Toast.LENGTH_LONG);
			exitToast.show();
			exitWait = true;
			drawerLayout.postDelayed(new Runnable() {
				@Override
				public void run() {
					exitWait = false;
				}
			}, EXIT_DELAY);
		} else {
			exitToast.cancel();
			super.onBackPressed();
		}
	}
}
