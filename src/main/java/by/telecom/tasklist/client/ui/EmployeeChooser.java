package by.telecom.tasklist.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class EmployeeChooser extends Composite {

	private static EmployeeChooserUiBinder uiBinder = GWT.create(EmployeeChooserUiBinder.class);

	interface EmployeeChooserUiBinder extends UiBinder<Widget, EmployeeChooser> {
	}

	public EmployeeChooser() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
