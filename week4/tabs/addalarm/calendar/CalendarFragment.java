package com.david.calendaralarm.tabs.addalarm.calendar;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.david.calendaralarm.R;
import com.david.calendaralarm.tabs.addalarm.AddDialogFragment;
import butterknife.ButterKnife;

/**
 * Fragment of calendar.
 */
public class CalendarFragment extends AddDialogFragment{

    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = layoutInflater
                .inflate(R.layout.fragment_calendar, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupUi();
    }

    private void setupUi() {
    }


}
