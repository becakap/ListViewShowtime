package lv.iqbal.com.listviewshowtime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.StringTokenizer;

public class ReviewTiket extends AppCompatActivity {

    TextView textViewReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_tiket);

       // textViewReview = (TextView) findViewById(R.id.textViewReview);



        Intent intent = getIntent();
        String gid = intent.getStringExtra("idTiket");
        String gnama = intent.getStringExtra("namaTiket");
        String gjumlah = intent.getStringExtra("jumlahTiket");


        String idTiket = gid.replaceFirst(".$","");
        String namaTiket = gnama.replaceFirst(".$","");
        String jumlahTiket = gjumlah.replaceFirst(".$","");


        StringTokenizer st1 = new StringTokenizer(idTiket, "|");
        StringTokenizer st2 = new StringTokenizer(namaTiket, "|");
        StringTokenizer st3 = new StringTokenizer(jumlahTiket, "|");
        String Tampilan = "";
        int k = 0;
        while (st1.hasMoreElements()) {

            String nmId  = String.valueOf(st1.nextElement());
            String nmTiket     = String.valueOf(st2.nextElement());
            int jumlah_tiket = Integer.parseInt(String.valueOf(st3.nextElement()));

            if(jumlah_tiket!=0){
                Tampilan += "\n Tiket :"+nmTiket+"\n"+"Jumlah Tiket :"+jumlah_tiket+" \n ";
            }


            k++;
        }



      //  textViewReview.setText(Tampilan);






    }


}
