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
public class SlidingButtonDemoActivity extends Activity implements View.OnClickListener {
	private ViewFlipper	viewFlipper;


	@Override
	public void onClick(final View v) {
		final int id = v.getId();

		switch (id) {
			case R.id.sliding_button_demo_nextButton:
				viewFlipper.setInAnimation(v.getContext(), R.anim.view_transition_in_left);
				viewFlipper.setOutAnimation(v.getContext(), R.anim.view_transition_out_left);
				viewFlipper.showNext();
				break;

			case R.id.sliding_button_demo_previousButton:
				viewFlipper.setInAnimation(v.getContext(), R.anim.view_transition_in_right);
				viewFlipper.setOutAnimation(v.getContext(), R.anim.view_transition_out_right);
				viewFlipper.showPrevious();
				break;
		}
	}


	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sliding_button_demo);

		// Find the view flipper
		viewFlipper = (ViewFlipper) findViewById(R.id.sliding_button_demo_viewFlipper);

		// Find the buttons
		findViewById(R.id.sliding_button_demo_nextButton).setOnClickListener(this);
		findViewById(R.id.sliding_button_demo_previousButton).setOnClickListener(this);
	}
}
