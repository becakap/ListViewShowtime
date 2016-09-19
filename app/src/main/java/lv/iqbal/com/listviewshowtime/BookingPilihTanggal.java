package lv.iqbal.com.listviewshowtime;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

import lv.iqbal.com.listviewshowtime.adapter.TanggalAdapter;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class BookingPilihTanggal extends Activity {
    ListView listView ;
    ArrayList tgl = new ArrayList();
    ArrayList dis = new ArrayList();
    ArrayList gbr = new ArrayList();
    protected ListView lv;
    protected ListAdapter adapter;
    SimpleAdapter Adapter;
    HashMap<String, String> map;
    ArrayList<HashMap<String, String>> mylist;



    public static final String ROOT_URL = "http://api.hypemedan.id/";

    public static final String MyPREFERENCES = "PilihanTiket" ;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tanggal_event);
        setTheme(android.R.style.Theme_Holo);

        SharedPreferences shared = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String sTanggalTiket = (shared.getString("tanggal_tiket_keluar", ""));
        String pTanggalTiket = sTanggalTiket.replaceFirst(".$","");


        System.out.println(pTanggalTiket);


        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.lv_item_tanggal);

        // Defined Array values to show in ListView
        String pola = "dd MMMM yyyy";
        Locale lokal = null;

        StringTokenizer st1 = new StringTokenizer(pTanggalTiket, "|");
        int k = 0;
        while (st1.hasMoreElements()) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String tglTiket  = String.valueOf(st1.nextElement());
            tgl.add(tglTiket);

            try {
                Date parsed = format.parse(tglTiket);
                String displaytanggal = tampilkanTanggalDanWaktu(parsed , pola, lokal);
                System.out.println(displaytanggal);
                dis.add(displaytanggal);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            gbr.add(Integer.toString(R.drawable.bg1));

            k++;
        }

        String[] values  = (String[]) dis.toArray(new String[dis.size()]);
        String[] pid     = (String[]) tgl.toArray(new String[tgl.size()]);
        String[] Gbr     = (String[]) gbr.toArray(new String[gbr.size()]);

        mylist = new ArrayList<HashMap<String,String>>();

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        TanggalAdapter adapter=new TanggalAdapter(this, pid, Gbr, values);
        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // ListView Clicked item index
                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                int itemPosition    = position;
                // ListView Clicked item value
                String  itemValue   = (String) listView.getItemAtPosition(position);
                String tanggalSql   = String.valueOf(itemPosition);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("tanggalSql",tanggalSql);
                editor.putString("tanggalDisplay",itemValue);
                editor.commit();
                kirimIdTangggalEvent(tanggalSql);
                // Show Alert
                // Toast.makeText(getApplicationContext(),
                //        "Position :"+itemPosition+"  TanggalSql : " +tanggalSql , Toast.LENGTH_LONG)
                //       .show();
                //Intent k = new Intent(BookingPilihTanggal.this, BookingPilihJam.class);
                //startActivity(k);
            }
        });
    }



    protected  String tampilkanTanggalDanWaktu(Date tanggalDanWaktu, String pola, Locale lokal) {
        String tanggalStr = null;
        SimpleDateFormat formatter = null;
        if (lokal == null) {
            formatter = new SimpleDateFormat(pola);
        } else {
            formatter = new SimpleDateFormat(pola, lokal);
        }

        tanggalStr = formatter.format(tanggalDanWaktu);
        return tanggalStr;
    }

    private void kirimIdTangggalEvent(String tanggalSql) {

        String id_event="7";
        final String keluaran = "";
        //Here we will handle the http request to insert user to mysql db
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        GetShowTimeJamAPI api = adapter.create(GetShowTimeJamAPI.class);

        //Defining the method insertuser of our interface
        api.kirimIdTanggalEventAPI(
                id_event,
                tanggalSql,
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
                        Toast.makeText(BookingPilihTanggal.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void setData(String output) {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String nama_event_tiket_keluar ="";
        String jam_event_tiket_keluar ="";
        final JSONObject obj;
        try {
            obj = new JSONObject(output);
            final JSONArray event = obj.getJSONArray("event");
            final int n = event.length();
            for (int i = 0; i < n; ++i) {
                final JSONObject person = event.getJSONObject(i);
                String nama_event_tiket = person.getString("nama");
                String jam_event_tiket = person.getString("jam");
                nama_event_tiket_keluar  += nama_event_tiket +"|";
                jam_event_tiket_keluar   += jam_event_tiket +"|";

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("nama_event_tiket_keluar", nama_event_tiket_keluar);
        editor.putString("jam_event_tiket_keluar", jam_event_tiket_keluar);
        editor.commit();
        System.out.println(nama_event_tiket_keluar);
        System.out.println(jam_event_tiket_keluar);
        Intent intent = new Intent(BookingPilihTanggal.this, BookingPilihJam.class);
        startActivity(intent);
    }
}


