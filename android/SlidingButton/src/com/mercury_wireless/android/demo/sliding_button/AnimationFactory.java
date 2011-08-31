package com.mercury_wireless.android.demo.sliding_button;


import android.view.animation.TranslateAnimation;


/**
 * Animation Factory interface for moving the "button" to the left(unchecked) &
 * right(checked)
 * 
 * @author G Nagel
 */
interface AnimationFactory {
	/**
	 * Get the checked animation. This will slide the button to the right
	 */
	public TranslateAnimation getAnimationCheck();


	/**
	 * Get the checked animation. This will slide the button to the left
	 */
	public TranslateAnimation getAnimationUncheck();
}
