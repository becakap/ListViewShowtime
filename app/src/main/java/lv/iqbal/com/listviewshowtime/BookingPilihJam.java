package lv.iqbal.com.listviewshowtime;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import lv.iqbal.com.listviewshowtime.adapter.JamAdapter;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class BookingPilihJam extends Activity {
    ListView listView ;
    ArrayList pid  = new ArrayList();
    ArrayList jam  = new ArrayList();
    ArrayList nama = new ArrayList();
    ArrayList gbr  = new ArrayList();
    protected ListAdapter adapter;
    SimpleAdapter Adapter;
    HashMap<String, String> map;
    ArrayList<HashMap<String, String>> mylist;


    public static final String MyPREFERENCES = "PilihanTiket" ;
    SharedPreferences sharedpreferences;

    public static final String ROOT_URL = "http://api.hypemedan.id/";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jam_event);
        setTheme(android.R.style.Theme_Holo);

        ImageView btnBack = (ImageView) findViewById(R.id.btnBack);

        SharedPreferences shared = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        String sNamaEventTiket = (shared.getString("nama_event_tiket_keluar", ""));
        String pNamaEventTiket = sNamaEventTiket.replaceFirst(".$","");

        String sJamEventTiket = (shared.getString("jam_event_tiket_keluar", ""));
        String pJamEventTiket = sJamEventTiket.replaceFirst(".$","");

        System.out.println(pNamaEventTiket);
        System.out.println(pJamEventTiket);


        // Massukkan nama tiket dan jam tiket ke ArrayList Adaapter
        StringTokenizer st1 = new StringTokenizer(pNamaEventTiket, "|");
        StringTokenizer st2 = new StringTokenizer(pJamEventTiket, "|");
        int k = 0;
        while (st1.hasMoreElements()) {
            String namaTiket  = String.valueOf(st1.nextElement());
            String jamTiket  = String.valueOf(st2.nextElement());
            pid.add(String.valueOf(k));
            nama.add(namaTiket);
            jam.add(jamTiket);
            gbr.add(Integer.toString(R.drawable.bg1));
            k++;
        }

        String[] apid     = (String[])  pid.toArray(new String[pid.size()]);
        String[] anama    = (String[])  nama.toArray(new String[nama.size()]);
        String[] ajam     = (String[])  jam.toArray(new String[jam.size()]);
        String[] agbr     = (String[])  gbr.toArray(new String[gbr.size()]);

        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

                Intent intent = new Intent(BookingPilihJam.this, BookingPilihTanggal.class);

            }
        });

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
        listView = (ListView) findViewById(R.id.lv_jam_item);
        JamAdapter adapter = new JamAdapter(this, apid, anama, agbr, ajam);
        // Assign adapter to ListView
       listView.setAdapter(adapter);

        // ListView Item Click Listener
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String  nama_tiket_event = String.valueOf(nama.get(position));
                String  itemPosition    = String.valueOf(position);
                String  itemValue       = (String) listView.getItemAtPosition(position);

                Toast.makeText(getApplicationContext(),
                       "Position :"+itemPosition+"  itemValue : " +itemValue+ "Nama Tiket" + nama_tiket_event, Toast.LENGTH_LONG)
                      .show();

               kirimIdEvent();


            }

        });
    }

    private void kirimIdEvent() {
        String id_event="7";
        final String keluaran = "";
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        GetShowTimeTicketAPI api = adapter.create(GetShowTimeTicketAPI.class);

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
                        Toast.makeText(BookingPilihJam.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void setData(String output) {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String nama_tiket_keluar ="";
        String id_tiket_keluar = "";
        String detail_tiket_keluar = "";
        String harga_tiket_keluar = "";




        final JSONObject obj;
        try {
            obj = new JSONObject(output);
            final JSONArray event = obj.getJSONArray("event");
            final int n = event.length();
            for (int i = 0; i < n; ++i) {
                final JSONObject person = event.getJSONObject(i);
                String id_tiket         = String.valueOf(person.getInt("pid"));
                String nama_tiket       = person.getString("nama");
                String detail_tiket     = person.getString("detail");
                String harga_tiket      = person.getString("harga");
                nama_tiket_keluar       += nama_tiket +"|";
                id_tiket_keluar         += id_tiket + "|";
                detail_tiket_keluar     += detail_tiket + "|";
                harga_tiket_keluar      += harga_tiket + "|";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("NamaTiket",   nama_tiket_keluar);
        editor.putString("IdTiket",     id_tiket_keluar);
        editor.putString("DetailTiket", detail_tiket_keluar);
        editor.putString("HargaTiket",  harga_tiket_keluar);
        editor.commit();

        Intent intent = new Intent(BookingPilihJam.this, BookingPilihTiket.class);
        startActivity(intent);
    }
}
