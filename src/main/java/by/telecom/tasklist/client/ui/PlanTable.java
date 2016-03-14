package by.telecom.tasklist.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

public class PlanTable extends Composite {

	private static PlanTableUiBinder uiBinder = GWT.create(PlanTableUiBinder.class);
	@UiField
	FlexTable planTable;

	interface PlanTableUiBinder extends UiBinder<Widget, PlanTable> {
	}

	public PlanTable() {
		initWidget(uiBinder.createAndBindUi(this));
		initTable();
	}

	public void initTable() {
		planTable.getColumnFormatter().setWidth(0, "20px");
		planTable.getColumnFormatter().setWidth(1, "300px");
		planTable.setText(0, 0, "ИД");
		planTable.setText(0, 1, "Название");
		planTable.getRowFormatter().addStyleName(0, "header");

		int day = 1;
		for (day = 1; day <= 31; day++) {
			planTable.getColumnFormatter().setWidth(day + 1, "17px");
			planTable.setText(0, day + 1, String.valueOf(day));
		}
	}
}
