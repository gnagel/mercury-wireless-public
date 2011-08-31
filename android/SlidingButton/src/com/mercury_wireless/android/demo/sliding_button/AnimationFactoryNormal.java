package com.mercury_wireless.android.demo.sliding_button;


import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;


/**
 * Animation Factory used when the layout is starting out normally (checked)
 * 
 * @author G Nagel
 */
final class AnimationFactoryNormal implements AnimationFactory {
	// Handle to the button to be animated
	private final View	view;


	public AnimationFactoryNormal(final View view) {
		super();
		this.view = view;
	}


	/**
	 * Get the checked animation. This will slide the button to the right
	 */
	@Override
	public TranslateAnimation getAnimationCheck() {
		Log.w("", "Checked!");
		return new TranslateAnimation(-view.getWidth(), 0F, 0F, 0F);
	}


	/**
	 * Get the checked animation. This will slide the button to the left
	 */
	@Override
	public TranslateAnimation getAnimationUncheck() {
		Log.w("", "UnChecked!");
		return new TranslateAnimation(0F, -view.getWidth(), 0F, 0F);
	}
}
