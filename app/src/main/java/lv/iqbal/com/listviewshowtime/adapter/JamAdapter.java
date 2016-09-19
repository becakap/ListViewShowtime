package lv.iqbal.com.listviewshowtime.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import lv.iqbal.com.listviewshowtime.BookingPilihJam;
import lv.iqbal.com.listviewshowtime.R;


/**
 * Created by IQBAL on 9/10/2016.
 */
public class JamAdapter extends ArrayAdapter<String> {

    private final BookingPilihJam context;
    private final String[] Pid;
    private final String[] Nama;
    private final String[] Gbr;
    private final String[] Jam;

    public JamAdapter(BookingPilihJam context, String[] Pid, String[] Nama, String[] Gbr, String[] Jam) {
        super(context, R.layout.jam_event, Jam);


        this.context=context;
        this.Pid=Pid;
        this.Nama=Nama;
        this.Gbr=Gbr;
        this.Jam=Jam;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.lsv_jam_event, null,true);

        TextView pid       = (TextView) rowView.findViewById(R.id.pid);
        ImageView img_event = (ImageView) rowView.findViewById(R.id.img_event);
        TextView tvNamaEventTiket = (TextView) rowView.findViewById(R.id.tvNamaEventTiket);
        TextView tvJamEventTiket = (TextView) rowView.findViewById(R.id.tvJamEventTiket);

        pid.setText(Pid[position]);
        img_event.setImageResource(Integer.parseInt(Gbr[position]));
        tvNamaEventTiket.setText(Nama[position]);
        tvJamEventTiket.setText(Jam[position]);
        return rowView;

    };

}
