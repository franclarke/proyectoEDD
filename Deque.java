package TDADeque;

public interface Deque<E> {

	public int size();
	
	public boolean isEmpty();
	 
    public void addFirst(E item);
 
    public void addLast(E item);
 
    public void removeFirst() throws EmptyDequeException;
 
    public void removeLast() throws EmptyDequeException;
 
    public E getFirst() throws EmptyDequeException;
 
    public E getLast() throws EmptyDequeException;
	
}
