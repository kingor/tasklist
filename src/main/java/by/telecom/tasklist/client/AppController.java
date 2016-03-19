package by.telecom.tasklist.client;

import by.telecom.tasklist.client.presenter.EmployeePresenter;
import by.telecom.tasklist.client.presenter.Presenter;
import by.telecom.tasklist.client.service.EmployeeServiceAsync;
import by.telecom.tasklist.client.ui.FirstPanel;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter, ValueChangeHandler<String> {

	private final HandlerManager eventBus;
	private final EmployeeServiceAsync rpcService;
	private HasWidgets container;

	public AppController(HandlerManager eventBus, EmployeeServiceAsync rpcService) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		bind();
	}

	@Override
	public void go(HasWidgets container) {
		this.container = container;
		if ("".equals(History.getToken())) {
			History.newItem("list");
		} else {
			History.fireCurrentHistoryState();
		}
	}

	private void bind() {
		History.addValueChangeHandler(this);
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();

		if (token != null) {
			Presenter presenter = null;

			if (token.equals("list")) {
				presenter = new EmployeePresenter(rpcService, eventBus, new FirstPanel());
			}

			if (presenter != null) {
				presenter.go(container);
			}
		}

	}

}
