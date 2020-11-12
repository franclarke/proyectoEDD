package TDADeque;

import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.Lista_doble_enlazada;
import TDALista.PositionList;

public class DequeConLista<E> implements Deque<E> {

	private PositionList<E> deque;
	
	public DequeConLista() {
		deque = new Lista_doble_enlazada<E>();
	}
	
	@Override
	public int size() {
		return deque.size();
	}
	
	@Override
	public boolean isEmpty() {
		return (size()==0);
	}
	
	@Override
    public void addFirst(E item){
        deque.addFirst(item);
    }
	
	@Override
    public void addLast(E item){
        deque.addLast(item);
    }
	
	@Override
    public void removeFirst() throws EmptyDequeException{
        if(deque.isEmpty()){
        	throw new EmptyDequeException("La deque esta vacia");
        }
        try {
			deque.remove(deque.first());
		} catch (InvalidPositionException | EmptyListException e) {
			e.printStackTrace();
		}
    }
	
	@Override
    public void removeLast() throws EmptyDequeException{
        if(deque.isEmpty())
        	throw new EmptyDequeException("La deque esta vacia");
        try {
			deque.remove(deque.last());
		} catch (InvalidPositionException | EmptyListException e) {
			e.printStackTrace();
		}
    }
	
	@Override
    public E getFirst() throws EmptyDequeException{
        E item = null;
        if(deque.isEmpty())
        	throw new EmptyDequeException("La deque esta vacia");
		try {
			item = deque.first().element();
		} catch (EmptyListException e) {
			e.printStackTrace();
		}
        return item;
    }
	
	@Override
    public E getLast() throws EmptyDequeException{
        E item = null;
        if(deque.isEmpty())
        	throw new EmptyDequeException("La deque esta vacia");
		try {
			item = deque.last().element();
		} catch (EmptyListException e) {
			e.printStackTrace();
		}
        return item;
    }
}