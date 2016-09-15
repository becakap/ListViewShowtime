package lv.iqbal.com.listviewshowtime;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;


public interface GetShowTimeTicketAPI {

    @FormUrlEncoded
    @POST("/androidapi/list_showtime_tiket.php")
    public void kirimIdEventAPI(
            @Field("id_event") String idevent,
            Callback<Response> callback);
}
