package com.yoloswag.vino.viewentries;

import java.util.ArrayList;

import com.yoloswag.vino.model.Entry;
import com.yoloswag.vino.warning.TouchThisAndIWillFuckingKillYou;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

@TouchThisAndIWillFuckingKillYou
public class DiaryAdapter extends ArrayAdapter<Entry> {

	public DiaryAdapter(Context context, int resource, ArrayList<Entry> lst) {
		super(context, resource, lst);
	}
	
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DiaryItemView diaryItemView;
        if (convertView == null) {
        	diaryItemView = DiaryItemView.build(getContext());
        } else {
        	diaryItemView = (DiaryItemView)convertView;
        }
        
        diaryItemView.bind(getItem(position), parent);

        return diaryItemView;
    }
}
