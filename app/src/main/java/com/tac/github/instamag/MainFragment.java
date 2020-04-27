package com.tac.github.instamag;

import com.tac.github.instamag.R;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author vidit
 */
public class MainFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section-icon";
    private static final String ARG_SECTION_COLOR = "section-color";
    private static final String ARG_SECTION_NAME = "section-name";
    private static final String ARG_SECTION_DESCRIPTION = "section-desc";

    public static MainFragment newInstance(int color, int icon,String name,String desc) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, icon);
        args.putInt(ARG_SECTION_COLOR, color);
        args.putString(ARG_SECTION_NAME,name);
        args.putString(ARG_SECTION_DESCRIPTION,desc);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        rootView.setBackgroundColor(ContextCompat.getColor(getContext(), getArguments().getInt(ARG_SECTION_COLOR)));
        ImageView image = rootView.findViewById(R.id.iv_icon);
        TextView name=rootView.findViewById(R.id.txtname);
        TextView desc=rootView.findViewById(R.id.txtdes);
        image.setImageResource(getArguments().getInt(ARG_SECTION_NUMBER));
        name.setText((getArguments().getString(ARG_SECTION_NAME)));
        desc.setText((getArguments().getString(ARG_SECTION_DESCRIPTION)));

        if(getArguments().getString(ARG_SECTION_NAME).equals("Trail Blaze")){
            name.setTextSize(29f);
        }
        return rootView;
    }

}