package com.scit.samiemad.emart.ui;

import com.scit.samiemad.emart.R;
import com.scit.samiemad.emart.R.color;
import com.scit.samiemad.emart.R.id;
import com.scit.samiemad.emart.R.layout;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

public class SplashActivity extends Activity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setStatusBarColor(getResources().getColor(R.color.primary_dark));
		}

		final View imageE = findViewById(R.id.imageSplashE);
		final View imageMarket = findViewById(R.id.imageSplashMarket);
		final View imageDash = findViewById(R.id.imageSplashDash);
		final View view = findViewById(R.id.splashLayout);

		imageE.setTranslationX(-100);
		imageE.setAlpha(0);
		imageE.setRotation(90);
		imageE.setTranslationY(-50);

		imageMarket.setTranslationX(150);
		imageMarket.setAlpha(0);
		imageMarket.setRotation(-90);
		imageMarket.setTranslationY(-50);
		imageMarket.setPivotX(0);
		imageMarket.setPivotY(0);

		imageDash.setTranslationY(50);
		imageDash.setAlpha(0);

		final AnimatorListener fnish = new AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {
			}

			@Override
			public void onAnimationRepeat(Animator animation) {
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				startActivity(new Intent(getApplicationContext(), LoginActivity.class));
				finish();
			}

			@Override
			public void onAnimationCancel(Animator animation) {
			}
		};
		final AnimatorListener crt = new AnimatorListener() {

			@Override
			public void onAnimationStart(Animator animation) {
			}

			@Override
			public void onAnimationRepeat(Animator animation) {
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				view.animate().alpha(0.5f).scaleX(2f).scaleY(0f).translationY(5)
						.setInterpolator(AnimationUtils.loadInterpolator(getApplicationContext(),
								android.R.anim.accelerate_decelerate_interpolator))
						.setListener(fnish).setStartDelay(500).start();
			}

			@Override
			public void onAnimationCancel(Animator animation) {
			}
		};
		Interpolator pol = AnimationUtils.loadInterpolator(getApplicationContext(),
				android.R.anim.anticipate_overshoot_interpolator);

		imageE.animate().alpha(1).translationX(0).setDuration(1700).translationY(0).rotation(0).setStartDelay(300)
				.setInterpolator(pol).start();

		imageDash.animate().alpha(1).translationY(0).setStartDelay(1000).setInterpolator(pol).start();

		imageMarket.animate().alpha(1).translationX(0).setDuration(2000).translationY(0).rotation(0).setStartDelay(700)
				.setInterpolator(pol).setListener(crt).start();
		
		
//		ObjectAnimator anim = ;

	}

}
