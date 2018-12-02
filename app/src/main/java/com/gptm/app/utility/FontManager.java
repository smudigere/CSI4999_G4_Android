package com.gptm.app.utility;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Fontawesome is unlike other fonts. Each element has a unicode.
 * This class helps understand the unicode of each element and displays the user the icon according to the requirements.
 */

public class FontManager {

    private static final String ROOT = "fonts/";

    public static final String FONTAWESOME = ROOT + "fontawesome_webfont.ttf";

    public static final String MATERIAL_DESIGN = ROOT + "google_material_icon.ttf";

    public static Typeface getTypeface(Context context, String font) {
        return Typeface.createFromAsset(context.getAssets(), font);
    }

    public static void markAsIconContainer(View v, Typeface typeface) {
        if (v instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) v;
            for (int i = 0; i < vg.getChildCount(); i++) {
                View child = vg.getChildAt(i);
                markAsIconContainer(child, typeface);
            }
        } else if (v instanceof TextView) {
            ((TextView) v).setTypeface(typeface);
        }
    }

}