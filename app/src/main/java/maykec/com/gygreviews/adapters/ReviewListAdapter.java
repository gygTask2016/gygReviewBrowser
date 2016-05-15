package maykec.com.gygreviews.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import maykec.com.gygreviews.R;
import maykec.com.gygreviews.model.Review;

/**
 * Created by marko on 5/15/2016.
 */
public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ReviewsRowHolder> {

    private List<Review> mReviews;


    public ReviewListAdapter(){
        mReviews = new ArrayList<>();
    }

    @Override
    public ReviewsRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_list_item, parent, false);

        return new ReviewsRowHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ReviewsRowHolder holder, int position) {
        Review review = mReviews.get(position);
        holder.mReviewTitle.setText(review.getTitle());
        holder.mReviewMessage.setText(review.getMessage());
        holder.mReviewAuthor.setText(review.getAuthor());
        holder.mReviewDate.setText(review.getDate());
        String ratingString = review.getRating();

        try {
            float rating = Float.parseFloat(ratingString);
            holder.rbReviewRating.setRating(rating);
        }catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    public void appendData(Review[] data) {
        if (data == null || data.length == 0) {
            return;
        }

        for (Review review : data) {
            mReviews.add(review);
        }

        notifyDataSetChanged();
    }

    public void clearData() {
        mReviews.clear();
    }

    public class ReviewsRowHolder extends RecyclerView.ViewHolder {
        public TextView mReviewTitle;
        public TextView mReviewMessage;
        public TextView mReviewAuthor;
        public TextView mReviewDate;
        public RatingBar rbReviewRating;

        public ReviewsRowHolder(View view) {
            super(view);
            mReviewTitle = (TextView) view.findViewById(R.id.tv_review_title);
            mReviewMessage = (TextView) view.findViewById(R.id.tv_review_message);
            mReviewAuthor = (TextView) view.findViewById(R.id.tv_review_author);
            mReviewDate = (TextView) view.findViewById(R.id.tv_review_date);
            rbReviewRating = (RatingBar) view.findViewById(R.id.rb_review_rating);
        }
    }
}
