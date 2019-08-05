package com.guilhempelissier.mareu.view.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.guilhempelissier.mareu.R;

import java.util.ArrayList;
import java.util.List;

public class NewMeetingDialog extends DialogFragment {
	private NewMeetingDialogListener listener;

	private EditText participantEditText;
	private EditText topicEditText;
	private EditText placeEditText;
	private ChipGroup entryChipGroup;

	List<String> participants = new ArrayList<>();

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		listener = (NewMeetingDialogListener) context;
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		entryChipGroup = view.findViewById(R.id.newMeetingChipGroup);
		participantEditText = view.findViewById(R.id.newMeetingParticipantEditText);
		topicEditText = view.findViewById(R.id.newMeetingTopicEditText);
		placeEditText = view.findViewById(R.id.newMeetingPlaceEditText);
		participantEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
				boolean handled = false;
				if (actionId == EditorInfo.IME_ACTION_GO) {
					handled = true;
					String text = textView.getText().toString();
					entryChipGroup.addView(createChip(entryChipGroup, text));
					participants.add(text);
				}
				return handled;
			}
		});
	}

	@NonNull
	@Override
	public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setTitle(R.string.add_new_meeting)
				.setPositiveButton(R.string.new_meeting_positive, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						String topic = topicEditText.getText().toString();
						String place = placeEditText.getText().toString();

						listener.onDialogPositiveClick(topic, place, 0,participants);
					}
				})
				.setNegativeButton(R.string.new_metting_negative, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						NewMeetingDialog.this.getDialog().cancel();
					}
				})
				.setView(requireActivity().getLayoutInflater().inflate(R.layout.dialog_add_new_meeting, null));

		return builder.create();
	}

	private Chip createChip(final ChipGroup entryChipGroup, final String text) {
		final Chip chip = new Chip(requireContext());
		chip.setText(text);
		chip.setChipIcon(getContext().getDrawable(R.drawable.ic_cancel_gray_24dp));
		chip.setOnCloseIconClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				entryChipGroup.removeView(chip);
				participants.remove(text);
			}
		});
		return chip;
	}

	public interface NewMeetingDialogListener {
		public void onDialogPositiveClick(String topic, String place, int time, List<String> participants);
	}
}
