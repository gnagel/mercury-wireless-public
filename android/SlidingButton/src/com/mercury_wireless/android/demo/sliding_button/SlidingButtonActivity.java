package com.mercury_wireless.android.demo.sliding_button;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;


public class SlidingButtonActivity extends Activity {
	// @Override
	// public void onCreate(final Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	// setContentView(R.layout.main);
	//
	// final ViewFlipper viewFlipper = (ViewFlipper)
	// findViewById(R.id.viewFlipper);
	//
	// final Button nextButton = (Button) findViewById(R.id.nextButton);
	// nextButton.setOnClickListener(new View.OnClickListener() {
	// @Override
	// public void onClick(final View v) {
	// viewFlipper.showNext();
	// }
	// });
	//
	// final Button previousButton = (Button) findViewById(R.id.previousButton);
	// previousButton.setOnClickListener(new View.OnClickListener() {
	// @Override
	// public void onClick(final View v) {
	// viewFlipper.showPrevious();
	// }
	// });
	// }

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final ViewFlipper viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
		final Button nextButton = (Button) findViewById(R.id.nextButton);
		nextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				viewFlipper.setInAnimation(SlidingButtonActivity.this, R.anim.view_transition_in_left);
				viewFlipper.setOutAnimation(SlidingButtonActivity.this, R.anim.view_transition_out_left);
				viewFlipper.showNext();
			}
		});

		final Button previousButton = (Button) findViewById(R.id.previousButton);
		previousButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				viewFlipper.setInAnimation(SlidingButtonActivity.this, R.anim.view_transition_in_right);
				viewFlipper.setOutAnimation(SlidingButtonActivity.this, R.anim.view_transition_out_right);
				viewFlipper.showPrevious();
			}
		});
	}
}
