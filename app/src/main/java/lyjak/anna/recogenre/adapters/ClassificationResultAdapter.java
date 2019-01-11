package lyjak.anna.recogenre.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

import lyjak.anna.recogenre.R;
import lyjak.anna.recogenre.model.ClassificationResult;

public class ClassificationResultAdapter extends ArrayAdapter<ClassificationResult> {

    private List<ClassificationResult> dataSet;
    private Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtGenre;
        TextView txtPred;
    }

    public ClassificationResultAdapter(@NonNull Context context, List<ClassificationResult> dataSet) {
        super(context, R.layout.result_item, dataSet);
        this.dataSet = dataSet;
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ClassificationResult dataModel = getItem(position);
        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.result_item, parent, false);
            viewHolder.txtGenre = convertView.findViewById(R.id.textViewGenre);
            viewHolder.txtPred = convertView.findViewById(R.id.textViewPred);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        int lastPosition = position;

        viewHolder.txtGenre.setText(Objects.requireNonNull(dataModel).getGenre());
        viewHolder.txtPred.setText(String.format("%s%%", dataModel.getPred()));
        return convertView;
    }

}
