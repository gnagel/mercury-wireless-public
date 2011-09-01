package com.mercury_wireless.lib.sliding_button;


import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import com.mercury_wireless.lib.sliding_button.R;


/**
 * Animation Factory used when the layout is starting out reversed (unchecked)
 * 
 * @author G Nagel
 */
final class AnimationFactoryReversed implements AnimationFactory {
	// Handle to the button to be animated
	private final View	view;


	public AnimationFactoryReversed(final View view) {
		super();
		this.view = view;
	}


	/**
	 * Get the checked animation. This will slide the button to the right
	 */
	@Override
	public TranslateAnimation getAnimationCheck() {
		Log.w("", "Checked!");
		return new TranslateAnimation(0F, view.getWidth(), 0F, 0F);
	}


	/**
	 * Get the checked animation. This will slide the button to the left
	 */
	@Override
	public TranslateAnimation getAnimationUncheck() {
		Log.w("", "UnChecked!");
		return new TranslateAnimation(view.getWidth(), 0F, 0F, 0F);
	}
}
