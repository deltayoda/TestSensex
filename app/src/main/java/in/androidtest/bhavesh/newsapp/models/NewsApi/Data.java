
package in.androidtest.bhavesh.newsapp.models.NewsApi;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Parcelable
{

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("sensex")
    @Expose
    private String sensex;
    @SerializedName("equity")
    @Expose
    private String equity;
    @SerializedName("point")
    @Expose
    private String point;
    public final static Creator<Data> CREATOR = new Creator<Data>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        public Data[] newArray(int size) {
            return (new Data[size]);
        }

    }
    ;

    protected Data(Parcel in) {
        this.date = ((String) in.readValue((String.class.getClassLoader())));
        this.sensex = ((String) in.readValue((String.class.getClassLoader())));
        this.equity = ((String) in.readValue((String.class.getClassLoader())));
        this.point = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Data() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSensex() {
        return sensex;
    }

    public void setSensex(String sensex) {
        this.sensex = sensex;
    }

    public String getEquity() {
        return equity;
    }

    public void setEquity(String equity) {
        this.equity = equity;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(date);
        dest.writeValue(sensex);
        dest.writeValue(equity);
        dest.writeValue(point);
    }

    public int describeContents() {
        return  0;
    }

}
