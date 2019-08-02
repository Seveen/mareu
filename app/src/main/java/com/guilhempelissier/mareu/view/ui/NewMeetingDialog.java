package com.guilhempelissier.mareu.view.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.guilhempelissier.mareu.R;

public class NewMeetingDialog extends DialogFragment {
	NewMeetingDialogListener listener;


	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		listener = (NewMeetingDialogListener) context;
	}

	@NonNull
	@Override
	public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setTitle(R.string.add_new_meeting)
				.setPositiveButton(R.string.new_meeting_positive, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {

					}
				})
				.setNegativeButton(R.string.new_metting_negative, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {

					}
				})
				.setView(requireActivity().getLayoutInflater().inflate(R.layout.dialog_add_new_meeting, null))
				.setAdapter();

		return builder.create();
	}

	public interface NewMeetingDialogListener {
		public void onDialogPositiveClick(DialogFragment dialog);
	}
}
