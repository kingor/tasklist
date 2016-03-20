package by.telecom.tasklist.client;

import by.telecom.tasklist.client.presenter.EmployeePresenter;
import by.telecom.tasklist.client.presenter.Presenter;
import by.telecom.tasklist.client.ui.FirstPanel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Tasklist implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// final Label errorLabel = new Label();
		// HandlerManager eventBus = new HandlerManager(null);
		// AppController appViewer = new AppController(eventBus);
		// appViewer.go(RootPanel.get("task"));
		Presenter presenter = new EmployeePresenter(new HandlerManager(null), new FirstPanel());
		presenter.go(RootPanel.get("task"));
	}
}
