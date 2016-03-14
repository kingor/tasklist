package by.telecom.tasklist.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class TaskPanel extends Composite {

	private static TaskPanelUiBinder uiBinder = GWT.create(TaskPanelUiBinder.class);

	interface TaskPanelUiBinder extends UiBinder<Widget, TaskPanel> {
	}

	public TaskPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
