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
import android.widget.Toast;

import com.skyfishjy.library.RippleBackground;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import lyjak.anna.recogenre.R;
import lyjak.anna.recogenre.activities.ResultActivity;
import lyjak.anna.recogenre.model.ClassificationResult;
import lyjak.anna.recogenre.recording.RecordingController;
import lyjak.anna.recogenre.service.GetClassificationService;
import lyjak.anna.recogenre.service.RetrofitClientInstance;
import lyjak.anna.recogenre.service.SSHConnectionService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecordFragment extends Fragment {

    private static final String TAG = RecordFragment.class.getName();

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
                        classifySong(filePath, fileName);
//                            recordingController.play(getView());
                    }
                }
            }
        });
        return view;
    }

    private void classifySong(final String filePath, final String fileName) {
        Log.i(TAG, "Start classify Song");
        @SuppressLint("StaticFieldLeak") AsyncTask<Void, Void, Void> asyncTask =
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        SSHConnectionService controller = new SSHConnectionService();
                        controller.executeRemoteConnection(filePath, fileName);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        requestResult();
                    }
                };
        asyncTask.execute();
    }

    private void requestResult() {
        Log.i(TAG, "Request Song result");
        /*Create handle for the RetrofitInstance interface*/
        GetClassificationService service = RetrofitClientInstance.getRetrofitInstance().
                create(GetClassificationService.class);
        Call<List<ClassificationResult>> call = service.getClassificationResult();
        call.enqueue(new Callback<List<ClassificationResult>>() {
            @Override
            public void onResponse(@NonNull Call<List<ClassificationResult>> call,
                                   @NonNull Response<List<ClassificationResult>> response) {
//                progressDoalog.dismiss();
                List<ClassificationResult> body = response.body();
                Log.i("ConnectionController",
                        "Response size: " + Objects.requireNonNull(body).size());
                ResultActivity.LIST_OF_RESULTS = body;
                startNewActivity(ResultActivity.class);
            }

            @Override
            public void onFailure(@NonNull Call<List<ClassificationResult>> call,
                                  @NonNull Throwable t) {
//                progressDoalog.dismiss();
                Toast.makeText(getContext(), "Something went wrong...Please try later!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Starts new activity
     * @param activityClass - activity to start intent
     */
    @SuppressWarnings("SameParameterValue")
    private void startNewActivity(Class<?> activityClass){
        Intent intent = new Intent(getActivity(), activityClass);
        startActivity(intent);
    }

}
