package by.telecom.tasklist.server.other;

import by.telecom.tasklist.shared.domain.Employee;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;

public class TaskPanel extends Composite {

	private static TaskPanelUiBinder uiBinder = GWT.create(TaskPanelUiBinder.class);
	@UiField
	MainChooser mainChooser;
	// @UiField
	// EmployeeChooser employeeChooser;
	@UiField
	PlanTable planTable;
	// @UiField
	// TaskTable taskTable;
	@UiField
	TabPanel tabPanel;

	interface TaskPanelUiBinder extends UiBinder<Widget, TaskPanel> {
	}

	public TaskPanel() {
		initWidget(uiBinder.createAndBindUi(this));
		init();
		mainChooser.setListener(new MainChooser.Listener() {
			public void onItemSelected(Employee employee) {
				planTable.refreshPlanTable(employee);
			}
		});

		// employeeChooser.setListener(new EmployeeChooser.Listener() {
		// public void onItemSelected(Employee employee) {
		// taskTable.refreshTaskTable(employee);
		// }
		// });
	}

	public void init() {
		tabPanel.selectTab(0);
	}

}
