package lyjak.anna.recogenre.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.skyfishjy.library.RippleBackground;

import lyjak.anna.recogenre.R;

public class RecordFragment extends Fragment {

    private RippleBackground rippleBackground;
    private boolean animationOn = false;

    public RecordFragment() {
        // Required empty public constructor
    }

    public static RecordFragment newInstance() {
        RecordFragment fragment = new RecordFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        rippleBackground=(RippleBackground)view.findViewById(R.id.content);
        ImageView imageView=(ImageView)view.findViewById(R.id.centerImage);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!animationOn) {
                    animationOn = true;
                    rippleBackground.startRippleAnimation();
                } else {
                    animationOn = false;
                    rippleBackground.stopRippleAnimation();
                }
            }
        });
        return view;
    }

}
