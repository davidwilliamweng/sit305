package com.david.calendaralarm.tabs.ui;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.widget.TextView;
import com.david.calendaralarm.R;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.WeekBar;

public class EnglishWeekBar extends WeekBar {

    private int mPreSelectedIndex;

    public EnglishWeekBar(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.english_week_bar, this, true);
        setBackgroundColor(Color.WHITE);
        int padding = dipToPx(context, 10);
        setPadding(padding, 0, padding, 0);
    }

    /**
     * Date selection event
     * @param calendar the calendar
     * @param weekStart the start week
     * @param isClick It's can be click
     */
    @Override
    protected void onDateSelected(Calendar calendar, int weekStart, boolean isClick) {
        getChildAt(mPreSelectedIndex).setSelected(false);
        int viewIndex = getViewIndexByCalendar(calendar, weekStart);
        getChildAt(viewIndex).setSelected(true);
        mPreSelectedIndex = viewIndex;
    }

    /**
     * When the week begins to change, you need to rewrite this method to avoid problems using custom layouts
     *
     * @param weekStart
     */
    @Override
    protected void onWeekStartChange(int weekStart) {
        for (int i = 0; i < getChildCount(); i++) {
            ((TextView) getChildAt(i)).setText(getWeekString(i, weekStart));
        }
    }

    /**
     * Get week text, which is only for parent use
     *
     * @param index     index
     * @param weekStart weekStart
     * @return weekString
     */
    private String getWeekString(int index, int weekStart) {
        String[] weeks = getContext().getResources().getStringArray(R.array.english_week_string_array);

        if (weekStart == 1) {
            return weeks[index];
        }
        if (weekStart == 2) {
            return weeks[index == 6 ? 0 : index + 1];
        }
        return weeks[index == 0 ? 6 : index - 1];
    }

    /**
     * dp to px
     *
     * @param context context
     * @param dpValue dp
     * @return px
     */
    private static int dipToPx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
