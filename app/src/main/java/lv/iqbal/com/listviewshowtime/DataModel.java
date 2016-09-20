package lv.iqbal.com.listviewshowtime;

/**
 * Created with IntelliJ IDEA.
 * User: Shahab
 * Date: 8/22/12
 * Time: 11:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class DataModel {
    private String name;
    private int anInt;
    private String OtherData;
    private String detail;
    private String harga;

    public DataModel(String name, int anInt,  String otherData, String detail, String harga) {
        this.name = name;
        this.anInt = anInt;
        OtherData = otherData;
        this.detail = detail;
        this.harga = harga;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public int getAnInt() {
        return anInt;
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }

    public String getOtherData() {
        return OtherData;
    }

    public void setOtherData(String otherData) {
        OtherData = otherData;
    }


    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }




}