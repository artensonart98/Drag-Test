package com.arthur.hritik.dragtest.customviews;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.arthur.hritik.dragtest.R;
import com.arthur.hritik.dragtest.databinding.LayoutSourceDestinantionBinding;

public class SourceDestinationView extends ConstraintLayout {
    private LayoutSourceDestinantionBinding binding;

    public SourceDestinationView(Context context) {
        super(context);
    }

    public SourceDestinationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SourceDestinationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        SourceDestinationView.inflate(getContext(), R.layout.layout_source_destinantion, this);
        binding = LayoutSourceDestinantionBinding.bind(this);
    }

    public void setSource(@NonNull String source) {
        if (source.isEmpty()) return;
        binding.textSource.setText(source);
    }

    public void setDestination(@NonNull String destination) {
        if (destination.isEmpty()) return;
        binding.textDestination.setText(destination);
    }
}
