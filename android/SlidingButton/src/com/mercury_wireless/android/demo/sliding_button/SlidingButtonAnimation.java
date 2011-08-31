package com.mercury_wireless.android.demo.sliding_button;


import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;


public class SlidingButtonAnimation extends Animation {
	public static void rotate(final View view, final float start, final float end) {
		// Find the center of image
		final float centerX = view.getWidth() / 2.0f;
		final float centerY = view.getHeight() / 2.0f;

		// Create a new 3D rotation with the supplied parameter
		// The animation listener is used to trigger the next animation
		final android.view.animation.RotateAnimation rotation = new RotateAnimation(start, end, centerX, centerY);

		// final SlidingButtonAnimation rotation = new
		// SlidingButtonAnimation(start, end, centerX, centerY);
		rotation.setDuration(500);
		rotation.setFillAfter(true);
		rotation.setInterpolator(new AccelerateInterpolator());
		// rotation.setAnimationListener(new DisplayNextView(isFirstImage,
		// image1, image2));

		view.startAnimation(rotation);
	}


	private Camera		mCamera;

	private final float	mCenterX;

	private final float	mCenterY;

	private final float	mFromDegrees;

	private final float	mToDegrees;


	public SlidingButtonAnimation(final float fromDegrees, final float toDegrees, final float centerX,
			final float centerY) {
		mFromDegrees = fromDegrees;
		mToDegrees = toDegrees;
		mCenterX = centerX;
		mCenterY = centerY;
	}


	@Override
	protected void applyTransformation(final float interpolatedTime, final Transformation t) {
		final float fromDegrees = mFromDegrees;
		final float degrees = fromDegrees + (mToDegrees - fromDegrees) * interpolatedTime;

		final float centerX = mCenterX;
		final float centerY = mCenterY;
		final Camera camera = mCamera;

		final Matrix matrix = t.getMatrix();

		camera.save();

		camera.rotateY(degrees);

		camera.getMatrix(matrix);
		camera.restore();

		matrix.preTranslate(-centerX, -centerY);
		matrix.postTranslate(centerX, centerY);

	}


	@Override
	public void initialize(final int width, final int height, final int parentWidth, final int parentHeight) {
		super.initialize(width, height, parentWidth, parentHeight);
		mCamera = new Camera();
	}
}
