package com.omidrayaneh.omid.kharidchi.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.omidrayaneh.omid.kharidchi.Interfacers.userAddress;
import com.omidrayaneh.omid.kharidchi.Interfacers.userState;
import com.omidrayaneh.omid.kharidchi.Interfacers.userTown;
import com.omidrayaneh.omid.kharidchi.helper.PrefManager;
import com.omidrayaneh.omid.kharidchi.R;
import org.json.JSONArray;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddressActivity extends AppCompatActivity {
    String[] state;
    String[] town;
    Spinner spinner_state;
    Spinner spinner_town;

    EditText txtFullAddress, txtphone;
    Button btn_save_address;
    PrefManager pref;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        spinner_state = findViewById(R.id.stateSpinner);
        spinner_town = findViewById(R.id.townSpinner);
        txtphone = findViewById(R.id.txtAddressPhone);
        txtFullAddress = findViewById(R.id.txtFullAddress);
        btn_save_address = findViewById(R.id.btn_save_address);
        pref = new PrefManager(this);

        progressBar = findViewById(R.id.progressBar);

        GetStateNameRetrofit();
        spinner_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String state = adapterView.getItemAtPosition(i).toString();
                town = null;
                progressBar.setVisibility(View.VISIBLE);
                GetTownNameRetrofit(state);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "درحال حاضر ارسال برای استان های دیگر مقدور نمی باشد.", Toast.LENGTH_LONG).show();
            }
        });


        btn_save_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtFullAddress.length() == 0 && txtphone.length() == 0) {
                    Toast.makeText(getApplicationContext(), R.string.fill_value, Toast.LENGTH_LONG).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                String town = spinner_town.getSelectedItem().toString();
                String addresss = txtFullAddress.getText().toString();
                String phone = txtphone.getText().toString();
                String mobile = pref.getMobileNumber();
                SendAddressDetailsRetrofit(town, addresss, phone, mobile);
            }
        });

    }

    public void GetStateNameRetrofit() {


        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://kharidchi.ir/").addConverterFactory(GsonConverterFactory.create()).build();
        userState registerInterface = retrofit.create(userState.class);
        Call call = registerInterface.getState();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                String stringRequest = new Gson().toJson(response.body());

                try {
                    JSONArray array = new JSONArray(stringRequest);
                    state = new String[array.length()];

                    for (int n = 0; n < array.length(); n++) {
                        JSONObject obj = array.getJSONObject(n);
                        state[n] = obj.getString("state_name");
                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
                ArrayAdapter bb = new ArrayAdapter(getApplicationContext(), R.layout.spinner_item, state);
                bb.setDropDownViewResource(R.layout.spinner_item);
                spinner_state.setAdapter(bb);
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    public void GetTownNameRetrofit(String state) {

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://kharidchi.ir/").addConverterFactory(GsonConverterFactory.create(gson)).build();
        userTown registerInterface = retrofit.create(userTown.class);
        Call call = registerInterface.getTown(state);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                String stringRequest = new Gson().toJson(response.body());

                try {

                    JSONArray array = new JSONArray(stringRequest);
                    town = new String[array.length()];

                    for (int n = 0; n < array.length(); n++) {
                        JSONObject obj = array.getJSONObject(n);
                        town[n] = obj.getString("town_name");
                    }

                } catch (Exception e) {
                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
                ArrayAdapter aa = new ArrayAdapter(getApplicationContext(), R.layout.spinner_item, town);
                aa.setDropDownViewResource(R.layout.spinner_item);
                spinner_town.setAdapter(aa);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    public void SendAddressDetailsRetrofit(String town, String address, String phone, String mobile) {

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://kharidchi.ir/").addConverterFactory(GsonConverterFactory.create(gson)).build();
        userAddress registerInterface = retrofit.create(userAddress.class);
        Call call = registerInterface.sendaddress(mobile, town, address, phone);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                String stringRequest = new Gson().toJson(response.body());
                Toast.makeText(getApplicationContext(), R.string.save_address, Toast.LENGTH_LONG).show();
                finish();
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);

            }
        });

    }

}
