package by.telecom.tasklist.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface GetTaskEventHandler extends EventHandler {
	void onGetTask(GetTaskEvent event);
}
