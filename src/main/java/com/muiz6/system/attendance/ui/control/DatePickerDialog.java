package com.muiz6.system.attendance.ui.control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.layout.VBox;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class DatePickerDialog extends Dialog<Long>
		implements EventHandler<ActionEvent> {

	private final DatePicker _picker = new DatePicker();
	private final Button _btnOk = new Button("OK");

	public DatePickerDialog() {
		this.setTitle("Date Picker");

		_picker.setEditable(false);
		_btnOk.setOnAction(this);
		_btnOk.setMaxWidth(Double.MAX_VALUE);
		VBox root = new VBox();
		root.setSpacing(15);
		root.getChildren().addAll(_picker, _btnOk);
		this.getDialogPane().setContent(root);
	}

	@Override
	public void handle(ActionEvent event) {
		final LocalDate date = _picker.getValue();
		long epoch = 1577865600L; // epoch value of 1-Jan-2020
		if (date != null) {
			epoch = date.atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
		}
		// convert epoch seconds to milliseconds by multiplying it by 1000
		this.setResult(epoch * 1000);
		this.close();
	}

	public void setDate(long timestamp) {
		final LocalDate date = Instant.ofEpochMilli(timestamp)
				.atZone(ZoneId.systemDefault()).toLocalDate();
		_picker.setValue(date);
	}
}
