package tom.smally;

/**
 * Created by 611218504 on 06/06/2017.
 */

public class Venue {

    String name = "";
    Boolean clubOpen = true;
    Integer clubDrawable = 0;
    String url = "";
    String ticketInfo = "";
    String ticketLink = "";
    openingTimes openingTimes = new openingTimes();
    String mapLink = "";
    String address = "";
    String description = "";
    String drinks = "";
    String phone = "";
    String email = "";
    String Facebook = "";
    String Twitter = "";
    String Instagram = "";
    String Google = "";

    String ID = "";
    String Title = "";
    Boolean OpenStatus = false;
    openingTimes OpenTimes = new openingTimes();
    Integer TimeToClose = -1; //set to "closed"
    String Location = "campus";
    String FileName = "freshtimes";
    int Drawable = R.drawable.limeopen;

    public Venue(String title, Boolean openStatus, String filename, String location) {
        Title = title;
        OpenStatus = openStatus;
        FileName = filename;
        Location = location;
    }

    public String getID() {
        return ID;
    }

    public void setID(String restID) {
        this.ID = restID;
    }

    public String getTitle() {
        return Title;
    }

    public Boolean getOpenStatus() {
        return OpenStatus;
    }

    public openingTimes getOpenTimes() {
        return OpenTimes;
    }

    public Integer getTimeToClose() {
        return TimeToClose;
    }

    public String getTimeToClose_InHourMins() { return convertMinsToHourMins(TimeToClose); }

    /**
     * Fully works - could add more if statements
     */
    public String convertMinsToHourMins(int timeToClose) {
        if (timeToClose >= 120 || timeToClose < 60) {
            return (timeToClose / 60) + "hrs " + (timeToClose % 60) + "mins";
        } else {
            return (timeToClose / 60) + "hr " + (timeToClose % 60) + "mins";
        }
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String restLocation) {
        Location = restLocation;
    }

    public String getFileName() {
        return FileName;
    }

    public int getDrawable() {
        return Drawable;
    }


    public void setTitle(String title) {
        Title = title;
    }

    public void setOpenStatus(Boolean status) {
        OpenStatus = status;
    }

    public void setOpenTimes(openingTimes openTimes) {
        OpenTimes = openTimes;
    }

    public void setTimeToClose(Integer timeToClose) { TimeToClose = timeToClose; }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public void setDrawable(int drawable) {
        Drawable = drawable;
    }

}
