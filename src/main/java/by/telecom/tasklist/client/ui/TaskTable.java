package by.telecom.tasklist.client.ui;

import java.util.List;
import java.util.logging.Logger;

import by.telecom.tasklist.client.service.TaskService;
import by.telecom.tasklist.client.service.TaskServiceAsync;
import by.telecom.tasklist.shared.model.Task;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

public class TaskTable extends Composite {

	private final TaskServiceAsync taskService = GWT.create(TaskService.class);

	private Logger logger = Logger.getLogger(TaskTable.class.getName());
	private static TaskTableUiBinder uiBinder = GWT.create(TaskTableUiBinder.class);
	@UiField
	FlexTable taskTable;

	interface TaskTableUiBinder extends UiBinder<Widget, TaskTable> {
	}

	public TaskTable() {
		initWidget(uiBinder.createAndBindUi(this));
		initTable();
		refreshPlanTable();
	}

	public void initTable() {
		taskTable.getColumnFormatter().setWidth(0, "20px");
		taskTable.getColumnFormatter().setWidth(1, "250px");
		taskTable.setText(0, 0, "ИД");
		taskTable.setText(0, 1, "Название");
		taskTable.setText(0, 2, "Дата начала");
		taskTable.setText(0, 3, "Дата окончания");
		taskTable.setText(0, 4, "Выполнено");
		taskTable.getRowFormatter().addStyleName(0, "header");

	}

	public void refreshPlanTable(/* Employee employee */) {
		// logger.info("EMPLOYEE - " + employee.getName());
		taskService.getAll(new AsyncCallback<List<Task>>() {
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
		taskTable.removeAllRows();
		DateTimeFormat format = DateTimeFormat.getFormat("yyyy-MM-dd");
		initTable();
		int row = 1;
		for (Task task : taskList) {
			taskTable.setText(row, 0, String.valueOf(task.getId()));
			taskTable.setText(row, 1, task.getName());

			taskTable.setText(row, 2, format.format(task.getDateBegin()));
			taskTable.setText(row, 3, format.format(task.getDateEnd()));
			CheckBox cb = new CheckBox();
			cb.setValue(task.getComplited() > 0);
			cb.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
				}
			});
			taskTable.setWidget(row, 4, cb);
			// Date dateBegin = task.getDateBegin();
			// Date dateEnd = task.getDateEnd();
			row++;
		}
	}
}
