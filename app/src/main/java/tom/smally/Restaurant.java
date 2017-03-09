package tom.smally;

import android.graphics.drawable.Drawable;

/**
 * Created by 611218504 on 08/03/2017.
 */

public class Restaurant extends Food {

    String restID = "";
    String restTitle = "";
    Boolean restOpenStatus = false;
    openingTimes restOpenTimes = new openingTimes();
    Integer restTimeToClose = -1; //set to "closed"
    String restLocation = "campus";
    String restFileName = "freshtimes";
    int restDrawable = R.drawable.limeopen;

    public Restaurant(String title, Boolean openStatus, String filename, String location) {
        restTitle = title;
        restOpenStatus = openStatus;
        restFileName = filename;
        restLocation = location;
    }

    public String getRestID() {
        return restID;
    }

    public void setRestID(String restID) {
        this.restID = restID;
    }

    public String getRestTitle() {
        return restTitle;
    }

    public Boolean getRestOpenStatus() {
        return restOpenStatus;
    }

    public openingTimes getRestOpenTimes() {
        return restOpenTimes;
    }

    public Integer getRestTimeToClose() {
        return restTimeToClose;
    }
    public String getRestTimeToClose_InHourMins(){
        return convertMinsToHourMins(restTimeToClose);
    }
    /**
     * Fully works - could add more if statements
     */
    public String convertMinsToHourMins(int timeToClose)
    {
        if(timeToClose>= 120 || timeToClose<60 ) {
            return (timeToClose / 60) + "hrs " + (timeToClose % 60) + "mins";
        }else {
            return (timeToClose / 60) + "hr " + (timeToClose % 60) + "mins";
        }
    }

    public String getRestLocation() {
        return restLocation;
    }

    public void setRestLocation(String restLocation) {
        this.restLocation = restLocation;
    }

    public String getRestFileName() {
        return restFileName;
    }

    public int getRestDrawable() {
        return restDrawable;
    }


    public void setRestTitle(String title) {
        restTitle = title;
    }

    public void setRestOpenStatus(Boolean status) {
        restOpenStatus = status;
    }

    public void setRestOpenTimes(openingTimes openTimes) {
        restOpenTimes = openTimes;
    }

    public void setRestTimeToClose(Integer timeToClose) {
        restTimeToClose = timeToClose;
    }

    public void setLocation(String location) {
        restLocation = location;
    }

    public void setRestFileName(String fileName) {
        restFileName = fileName;
    }

    public void setRestDrawable(int drawable){
        restDrawable = drawable;
    }
}
