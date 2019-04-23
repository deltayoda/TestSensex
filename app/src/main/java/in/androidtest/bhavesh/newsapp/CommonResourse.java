package in.androidtest.bhavesh.newsapp;

import android.animation.ValueAnimator;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class CommonResourse {

    public static void setNumberTextViewAnimationAndStart(final TextView textView, int startNum, int endNum, long duration) {

        if (startNum == 0) {
            if (endNum <= 100) {
                startNum = 0;
            } else {
                startNum = endNum - 100;
            }
        }

        final DecimalFormat format = (DecimalFormat) NumberFormat.getCurrencyInstance(new Locale("en", "in"));
        format.setMaximumFractionDigits(0);
        DecimalFormatSymbols symbols = format.getDecimalFormatSymbols();
        symbols.setCurrencySymbol(""); // Don't use null.
        format.setDecimalFormatSymbols(symbols);
        ValueAnimator animator = ValueAnimator.ofInt(startNum, endNum);
        animator.setDuration(duration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {

                textView.setText(format.format(convertToInt(animation.getAnimatedValue().toString())));
            }
        });
        animator.start();
    }

    public static int convertToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return 0;
        }
    }
}
