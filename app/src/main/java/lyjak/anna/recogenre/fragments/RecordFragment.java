package lyjak.anna.recogenre.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.skyfishjy.library.RippleBackground;

import java.io.IOException;

import lyjak.anna.recogenre.R;
import lyjak.anna.recogenre.recording.RecordingController;

public class RecordFragment extends Fragment {

    private RippleBackground rippleBackground;
    private boolean animationOn = false;
    private RecordingController recordingController;

    public RecordFragment() {
        // Required empty public constructor
    }

    public static RecordFragment newInstance() {
        RecordFragment fragment = new RecordFragment();
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        rippleBackground = view.findViewById(R.id.content);
        ImageView imageView = view.findViewById(R.id.centerImage);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!animationOn) {
                    animationOn = true;
                    rippleBackground.startRippleAnimation();
                    String path = String.valueOf(System.currentTimeMillis());
                    recordingController = new RecordingController(path);
                    try {
                        recordingController.startRecording(getContext(), getActivity());
                    } catch (IOException e) {
                        animationOn = false;
                        rippleBackground.stopRippleAnimation();
                        e.printStackTrace();
                    }
                } else {
                    animationOn = false;
                    rippleBackground.stopRippleAnimation();
                    if(recordingController != null && recordingController.isRecording()){
                        try {
                            recordingController.stop();
//                            recordingController.play(getView());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        return view;
    }

}
