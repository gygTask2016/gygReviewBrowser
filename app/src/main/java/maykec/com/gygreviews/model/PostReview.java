package maykec.com.gygreviews.model;

/**
 * Created by marko on 5/15/2016.
 */
public class PostReview {

    private long tour_id;
    private String user_token;
    private String review_message;
    private float rating;

    public PostReview(long tour_id, String user_token, String review_message, float rating) {
        this.tour_id = tour_id;
        this.user_token = user_token;
        this.review_message = review_message;
        this.rating = rating;
    }
}
