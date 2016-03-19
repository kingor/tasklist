package by.telecom.tasklist.client.presenter;

import java.util.List;
import java.util.logging.Logger;

import by.telecom.tasklist.client.service.EmployeeServiceAsync;
import by.telecom.tasklist.shared.model.Employee;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class EmployeePresenter implements Presenter {

	public interface Display {
		void setEmployeeList(List<Employee> employeeData);

		Widget asWidget();
	}

	private final static Logger logger = Logger.getLogger(EmployeePresenter.class.getName());
	private List<Employee> employeeAll;
	private final EmployeeServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	@Override
	public void go(HasWidgets container) {
		bind();
		container.add(display.asWidget());
		fetchEmployeeAll();
	}

	public EmployeePresenter(EmployeeServiceAsync rpcService, HandlerManager eventBus, Display display) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = display;
	}

	private void fetchEmployeeAll() {
		rpcService.getAll(new AsyncCallback<List<Employee>>() {
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				logger.info("Async callback don`t work");
				// employeeList.addItem("onFailure");
			}

			public void onSuccess(List<Employee> employeeAll) {
				// employeeList.addItem("onSuccess");
				logger.info("Async callback is working");
				display.setEmployeeList(employeeAll);
			}
		});
	}

	private void bind() {

	}

}
