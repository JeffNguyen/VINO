package com.yoloswag.vino.favorites;


import java.util.ArrayList;
import java.util.List;

import com.yoloswag.vino.R;
import com.yoloswag.vino.R.id;
import com.yoloswag.vino.R.layout;
import com.yoloswag.vino.model.Entry;
import com.yoloswag.vino.model.Wine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;

public class FavoritesFragment extends Fragment implements OnGroupExpandListener
{
	Entry[] entries = Entry.getAll();
    ExpandableListView exv;
    public static final Wine[][] suggestionNames = new Wine [FavoritesAdapter.favoriteSize][4];
    
    public FavoritesFragment() 
    {
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
		 for (int i = 0; i < FavoritesAdapter.favoriteSize; ++i)
		 {
			 suggestionNames[i] = suggestions(FavoritesAdapter.favoriteWines[i]);
		 }
    	
        View rootView = inflater.inflate(R.layout.fragment_favorites, container, false);
    	exv = (ExpandableListView)rootView.findViewById(R.id.expandableListView1);
    	exv.setOnGroupExpandListener((OnGroupExpandListener) this);
		exv.setAdapter(new FavoritesAdapter(this));
		
        return rootView;
    }
    
    /*Instantiate suggestions fragment for the given wine
    public Fragment displaySuggestions(){
    	Fragment SuggestionsFragment = new SuggestionsFragment();
    	return SuggestionsFragment;
    }*/
    
    public void onGroupExpand(int groupPosition) {
	    int len = exv.getCount();

	    for (int i = 0; i < len; i++) {
	        if (i != groupPosition) {
	            exv.collapseGroup(i);
	        }
	    }
	}

	
	public Wine[] suggestions(Wine clickedFavorite)
	{
		List<Wine> suggestionsList = new ArrayList<Wine>();
		Wine[] wines = Wine.getAll();
		int n = wines.length;
		int counter = 0;

				for (int j = 0; j < n; ++j)
				{
					if (clickedFavorite != wines[j] && wines[j].rating == 0)
					{
						if ((clickedFavorite.category.category.compareToIgnoreCase(wines[j].category.category) == 0)
							&& (clickedFavorite.sweetOrDry.taste.compareToIgnoreCase(wines[j].sweetOrDry.taste) == 0)
						    && (clickedFavorite.varietal.varietal_name.compareToIgnoreCase(wines[j].varietal.varietal_name) == 0))
						{						             
									suggestionsList.add(wines[j]);
						             ++counter;
						}
						
						else if (counter < 4)             
						{
							if ((clickedFavorite.category.category.compareToIgnoreCase(wines[j].category.category) == 0)
						        && (clickedFavorite.sweetOrDry.taste.compareToIgnoreCase(wines[j].sweetOrDry.taste) == 0))
						    {         
								suggestionsList.add(wines[j]);
							    ++counter;
						    }					
						
							else if ((clickedFavorite.category.category.compareToIgnoreCase(wines[j].category.category) == 0))
							{
								suggestionsList.add(wines[j]);
								++counter;
							}
	
						}
						    
					}
				}
		
		Wine[] suggestedWines = new Wine[4];

		for (int i = 0; i < 4; ++i)
		{
			int pos = (int)(Math.random()*suggestionsList.size());
			suggestedWines[i] = suggestionsList.remove(pos);			
		}
		return suggestedWines;
	}
}
