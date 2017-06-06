package tom.smally;
/*
import android.util.Log;

import com.amazonaws.AmazonClientException;
import com.amazonaws.mobile.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.models.nosql.NotesDO;


public class AWSDBApi {


    public void insertData() {
        // Fetch the default configured DynamoDB ObjectMapper
        final DynamoDBMapper dynamoDBMapper = AWSMobileClient.defaultMobileClient().getDynamoDBMapper();
        final NotesDO note = new NotesDO(); // Initialize the Notes Object

        // The userId has to be set to user's Cognito Identity Id for private / protected tables.
        // User's Cognito Identity Id can be fetched by using:
        // AWSMobileClient.defaultMobileClient().getIdentityManager().getCachedUserID()
        note.setUserId("User_Cognito_Identity_Id");
        note.setNoteId("demo-noteId-500000");
        note.setContent("demo-content");
        note.setCreationDate(1471643627.00); // GMT: Fri, 19 Aug 2016 21:53:47 GMT
        note.setTitle("demo-title");
        AmazonClientException lastException = null;

        try {
            dynamoDBMapper.save(note);
        } catch (final AmazonClientException ex) {
            Log.e(LOG_TAG, "Failed saving item : " + ex.getMessage(), ex);
            lastException = ex;
        }
    }
    public void getData(){

    }

}
*/