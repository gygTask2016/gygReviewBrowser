package maykec.com.gygreviews.ui.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;

import maykec.com.gygreviews.R;
import maykec.com.gygreviews.adapters.ReviewListAdapter;
import maykec.com.gygreviews.data.GygSharedPreferences;
import maykec.com.gygreviews.listeners.EndlessRecyclerViewScrollListener;
import maykec.com.gygreviews.model.Review;
import maykec.com.gygreviews.network.GygService;
import maykec.com.gygreviews.ui.RecyclerViewItemDecoration;
import maykec.com.gygreviews.ui.fragment.SubmitReviewDialogFragment;

public class MainActivity extends Activity implements MainActivityView, View.OnClickListener, RatingBar.OnRatingBarChangeListener {
    private static final String TAG = "MainActivity";
    private static final String SUBMIT_REVIEW_DIALOG_TAG = "submitReviewDialogFragment";

    private RecyclerView mReviewList;
    private FloatingActionButton mBtPostReview;
    private RatingBar mRatingFilter;

    private MainActivityPresenter mPresenter;
    private ReviewListAdapter mReviewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainActivityPresenter(this);

        //init views
        mReviewList = (RecyclerView)findViewById(R.id.rc_reviews_list);
        mRatingFilter = (RatingBar)findViewById(R.id.rb_filter);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mReviewList.setLayoutManager(mLayoutManager);
        mReviewList.setItemAnimator(new DefaultItemAnimator());
        mReviewList.addOnScrollListener(new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                loadReviewsPage(page);
            }
        });

        mReviewList.addItemDecoration(new RecyclerViewItemDecoration(this));
        mBtPostReview = (FloatingActionButton) findViewById(R.id.fab);
        mBtPostReview.setOnClickListener(this);
        mRatingFilter.setOnRatingBarChangeListener(this);

        mReviewsAdapter = new ReviewListAdapter();
        mReviewList.setAdapter(mReviewsAdapter);


    }

    private void loadReviewsPage(int page) {
        mPresenter.getReviews(page);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getReviews(0);
    }


    @Override
    public void onReviewsReceived(Review[] data) {
        mReviewsAdapter.appendData(data);

    }

    @Override
    public void openPostReviewFragmentDialog(GygService mGygService, GygSharedPreferences mGygSharedPreferences, long tourId) {
        FragmentTransaction mFragmentTransaction = getFragmentManager().beginTransaction();
        Fragment prevFragment = getFragmentManager().findFragmentByTag(SUBMIT_REVIEW_DIALOG_TAG);
        if (prevFragment != null) {
            mFragmentTransaction.remove(prevFragment);
        }
        mFragmentTransaction.addToBackStack(null);

        SubmitReviewDialogFragment mSubmitReviewDialogFragment =
                SubmitReviewDialogFragment.getInstance(mGygService, mGygSharedPreferences, tourId);

        mSubmitReviewDialogFragment.show(mFragmentTransaction, SUBMIT_REVIEW_DIALOG_TAG);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                mPresenter.onPostReviewClick();

                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        mPresenter.setRatingFilter(rating);

        //clear current data and request data from page 0
        mReviewsAdapter.clearData();
        mPresenter.getReviews(0);
    }
}
