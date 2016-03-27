package com.akturk.trimbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.akturk.timebar.R;
import com.appyvet.rangebar.RangeBar;

public class TrimBar extends FrameLayout implements RangeBar.OnRangeBarChangeListener, View.OnTouchListener {

    private RangeBar mRangeBar;
    private Interval mInterval;
    private OnSeekListener mSeekListener;

    public TrimBar(Context context) {
        super(context);

        if (!isInEditMode()) {
            init();
        }
    }

    public TrimBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (!isInEditMode()) {
            init();
        }

    }

    public TrimBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (!isInEditMode()) {
            init();
        }

    }

    private void init() {
        inflate(getContext(), R.layout.component_trimbar, this);

        mInterval = new Interval();

        mRangeBar = (RangeBar) findViewById(R.id.component_trimbar_rangebar);
        mRangeBar.setOnRangeBarChangeListener(this);
        mRangeBar.setOnTouchListener(this);
    }

    @Override
    public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
        mInterval.mStartTime = leftPinIndex;
        mInterval.mEndTime = rightPinIndex;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (mSeekListener == null) {
            return false;
        }

        int action = motionEvent.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mSeekListener.onSeekStart();
                break;
            case MotionEvent.ACTION_MOVE:
                mSeekListener.onSeekMove(mInterval.mStartTime);
                break;
            case MotionEvent.ACTION_UP:
                mSeekListener.onSeekEnd(mInterval.mStartTime, mInterval.mStartTime, mInterval.mEndTime);
                break;
        }

        return super.onTouchEvent(motionEvent);
    }

    public void setOnSeekListener(OnSeekListener listener) {
        this.mSeekListener = listener;
    }

    public interface OnSeekListener {
        void onSeekStart();

        void onSeekMove(int time);

        void onSeekEnd(int time, int start, int end);
    }
}
