package lv.iqbal.com.listviewshowtime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BookingPilihJam extends Activity {
    ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waktu_event);
        setTheme(android.R.style.Theme_Holo);


        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.lv_jam_item);

        // Defined Array values to show in ListView
        String[] values = new String[] {
                " 12:00 WIB ",
                " 13:00 WIB ",
                " 14:00 WIB ",
                " 17:00 WIB ",
                " 20:00 WIB ",
                " 21:00 WIB ",
                " 22:00 WIB ",
                " 23:00 WIB ",
        };

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent k = new Intent(BookingPilihJam.this, ButtonGetData.class);
                startActivity(k);


            }

        });
    }
}
