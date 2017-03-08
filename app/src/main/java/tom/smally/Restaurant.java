package tom.smally;

import android.graphics.drawable.Drawable;

/**
 * Created by 611218504 on 08/03/2017.
 */

public class Restaurant extends Food {
    String restTitle = "";
    Boolean restOpenStatus = false;
    openingTimes restOpenTimes = new openingTimes();
    Integer restTimeToClose = -1; //set to "closed"
    String restLocation = "";
    String restFileName = "freshtimes";
    int restDrawable = R.drawable.limeopen;

    public Restaurant(String title, Boolean openStatus, String filename) {
        restTitle = title;
        restOpenStatus = openStatus;
        restFileName = filename;
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
}
