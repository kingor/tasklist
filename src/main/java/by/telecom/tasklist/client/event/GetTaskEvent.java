package by.telecom.tasklist.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class GetTaskEvent extends GwtEvent<GetTaskEventHandler> {
	public static Type<GetTaskEventHandler> TYPE = new Type<GetTaskEventHandler>();

	@Override
	public Type<GetTaskEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(GetTaskEventHandler handler) {
		handler.onGetTask(this);
	}
}
