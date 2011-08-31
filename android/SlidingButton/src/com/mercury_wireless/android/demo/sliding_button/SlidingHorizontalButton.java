package com.mercury_wireless.android.demo.sliding_button;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;


public class SlidingHorizontalButton extends LinearLayout implements View.OnClickListener {

	private ViewFlipper	viewFlipper;

	private View		viewLeft;

	private View		viewRight;

	private View		view;

	private boolean		checked	= true;


	public SlidingHorizontalButton(final Context context) {
		super(context);

		addView(root(context), new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.FILL_PARENT,
				android.view.ViewGroup.LayoutParams.FILL_PARENT));
	}


	public SlidingHorizontalButton(final Context context, final AttributeSet attrs) {
		super(context, attrs);

		addView(root(context), new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.FILL_PARENT,
				android.view.ViewGroup.LayoutParams.FILL_PARENT));
	}


	@Override
	public void onClick(final View v) {
		final int id = v.getId();
		final Context context = getContext();

		switch (id) {
			case R.id.container_off:
			case R.id.button_off:
				viewFlipper.setInAnimation(context, R.anim.toggle_button_transition_in_right);
				viewFlipper.setOutAnimation(context, R.anim.toggle_button_transition_out_right);
				viewFlipper.showNext();
				break;

			case R.id.container_on:
			case R.id.button_on:
				viewFlipper.setInAnimation(context, R.anim.toggle_button_transition_in_left);
				viewFlipper.setOutAnimation(context, R.anim.toggle_button_transition_out_left);
				viewFlipper.showPrevious();
				break;

			case R.id.container:
			case R.id.button:
				// final View view = (View) this.view.getParent();

				// Find the center of image
				final float centerX = view.getWidth() / 2.0f;
				final float centerY = view.getHeight() / 2.0f;

				// Create a new 3D rotation with the supplied parameter
				// The animation listener is used to trigger the next animation
				final android.view.animation.TranslateAnimation rotation = checked ? new TranslateAnimation(0F,
						-view.getWidth(), 0F, 0F) : new TranslateAnimation(-view.getWidth(), 0F, 0F, 0F);

				// final SlidingButtonAnimation rotation = new
				// SlidingButtonAnimation(start, end, centerX, centerY);
				rotation.setDuration(200);
				rotation.setFillAfter(true);
				rotation.setInterpolator(new AccelerateInterpolator());
				// rotation.setAnimationListener(new
				// DisplayNextView(isFirstImage,
				// image1, image2));

				view.startAnimation(rotation);

				// SlidingButtonAnimation.rotate(view, 0, -180);

				checked = !checked;

				// view.startAnimation(AnimationUtils.loadAnimation(context,
				// R.anim.toggle_button_rotate));
		}
	}


	private View root(final Context context) {
		final View root = inflate(context, R.layout.toggle_button, null);

		viewFlipper = (ViewFlipper) root.findViewById(R.id.view_flipper);

		View container = null;

		container = viewFlipper.findViewById(R.id.container_off);
		container.setOnClickListener(this);
		viewLeft = container.findViewById(R.id.button_off);
		viewLeft.setOnClickListener(this);

		container = viewFlipper.findViewById(R.id.container_on);
		container.setOnClickListener(this);
		viewRight = container.findViewById(R.id.button_on);
		viewRight.setOnClickListener(this);

		container = root.findViewById(R.id.container);
		container.setOnClickListener(this);

		view = container.findViewById(R.id.button);
		view.setOnClickListener(this);

		return root;
	}
}
