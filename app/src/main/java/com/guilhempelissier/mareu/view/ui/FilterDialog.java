package com.guilhempelissier.mareu.view.ui;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.guilhempelissier.mareu.R;
import com.guilhempelissier.mareu.viewmodel.MeetingListViewModel;
import com.hootsuite.nachos.NachoTextView;
import com.hootsuite.nachos.terminator.ChipTerminatorHandler;
import com.hootsuite.nachos.validator.ChipifyingNachoValidator;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FilterDialog extends DialogFragment {
	private FilterDialogListener listener;

	private Button pickStartButton;
	private Button pickStopButton;
	private Button clearStartButton;
	private Button clearStopButton;
	private NachoTextView chipsTextView;

	private DateFormat df = DateFormat.getDateTimeInstance();
	private Calendar startCalendar = Calendar.getInstance();
	private Calendar stopCalendar = Calendar.getInstance();

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		listener = (FilterDialogListener) context;
	}

	@NonNull
	@Override
	public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		final View view = requireActivity().getLayoutInflater().inflate(R.layout.dialog_filter_meetings, null);

		startCalendar.setTimeInMillis(0);
		stopCalendar.setTimeInMillis(0);

		pickStartButton = view.findViewById(R.id.filterMeetingPickStartDateButton);
		pickStopButton = view.findViewById(R.id.filterMeetingPickStopDateButton);
		clearStartButton = view.findViewById(R.id.filterMeetingClearStartDateButton);
		clearStopButton = view.findViewById(R.id.filterMeetingClearStopDateButton);
		chipsTextView = view.findViewById(R.id.filterMeetingChipsTextView);

		pickStartButton.setText("None");
		pickStopButton.setText("None");

		chipsTextView.addChipTerminator('\n', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_ALL);
		chipsTextView.enableEditChipOnTouch(true, true);
		chipsTextView.setNachoValidator(new ChipifyingNachoValidator());

		pickStartButton.setOnClickListener(view1 -> {
			startCalendar.setTime(new Date());
			DatePickerDialog dateDialog = new DatePickerDialog(requireContext(),
					(datePicker, year, month, dayOfMonth) -> {
						startCalendar.set(year, month, dayOfMonth);
						TimePickerDialog  timeDialog = new TimePickerDialog(requireContext(),
								(timePicker, hourOfDay, minute) -> {
									startCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
									startCalendar.set(Calendar.MINUTE, minute);
									pickStartButton.setText(
											df.format(new Date(startCalendar.getTimeInMillis()))
									);
								},
								startCalendar.get(Calendar.HOUR_OF_DAY),
								startCalendar.get(Calendar.MINUTE),
								true
						);
						timeDialog.show();
					},
					startCalendar.get(Calendar.YEAR),
					startCalendar.get(Calendar.MONTH),
					startCalendar.get(Calendar.DAY_OF_MONTH)
			);
			dateDialog.show();
		});

		pickStopButton.setOnClickListener(view1 -> {
			stopCalendar.setTime(new Date());
			DatePickerDialog dateDialog = new DatePickerDialog(requireContext(),
					(datePicker, year, month, dayOfMonth) -> {
						stopCalendar.set(year, month, dayOfMonth);
						TimePickerDialog  timeDialog = new TimePickerDialog(requireContext(),
								(timePicker, hourOfDay, minute) -> {
									stopCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
									stopCalendar.set(Calendar.MINUTE, minute);
									pickStopButton.setText(
											df.format(new Date(stopCalendar.getTimeInMillis()))
									);
								},
								stopCalendar.get(Calendar.HOUR_OF_DAY),
								stopCalendar.get(Calendar.MINUTE),
								true
						);
						timeDialog.show();
					},
					stopCalendar.get(Calendar.YEAR),
					stopCalendar.get(Calendar.MONTH),
					stopCalendar.get(Calendar.DAY_OF_MONTH)
			);
			dateDialog.show();
		});

		clearStartButton.setOnClickListener(view1 -> {
			startCalendar.setTimeInMillis(0);
			pickStartButton.setText("None");
		});

		clearStopButton.setOnClickListener(view1 -> {
			stopCalendar.setTimeInMillis(0);
			pickStopButton.setText("None");
		});

		builder.setTitle(R.string.filter_title)
				.setPositiveButton(R.string.filter_positive_button, (dialogInterface, i) -> {
					listener.onDialogPositiveClick(
							startCalendar.getTimeInMillis(),
							stopCalendar.getTimeInMillis(),
							chipsTextView.getChipValues()
					);
				})
				.setNegativeButton(R.string.filter_negative_button, (dialogInterface, i) -> FilterDialog.this.getDialog().cancel())
				.setView(view);

		return builder.create();
	}

	public interface FilterDialogListener {
		void onDialogPositiveClick(long startDateFilter, long stopDateFilter, List<String> allowedPlacesFilter);
	}
}
