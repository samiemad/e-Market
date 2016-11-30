package com.scit.samiemad.emart.ui;

import com.scit.samiemad.emart.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class RegisterActivity extends Activity {

	ProgressBar pbReg;
	EditText etPass, etPass2, etEmail, etEmail2;
	Button bLogin, bRegister;
	LinearLayout view;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(getResources().getColor(R.color.primary_dark));
		}

		linkXML();

	}

	private void linkXML() {
		pbReg = (ProgressBar) findViewById(R.id.progressBarLogin);
		etPass = (EditText) findViewById(R.id.editTextPassword);
		etPass2 = (EditText) findViewById(R.id.editTextPasswordConfirm);
		etEmail = (EditText) findViewById(R.id.editTextEmail);
		etEmail2 = (EditText) findViewById(R.id.editTextEmailConfirm);
		bLogin = (Button) findViewById(R.id.buttonLogin);
		bRegister = (Button) findViewById(R.id.buttonRegister);
		view = (LinearLayout) findViewById(R.id.linearLayoutLogin);
	}

	public void clickRegister(View v) {
		etPass.setEnabled(false);
		etEmail.setEnabled(false);
		etPass2.setEnabled(false);
		etEmail2.setEnabled(false);
		bLogin.setEnabled(false);
		bRegister.setEnabled(false);
		view.animate().scaleX(0.9f).scaleY(0.9f).translationY(-15).start();

		pbReg.setVisibility(View.VISIBLE);

		v.postDelayed(r, 1000);
	}

	public void clickLogin(View v) {
		startActivity(new Intent(this, LoginActivity.class));
		finish();
	}

	private void success() {
		startActivity(new Intent(this, HomeActivity.class));
		finish();
	}

	private void fail() {
		etPass.setEnabled(true);
		etEmail.setEnabled(true);
		etPass2.setEnabled(true);
		etEmail2.setEnabled(true);
		bLogin.setEnabled(true);
		bRegister.setEnabled(true);

		view.animate().scaleX(1).scaleY(1).translationY(0).start();

		pbReg.setVisibility(View.INVISIBLE);

	}

	private Runnable r = new Runnable() {
		@Override
		public void run() {
			Animation shake = AnimationUtils.loadAnimation(RegisterActivity.this, R.anim.shake);
			boolean good = true;
			if (!etPass.getText().toString().equals(etPass2.getText().toString())) {
				etPass2.startAnimation(shake);
				etPass2.setError("Passwords must match");
				good = false;
			}
			if (!etEmail.getText().toString().equals(etEmail2.getText().toString())) {
				etEmail2.startAnimation(shake);
				etEmail2.setError("Emails must match");
				good = false;
			}
			if (good)
				success();
			else
				fail();
		}

	};
}
