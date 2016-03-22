package by.telecom.tasklist.client;

import by.telecom.tasklist.client.presenter.PlanTabPresenter;
import by.telecom.tasklist.client.presenter.Presenter;
import by.telecom.tasklist.client.presenter.TaskTabPresenter;
import by.telecom.tasklist.client.ui.PlanTabView;
import by.telecom.tasklist.client.ui.TaskTabView;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Tasklist implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		HandlerManager eventBus = new HandlerManager(null);
		TaskTabView taskView = new TaskTabView();
		PlanTabView planView = new PlanTabView();
		Presenter taskPresenter = new TaskTabPresenter(taskView, eventBus);
		Presenter planPresenter = new PlanTabPresenter(planView, eventBus);

		TabPanel tabPanel = new TabPanel();

		VerticalPanel t1 = new VerticalPanel();
		VerticalPanel t2 = new VerticalPanel();
		tabPanel.add(t1, "Задачи");
		tabPanel.add(t2, "План");
		tabPanel.selectTab(0);
		taskPresenter.go(t1);
		planPresenter.go(t2);
		RootPanel.get("task").add(tabPanel);
	}
}
