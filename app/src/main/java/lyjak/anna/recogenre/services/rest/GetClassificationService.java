package lyjak.anna.recogenre.services.rest;

import java.util.List;

import lyjak.anna.recogenre.model.retrofit.ClassificationResult;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetClassificationService {

    @GET("/predict")
    Call<List<ClassificationResult>> getClassificationResult();
}
