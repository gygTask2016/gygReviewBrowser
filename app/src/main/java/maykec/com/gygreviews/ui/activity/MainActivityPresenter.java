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

    public void getReviews(int page) {
        Call<ReviewsResponse> reviewsRequest = mGygService.getReviews(page, mMinRating);
        reviewsRequest.enqueue(new Callback<ReviewsResponse>() {
            @Override
            public void onResponse(Call<ReviewsResponse> call, Response<ReviewsResponse> response) {
                if (response.body() != null) {
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

    public void setRatingFilter(float ratingFilter) {
        mMinRating = (int) ratingFilter;
    }
}
