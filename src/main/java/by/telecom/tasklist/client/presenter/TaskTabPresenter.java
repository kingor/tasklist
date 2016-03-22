package by.telecom.tasklist.client.presenter;

import java.util.List;

import by.telecom.tasklist.client.service.EmployeeService;
import by.telecom.tasklist.client.service.EmployeeServiceAsync;
import by.telecom.tasklist.client.service.TaskService;
import by.telecom.tasklist.client.service.TaskServiceAsync;
import by.telecom.tasklist.shared.domain.Employee;
import by.telecom.tasklist.shared.domain.Task;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class TaskTabPresenter implements Presenter {

	public interface Display {
		void setEmployeeList(List<Employee> employeeData);

		void setTaskList(List<Task> taskList);

		int getChangedRow();

		HasChangeHandlers getEmployeeComboBox();

		Widget asWidget();
	}

	private final Display display;
	private List<Employee> employeeAll;
	private EmployeeServiceAsync emplRpcService = GWT.create(EmployeeService.class);
	private TaskServiceAsync taskRpcService = GWT.create(TaskService.class);

	// private final HandlerManager eventBus;

	public TaskTabPresenter(Display display, HandlerManager eventBus) {
		this.display = display;
		// this.eventBus = eventBus;
	}

	@Override
	public void go(HasWidgets container) {
		container.add(display.asWidget());
		fetchEmployeeAll();
		bind();

	}

	private void fetchEmployeeAll() {
		emplRpcService.getAll(new AsyncCallback<List<Employee>>() {
			public void onFailure(Throwable caught) {
				// logger.info("Async callback don`t work");
			}

			public void onSuccess(List<Employee> emplAll) {
				// logger.info("Async callback is working");
				display.setEmployeeList(emplAll);
				employeeAll = emplAll;
				chooseSelectedEmployee(0);
			}
		});
	}

	private void bind() {

		display.getEmployeeComboBox().addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				chooseSelectedEmployee(display.getChangedRow());
			}
		});

	}

	private void chooseSelectedEmployee(int selectedRow) {
		if (!employeeAll.isEmpty()) {
			Employee employee = employeeAll.get(selectedRow);
			taskRpcService.getByEmployee(employee, new AsyncCallback<List<Task>>() {
				public void onFailure(Throwable caught) {
					Window.alert("Error deleting selected contacts");
				}

				@Override
				public void onSuccess(List<Task> result) {
					display.setTaskList(result);
				}
			});
		} else
			Window.alert("Нет сотрудников в базе");
	}

}
