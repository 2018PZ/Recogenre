package lyjak.anna.recogenre;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private String genreName;
    private TextView resultInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultInfo = findViewById(R.id.textViewResult);
        setGenreName("Pop");
    }

    private void setGenreName(String genre){
        genreName = genre;
        resultInfo.setText(genreName);
    }
}
