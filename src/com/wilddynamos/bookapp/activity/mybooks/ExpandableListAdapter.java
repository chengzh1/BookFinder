package com.wilddynamos.bookapp.activity.mybooks;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.wilddynamos.bookapp.R;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
		
	    private Context _context;
	    private List<String> _listDataHeader; // header titles
	    // child data in format of header title, child title
	    private HashMap<String, List<String>> _listDataChild;
	 
	    public ExpandableListAdapter(Context context, List<String> listDataHeader,
	            HashMap<String, List<String>> listChildData) {
	        this._context = context;
	        this._listDataHeader = listDataHeader;
	        this._listDataChild = listChildData;
	    }
	 
	    @Override
	    public Object getChild(int groupPosition, int childPosititon) {
	        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
	                .get(childPosititon);
	    }
	 
	    @Override
	    public long getChildId(int groupPosition, int childPosition) {
	        return childPosition;
	    }
	 
	    @Override
	    public View getChildView(int groupPosition, final int childPosition,
	            boolean isLastChild, View convertView, ViewGroup parent) {
	 
	    	final String childText = (String) getChild(groupPosition, childPosition);
	    	convertView = null;
//	        if (convertView == null) {
//	            LayoutInflater infalInflater = (LayoutInflater) this._context
//	                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//	            convertView = infalInflater.inflate(R.layout.list_item, null);
//	        }
	        if (childPosition == 0) {
	        	if (convertView == null) {
		            LayoutInflater infalInflater = (LayoutInflater) this._context
		                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		            convertView = infalInflater.inflate(R.layout.list_checkbox, null);
		        }
	        }
	        else {
	        	if (convertView == null) {
		            LayoutInflater infalInflater = (LayoutInflater) this._context
		                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		            convertView = infalInflater.inflate(R.layout.list_item, null);
		        }
	        	TextView txtListChild = (TextView) convertView
		                .findViewById(R.id.lblListItem);
		 
		        txtListChild.setText(childText);
	        }
	        	
	        
//        	TextView txtListChild = (TextView) convertView
//	                .findViewById(R.id.lblListItem);
//	 
//	        txtListChild.setText(childText);
	        
	        return convertView;
	    }
	 
	    @Override
	    public int getChildrenCount(int groupPosition) {
	        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
	                .size();
	    }
	 
	    @Override
	    public Object getGroup(int groupPosition) {
	        return this._listDataHeader.get(groupPosition);
	    }
	 
	    @Override
	    public int getGroupCount() {
	        return this._listDataHeader.size();
	    }
	 
	    @Override
	    public long getGroupId(int groupPosition) {
	        return groupPosition;
	    }
	 
	    @Override
	    public View getGroupView(int groupPosition, boolean isExpanded,
	            View convertView, ViewGroup parent) {
	        String headerTitle = (String) getGroup(groupPosition);
	        if (convertView == null) {
	            LayoutInflater infalInflater = (LayoutInflater) this._context
	                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            convertView = infalInflater.inflate(R.layout.list_group, null);
	        }
	 
	        TextView lblListHeader = (TextView) convertView
	                .findViewById(R.id.lblListHeader);
	        ImageView new_image = (ImageView) convertView.findViewById(R.id.new_button);
	        
	        lblListHeader.setTypeface(null, Typeface.BOLD);
	        lblListHeader.setText(headerTitle);
	        
//	        new_image.setImageResource(R.drawable.ic_action_new);
//	        if(isExpanded){
//                arrow_image.setImageResource(R.drawable.ic_action_collapse);
//            }else{
//                arrow_image.setImageResource(R.drawable.ic_action_expand);
//            }
	        switch (groupPosition) {
	        case 0:
	            new_image.setImageResource(R.drawable.ic_action_new);
	            new_image.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						newSell(arg0);
					}
	            	
	            });
	            break;
	        case 1:
	            new_image.setImageResource(R.drawable.ic_action_new);
	            new_image.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						newRent(arg0);
					}
	            	
	            });

	            break;
	        case 2:
	        	new_image.setImageResource(0);
	        	break;
	        case 3:
	        	new_image.setImageResource(0);
	        	break;
	        default:
	            break;
	        }
	 
	        return convertView;
	    }
	 
	    @Override
	    public boolean hasStableIds() {
	        return false;
	    }
	 
	    @Override
	    public boolean isChildSelectable(int groupPosition, int childPosition) {
	        return true;
	    }
	    
		/*new for sell*/
		public void newSell(View view){
			Intent intent = new Intent(_context, PostNewBookForSaleActivity.class);
			_context.startActivity(intent);
		}
		/* new for rent*/
		public void newRent(View view){
			Intent intent = new Intent(_context, PostNewBookForRentActivity.class);
			_context.startActivity(intent);
		}
}
