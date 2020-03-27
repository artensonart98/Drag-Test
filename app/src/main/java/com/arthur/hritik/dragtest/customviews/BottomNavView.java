package com.arthur.hritik.dragtest.customviews;

import android.content.Context;
import android.util.AttributeSet;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.arthur.hritik.dragtest.R;
import com.arthur.hritik.dragtest.databinding.LayoutBottomNavBinding;

public class BottomNavView extends ConstraintLayout {
    LayoutBottomNavBinding binding;
    public BottomNavView(Context context) {
        super(context);
    }

    public BottomNavView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BottomNavView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        BottomNavView.inflate(getContext(), R.layout.layout_bottom_nav, this);
        binding = LayoutBottomNavBinding.bind(this);
    }

    //listeners for the 3 buttons can be implemented the same way as done for TopBar
}
