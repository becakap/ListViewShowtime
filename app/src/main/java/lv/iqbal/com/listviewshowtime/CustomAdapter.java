package lv.iqbal.com.listviewshowtime;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class CustomAdapter extends BaseAdapter {

    private static final String TAG = CustomAdapter.class.getSimpleName();
    ArrayList<DataModel>listArray;

    public static final String ROOT_URL = "http://api.hypemedan.id/";



    public CustomAdapter(String pNamaTiket, String pIdTiket) {
        listArray = new ArrayList<DataModel>();
        StringTokenizer st1 = new StringTokenizer(pNamaTiket, "|");
        StringTokenizer st2 = new StringTokenizer(pIdTiket, "|");
        int k = 0;
        while (st1.hasMoreElements()) {

            String nmTiket  = String.valueOf(st1.nextElement());
            String nmId     = String.valueOf(st2.nextElement());
            listArray.add(new DataModel(nmTiket, 0, nmId));

            k++;
        }


       /* listArray = new ArrayList<DataModel>();
        listArray.add(new DataModel("Tiket VIP A", 0, "1"));
        listArray.add(new DataModel("Pre Sale 1 ", 0, "2"));
        listArray.add(new DataModel("Pre Sale 2", 0, "3"));
        listArray.add(new DataModel("Atrium Kanan", 0, "4"));
        listArray.add(new DataModel("Atrium Kiri", 0, "5"));*/

    }





    @Override
    public int getCount() {
        return listArray.size();    // total number of elements in the list
    }

    @Override
    public Object getItem(int i) {
        return listArray.get(i);    // single item in the list
    }

    @Override
    public long getItemId(int i) {
        return i;                   // index number
    }

    @Override
    public View getView(int index, View view, final ViewGroup parent) {

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.lsv_tiket_event, parent, false);
        }

        final DataModel dataModel = listArray.get(index);

        TextView textView = (TextView) view.findViewById(R.id.tv_string_data);
        textView.setText(dataModel.getName());

        TextView textViewID = (TextView) view.findViewById(R.id.list_item_string);
        textViewID.setText(dataModel.getOtherData());



        Button button = (Button) view.findViewById(R.id.btn_number_data);
        button.setText("" + dataModel.getAnInt());


        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button)view.findViewById(R.id.delete_btn);
        Button addBtn = (Button)view.findViewById(R.id.add_btn);

        final View finalView = view;

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                int xxx = dataModel.getAnInt() ;
                if (xxx == 0){
                    return;
                }
                int kkk = xxx-1;
                dataModel.setAnInt(kkk);

                notifyDataSetChanged();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int xxx = dataModel.getAnInt() ;
                if (xxx == 99){
                    return;
                }
                int kkk = xxx+1;
                dataModel.setAnInt(kkk);

                notifyDataSetChanged();

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "string: " + dataModel.getName());
                Log.d(TAG, "int: " + dataModel.getAnInt());
                Log.d(TAG, "otherData: " + dataModel.getOtherData());

                Toast.makeText(parent.getContext(), "button clicked: " + dataModel.getName(), Toast.LENGTH_SHORT).show();
            }
        });








        // if you commented out the above chunk of code and
        // activate this chunk of code
        // then if user click on any view inside the list view (except button)
        // this chunk of code will execute
        // because we set the click listener on the whole view
       return view;
    }
}