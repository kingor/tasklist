package by.telecom.tasklist.client.ui;

import java.util.List;

import by.telecom.tasklist.client.presenter.TaskTabPresenter;
import by.telecom.tasklist.shared.domain.Employee;
import by.telecom.tasklist.shared.domain.Task;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class TaskTabView extends Composite implements TaskTabPresenter.Display {

	private static TaskTabViewUiBinder uiBinder = GWT.create(TaskTabViewUiBinder.class);
	@UiField
	ListBox employeeList;

	@UiField
	FlexTable taskTable;

	interface TaskTabViewUiBinder extends UiBinder<Widget, TaskTabView> {
	}

	public TaskTabView() {
		initWidget(uiBinder.createAndBindUi(this));
		initTaskTable();
	}

	public void initTaskTable() {
		taskTable.getColumnFormatter().setWidth(0, "20px");
		taskTable.getColumnFormatter().setWidth(1, "300px");
		taskTable.setText(0, 0, "ИД");
		taskTable.setText(0, 1, "Название");
		taskTable.setText(0, 2, "Дата начала");
		taskTable.setText(0, 3, "Дата окончания");
		taskTable.setText(0, 4, "Выполнено");
		taskTable.getRowFormatter().addStyleName(0, "header");

	}

	@Override
	public void setEmployeeList(List<Employee> employeeData) {
		for (Employee empl : employeeData) {
			employeeList.addItem(empl.getName());
		}
	}

	@Override
	public void setTaskList(List<Task> taskList) {
		taskTable.removeAllRows();
		DateTimeFormat format = DateTimeFormat.getFormat("dd LLL");
		initTaskTable();
		int row = 1;
		for (Task task : taskList) {
			taskTable.setText(row, 0, String.valueOf(task.getId()));
			taskTable.setText(row, 1, task.getName());

			taskTable.setText(row, 2, format.format(task.getDateBegin()).toLowerCase());
			taskTable.setText(row, 3, format.format(task.getDateEnd()).toLowerCase());
			CheckBox cb = new CheckBox();
			cb.setValue(task.getComplited() > 0);
			taskTable.setWidget(row, 4, cb);
			row++;
		}
	}

	@Override
	public int getChangedRow() {
		int selectedRow = 0;
		selectedRow = employeeList.getSelectedIndex();
		Window.alert(String.valueOf(selectedRow));
		return selectedRow;
	}

	@Override
	public HasChangeHandlers getEmployeeComboBox() {
		return employeeList;
	}

}
