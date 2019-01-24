package lyjak.anna.recogenre.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lyjak.anna.recogenre.R;
import lyjak.anna.recogenre.view.adapters.ClassificationResultAdapter;
import lyjak.anna.recogenre.model.retrofit.ClassificationResult;

public class ResultActivity extends AppCompatActivity {

    public static List<ClassificationResult> LIST_OF_RESULTS = new ArrayList<>();
    private String genreName;
    private TextView resultInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultInfo = findViewById(R.id.textViewResult);
        ListView resultList = findViewById(R.id.result_list_view);

        if (LIST_OF_RESULTS.isEmpty()) {
            setGenreName("Pop");
        } else {
            ClassificationResult result = LIST_OF_RESULTS.get(0);
            setGenreName(result.getGenre() + " (w " + result.getPred() +"%)");
            ArrayAdapter<ClassificationResult> adapter =
                    new ClassificationResultAdapter(this, LIST_OF_RESULTS);
            resultList.setAdapter(adapter);
        }
    }

    private void setGenreName(String genre){
        genreName = genre;
        resultInfo.setText(genreName);
    }
}
