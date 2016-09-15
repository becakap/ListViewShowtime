package lv.iqbal.com.listviewshowtime;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class BookingPilihTiket extends Activity {

    private static final String TAG = BookingPilihTiket.class.getSimpleName();
    Button btnGetCount;

    public static final String MyPREFERENCES = "PilihanTiket" ;
    SharedPreferences sharedpreferences;

    public static final String ROOT_URL = "http://api.hypemedan.id/";

    ArrayList<DataModel> listArray;




    // Create new ArrayList.
    ArrayList<String> namaTiket = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listView = (ListView) findViewById(R.id.list_data);
        btnGetCount = (Button)findViewById(R.id.btnGetCount);



        SharedPreferences shared = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String sNamaTiket = (shared.getString("NamaTiket", ""));
        String sIdTiket = (shared.getString("IdTiket", ""));

        String pNamaTiket = sNamaTiket.replaceFirst(".$","");
        String pIdTiket = sIdTiket.replaceFirst(".$","");




        CustomAdapter customAdapter = new CustomAdapter(pNamaTiket , pIdTiket);
        listView.setAdapter(customAdapter);




        // we have added a button on our list view
        // for that reason, list view on item click wont get the event
        // we need to implement click listener in custom adapter
        // this method will work if we do not add button on our list view

        final int count = listView.getCount();



        btnGetCount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

                String jlh_tiket = "";
                String nama_tiket = "";


                View parentView = null;
                int x = listView.getCount();
                int temp = 0;
                int getTotal = 0;
                String variasiNamaTiket = "";
                String vairasiIdTiket = "";
                String vairasiJumlahTiket = "";
                for (int i = 0; i < x; i++) {

                    parentView = getViewByPosition(i, listView);

                    String namaTiket = ((TextView) parentView
                            .findViewById(R.id.tv_string_data)).getText().toString();

                    String idTiket = ((TextView) parentView
                            .findViewById(R.id.list_item_string)).getText().toString();



                    String getString = ((TextView) parentView
                            .findViewById(R.id.btn_number_data)).getText().toString();

                    vairasiIdTiket += idTiket + "|";
                    variasiNamaTiket += namaTiket + "|";
                    vairasiJumlahTiket += getString + "|";




                    getTotal = Integer.parseInt(getString);
                    temp = temp + getTotal;

                }

                //Toast.makeText(BookingPilihTiket.this, "Jumlah Tiket " + temp, Toast.LENGTH_SHORT).show();
                //Toast.makeText(BookingPilihTiket.this, "vairasiIdTiket \n " + vairasiIdTiket +"\n", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(BookingPilihTiket.this, ReviewTiket.class);
                intent.putExtra("idTiket",vairasiIdTiket );
                intent.putExtra("namaTiket",variasiNamaTiket );
                intent.putExtra("jumlahTiket",vairasiJumlahTiket );
                startActivity(intent);



            }
        });



    }

  public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition
                + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }




}

