package com.example.jobapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    List<jobModel> ps;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView =findViewById(R.id.list);
        Retrofit.Builder retrofitBuilder =
                new Retrofit.Builder()
                        .baseUrl("http://192.168.2.13:4000/")
                        .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = retrofitBuilder.build();
        api ap=retrofit.create(api.class);
        Call<List<jobModel>> ad=ap.getitems();
        ad.enqueue(new Callback<List<jobModel>>() {
            @Override
            public void onResponse(Call<List<jobModel>> call, Response<List<jobModel>> response) {
                ps=response.body();
                listadapter li=new listadapter();
                listView.setAdapter(li);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<jobModel>> call, Throwable t) {

            }
        });
    }

    public class listadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return ps.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v=getLayoutInflater().from(MainActivity.this).inflate(R.layout.childjobs,viewGroup,false);
            TextView tv1=v.findViewById(R.id.company);
            TextView tv2=v.findViewById(R.id.designation);
            TextView tv3=v.findViewById(R.id.roles);

            tv1.setText(ps.get(i).company);
            tv2.setText(ps.get(i).designation);
            tv3.setText(ps.get(i).roles);

            return v;
        }
    }
}