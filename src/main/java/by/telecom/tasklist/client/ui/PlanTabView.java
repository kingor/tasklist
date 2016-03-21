package by.telecom.tasklist.client.ui;

import java.util.Date;
import java.util.List;

import by.telecom.tasklist.client.presenter.PlanTabPresenter;
import by.telecom.tasklist.shared.model.Employee;
import by.telecom.tasklist.shared.model.Task;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class PlanTabView extends Composite implements PlanTabPresenter.Display {

	private static PlanTabViewUiBinder uiBinder = GWT.create(PlanTabViewUiBinder.class);
	@UiField
	ListBox employeeList;

	@UiField
	ListBox monthList;

	@UiField
	FlexTable planTable;

	interface PlanTabViewUiBinder extends UiBinder<Widget, PlanTabView> {
	}

	public PlanTabView() {
		initWidget(uiBinder.createAndBindUi(this));
		initPlanTable();
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

	@Override
	public void setEmployeeList(List<Employee> employeeData) {
		for (Employee empl : employeeData) {
			employeeList.addItem(empl.getName());
		}
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
				Date today = new Date(115, getMonthRow(), day);
				if (isBusyDay(dateBegin, dateEnd, today))
					stilizeCell(row, day + 1);
			}
			row++;
		}
	}

	@Override
	public void setMonthList(List<String> months) {
		for (String month : months)
			monthList.addItem(month);

	}

	@Override
	public HasChangeHandlers getEmployeeComboBox() {
		return employeeList;
	}

	@Override
	public int getChangedRow() {
		int selectedRow = -1;
		selectedRow = employeeList.getSelectedIndex();

		return selectedRow;
	}

	private void stilizeCell(int row, int column) {
		planTable.getCellFormatter().setStyleName(row, column, "busyDay");
	}

	private boolean isBusyDay(Date begin, Date end, Date today) {
		if ((today.after(begin) || today.equals(begin)) && (today.before(end) || today.equals(end))) {
			return true;
		}
		return false;
	}

	@Override
	public int getMonthRow() {
		int selectedMonth = 0;
		selectedMonth = monthList.getSelectedIndex();

		return selectedMonth;
	}

}
