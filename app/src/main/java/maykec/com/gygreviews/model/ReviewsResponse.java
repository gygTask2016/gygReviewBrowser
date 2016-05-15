package maykec.com.gygreviews.model;

/**
 * Created by marko on 5/14/2016.
 */
public class ReviewsResponse {

    private String status;
    private long total_reviews;
    private Review[] data;

    public String getStatus() {
        return status;
    }

    public long getTotal_reviews() {
        return total_reviews;
    }

    public Review[] getData() {
        return data;
    }
}
