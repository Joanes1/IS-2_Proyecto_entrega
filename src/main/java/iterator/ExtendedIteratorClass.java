package iterator;

import java.util.Vector;
import domain.Event;

public class ExtendedIteratorClass implements ExtendedIterator<Event> {
	Vector<Event> eventos;
	int unekoa = 0;
	
	public ExtendedIteratorClass(Vector<Event> eVector) {
		this.eventos = eVector;
	}

	@Override
	public boolean hasNext() {
		if(unekoa<eventos.size()) {
			return true;
		}
		return false;
	}
	
	//return	the	actual	element	and	go	to	the	next
	@Override
	public Event next() {
		unekoa++;
		return eventos.get(unekoa-1);
	}

	//return	the	actual	element	and	go	to	the	previous
	@Override
	public Event previous() {
		unekoa--;
		return eventos.get(unekoa+1);
	}

	@Override
	public boolean hasPrevious() {
		if(unekoa==0) {
			return false;
		}
		return true;
	}

	@Override
	public void goFirst() {
		unekoa = 0;
		
	}

	@Override
	public void goLast() {
		unekoa = eventos.size();
		
	}
	
	@Override
	public boolean isEmpty() {
		if(eventos.size()==0) {
			return true;
		}
		return false;
	}
	

	
}
