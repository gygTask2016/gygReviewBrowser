package maykec.com.gygreviews.ui.activity;

import maykec.com.gygreviews.data.GygSharedPreferences;
import maykec.com.gygreviews.model.Review;
import maykec.com.gygreviews.network.GygService;

/**
 * Created by marko on 5/15/2016.
 */
public interface MainActivityView {
    void onReviewsReceived(Review[] data);
    void openPostReviewFragmentDialog(GygService mGygService,
                                      GygSharedPreferences mGygSharedPreferences,
                                      long tourId);
}
