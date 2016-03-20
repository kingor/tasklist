package by.telecom.tasklist.client.presenter;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import by.telecom.tasklist.client.service.EmployeeService;
import by.telecom.tasklist.client.service.EmployeeServiceAsync;
import by.telecom.tasklist.client.service.TaskService;
import by.telecom.tasklist.client.service.TaskServiceAsync;
import by.telecom.tasklist.shared.model.Employee;
import by.telecom.tasklist.shared.model.Task;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class EmployeePresenter implements Presenter {

	public interface Display {
		void setEmployeeList(List<Employee> employeeData);

		void setPlanList(List<Task> taskData);

		void setTaskList(List<Task> taskList);

		void setMonthList(List<String> monthList);

		int getChangedRow();

		HasChangeHandlers getEmployeeComboBox();

		Widget asWidget();
	}

	private static final String[] MONTHS = { "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Ноябрь", "Декабрь" };

	private final static Logger logger = Logger.getLogger(EmployeePresenter.class.getName());
	private List<Employee> employeeAll;
	private EmployeeServiceAsync emplRpcService = GWT.create(EmployeeService.class);
	private TaskServiceAsync taskRpcService = GWT.create(TaskService.class);;
	// private final HandlerManager eventBus;
	private final Display display;

	@Override
	public void go(HasWidgets container) {
		container.add(display.asWidget());
		fetchEmployeeAll(emplRpcService);
		refreshPlanTable(taskRpcService);
		// chooseSelectedEmployee(taskRpcService);
		setMonthOfYear();
		bind();
	}

	public EmployeePresenter(HandlerManager eventBus, Display display) {
		// this.eventBus = eventBus;
		this.display = display;
	}

	private void fetchEmployeeAll(EmployeeServiceAsync rpcService) {
		rpcService.getAll(new AsyncCallback<List<Employee>>() {
			public void onFailure(Throwable caught) {
				logger.info("Async callback don`t work");
			}

			public void onSuccess(List<Employee> emplAll) {
				logger.info("Async callback is working");
				display.setEmployeeList(emplAll);
				employeeAll = emplAll;
			}
		});
	}

	public void refreshTaskTable(TaskServiceAsync rpcService/* , Employee employee */) {
		rpcService.getAll(new AsyncCallback<List<Task>>() {
			public void onFailure(Throwable caught) {
				logger.info("Async callback don`t work");
			}

			public void onSuccess(List<Task> taskList) {
				logger.info("Async callback is working");
				display.setTaskList(taskList);
			}
		});
	}

	public void refreshPlanTable(TaskServiceAsync rpcService/* , Employee employee */) {
		rpcService.getAll(new AsyncCallback<List<Task>>() {
			public void onFailure(Throwable caught) {
				logger.info("Async callback don`t work");
			}

			public void onSuccess(List<Task> taskList) {
				logger.info("Async callback is working");
				display.setPlanList(taskList);
			}
		});
	}

	private void bind() {
		display.getEmployeeComboBox().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				chooseSelectedEmployee(taskRpcService);
			}
		});
	}

	private void chooseSelectedEmployee(TaskServiceAsync rpcService) {
		int selectedRow = display.getChangedRow();
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
	}

	private void setMonthOfYear() {
		display.setMonthList(Arrays.asList(MONTHS));
	}
}
