package com.gptm.app.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gptm.app.R;


/**
 * The first page of the tutorial is created from this class.
 */
public class ViewGolfFragment extends Fragment {

    public static ViewGolfFragment newInstance(int count) {
        ViewGolfFragment fragment = new ViewGolfFragment();
        fragment.count = count;

        return fragment;
    }

    private int count;
    private String[] images = {
            "https://www.fieldstonegolfclub.com/images/course-layout/5_ffee2447b152494b43d9816faaea83c8_s.jpg",
            "https://www.fieldstonegolfclub.com/images/course-layout/6_ada9a09acea936d776a6f55c82778c43_s.jpg",
            "https://www.fieldstonegolfclub.com/images/course-layout/7_9caa2793658f3cc387f216157300b1ce_s.jpg",
            "https://www.fieldstonegolfclub.com/images/course-layout/8_184b7cb84d7b456c96a0bdfbbeaa5f14_s.jpg",
            "https://www.fieldstonegolfclub.com/images/course-layout/9_d61d44254608dd06ccdd2ff02982d14d_s.jpg",
            "https://www.fieldstonegolfclub.com/images/course-layout/10_e31ace2a15a7c70645ad83df9ecd43b0_s.jpg",
            "https://www.fieldstonegolfclub.com/images/course-layout/11_c82cc4e14a1d2c8c8ffff9840d24b558_s.jpg"

    };

    private String[] texts = {
            "Overview",
            "Hole 1",
            "Hole 2",
            "Hole 3",
            "Hole 4",
            "Hole 5",
            "Hole 6"
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_golf, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView mGolfImageView = view.findViewById(R.id.golf_image_view);
        Glide.with(view)
                .load(images[count])
                .into(mGolfImageView);

        TextView infoTextView = view.findViewById(R.id.info_text_view);
        infoTextView.setText(texts[count]);
        infoTextView.setTextSize(18);
        infoTextView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
    }
}