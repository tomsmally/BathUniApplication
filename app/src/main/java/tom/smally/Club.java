package tom.smally;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Thomas on 01/06/2016.
 */
public class Club extends Venue
{
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

    public Club(String title, Boolean openStatus, String filename, String location) {
        super(title, openStatus, filename, location);
    }
}
