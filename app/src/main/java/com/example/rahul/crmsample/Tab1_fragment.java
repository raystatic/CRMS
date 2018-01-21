package com.example.rahul.crmsample;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by rahul on 20/1/18.
 */

public class Tab1_fragment extends android.support.v4.app.Fragment {

    TextView textView;

    public Tab1_fragment()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tab1_fragment,container,false);

      //  textView=(TextView)view.findViewById(R.id.tv);

        return view;
    }
}
