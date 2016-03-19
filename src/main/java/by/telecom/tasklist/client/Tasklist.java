package by.telecom.tasklist.client;

import by.telecom.tasklist.client.service.EmployeeService;
import by.telecom.tasklist.client.service.EmployeeServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Tasklist implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Label errorLabel = new Label();

		// Add the taskPanel to the RootPanel
		// Use RootPanel.get() to get the entire body element
		// TaskPanel taskPanel = new TaskPanel();
		// RootPanel.get("task").add(taskPanel);
		EmployeeServiceAsync rpcService = GWT.create(EmployeeService.class);
		HandlerManager eventBus = new HandlerManager(null);
		AppController appViewer = new AppController(eventBus, rpcService);
		appViewer.go(RootPanel.get("task"));
	}
}
