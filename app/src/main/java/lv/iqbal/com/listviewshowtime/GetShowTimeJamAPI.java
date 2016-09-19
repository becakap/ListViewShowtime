package lv.iqbal.com.listviewshowtime;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by IQBAL on 9/16/2016.
 */
public interface GetShowTimeJamAPI {
    @FormUrlEncoded
    @POST("/androidapi/list_showtime_jam.php")
    public void kirimIdTanggalEventAPI(
            @Field("id_event") String idevent,
            @Field("tanggal") String tanggal,
            Callback<Response> callback);
}
