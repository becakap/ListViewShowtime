package lv.iqbal.com.listviewshowtime;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ButtonGetData extends AppCompatActivity {


    public static final String MyPREFERENCES = "PilihanTiket" ;
    SharedPreferences sharedpreferences;

    public static final String ROOT_URL = "http://api.hypemedan.id/";


    Button btnGetTiket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_get_data);

        btnGetTiket = (Button)findViewById(R.id.btnGetTiket);

        btnGetTiket.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

                kirimIdEvent();

            }
        });







    }



    private void kirimIdEvent() {

        String id_event="7";
        final String keluaran = "";
        //Here we will handle the http request to insert user to mysql db
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        GetShowTimeTanggalAPI api = adapter.create(GetShowTimeTanggalAPI.class);

        //Defining the method insertuser of our interface
        api.kirimIdEventAPI(

                id_event,

                //Creating an anonymous callback
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {
                        //On success we will read the server's output using bufferedreader
                        //Creating a bufferedreader object
                        BufferedReader reader = null;

                        //An string to store output from the server
                        String output = "";

                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            //Reading the output in the string
                            output = reader.readLine();


                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        setData(output);



                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(ButtonGetData.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void setData(String output) {

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String tanggal_tiket_keluar ="";




        final JSONObject obj;
        try {
            obj = new JSONObject(output);
            final JSONArray event = obj.getJSONArray("event");
            final int n = event.length();
            for (int i = 0; i < n; ++i) {
                final JSONObject person = event.getJSONObject(i);
                String tanggal_tiket = person.getString("tanggal");
                tanggal_tiket_keluar  += tanggal_tiket +"|";

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("event_sekarang", "Java Jazz Prambanan 2016");
        editor.putString("tanggal_tiket_keluar", tanggal_tiket_keluar);
        editor.commit();


        System.out.println(tanggal_tiket_keluar);

        Intent intent = new Intent(ButtonGetData.this, BookingPilihTanggal.class);
        startActivity(intent);



    }
}
