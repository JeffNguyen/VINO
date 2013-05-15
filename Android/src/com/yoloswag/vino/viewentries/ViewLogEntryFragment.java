package com.yoloswag.vino.viewentries;

import com.yoloswag.vino.R;
import com.yoloswag.vino.R.id;
import com.yoloswag.vino.R.layout;
import com.yoloswag.vino.model.Entry;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.app.ListActivity;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class ViewLogEntryFragment extends Fragment 
{
	Entry[] entries;
	
	public ViewLogEntryFragment()
	{	
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
	 {
	   super.onCreate(savedInstanceState);
	   entries = Entry.getAll();
     }
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) 
    {
        View rootView = inflater.inflate(R.layout.fragment_view_entry, container, false);
        
        //creates seperate view if there no entries
        //rootView1 is the view that would be displayed incase if there are no entries
        if(entries.length == 0)
        {
        	 View rootView1 = inflater.inflate(R.layout.fragment_view_no_entry, container, false);
        	 return rootView1;
        }
        
 	   ExpandableListView list;
 	   list = (ExpandableListView) rootView.findViewById(R.id.viewLogEx);
 	   list.setAdapter(new ViewLogEntryAdapter(this.getActivity(), entries));
        
        return rootView;
        
      
    }
    
    
}
