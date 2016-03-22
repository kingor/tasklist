package by.telecom.tasklist.server.other;

import java.util.Date;
import java.util.List;

import by.telecom.tasklist.shared.domain.Employee;
import by.telecom.tasklist.shared.domain.Task;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;

public class FirstPanel extends Composite implements EmployeePresenter.Display {

	private static FirstPanelUiBinder uiBinder = GWT.create(FirstPanelUiBinder.class);

	interface FirstPanelUiBinder extends UiBinder<Widget, FirstPanel> {
	}

	@UiField
	TabPanel tabPanel;

	@UiField
	ListBox employeeList;

	@UiField
	FlexTable taskTable;

	@UiField
	ListBox employeeList2;

	@UiField
	ListBox monthList;

	@UiField
	FlexTable planTable;

	public FirstPanel() {
		initWidget(uiBinder.createAndBindUi(this));
		initTable1();
		tabPanel.selectTab(0);
		// initMonthList();
		initPlanTable();
	}

	public void initTable1() {
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
			employeeList2.addItem(empl.getName());
		}
	}

	public void initPlanTable() {
		planTable.getColumnFormatter().setWidth(0, "20px");
		planTable.getColumnFormatter().setWidth(1, "300px");
		planTable.setText(0, 0, "ИД");
		planTable.setText(0, 1, "Название");
		planTable.getRowFormatter().setStyleName(0, "header");

		int day = 1;
		for (day = 1; day <= 31; day++) {
			planTable.getColumnFormatter().setWidth(day + 1, "19px");
			planTable.setText(0, day + 1, String.valueOf(day));
		}

	}

	private void stilizeCell(int row, int column) {
		planTable.getCellFormatter().setStyleName(row, column, "busyDay");
	}

	@Override
	public void setPlanList(List<Task> taskData) {
		planTable.removeAllRows();
		initPlanTable();
		int row = 1;
		for (Task task : taskData) {
			planTable.getRowFormatter().setStyleName(row, "emptyDay");
			planTable.setText(row, 0, String.valueOf(task.getId()));
			planTable.setText(row, 1, task.getName());
			Date dateBegin = task.getDateBegin();
			Date dateEnd = task.getDateEnd();
			int day = 1;
			for (day = 1; day <= 31; day++) {
				planTable.getCellFormatter().setStyleName(row, day + 1, "emptyDay");
				Date today = new Date(115, 0, day);
				if (isBusyDay(dateBegin, dateEnd, today))
					stilizeCell(row, day + 1);
			}
			row++;
		}
	}

	private boolean isBusyDay(Date begin, Date end, Date today) {
		if ((today.after(begin) || today.equals(begin)) && (today.before(end) || today.equals(end))) {
			return true;
		}
		return false;
	}

	@Override
	public void setTaskList(List<Task> taskList) {
		taskTable.removeAllRows();
		DateTimeFormat format = DateTimeFormat.getFormat("dd LLL");
		initTable1();
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
		int selectedRow = -1;
		selectedRow = employeeList.getSelectedIndex();

		return selectedRow;
	}

	@Override
	public HasChangeHandlers getEmployeeComboBox() {
		return employeeList;
	}

	@Override
	public HasChangeHandlers getEmployeeComboBox2Tab() {
		return employeeList2;
	}

	@Override
	public void setMonthList(List<String> months) {
		for (String month : months)
			monthList.addItem(month);
	}
}
