package com.gptm.app.utility;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.gptm.app.R;
import com.gptm.app.controller.PlayersSql;
import com.gptm.app.model.Player;

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

    public static void enterPlayers(Player[] players, Context context)  {
        try {

            PlayersSql sql = new PlayersSql(context);
            sql.insertPlayers(players);

            sql.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Player[] getPlayers(Context context) {

        PlayersSql sql = new PlayersSql(context);

        try {
            return sql.getPlayers();
        } finally {
            sql.close();
        }
    }
}