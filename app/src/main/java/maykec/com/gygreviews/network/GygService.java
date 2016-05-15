package maykec.com.gygreviews.network;

import maykec.com.gygreviews.model.PostReview;
import maykec.com.gygreviews.model.ReviewsResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by marko on 5/14/2016.
 */
public interface GygService {
    @GET("berlin-l17/tempelhof-2-hour-airport-history-tour-berlin-airlift-more-t23776/reviews.json?count=10&type=&sortBy=date_of_review&direction=DESC")
    Call<ReviewsResponse> getReviews(@Query("page") int page, @Query("rating") int rating);

    @POST("postReview")
    Call<PostReview> postReview(@Body PostReview postReview);

}
