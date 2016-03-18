package by.telecom.tasklist.client.ui;

import java.util.List;
import java.util.logging.Logger;

import by.telecom.tasklist.client.service.EmployeeService;
import by.telecom.tasklist.client.service.EmployeeServiceAsync;
import by.telecom.tasklist.shared.model.Employee;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class MainChooser extends Composite {

	private final EmployeeServiceAsync employeeService = GWT.create(EmployeeService.class);

	private final static Logger logger = Logger.getLogger(MainChooser.class.getName());

	private static MainChooserUiBinder uiBinder = GWT.create(MainChooserUiBinder.class);
	@UiField
	ListBox monthList;
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

	interface MainChooserUiBinder extends UiBinder<Widget, MainChooser> {
	}

	public MainChooser() {
		initWidget(uiBinder.createAndBindUi(this));
		// taskList = new TaskItems();
		initMonthList();
		initEmployeeList();
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

	public void initEmployeeList() {
		refreshEmplBox();
		logger.info("!!!!!!!!!!!!!!!!initEmployeeList");
	}

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
	//
	// public boolean isDayInPeriod(int day, Date begin, Date end) {
	// Date thisDay = new Date(2015, 1, day);
	// if (thisDay.after(begin) && thisDay.before(end))
	// return true;
	// return false;
	// }

	private void refreshEmplBox() {
		employeeService.getAll(new AsyncCallback<List<Employee>>() {
			public void onFailure(Throwable caught) {
				logger.info("Async callback don`t work");
			}

			public void onSuccess(List<Employee> employeeAll) {
				// employeeList.addItem("onSuccess");
				logger.info("Async callback is working");
				fillEmplBox(employeeAll);
				employeeListData = employeeAll;
				// logger.info(employeeAll.toString());
			}
		});
	}

	private void fillEmplBox(List<Employee> employeeAll) {
		logger.info("fillEmplBox calls");
		for (Employee empl : employeeAll)
			employeeList.addItem(empl.getName());
	}

	@UiHandler("employeeList")
	void onChange(ClickEvent event) {
		// Select the row that was clicked (-1 to account for header row).
		int index = employeeList.getSelectedIndex();
		// if (index != -1) {
		// index = 1;
		Employee employee = employeeListData.get(index);
		if (listener != null) {
			listener.onItemSelected(employee);
		}
		// }
	}

}
