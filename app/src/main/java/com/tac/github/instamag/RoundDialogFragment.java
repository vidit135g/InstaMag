package com.tac.github.instamag;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tac.RoundedBottomSheet;


public class RoundDialogFragment extends RoundedBottomSheet {
    TextView download;
    TextView history;
    TextView share;
    TextView about;
    TextView gallery;
    private CheckRefreshClickListener mCheckHistoryListener;
    private CheckRefreshClickListener mCheckGalleryListener;
    private CheckRefreshClickListener mCheckDownloadListener;
    private CheckRefreshClickListener mCheckAboutListener;
    private CheckRefreshClickListener mCheckShareListener;

    public static RoundDialogFragment newInstance() {
        return new RoundDialogFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mCheckHistoryListener = (CheckRefreshClickListener) context;
        mCheckDownloadListener = (CheckRefreshClickListener) context;
        mCheckAboutListener = (CheckRefreshClickListener) context;
        mCheckShareListener = (CheckRefreshClickListener) context;
        mCheckGalleryListener=(CheckRefreshClickListener)context;
        CheckRefreshClickListener mCheckRefresh = (CheckRefreshClickListener) context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_round_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        download = getView().findViewById(R.id.download);
        history = getView().findViewById(R.id.history);
        about = getView().findViewById(R.id.about);
        share = getView().findViewById(R.id.share);
        gallery=getView().findViewById(R.id.gallery);
        download.setOnClickListener(v -> mCheckDownloadListener.onDownloadClick());
        history.setOnClickListener(v -> mCheckHistoryListener.onHistoryClick());
        about.setOnClickListener(v -> mCheckAboutListener.onAboutClick());
        share.setOnClickListener(v -> mCheckShareListener.onShareClick());
        gallery.setOnClickListener(v-> mCheckGalleryListener.onGalleryClick());
        super.onViewCreated(view, savedInstanceState);

    }
}


interface CheckRefreshClickListener {
    void onHistoryClick();
    void onDownloadClick();
    void onShareClick();
    void onGalleryClick();
    void onAboutClick();
}
