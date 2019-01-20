package lyjak.anna.recogenre.model.room.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

@Entity(tableName = "songs")
public class Song {

    @PrimaryKey
    @NonNull
    public String id;

    @ColumnInfo(name = "file_name")
    private String fileName;

    @ColumnInfo(name = "path")
    private String path;

    @ColumnInfo(name = "predictedGenre")
    private String predictedGenre;

    @ColumnInfo(name = "blues")
    private String blues;

    @ColumnInfo(name = "classical")
    private String classical;

    @ColumnInfo(name = "country")
    private String country;

    @ColumnInfo(name = "disco")
    private String disco;

    @ColumnInfo(name = "hipHop")
    private String hipHop;

    @ColumnInfo(name = "jazz")
    private String jazz;

    @ColumnInfo(name = "metal")
    private String metal;

    @ColumnInfo(name = "pop")
    private String pop;

    @ColumnInfo(name = "reggae")
    private String reggae;

    @ColumnInfo(name = "rock")
    private String rock;

    public Song() {
        this.id = UUID.randomUUID().toString();
    }

    public static Song newInstance() {
        return new Song();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPredictedGenre() {
        return predictedGenre;
    }

    public void setPredictedGenre(String predictedGenre) {
        this.predictedGenre = predictedGenre;
    }

    public String getBlues() {
        return blues;
    }

    public void setBlues(String blues) {
        this.blues = blues;
    }

    public String getClassical() {
        return classical;
    }

    public void setClassical(String classical) {
        this.classical = classical;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDisco() {
        return disco;
    }

    public void setDisco(String disco) {
        this.disco = disco;
    }

    public String getHipHop() {
        return hipHop;
    }

    public void setHipHop(String hipHop) {
        this.hipHop = hipHop;
    }

    public String getJazz() {
        return jazz;
    }

    public void setJazz(String jazz) {
        this.jazz = jazz;
    }

    public String getMetal() {
        return metal;
    }

    public void setMetal(String metal) {
        this.metal = metal;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getReggae() {
        return reggae;
    }

    public void setReggae(String reggae) {
        this.reggae = reggae;
    }

    public String getRock() {
        return rock;
    }

    public void setRock(String rock) {
        this.rock = rock;
    }
}
