package maykec.com.gygreviews.ui.activity;

import maykec.com.gygreviews.data.GygSharedPreferences;
import maykec.com.gygreviews.model.ReviewsResponse;
import maykec.com.gygreviews.network.GygApiServiceProvider;
import maykec.com.gygreviews.network.GygService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by marko on 5/14/2016.
 * Presenter class to hold business logic for MainActivity
 */
public class MainActivityPresenter {
    private static final String TAG = "MainActivityPresenter";

    private MainActivityView mView;
    private GygService mGygService;
    private GygSharedPreferences mGygSharedPreferences;

    //filter for rating
    private int mMinRating = 0;

    public MainActivityPresenter(MainActivity mainActivity) {
        mGygService = GygApiServiceProvider.provideGygService("https://www.getyourguide.com/",
                mainActivity.getApplicationContext());
        mView = mainActivity;
        mGygSharedPreferences = new GygSharedPreferences(mainActivity.getApplicationContext());


    }

    /**
     * @param page
     * GET request on reviews endpoint with given page
     */
    public void getReviews(int page) {
        Call<ReviewsResponse> reviewsRequest = mGygService.getReviews(page, mMinRating);
        reviewsRequest.enqueue(new Callback<ReviewsResponse>() {
            @Override
            public void onResponse(Call<ReviewsResponse> call, Response<ReviewsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mView.onReviewsReceived(response.body().getData());
                } else {
                    //TODO handle error with no body
                    //ex: mView.showNoDataMessage();
                }


            }

            @Override
            public void onFailure(Call<ReviewsResponse> call, Throwable t) {
                //TODO handle failure
                //ex: mView.showRequestFailureDialog();

            }
        });
    }

    public void onPostReviewClick() {
        //Tour ID is mocked
        long mockedTourId = 5465484;
        mView.openPostReviewFragmentDialog(mGygService, mGygSharedPreferences, mockedTourId);
    }

    /**
     * @param ratingFilter
     * Sets minimal rating parameter in request
     */
    public void setRatingFilter(float ratingFilter) {
        mMinRating = (int) ratingFilter;
    }
}
