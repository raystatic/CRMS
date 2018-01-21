package com.example.rahul.crmsample;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by rahul on 20/1/18.
 */

public class Tab2_fragment extends android.support.v4.app.Fragment {

    public Tab2_fragment()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tab2_fragment,container,false);

        return view;
    }
}
