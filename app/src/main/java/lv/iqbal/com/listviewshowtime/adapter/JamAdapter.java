package lv.iqbal.com.listviewshowtime.adapter;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import lv.iqbal.com.listviewshowtime.R;


/**
 * Created by IQBAL on 9/10/2016.
 */
public class JamAdapter extends ArrayAdapter<String> {

    private final AppCompatActivity context;
    private final String[] Pid;
    private final String[] Gbr;
    private final String[] Jam;

    public JamAdapter(AppCompatActivity context, String[] Pid, String[] Gbr, String[] Jam) {
        super(context, R.layout.tanggal_event, Jam);


        this.context=context;
        this.Pid=Pid;
        this.Gbr=Gbr;
        this.Jam=Jam;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.lsv_jam_event, null,true);

        TextView pid       = (TextView) rowView.findViewById(R.id.pid);
        ImageView img_event = (ImageView) rowView.findViewById(R.id.img_event);
        TextView tvJamEvent = (TextView) rowView.findViewById(R.id.tvJamEvent);

        pid.setText(Pid[position]);
        img_event.setImageResource(Integer.parseInt(Gbr[position]));
        tvJamEvent.setText(Jam[position]);
        return rowView;

    };

}
