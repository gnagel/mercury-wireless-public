package com.mercury_wireless.lib.sliding_button;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A horizontal checkbox button that slides from left to right & right to left.
 * 
 * @author G Nagel
 */
public final class SlidingHorizontalButton extends LinearLayout implements View.OnClickListener {
	/**
	 * Interface definition for a callback to be invoked when the checked_value
	 * state of a sliding button changed.
	 * 
	 * @author G Nagel
	 */
	public static interface OnCheckedChangeListener {
		/**
		 * Called when the checked_value state of a compound button has changed.
		 * 
		 * @param buttonView
		 * @param isChecked
		 */
		abstract void onCheckedChanged(SlidingHorizontalButton buttonView, boolean isChecked);
	}


	// Animation factory for the UI
	private AnimationFactory		animation;

	// Checked State checked_listener
	private OnCheckedChangeListener	checked_listener	= null;

	// Current checked_value state
	private boolean					checked_value		= true;

	// "On" / "Off" labels
	private final TextView			labelOn, labelOff;

	// The button that is toggeled
	private final View				toggle_button;

	// Container for the button
	private final LinearLayout		toggle_container;


	/**
	 * Construct a new instance of the {@link SlidingHorizontalButton} with the
	 * default styles.
	 */
	public SlidingHorizontalButton(final Context context) {
		this(context, null);
	}


	/**
	 * Construct a new instance of the {@link SlidingHorizontalButton} with the
	 * given style attributes.
	 */
	public SlidingHorizontalButton(final Context context, final AttributeSet attrs) {
		super(context, attrs);

		/**
		 * Setup the UI
		 */
		final View root = View.inflate(context, R.layout.sliding_button_toggle_button, null);

		// Find the container and wire it up
		toggle_container = (LinearLayout) root.findViewById(R.id.SlidingHorizontalButton_Toggle_Container);
		try {
			toggle_container.setGravity(Gravity.RIGHT);
			toggle_container.setOnClickListener(this);
		}
		catch (final java.lang.NullPointerException e) {
			// Catch the error when we are in ADT's XML Editor
		}

		// Find the button and wire it up
		toggle_button = root.findViewById(R.id.SlidingHorizontalButton_Toggle_Button);
		try {
			toggle_button.setOnClickListener(this);
		}
		catch (final java.lang.NullPointerException e) {
			// Catch the error when we are in ADT's XML Editor
		}

		// Find the labels
		labelOn = (TextView) root.findViewById(R.id.SlidingHorizontalButton_Label_TextOn);
		labelOff = (TextView) root.findViewById(R.id.SlidingHorizontalButton_Label_TextOff);

		// Setup the animation callback
		animation = new AnimationFactoryNormal(toggle_button);

		// Add the layout to the container
		addView(root, new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.FILL_PARENT,
				android.view.ViewGroup.LayoutParams.FILL_PARENT));

		// If no attributes were passed in, then abort now
		if (null == attrs) {
			return;
		}

		/**
		 * Parse the attributes and apply the values to the UI
		 */
		final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SlidingHorizontalButton);

		// Set the "On" text
		final Object valueOn = typedArray.getString(R.styleable.SlidingHorizontalButton_textOn);
		if (null != valueOn) {
			try {
				labelOn.setText(valueOn.toString());
			}
			catch (final java.lang.NullPointerException e) {
				// Catch the error when we are in ADT's XML Editor
			}
		}

		// Set the "Off" text
		final Object valueOff = typedArray.getString(R.styleable.SlidingHorizontalButton_textOff);
		if (null != valueOff) {
			try {
				labelOff.setText(valueOff.toString());
			}
			catch (final java.lang.NullPointerException e) {
				// Catch the error when we are in ADT's XML Editor
			}
		}

		// Set the "checked_value" value
		final boolean valueChecked = typedArray.getBoolean(R.styleable.SlidingHorizontalButton_checked, checked_value);
		if (valueChecked != checked_value) {
			try {
				// animation = new AnimationFactoryReversed(toggle_button);
				// toggle_container.setGravity(Gravity.LEFT);
				// checked_value = valueChecked;
			}
			catch (final java.lang.NullPointerException e) {
				// Catch the error when we are in ADT's XML Editor
			}
		}
	}


	/**
	 * Get the checked_value state.
	 */
	public final boolean isChecked() {
		return checked_value;
	}


	/**
	 * Perform the click event for the given id
	 */
	@Override
	public final void onClick(View view) {
		// Just in case someone does something stupid ...
		if (null == view) {
			view = toggle_button;
		}

		// Get the id of the view
		final int id = view.getId();

		// If the ID matches the container or the button then toggle the view
		switch (id) {
			case R.id.SlidingHorizontalButton_Toggle_Container:
			case R.id.SlidingHorizontalButton_Toggle_Button:
				// Get the animation for the view
				final TranslateAnimation rotation = checked_value ? animation.getAnimationUncheck() : animation
						.getAnimationCheck();
				rotation.setDuration(150);
				rotation.setFillAfter(true);
				rotation.setInterpolator(new AccelerateInterpolator());
				toggle_button.startAnimation(rotation);

				// Toggle the checked_value state
				checked_value = !checked_value;

				Log.w("", checked_value ? "Checked!" : "UnChecked!");

				// Fire the checked_value checked_listener
				if (null != checked_listener) {
					try {
						checked_listener.onCheckedChanged(this, checked_value);
					}
					catch (final java.lang.NullPointerException e) {
						// Catch the error when we are in ADT's XML Editor
					}
				}
		}
	}


	/**
	 * Call this view's OnClickListener, if it is defined.
	 */
	@Override
	public final boolean performClick() {
		return toggle_button.performClick();
	}


	/**
	 * Changes the checked_value state of this button.
	 * 
	 * @param checked_value
	 */
	public final void setChecked(final boolean checked) {
		if (checked != isChecked()) {
			onClick(toggle_button);
		}
	}


	/**
	 * Register a callback to be invoked when the checked_value state of this
	 * button changes.
	 * 
	 * @param checked_listener
	 */
	public final void setOnCheckedChangeListener(final OnCheckedChangeListener listener) {
		checked_listener = listener;
	}


	/**
	 * Change the checked_value state of the view to the inverse of its current
	 * state
	 */
	public final void toggle() {
		setChecked(!isChecked());
	}
}
