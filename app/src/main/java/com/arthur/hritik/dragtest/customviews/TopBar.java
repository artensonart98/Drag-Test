package com.arthur.hritik.dragtest.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.arthur.hritik.dragtest.R;
import com.arthur.hritik.dragtest.databinding.LayoutTopBarBinding;

public class TopBar extends RelativeLayout {
    private LayoutTopBarBinding binding;
    private OnBackPressListener backPressListener;
    private OnBellPressListener bellPressListener;

    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        TopBar.inflate(getContext(), R.layout.layout_top_bar, this);
        binding = LayoutTopBarBinding.bind(this);

        binding.imageBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (backPressListener != null)
                    backPressListener.onBackPress();
            }
        });

        binding.imageBell.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bellPressListener != null)
                    bellPressListener.onBellPressed();
            }
        });
    }

    public void setTitle(String title) {
        if (title == null) return;
        binding.textTitle.setText(title);
    }

    public void setBackPressListener(OnBackPressListener backPressListener) {
        this.backPressListener = backPressListener;
    }

    public void setBellPressListener(OnBellPressListener bellPressListener) {
        this.bellPressListener = bellPressListener;
    }

    public interface OnBackPressListener {
        public void onBackPress();
    }

    public interface OnBellPressListener {
        public void onBellPressed();
    }
}

