package lv.iqbal.com.listviewshowtime;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by IQBAL on 9/16/2016.
 */
public interface GetShowTimeTanggalAPI {
    @FormUrlEncoded
    @POST("/androidapi/list_showtime_tanggal.php")
    public void kirimIdEventAPI(
            @Field("id_event") String idevent,
            Callback<Response> callback);
}
