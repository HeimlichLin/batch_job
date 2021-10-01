package batchJob.common.queue;

import java.util.ArrayList;
import java.util.List;

public class AbstractSubject implements Subject {
	
	private List<Observer> observers = new ArrayList<Observer>();
	
	@Override
	public void registered(final Observer ob) {
		this.observers.add(ob);

	}

	@Override
	public void unRegistered(final Observer ob) {
		this.observers.remove(ob);

	}

	@Override
	public void changeValue() {
		for (final Observer observer : this.observers) {
			observer.update(this);
		}

	}


}
