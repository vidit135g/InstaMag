package com.tac.github.instamag.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.tac.github.instamag.MainActivity;
import com.tac.github.instamag.R;
import com.tac.github.instamag.adaptor.ImageRecyclerAdaptor;
import com.tac.github.instamag.database.DBController;

public class HistoryFragment extends Fragment implements MainActivity.FragmentRefresh {

	ImageView ivSettings;
	private FragmentActivity mContext;
	private RecyclerView rvInsta;
	private ImageView notfound;
	//DB
	private DBController dbcon;
	private ImageRecyclerAdaptor imageRecyclerAdaptor;

	public static HistoryFragment newInstance() {
		//Bundle args = new Bundle();
		//args.putString(ARG_PAGE, title);
		HistoryFragment fragment = new HistoryFragment();
		//fragment.setRetainInstance(true);
		//fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("Tag1","MoviesFrag");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_history, container, false);
		mContext =getActivity();
		//DB
		dbcon = new DBController(mContext);

		rvInsta= (RecyclerView) rootView.findViewById(R.id.rvInstaImages);
		notfound=rootView.findViewById(R.id.notfound);
		imageRecyclerAdaptor = new ImageRecyclerAdaptor(mContext);
		if(imageRecyclerAdaptor.getItemCount()==0){
			notfound.setVisibility(View.VISIBLE);
		}else{
			notfound.setVisibility(View.INVISIBLE);
		}
		rvInsta.setAdapter(imageRecyclerAdaptor);
		rvInsta.setLayoutManager(new LinearLayoutManager(mContext));

		rvInsta.setHasFixedSize(true);
		rvInsta.setItemViewCacheSize(20);
		rvInsta.setDrawingCacheEnabled(true);
		rvInsta.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
		//rvInsta.s/

		return rootView;
	}

	@Override public void refresh() {
		if (imageRecyclerAdaptor!=null) {
			imageRecyclerAdaptor.onRefreshh();
		}
	}
}