package com.mercury_wireless.android.demo.sliding_button;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;


public class SlidingHorizontalButton extends LinearLayout implements View.OnClickListener {

	private ViewFlipper	viewFlipper;

	private View		viewLeft;

	private View		viewRight;


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

		return root;
	}
}
