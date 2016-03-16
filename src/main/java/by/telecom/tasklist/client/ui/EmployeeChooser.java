package by.telecom.tasklist.client.ui;

import java.util.List;
import java.util.logging.Logger;

import by.telecom.tasklist.client.service.EmployeeService;
import by.telecom.tasklist.client.service.EmployeeServiceAsync;
import by.telecom.tasklist.shared.model.Employee;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class EmployeeChooser extends Composite {

	private final EmployeeServiceAsync employeeService = GWT.create(EmployeeService.class);

	private final static Logger logger = Logger.getLogger(MainChooser.class.getName());

	private static EmployeeChooserUiBinder uiBinder = GWT.create(EmployeeChooserUiBinder.class);

	interface EmployeeChooserUiBinder extends UiBinder<Widget, EmployeeChooser> {
	}

	@UiField
	ListBox employeeList;

	public EmployeeChooser() {
		initWidget(uiBinder.createAndBindUi(this));
		initEmployeeList();
	}

	public void initEmployeeList() {
		refreshEmplBox();
		logger.info("!!!!!!!!!!!!!!!!initEmployeeList");
	}

	private void refreshEmplBox() {
		employeeService.getAll(new AsyncCallback<List<Employee>>() {
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				logger.info("Async callback don`t work");
				// employeeList.addItem("onFailure");
			}

			public void onSuccess(List<Employee> employeeAll) {
				// employeeList.addItem("onSuccess");
				logger.info("Async callback is working");
				fillEmplBox(employeeAll);
				// logger.info(employeeAll.toString());
			}
		});
	}

	private void fillEmplBox(List<Employee> employeeAll) {
		logger.info("fillEmplBox calls");
		for (Employee empl : employeeAll)
			employeeList.addItem(empl.getName());
	}

}
