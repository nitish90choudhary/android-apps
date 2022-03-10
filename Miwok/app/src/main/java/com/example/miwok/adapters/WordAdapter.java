package com.example.miwok.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.miwok.R;
import com.example.miwok.model.Word;

import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {
    int textContainerColor = 0;

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context  The current context. Used to inflate the layout file.
     * @param wordList A List of Word objects to display in a list
     */
    public WordAdapter(Activity context, List<Word> wordList, int color) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, wordList);
        textContainerColor = color;
    }


    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view

        View listItemView = convertView;
        if (listItemView == null)
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        // Get the {@link Word} object located at this position in the list
        final Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID miwokTranslation
        TextView miwokTextView = listItemView.findViewById(R.id.miwokTranslation);

        // Get the miwokTranslation from the current Word object and set this text on the name TextView
        miwokTextView.setText(currentWord.getMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID defaultTranslation
        TextView defaultTextView = listItemView.findViewById(R.id.defaultTranslation);

        // Get the defaultTranslation from the current Word object and set this text on the number TextView
        defaultTextView.setText(currentWord.getDefaultTranslation());
        ImageView imageIcon = listItemView.findViewById(R.id.img);

        if (currentWord.getImgRes() != 0)
            imageIcon.setImageResource(currentWord.getImgRes());
        else {
            imageIcon.setVisibility(View.GONE);
        }
        //set theme color
        listItemView.findViewById(R.id.text_containers).setBackgroundColor(ContextCompat.getColor(getContext(), textContainerColor));

        return listItemView;
    }
}
