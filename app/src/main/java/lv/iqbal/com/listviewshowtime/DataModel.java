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

    public DataModel(String name, int anInt,  String otherData) {
        this.name = name;
        this.anInt = anInt;
        OtherData = otherData;
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
}