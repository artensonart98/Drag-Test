package com.arthur.hritik.dragtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.arthur.hritik.dragtest.customviews.TopBar;
import com.arthur.hritik.dragtest.customviews.TripHistoryView;
import com.arthur.hritik.dragtest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private TripHistoryView item1, item2, item3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUi();
        setTopBar();
        updateUi();
    }

    private void initUi() {
        item1 = binding.item1;
        item2 = binding.item2;
        item3 = binding.item3;
    }

    private void updateUi() {
        item1.setTimestamp("20 Mar 2019, 10:00 AM");
        item1.setCost("2000");
        item1.setBookingId("JF092319");
        item1.setSource("Silver Jubilee Tower");
        item1.setDestination("St. Mary's Basilica, Bengaluru");
        item1.setPaymentState(PaymentState.SUCCESSFUL);

        item2.setTimestamp("20 Mar 2019, 10:00 AM");
        item2.setCost("2000");
        item2.setBookingId("JF092319");
        item2.setSource("Silver Jubilee Tower");
        item2.setDestination("St. Mary's Basilica, Bengaluru");
        item2.setPaymentState(PaymentState.IN_PROCESS);

        item3.setTimestamp("20 Mar 2019, 10:00 AM");
        item3.setCost("2000");
        item3.setBookingId("JF092319");
        item3.setSource("Silver Jubilee Tower");
        item3.setDestination("St. Mary's Basilica, Bengaluru");
        item3.setPaymentState(PaymentState.FAILED);
    }

    private void setTopBar() {
        binding.topBar.setTitle("Your Trips");
        binding.topBar.setBackPressListener(new TopBar.OnBackPressListener() {
            @Override
            public void onBackPress() {
                Toast.makeText(MainActivity.this, "Back pressed", Toast.LENGTH_SHORT).show();
            }
        });
        binding.topBar.setBellPressListener(new TopBar.OnBellPressListener() {
            @Override
            public void onBellPressed() {
                Toast.makeText(MainActivity.this, "Bell pressed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}