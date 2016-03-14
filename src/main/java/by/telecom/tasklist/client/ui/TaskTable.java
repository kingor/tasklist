package by.telecom.tasklist.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

public class TaskTable extends Composite {

	private static TaskTableUiBinder uiBinder = GWT.create(TaskTableUiBinder.class);
	@UiField
	FlexTable taskTable;

	interface TaskTableUiBinder extends UiBinder<Widget, TaskTable> {
	}

	public TaskTable() {
		initWidget(uiBinder.createAndBindUi(this));
		initTable();
	}

	public void initTable() {
		taskTable.getColumnFormatter().setWidth(0, "20px");
		taskTable.getColumnFormatter().setWidth(1, "250px");
		// taskTable.getColumnFormatter().setWidth(2, "50px");
		// taskTable.getColumnFormatter().setWidth(3, "50px");
		// taskTable.getColumnFormatter().setWidth(4, "50px");
		taskTable.setText(0, 0, "ИД");
		taskTable.setText(0, 1, "Название");
		taskTable.setText(0, 2, "Дата начала");
		taskTable.setText(0, 3, "Дата окончания");
		taskTable.setText(0, 4, "Выполнено");
		taskTable.getRowFormatter().addStyleName(0, "header");

	}

}
