package com.example.mobilprogramlama_vize;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilprogramlama_vize.NetworkUtils;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppCompatEditText edtQuery;
    private EditText secondEdT;
    private TextView tvResult, tvUrl;
    private Button btnUygula;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        edtQuery = findViewById(R.id.ac_main_ac_ed_first);
        secondEdT = findViewById(R.id.ac_main_ed_second);
        tvResult = findViewById(R.id.ac_main_tv_title);
        tvUrl = findViewById(R.id.ac_main_tv_url);
        btnUygula = findViewById(R.id.ac_main_btn_uygula);
        recyclerView = findViewById(R.id.ac_main_recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<String> input = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            input.add("Deneme " + i);
        }

        mAdapter = new MyAdapter(input);
        recyclerView.setAdapter(mAdapter);
        initButtonWorks();
    }

    private void initButtonWorks() {
        btnUygula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GithubSearchQuery();
            }
        });
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<String> values;

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imgLogo;
            TextView txtHeader;
            TextView txtFooter;
            View layout;

            ViewHolder(View v) {
                super(v);
                layout = v;
                imgLogo = v.findViewById(R.id.imgLogo);
                txtHeader = v.findViewById(R.id.firstLine);
                txtFooter = v.findViewById(R.id.secondLine);
            }
        }

        void addItem(int position,String item) {
            values.add(position,item);
            notifyItemInserted(position);
        }

        void removeItem(int position) {
            values.remove(position);
            notifyItemRemoved(position);
        }

        MyAdapter(List<String> myDataSet) {
            values = myDataSet;
        }



        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.recycler_layout,parent,false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder bagla,final int position) {
            final String name = values.get(position);
            bagla.txtHeader.setText(name);

            final String description = "Footer:" + name;

            bagla.txtFooter.setText(description);

            bagla.imgLogo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent toDetail = new Intent(getApplicationContext(),DetailActivity.class);
                    toDetail.putExtra("HEADER",name);
                    toDetail.putExtra("DESCRIPTION",description);
                    startActivity(toDetail);
                }
            });

            bagla.txtFooter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addItem(position,"NEW");
                }
            });

            bagla.txtHeader.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeItem(position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return values.size();
        }
    }

    @SuppressLint("SetTextI18n")
    private void GithubSearchQuery() {
        String githubQuery = edtQuery.getText().toString();
        URL githubSearchUrl = NetworkUtils.buildUrl(githubQuery);
        tvUrl.setText("URL: " + githubSearchUrl.toString());

        String githubSearchResult = null;

        try {
            githubSearchResult = NetworkUtils.getResponseFromHttpUrl(githubSearchUrl);
            tvResult.setText(githubSearchResult);
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Hata! \n\n" + e.toString(), Toast.LENGTH_LONG).show();
        }
    }

}

