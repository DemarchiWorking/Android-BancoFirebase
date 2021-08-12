package ad.mob.atatdev_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import ad.mob.atatdev_android.retrofit.adapter.CustomAdapter;
import ad.mob.atatdev_android.retrofit.model.RetroDados;
import ad.mob.atatdev_android.retrofit.network.GetDataService;
import ad.mob.atatdev_android.retrofit.network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;

public class InicioActivity extends AppCompatActivity {

    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDoalog = new ProgressDialog(InicioActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        Call<List<RetroDados>> call = service.getAllPhotos();

        call.enqueue(new Callback<List<RetroDados>>() {

            @Override
            public void onResponse(Call<List<RetroDados>> call, Response<List<RetroDados>> response) {
                progressDoalog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<RetroDados>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(InicioActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }


        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<RetroDados> photoList) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new CustomAdapter(getApplicationContext(),photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(InicioActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}
