package lv.iqbal.com.listviewshowtime.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import lv.iqbal.com.listviewshowtime.BookingPilihTanggal;
import lv.iqbal.com.listviewshowtime.R;


/**
 * Created by IQBAL on 9/10/2016.
 */
public class TanggalAdapter extends ArrayAdapter<String> {

    private final BookingPilihTanggal context;
    private final String[] Pid;
    private final String[] Gbr;
    private final String[] Tgl;

    public TanggalAdapter(BookingPilihTanggal context, String[] Pid, String[] Gbr, String[] Tgl) {
        super(context, R.layout.tanggal_event, Tgl);


        this.context=context;
        this.Pid=Pid;
        this.Gbr=Gbr;
        this.Tgl=Tgl;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.lsv_tanggal_event, null,true);

        TextView pid       = (TextView) rowView.findViewById(R.id.pid);
        ImageView img_event = (ImageView) rowView.findViewById(R.id.img_event);
        TextView tvTanggalEvent = (TextView) rowView.findViewById(R.id.tvTanggalEvent);

        pid.setText(Pid[position]);
        img_event.setImageResource(Integer.parseInt(Gbr[position]));
        tvTanggalEvent.setText(Tgl[position]);
        return rowView;

    };

}
