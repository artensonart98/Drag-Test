package com.arthur.hritik.dragtest.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.arthur.hritik.dragtest.PaymentState;
import com.arthur.hritik.dragtest.R;
import com.arthur.hritik.dragtest.databinding.LayoutTripHistoryBinding;

import static com.arthur.hritik.dragtest.PaymentState.SUCCESSFUL;

public class TripHistoryView extends CardView {
    private LayoutTripHistoryBinding binding;

    public TripHistoryView(@NonNull Context context) {
        super(context);
    }

    public TripHistoryView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TripHistoryView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        TripHistoryView.inflate(getContext(), R.layout.layout_trip_history, this);
        binding = LayoutTripHistoryBinding.bind(this);

    }

    public void setTimestamp(@NonNull String timestamp) {
        if (timestamp.isEmpty()) return;
        binding.textTimestamp.setText(timestamp);
    }

    public void setBookingId(@NonNull String id) {
        if (id.isEmpty()) return;
        binding.textBookingId.setText(id);
    }

    public void setCost(@NonNull String cost) {
        if (cost.isEmpty()) return;
        binding.textCost.setText(cost);
    }

    public void setSource(@NonNull String source) {
        binding.cvSourceDestination.setSource(source);
    }

    public void setDestination(@NonNull String destination) {
        binding.cvSourceDestination.setDestination(destination);
    }

    public void setPaymentState(@NonNull PaymentState state) {
        switch (state) {
            case SUCCESSFUL:
                binding.textPayStatus.setText("Payment Successful");
                binding.textPayStatus
                        .setBackgroundColor(ContextCompat.getColor(getContext(), R.color.lightish_blue));
                break;

            case IN_PROCESS:
                binding.textPayStatus.setText("Payment in Process");
                binding.textPayStatus
                        .setBackgroundColor(ContextCompat.getColor(getContext(), R.color.yellow_orange));
                break;

            case FAILED:
                binding.textPayStatus.setText("Payment Failed");
                binding.textPayStatus
                        .setBackgroundColor(ContextCompat.getColor(getContext(), R.color.grapefruit));
        }
    }
}
