package maykec.com.gygreviews.ui.fragment;

import android.util.Log;

import maykec.com.gygreviews.R;
import maykec.com.gygreviews.data.GygSharedPreferences;
import maykec.com.gygreviews.model.PostReview;
import maykec.com.gygreviews.network.GygService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by marko on 5/15/2016.
 * Presenter class to hold business logic for SubmitReviewDialogFragment
 */
public class SubmitReviewPresenter {
    private static final String TAG = "SubmitReviewPresenter";

    private GygService mGygService;
    private GygSharedPreferences mGygSharedPreferences;
    private SubmitReviewView mView;
    private long mTourId;

    public SubmitReviewPresenter(SubmitReviewView view, GygService gygService,
                                 GygSharedPreferences gygSharedPreferences,
                                 long tourId) {
        mView = view;
        mGygService = gygService;
        mGygSharedPreferences = gygSharedPreferences;
        mTourId = tourId;
    }

    public void submitReview(String reviewText, float rating) {

        //User token is mocked
        String userToken = mGygSharedPreferences.getUserToken();

        //construct past payload
        PostReview postReview = new PostReview(mTourId, userToken, reviewText, rating);
        Call<PostReview> postReviewRequest = mGygService.postReview(postReview);
        postReviewRequest.enqueue(new Callback<PostReview>() {
            @Override
            public void onResponse(Call<PostReview> call, Response<PostReview> response) {
                if ( response.isSuccessful()) {
                    mView.dismissWithMessage(R.string.submit_success);
                } else {
                    mView.dismissWithMessage(response.message());
                }
            }

            @Override
            public void onFailure(Call<PostReview> call, Throwable t) {
                mView.dismissWithMessage(t.getMessage());

            }
        });


    }
}
