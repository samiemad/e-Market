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

public class LoginActivity extends Activity {

	ProgressBar pbLogin;
	EditText etPass, etEmail;
	Button bLogin, bRegister;
	LinearLayout view;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(getResources().getColor(R.color.primary_dark));
		}

		linkXML();

	}

	private void linkXML() {
		pbLogin = (ProgressBar) findViewById(R.id.progressBarLogin);
		etPass = (EditText) findViewById(R.id.editTextPassword);
		etEmail = (EditText) findViewById(R.id.editTextEmail);
		bLogin = (Button) findViewById(R.id.buttonLogin);
		bRegister = (Button) findViewById(R.id.buttonRegister);
		view = (LinearLayout) findViewById(R.id.linearLayoutLogin);
	}

	public void clickLogin(View v) {
		etPass.setEnabled(false);
		etEmail.setEnabled(false);
		bLogin.setEnabled(false);
		bRegister.setEnabled(false);
		view.animate().scaleX(0.9f).scaleY(0.9f).translationY(-15).start();

		pbLogin.setVisibility(View.VISIBLE);

		v.postDelayed(r, 3000);
	}

	public void clickRegister(View v) {
		startActivity(new Intent(this, RegisterActivity.class));
		finish();
	}

	private void success() {
		startActivity(new Intent(LoginActivity.this, HomeActivity.class));
		finish();
	}

	private void fail() {
		etPass.setEnabled(true);
		etEmail.setEnabled(true);
		bLogin.setEnabled(true);
		bRegister.setEnabled(true);

		etPass.setText("");
		etPass.setError(getString(R.string.error_pass_incorrect));
		etEmail.setError(getString(R.string.error_pass_incorrect));
		etPass.requestFocus();

		view.animate().scaleX(1).scaleY(1).translationY(0).start();

		Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
		etEmail.startAnimation(shake);
		etPass.startAnimation(shake);
		bLogin.startAnimation(shake);

		pbLogin.setVisibility(View.INVISIBLE);

	}

	private Runnable r = new Runnable() {
		@Override
		public void run() {
			if (etPass.getText().toString().equals("password")) {
				success();
			} else {
				fail();
			}
		}

	};
}
