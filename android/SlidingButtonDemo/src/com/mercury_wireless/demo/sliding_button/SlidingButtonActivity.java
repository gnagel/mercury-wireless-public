package com.mercury_wireless.demo.sliding_button;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;


import com.mercury_wireless.lib.sliding_button.SlidingHorizontalButton;


/**
 * Simple demo activity of the {@link SlidingHorizontalButton} compared to a
 * {@link ViewFlipper}.
 * 
 * @author G Nagel
 */
public class SlidingButtonActivity extends Activity implements View.OnClickListener {
	private ViewFlipper	viewFlipper;


	@Override
	public void onClick(final View v) {
		final int id = v.getId();

		switch (id) {
			case R.id.nextButton:
				viewFlipper.setInAnimation(SlidingButtonActivity.this, R.anim.view_transition_in_left);
				viewFlipper.setOutAnimation(SlidingButtonActivity.this, R.anim.view_transition_out_left);
				viewFlipper.showNext();
				break;

			case R.id.previousButton:
				viewFlipper.setInAnimation(SlidingButtonActivity.this, R.anim.view_transition_in_right);
				viewFlipper.setOutAnimation(SlidingButtonActivity.this, R.anim.view_transition_out_right);
				viewFlipper.showPrevious();
				break;
		}
	}


	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Find the view flipper
		viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);

		// Find the buttons
		findViewById(R.id.nextButton).setOnClickListener(this);
		findViewById(R.id.previousButton).setOnClickListener(this);
	}
}
