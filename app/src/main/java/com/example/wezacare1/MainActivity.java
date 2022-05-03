package com.example.wezacare1;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.wezacare1.Cruiseshipadapter.Cruiseshipviewholder;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okio.Timeout;

public class MainActivity extends AppCompatActivity implements Cruiseshipadapter.OnItemClickListiner{
    RecyclerView recyclerview;
    List<CruiseShipsModel> cruiseShipsModelList;
    Cruiseshipadapter cruiseShipAdapter;
    private BottomSheetBehavior mbottomsheet;
    TextView t1,t2,t3,t4,t5,t6,t7;
    private View bottomsheet;
    ImageButton imb1;
    EditText ed1;


    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = findViewById(R.id.recylerviewforships);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        cruiseShipsModelList = new ArrayList<>();
        cruiseShipAdapter = new Cruiseshipadapter(cruiseShipsModelList, MainActivity.this);
        recyclerview.setAdapter(cruiseShipAdapter);
        imb1=findViewById(R.id.searchButton);
        ed1=findViewById(R.id.searchshipbyid);

        bottomsheet=findViewById(R.id.frameLayout);
        t1=findViewById(R.id.shipid);
//        t2=findViewById(R.id.shipmodel);
//        t3=findViewById(R.id.shiptype);
//        t4=findViewById(R.id.shipyearbuilt);
//        t5=findViewById(R.id.shiphomeport);
//        t6=findViewById(R.id.shipactive);
//        t7=findViewById(R.id.shipstatus);
        String SHIPS_URL = "https://api.spacexdata.com/v3/ships";
        String SHIP_URL = "https://api.spacexdata.com/v3/ships/";
        mbottomsheet=BottomSheetBehavior.from(bottomsheet);


        imb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchedship=ed1.getText().toString();
                JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, SHIP_URL +searchedship, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                            CruiseShipsModel newinstance1= new CruiseShipsModel();

                        try {
                            newinstance1.setShip_id(response.getString("ship_id"));
                            newinstance1.setShip_name(response.getString("ship_name"));
                            newinstance1.setUrl(response.getString("url"));
                            newinstance1.setShip_model(response.getString("ship_model"));
                            newinstance1.setShip_type(response.getString("ship_type"));
                            newinstance1.setActive(response.getString("active"));
                            newinstance1.setYear_built(response.getString("year_built"));
                            newinstance1.setHome_port(response.getString("home_port"));
                            newinstance1.setStatus(response.getString("status"));
                            newinstance1.setImage(response.getString("image"));

                            cruiseShipsModelList = new ArrayList<>();
                            cruiseShipsModelList.add(newinstance1);
                            cruiseShipAdapter = new Cruiseshipadapter(cruiseShipsModelList, MainActivity.this);
                            recyclerview.setAdapter(cruiseShipAdapter);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
                cruiseSingleton.getInstance(MainActivity.this).addToRequestQueue(jsonObjectRequest);
            }

        });



        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, SHIPS_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        CruiseShipsModel newinstance= new CruiseShipsModel();

                        JSONObject resultone = response.getJSONObject(i);
                        try {


                            newinstance.setShip_id(resultone.getString("ship_id"));
                            newinstance.setShip_name(resultone.getString("ship_name"));
                            newinstance.setUrl(resultone.getString("url"));
                            newinstance.setShip_model(resultone.getString("ship_model"));
                            newinstance.setShip_type(resultone.getString("ship_type"));
                            newinstance.setActive(resultone.getString("active"));
                            newinstance.setYear_built(resultone.getString("year_built"));
                            newinstance.setHome_port(resultone.getString("home_port"));
                            newinstance.setStatus(resultone.getString("status"));
                            newinstance.setImage(resultone.getString("image"));
                            cruiseShipsModelList.add(newinstance);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }cruiseShipAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

            }
        });
        cruiseSingleton.getInstance(this).addToRequestQueue(request);
    }


    @Override
    public void onItemClick(int pos) {
        CruiseShipsModel current= cruiseShipsModelList.get(pos);
        String shipid= current.ship_id;
        String model=current.ship_model;
        String shiptype=current.ship_type;
        String shipyearbuild=current.year_built;
        String shiphomeport=current.home_port;
        String shipidactive=current.active;
        String shipstatus=current.status;

       int peekHeight=55;
        mbottomsheet.setPeekHeight(peekHeight);
        mbottomsheet.setHideable(true);
        mbottomsheet.setState(BottomSheetBehavior.STATE_EXPANDED);
        t1.setText(shipid);

//        mbottomsheet.setState(Bo);

     }
}

