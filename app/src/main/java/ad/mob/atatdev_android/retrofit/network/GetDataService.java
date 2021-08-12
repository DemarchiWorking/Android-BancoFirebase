package ad.mob.atatdev_android.retrofit.network;


import java.util.List;

import ad.mob.atatdev_android.retrofit.model.RetroDados;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {
    @GET("dadosAtividades.php")
    Call<List<RetroDados>> getAllPhotos();
}
