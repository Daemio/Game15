package com.example.damian.game15.view;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.damian.game15.events.CallBackViewSwiped;

/**
 * Created by Admin on 05.11.2015.
 */
public class MyOnSwipeListener implements View.OnTouchListener{
    CallBackViewSwiped callBackViewSwiped;
    View view;
    @SuppressWarnings("deprecation")
    private final GestureDetector gestureDetector = new GestureDetector(new GestureListener());

    public MyOnSwipeListener(CallBackViewSwiped callBackViewSwiped) {
        this.callBackViewSwiped = callBackViewSwiped;
    }

    @Override
    public boolean onTouch(final View v, final MotionEvent event) {
        view = v;
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public void onLongPress(MotionEvent motionEvent) {
            super.onLongPress(motionEvent);
            onLongPressDetected(motionEvent);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            // by default, don't consume the event
            boolean consumeEvent = false;
            try {
                if (e1 != null && e2 != null) {
                    float diffY = e2.getY() - e1.getY();
                    float diffX = e2.getX() - e1.getX();

                    if (Math.abs(diffX) > Math.abs(diffY) && Math.abs(diffX) > SWIPE_THRESHOLD
                            && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                        consumeEvent = true;
                    }

                    if (Math.abs(diffY) > Math.abs(diffX) && Math.abs(diffY) > SWIPE_THRESHOLD
                            && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY < 0) {
                            onSwipeTop();
                        } else {
                            onSwipeBottom();
                        }
                        consumeEvent = true;
                    }


                }
            } catch (Exception exception) {
            }
            return consumeEvent;
        }
    }

    public void onSwipeRight() {
        callBackViewSwiped.onRightSwipe(view);
    }

    public void onSwipeLeft() {
        callBackViewSwiped.onLeftSwipe(view);
    }

    public void onSwipeTop() {
        callBackViewSwiped.onTopSwipe(view);
    }

    public void onSwipeBottom() {
        callBackViewSwiped.onBottomSwipe(view);
    }

    public void onLongPressDetected(MotionEvent motionEvent) {

    }

    public void setIsLongpressEnabled(boolean enabled) {
        gestureDetector.setIsLongpressEnabled(enabled);
    }
}
