package com.scit.samiemad.emart.ui;

import com.scit.samiemad.emart.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class LocationDialog extends Dialog implements android.view.View.OnClickListener, OnItemClickListener {

	ItemInfoActivity activity;
	ListView lv;
	ArrayAdapter<String> adapter;
	Button select, addLocation;
	private String[] locations;
	private String place;

	public LocationDialog(ItemInfoActivity context) {
		super(context);
		this.activity = context;
		setTitle(R.string.title_locationDialog);
		setContentView(R.layout.dialog_location);
		lv = (ListView) findViewById(R.id.listViewLocation);
		locations = activity.getResources().getStringArray(R.array.locations);
		adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_single_choice, locations);
		lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);

		select = (Button) findViewById(R.id.buttonLocationSelect);
		addLocation = (Button) findViewById(R.id.buttonLocationAddNew);
		select.setOnClickListener(this);
		addLocation.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.buttonLocationSelect) {
			activity.orderComplete(place);
		} else if (v.getId() == R.id.buttonLocationAddNew) {
			activity.pickPlace();
		}
		dismiss();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		lv.setSelection(position);
		place = adapter.getItem(position);
	}

}
