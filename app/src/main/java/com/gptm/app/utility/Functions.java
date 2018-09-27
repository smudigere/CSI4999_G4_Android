package com.gptm.app.utility;

import android.annotation.SuppressLint;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.gptm.app.R;

/**
 * useful static functions
 */
public final class Functions {

    public static void  fragment_replacement(FragmentManager fragmentManager, @IdRes int containerViewId,
                                            Fragment fragment, boolean enableAnim) {

        popBackStack(fragmentManager);

        @SuppressLint("CommitTransaction") FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (enableAnim)
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, android.R.anim.slide_out_right);

        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.addToBackStack(null);
//        if (addToBackStack)
//             fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

    public static void popBackStack(FragmentManager fragmentManager)   {
        try {
            fragmentManager.popBackStack();
        } catch (Exception ignored) {}
    }
}