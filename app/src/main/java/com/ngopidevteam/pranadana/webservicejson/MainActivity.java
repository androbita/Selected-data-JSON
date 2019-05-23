package com.ngopidevteam.pranadana.webservicejson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView hasilID, hasilNama, hasilAsalDaerah, hasilKamar;
    private RequestQueue mQueue;
    EditText indexJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQueue = Volley.newRequestQueue(this);
//        textJson = findViewById(R.id.text_json);
        Button btnJson = findViewById(R.id.btn_json);

        btnJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uraiJson();
            }
        });
    }

    private void uraiJson() {
        String url = "http://petik.ngopidevteam.com/show.json";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
//                for (int i = 0; i < response.length(); i++){

                hasilID = findViewById(R.id.hasil_id);
                hasilNama = findViewById(R.id.hasil_nama);
                hasilAsalDaerah = findViewById(R.id.hasil_asal_daerah);
                hasilKamar = findViewById(R.id.hasil_kamar);
                indexJson = findViewById(R.id.index_json);

                int idx = Integer.parseInt(indexJson.getText().toString());
                    try {
                        JSONObject mahasantri = response.getJSONObject(idx - 1);

                        String id = mahasantri.getString("id");
                        String nama = mahasantri.getString("nama");
                        String asalDaerah = mahasantri.getString("asal_daerah");
                        String kamar = mahasantri.getString("kamar");

//                        textJson.setText(id + ", " + tanggal + ", " + jamMulai + ", " + jamSelesai + ","
//                                + jamSelesai+", "+jenisKualitas+"\n\n");
                        hasilID.setText(id);
                        hasilNama.setText(nama);
                        hasilAsalDaerah.setText(asalDaerah);
                        hasilKamar.setText(kamar);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
//                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(MainActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        mQueue.add(request);
    }
}
