package by.telecom.tasklist.client.ui;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class MainChooser extends Composite {

	private static MainChooserUiBinder uiBinder = GWT.create(MainChooserUiBinder.class);
	@UiField
	ListBox monthList;
	@UiField
	ListBox employeeList;

	// TaskItems taskList;

	interface MainChooserUiBinder extends UiBinder<Widget, MainChooser> {
	}

	public MainChooser() {
		initWidget(uiBinder.createAndBindUi(this));
		// taskList = new TaskItems();
		// initMonthList();
		// initEmployeeList();
	}

	public void initMonthList() {
		monthList.addItem("Январь");
		monthList.addItem("Февраль");
		monthList.addItem("Март");
		monthList.addItem("Апрель");
		monthList.addItem("Май");
		monthList.addItem("Июнь");
		monthList.addItem("Июль");
		monthList.addItem("Август");
		monthList.addItem("Сентябрь");
		monthList.addItem("Октябрь");
		monthList.addItem("Ноябрь");
		monthList.addItem("Декабрь");
	}

	public int getMonth() {
		return monthList.getSelectedIndex() + 1;
	}

	// public void initEmployeeList() {
	//
	// for (Employee empl : taskList.getEmployeeList()) {
	// employeeList.addItem(empl.getName());
	// }
	// }
	//
	// public void initTaskList() {
	//
	// for (Task task : taskList.getTaskList()) {
	// ;
	// }
	// }
	//
	// @UiHandler("employeeList")
	// void onEmployeeListChange(ChangeEvent event) {
	//
	// for (Employee empl : taskList.getEmployeeList()) {
	// employeeList.addItem(empl.getName());
	// }
	// }

	public boolean isDayInPeriod(int day, Date begin, Date end) {
		Date thisDay = new Date(2015, 1, day);
		if (thisDay.after(begin) && thisDay.before(end))
			return true;
		return false;
	}
}
