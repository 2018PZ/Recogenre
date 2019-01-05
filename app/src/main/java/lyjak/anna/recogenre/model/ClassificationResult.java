package lyjak.anna.recogenre.model;

import com.google.gson.annotations.SerializedName;

public class ClassificationResult {

    @SerializedName("genre")
    private String genre;
    @SerializedName("pred")
    private String pred;

    public ClassificationResult(String genre, String pred) {
        this.genre = genre;
        this.pred = pred;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPred() {
        return pred;
    }

    public void setPred(String pred) {
        this.pred = pred;
    }
}
