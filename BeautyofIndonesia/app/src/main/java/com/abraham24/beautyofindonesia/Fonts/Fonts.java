package com.abraham24.beautyofindonesia.Fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by KOCHOR on 9/13/2017.
 */

public class Fonts {

    public static void MontserratExtraLight(Context context, TextView tvData) {
        Typeface Robotos = Typeface.createFromAsset(context.getAssets(), "fonts/montserrat_extra_light.otf");
        tvData.setTypeface(Robotos);
    }

    public static void RobotoRegular(Context context, TextView tvData) {
        Typeface Robotos = Typeface.createFromAsset(context.getAssets(), "fonts/roboto_regular.ttf");
        tvData.setTypeface(Robotos);
    }
}
