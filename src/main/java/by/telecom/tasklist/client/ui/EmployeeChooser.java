package by.telecom.tasklist.client.ui;

import java.util.List;
import java.util.logging.Logger;

import by.telecom.tasklist.client.service.EmployeeService;
import by.telecom.tasklist.client.service.EmployeeServiceAsync;
import by.telecom.tasklist.shared.model.Employee;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
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

	List<Employee> employeeListData;

	private Listener listener;

	public interface Listener {
		void onItemSelected(Employee employee);
	}

	public void setListener(Listener listener) {
		this.listener = listener;
	}

	public EmployeeChooser() {
		initWidget(uiBinder.createAndBindUi(this));
		initEmployeeList();
	}

	public void initEmployeeList() {
		refreshEmplBox();
		logger.info("!!!!!!!!!!!!!!!!initEmployeeList");
	}

	public void setData(List<Employee> data) {
		fillEmplBox(data);
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
				employeeListData = employeeAll;
			}
		});
	}

	private void fillEmplBox(List<Employee> employeeAll) {
		logger.info("fillEmplBox calls");
		for (Employee empl : employeeAll)
			employeeList.addItem(empl.getName());
	}

	@UiHandler("employeeList")
	void onChange(ChangeEvent event) {
		selectEmployee();
	}

	private void selectEmployee() {
		// Select the row that was clicked (-1 to account for header row).
		int index = employeeList.getSelectedIndex();
		Employee employee = employeeListData.get(index);
		if (listener != null) {
			listener.onItemSelected(employee);
		}
	}

}
