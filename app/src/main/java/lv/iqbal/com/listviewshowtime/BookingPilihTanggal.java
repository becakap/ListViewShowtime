package lv.iqbal.com.listviewshowtime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import lv.iqbal.com.listviewshowtime.adapter.TanggalAdapter;

public class BookingPilihTanggal extends Activity {
    ListView listView ;
    String[] pid;
    String[] Gbr;
    String[] Tgl;
    protected ListView lv;
    protected ListAdapter adapter;
    SimpleAdapter Adapter;
    HashMap<String, String> map;
    ArrayList<HashMap<String, String>> mylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tanggal_event);
        setTheme(android.R.style.Theme_Holo);


        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.lv_item_tanggal);

        // Defined Array values to show in ListView
        String[] values = new String[] {
                "17 Agustus 2017",
                "17 Agustus 2017",
                "17 Agustus 2017",
                "17 Agustus 2017",
                "17 Agustus 2017",
                "17 Agustus 2017",
                "17 Agustus 2017",
                "17 Agustus 2017"
        };

        pid = new String[] {
                "1",
                "2",
                "3",
                "4",
        };

        Gbr = new String[] {
                Integer.toString(R.drawable.bg1),
                Integer.toString(R.drawable.bg1),
                Integer.toString(R.drawable.bg1),
                Integer.toString(R.drawable.bg1),
        };

        Tgl = new String[] {
                "17 Agustus 2017",
                "17 Agustus 2017",
                "17 Agustus 2017",
                "17 Agustus 2017",
        };

        mylist = new ArrayList<HashMap<String,String>>();

        for (int i = 0 ; i<pid.length; i++){
            map = new HashMap<String, String>();
            map.put("pid", pid[i]);
            map.put("tgl", Tgl[i]);
            map.put("gbr",Gbr[i]);
            mylist.add(map);
        }


        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        TanggalAdapter adapter=new TanggalAdapter(this, pid, Gbr, Tgl);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                //int itemPosition     = position;

                // ListView Clicked item value
                //String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert
                //Toast.makeText(getApplicationContext(),
                //        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                //       .show();
                Intent k = new Intent(BookingPilihTanggal.this, BookingPilihJam.class);
                startActivity(k);

            }

        });
    }
}
