package by.telecom.tasklist.client.ui;

import java.util.List;

import by.telecom.tasklist.client.presenter.EmployeePresenter;
import by.telecom.tasklist.shared.model.Employee;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
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
		initMonthList();
		initPlanTable();
	}

	public void initTable1() {
		taskTable.getColumnFormatter().setWidth(0, "20px");
		taskTable.getColumnFormatter().setWidth(1, "250px");
		taskTable.setText(0, 0, "ИД");
		taskTable.setText(0, 1, "Название");
		taskTable.setText(0, 2, "Дата начала");
		taskTable.setText(0, 3, "Дата окончания");
		taskTable.setText(0, 4, "Выполнено");
		taskTable.getRowFormatter().addStyleName(0, "header");

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
			planTable.getColumnFormatter().setWidth(day + 1, "17px");
			planTable.setText(0, day + 1, String.valueOf(day));
		}

	}

}
