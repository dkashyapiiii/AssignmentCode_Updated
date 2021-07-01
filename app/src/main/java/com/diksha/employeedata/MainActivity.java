package com.diksha.employeedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.diksha.employeedata.ModelClass.EmployeeModel;
import com.diksha.employeedata.ModelClass.Maindata;
import com.diksha.employeedata.ModelClass.RoomModel;
import com.diksha.employeedata.roomdb.AppDatabase2;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

    private static final int FILE_SELECT_CODE = 0;
    private RecyclerView recyclerView;
    LinearLayout linearLayout;
    SwipeRefreshLayout mSwipeRefreshLayout;
    String url="https://bbf2a516-7989-4779-a5bf-ecb2777960c4.mock.pstmn.io/v1/dev/t1/employee/";
    private List<Object> recyclerViewItems2 = new ArrayList<>();
    EmployeeAdapter2 employeeAdapter;
    Button jsonb;
    AppDatabase2 db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeRefreshLayout = findViewById(R.id.pullToRefresh);
        linearLayout = findViewById(R.id.nointernet);
        jsonb = findViewById(R.id.jsonfile);
        recyclerView=findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db = AppDatabase2.getDbInstance(getApplicationContext());
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(mSwipeRefreshLayout.isRefreshing()) {
                            if(isNetworkConnected()){
                                getrespondse();
                                mSwipeRefreshLayout.setRefreshing(true);
                                interntlayout();

                            }else{
                             //   nointerntlayout();
                                getroomdata();
                                Toast.makeText(getApplicationContext(), "Please check your connection", LENGTH_SHORT).show();
                                mSwipeRefreshLayout.setRefreshing(false);

                            }
                        }
                    }
                }, 1000);
            }
        });

        jsonb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });

        if(isNetworkConnected()){
            interntlayout();
            getrespondse();

        }else{
//            nointerntlayout();
            getroomdata();
            Toast.makeText(this, "Please check your connection", LENGTH_SHORT).show();
        }
    }

    private void getroomdata() {
        List<RoomModel> userList =db.userDao().getAllUsers();
        employeeAdapter = new EmployeeAdapter2(MainActivity.this, userList);
        recyclerView.setAdapter(employeeAdapter);
        employeeAdapter.notifyDataSetChanged();
    }

    private void interntlayout() {
        linearLayout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }
    private void nointerntlayout() {
        linearLayout.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    private void getrespondse() {
        List<RoomModel> userList =db.userDao().getAllUsers();
        for(int i=0;i<userList.size();i++){
            db.userDao().delete(userList.get(i));

        }
        mSwipeRefreshLayout.setRefreshing(true);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONHolder jsonHolder=retrofit.create(JSONHolder.class);
        Call<EmployeeModel> call= jsonHolder.getEmployeeModel();
        call.enqueue(new Callback<EmployeeModel>() {
            @Override
            public void onResponse(Call<EmployeeModel> call, Response<EmployeeModel> response) {
                if(response.isSuccessful()) {

                    List<Maindata> employeeModels = response.body().getBanner1();
                    List<EmployeeModel> employeeModeldata = Collections.singletonList(response.body());
                    EmployeeAdapter2 employeeAdapter = new EmployeeAdapter2(MainActivity.this, employeeModels,"live");
                    recyclerView.setAdapter(employeeAdapter);
                    savetoparentmodel(employeeModels);

                }
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<EmployeeModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), LENGTH_SHORT).show();
            }


        });

    }
    private void savetoparentmodel(List<Maindata> employeeModeldata) {
        List<RoomModel> data = new ArrayList<>();
        for(int i=0;i<employeeModeldata.size();i++){
            RoomModel ld=new RoomModel(employeeModeldata.get(i).getFirstname()
                    ,employeeModeldata.get(i).getLastname(),
                    employeeModeldata.get(i).getAge(),
                    employeeModeldata.get(i).getGender(),
                    employeeModeldata.get(i).getPicture(),
                    employeeModeldata.get(i).getJobholder().getExp(),
                    employeeModeldata.get(i).getJobholder().getOrganization(),
                    employeeModeldata.get(i).getJobholder().getRole(),
                    employeeModeldata.get(i).getEducation().getDegree(),
                    employeeModeldata.get(i).getEducation().getInstitution()
                  );
            data.add(ld);
        }
        for(int i=0;i<data.size();i++){
            db.userDao().insertUser(data.get(i));
        }

    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }
    public String getRealPathFromURI(MainActivity context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                     String path=getRealPathFromURI(this,uri);
                     String filename=path.substring(path.lastIndexOf("/")+1);
                     ReadFile(path);
                     Toast.makeText(this, filename, LENGTH_SHORT).show();

                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void ReadFile(String path) {
        mSwipeRefreshLayout.setRefreshing(true);
        Gson gson = new Gson();
        String text = "";
        try {
            File yourFile = new File(path);
            InputStream inputStream = new FileInputStream(yourFile);
            StringBuilder stringBuilder = new StringBuilder();

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                while ((receiveString = bufferedReader.readLine()) != null){
                    stringBuilder.append(receiveString);
                }
                inputStream.close();
                text = stringBuilder.toString();
                Parsetest(text);
                Log.d("TAG",text);
            }
        } catch (FileNotFoundException e) {
            Log.e("file",e.getMessage());

        } catch (IOException e) {
            Log.e("file",e.getMessage());
        }
    }
    private void Parsetest(String text) {
        EmployeeModel  employeeModel = new Gson().fromJson(text,EmployeeModel.class);
        employeeAdapter = new EmployeeAdapter2(MainActivity.this, employeeModel.getBanner1(),"live");
        recyclerView.setAdapter(employeeAdapter);
        employeeAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);

    }
}