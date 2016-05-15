package maykec.com.gygreviews.model;

/**
 * Created by marko on 5/14/2016.
 */
public class Review {

    private long review_id;
    private String rating;
    private String title;
    private String message;
    private String author;
    private boolean foreignLanguage;
    private String date;
    private String languageCode;

    //not sure which data type should be declared here. It has always null value in response
    private String traveler_type;


    public long getReview_id() {
        return review_id;
    }

    public String getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isForeignLanguage() {
        return foreignLanguage;
    }

    public String getDate() {
        return date;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public String getTraveler_type() {
        return traveler_type;
    }
}
