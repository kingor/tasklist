package by.telecom.tasklist.client;

import by.telecom.tasklist.client.ui.TaskPanel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Tasklist implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while " + "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Label errorLabel = new Label();

		// Add the taskPanel to the RootPanel
		// Use RootPanel.get() to get the entire body element
		TaskPanel taskPanel = new TaskPanel();
		RootPanel.get("task").add(taskPanel);

	}
}
