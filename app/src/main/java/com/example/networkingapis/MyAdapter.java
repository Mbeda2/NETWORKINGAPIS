package com.example.networkingapis; // Ensure this matches your package name

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for the RecyclerView to display a list of Post objects.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    // List to hold the Post objects that will be displayed
    public List<PostPost> postList;

    /**
     * Constructor for the adapter.
     * @param postList The list of Post objects to be displayed.
     */
    public MyAdapter(List<PostPost> postList) {
        this.postList = postList;
    }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for a single list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_layout, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the Post object at the current position
        Object post = postList.get(position);

        // Bind the data from the Post object to the TextViews in the ViewHolder
        holder.textViewTitle.setText(((com.example.networkingapis.PostPost) post).getTitle());
        holder.textViewBody.setText(((Post) post).getBody());
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return postList.size();
    }

    /**
     * Method to update the data in the adapter and notify the RecyclerView to refresh.
     * This is useful when new data is fetched from the API.
     * @param newPosts The new list of Post objects to display.
     */
    public void setPosts(ArrayList<PostPost> newPosts) {
        this.postList = newPosts;
        // Notify the adapter that the data set has changed, causing the RecyclerView to redraw.
        notifyDataSetChanged();
    }

    /**
     * ViewHolder class to hold the views for each item in the RecyclerView.
     * This improves performance by recycling views.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTitle;
        public TextView textViewBody;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize the TextViews from the list_item_layout.xml
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewBody = itemView.findViewById(R.id.textViewBody);
        }
    }
}