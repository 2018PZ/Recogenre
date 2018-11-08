package lyjak.anna.recogenre.recording;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.IOException;

import lyjak.anna.recogenre.MainActivity;

public class RecordingController {

    private final static String TAG = "TAG";

    private final MediaRecorder audioRecorder = new MediaRecorder();
    private final String path;
    private Boolean isRecording;

    /**
     * Creates a new audio recording at the given path (relative to root of SD card).
     */
    public RecordingController(String path) {
        this.path = sanitizePath(path);
        Log.e(TAG, this.path);
        this.isRecording = false;
    }

    private String sanitizePath(String path) {
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        if (!path.contains(".")) {
            path += ".3gp";
        }
        String directory = Environment.getExternalStorageDirectory().getAbsolutePath() + "/RecoGenre";
        return directory + path;
    }

    public void startRecording(Context context, Activity activity) throws IOException {
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.RECORD_AUDIO)
                == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                start();
            } else {
                Log.i(TAG, "Request Permission");
                requestWritePermission(activity);
            }
        } else {
            Log.i(TAG, "Request Permission");
            requestMicrophonePermission(activity);
        }
    }

    private void requestWritePermission(final Activity activity) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(activity, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, MainActivity.WRITE_EXTERNAL_STORAGE);
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, MainActivity.REQUEST_RECORD_AUDIO);
        }
    }

    private void requestMicrophonePermission(final Activity activity) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, android.Manifest.permission.RECORD_AUDIO)) {
            ActivityCompat.requestPermissions(activity, new String[]{
                    android.Manifest.permission.RECORD_AUDIO}, MainActivity.REQUEST_RECORD_AUDIO);
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{
                    android.Manifest.permission.RECORD_AUDIO}, MainActivity.REQUEST_RECORD_AUDIO);
        }
    }

    /**
     * Starts a new recording.
     */
    private void start() throws IOException {
        String state = android.os.Environment.getExternalStorageState();
        if(!state.equals(android.os.Environment.MEDIA_MOUNTED))  {
            throw new IOException("SD Card is not mounted.  It is " + state + ".");
        }

        // make sure the directory we plan to store the recording in exists
        File directory = new File(path).getParentFile();
        Log.i(TAG, "Ścieżka: " + directory.getPath());
        if(!directory.exists()){
            directory.mkdirs();
        }
        if (!directory.exists() && !directory.mkdirs()) {
            throw new IOException("Path to file could not be created.");
        }

        audioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        audioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        audioRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        audioRecorder.setOutputFile(path);
        audioRecorder.prepare();
        audioRecorder.start();
        Log.e(TAG, "RECORD");
        isRecording = true;
    }

    /**
     * Stops a recording that has been previously started.
     */
    public void stop() throws IOException {
        audioRecorder.stop();
        audioRecorder.release();
        Log.e(TAG, "STOP RECORD");
        isRecording = false;
    }

    /**
     * Method needed for testing - plays record
     */
    public void play(View view) throws IllegalArgumentException, SecurityException, IllegalStateException, IOException {
        MediaPlayer m = new MediaPlayer();
        m.setDataSource(path);
        m.prepare();
        m.start();
        Log.i(TAG, "PLAYING");
    }

    public Boolean isRecording() {
        return isRecording;
    }
}