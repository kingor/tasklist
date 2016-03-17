package by.telecom.tasklist.client.ui;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import by.telecom.tasklist.client.service.TaskService;
import by.telecom.tasklist.client.service.TaskServiceAsync;
import by.telecom.tasklist.shared.model.Employee;
import by.telecom.tasklist.shared.model.Task;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

public class PlanTable extends Composite {

	private final TaskServiceAsync taskService = GWT.create(TaskService.class);

	private static final Logger logger = Logger.getLogger(PlanTable.class.getName());

	private static PlanTableUiBinder uiBinder = GWT.create(PlanTableUiBinder.class);
	@UiField
	FlexTable planTable;

	private int month;

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
		planTable.getRowFormatter().setStyleName(0, "header");

		int day = 1;
		for (day = 1; day <= 31; day++) {
			planTable.getColumnFormatter().setWidth(day + 1, "17px");
			planTable.setText(0, day + 1, String.valueOf(day));
		}

	}

	private void stilizeCell(int row, int column) {
		planTable.getCellFormatter().setStyleName(row, column, "busyDay");
	}

	public void refreshPlanTable(Employee employee) {
		taskService.getByEmployee(employee, new AsyncCallback<List<Task>>() {
			// taskService.getAll(new AsyncCallback<List<Task>>() {
			public void onFailure(Throwable caught) {
				logger.info("Async callback don`t work");
			}

			public void onSuccess(List<Task> taskList) {
				logger.info("Async callback is working");
				fillPlanTable(taskList);
			}
		});
	}

	private void fillPlanTable(List<Task> taskList) {
		logger.info("METHOD - fillPlanTable called");
		int row = 1;
		for (Task task : taskList) {
			planTable.getRowFormatter().setStyleName(row, "emptyDay");
			planTable.setText(row, 0, String.valueOf(task.getId()));
			planTable.setText(row, 1, task.getName());
			Date dateBegin = task.getDateBegin();
			Date dateEnd = task.getDateEnd();
			int day = 1;
			for (day = 1; day <= 31; day++) {
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
}
