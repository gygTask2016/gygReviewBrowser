package maykec.com.gygreviews.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import maykec.com.gygreviews.R;
import maykec.com.gygreviews.data.GygSharedPreferences;
import maykec.com.gygreviews.network.GygService;

/**
 * Created by marko on 5/15/2016.
 */
public class SubmitReviewDialogFragment extends android.app.DialogFragment implements View.OnClickListener, SubmitReviewView{

    private EditText mReviewEditText;
    private RatingBar mSubmitRatingBar;
    private Button mSubmitButton;

    private SubmitReviewPresenter mPresenter;

    public static SubmitReviewDialogFragment getInstance(GygService gygService,
                                                         GygSharedPreferences gygSharedPreferences,
                                                         long tourId) {
        SubmitReviewDialogFragment fragment = new SubmitReviewDialogFragment();
        fragment.initPresenter(gygService, gygSharedPreferences, tourId);
        return fragment;
    }

    public void initPresenter(GygService gygService, GygSharedPreferences gygSharedPreferences, long tourId){
        mPresenter = new SubmitReviewPresenter(this, gygService, gygSharedPreferences, tourId);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sumbit_review_fragment, container, false);
        getDialog().setTitle(getResources().getString(R.string.submit_review_title));

        mReviewEditText = (EditText)v.findViewById(R.id.et_review_content);
        mSubmitButton = (Button)v.findViewById(R.id.bt_sumbit);
        mSubmitRatingBar = (RatingBar)v.findViewById(R.id.rb_rating);

        mSubmitButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_sumbit:
                mPresenter.submitReview(String.valueOf(mReviewEditText.getText()), mSubmitRatingBar.getRating());
            break;
        }
    }

    @Override
    public void dismissWithMessage(int submit_success) {
        Toast.makeText(getActivity(), submit_success, Toast.LENGTH_LONG).show();
        dismiss();
    }

    @Override
    public void dismissWithMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        dismiss();

    }
}
