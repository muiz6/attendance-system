package com.muiz6.system.attendance.ui.control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.layout.VBox;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DatePickerDialog extends Dialog<Long>
		implements EventHandler<ActionEvent> {

	public static final long CANCELLED = -1;
	private final DatePicker _picker = new DatePicker();
	private final Button _btnOk = new Button("OK");
	private final Button _btnCancel = new Button("Cancel");

	public DatePickerDialog() {
		this.setTitle("Date Picker");

		_picker.setEditable(false);
		_btnOk.setOnAction(this);
		_btnOk.setMaxWidth(Double.MAX_VALUE);
		_btnCancel.setOnAction(this);
		_btnCancel.setMaxWidth(Double.MAX_VALUE);
		VBox root = new VBox();
		root.setSpacing(15);
		root.getChildren().addAll(_picker, _btnOk, _btnCancel);
		this.getDialogPane().setContent(root);
	}

	@Override
	public void handle(ActionEvent event) {
		Object source = event.getSource();
		if (source == _btnOk) {
			final LocalDate date = _picker.getValue();
			long epoch = 1577865600L; // epoch value of 1-Jan-2020
			if (date != null) {
				epoch = date.atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
			}
			// convert epoch seconds to milliseconds by multiplying it by 1000
			this.setResult(epoch * 1000);
			this.close();
		}
		else if (source == _btnCancel) {
			this.setResult(CANCELLED);
			this.close();
		}
	}

	public static String getDateString(long timestamp) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = new Date(timestamp);
		return format.format(date);
	}

	public void setDate(long timestamp) {
		final LocalDate date = Instant.ofEpochMilli(timestamp)
				.atZone(ZoneId.systemDefault()).toLocalDate();
		_picker.setValue(date);
	}
}
