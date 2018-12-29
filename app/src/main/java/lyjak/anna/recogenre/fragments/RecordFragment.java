package lyjak.anna.recogenre.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.skyfishjy.library.RippleBackground;

import java.io.IOException;

import lyjak.anna.recogenre.R;
import lyjak.anna.recogenre.service.ConnectionController;
import lyjak.anna.recogenre.recording.RecordingController;

public class RecordFragment extends Fragment {

    private RippleBackground rippleBackground;
    private boolean animationOn = false;
    private RecordingController recordingController;

    public RecordFragment() {
        // Required empty public constructor
    }

    public static RecordFragment newInstance() {
        return new RecordFragment();
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
                        recordingController.stop();
                        String filePath = recordingController.getPath();
                        String fileName = recordingController.getFileName();
                        Log.i("TAG", "File created");
                        Log.i("TAG", "Start sending file "+ fileName);
                        sendFileToServer(filePath, fileName);
                        //TODO
//                            Log.i("TAG", "Open new activity");
//                            startNewActivity(ResultActivity.class);

//                            recordingController.play(getView());
                    }
                }
            }
        });
        return view;
    }

    private void sendFileToServer(final String filePath, final String fileName) {
        @SuppressLint("StaticFieldLeak") AsyncTask<Void, Void, Void> asyncTask =
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        ConnectionController controller = new ConnectionController();
                        controller.executeRemoteConnection(filePath, fileName);
                        return null;
                    }
                };
        asyncTask.execute();
    }

    private void startNewActivity(Class<?> activityClass){
        Intent intent = new Intent(getActivity(), activityClass);
        //TODO put result info to display
//        intent.putExtra("RESULT", "Rock");
        startActivity(intent);
    }

}
