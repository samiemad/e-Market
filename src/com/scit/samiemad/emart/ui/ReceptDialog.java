package com.scit.samiemad.emart.ui;

import com.scit.samiemad.emart.R;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReceptDialog extends Dialog implements android.view.View.OnClickListener {

	ItemInfoActivity activity;
	Button select;

	public ReceptDialog(ItemInfoActivity context, String msg) {
		super(context);
		this.activity = context;
		setTitle(R.string.receptDialog_title);
		setContentView(R.layout.dialog_recept);
		// setIcon(R.drawable.ic_done_all_black_48dp);
		select = (Button) findViewById(R.id.buttonReceptDismiss);
		select.setOnClickListener(this);

		TextView tv = (TextView) findViewById(R.id.textView_dialogReceptMsg);
		tv.setText(msg);

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.buttonLocationSelect) {
		}
		dismiss();
	}

}
