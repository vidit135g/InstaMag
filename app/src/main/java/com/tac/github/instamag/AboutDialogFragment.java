package com.tac.github.instamag;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Button;

import com.tac.github.instamag.R;

public class AboutDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setView(R.layout.about_layout)
                .create();

        alertDialog.show();

        Button btn=alertDialog.findViewById(R.id.pixelbutton);
        btn.setOnClickListener(v -> alertDialog.dismiss());
        return alertDialog;
    }
}
