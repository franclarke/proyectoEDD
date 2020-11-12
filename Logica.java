package Logica;
import Pair.Pair;

import TDACola.*;
import TDAColaCP.*;
import TDAColaCP.Entry;
import TDADeque.*;
import TDADiccionario.*;
import TDAPila.*;

public class Logica<E> {

	private int saldo;

	private Deque<E> historial = new DequeConLista<E>();
	private PriorityQueue<Integer, String> cola = new Heap<Integer, String>();
	private Dictionary<Integer, String> bdd = new Diccionario_hash_cerrado<Integer, String>();

	public Logica() {
		saldo = 0;
	}

	public int sizeCCP() {
		return cola.size();
	}
	
	public int sizeBDD() {
		return bdd.size();
	}
	
	@SuppressWarnings("unchecked")
	public Pair<Integer, String> getUltimaT(){
		Pair<Integer, String> p = null;
		if(!historial.isEmpty())
			try {
				p = (Pair<Integer, String>) historial.getFirst();
			} catch (EmptyDequeException e) {
				e.printStackTrace();
			}
		return p;
	}

	@SuppressWarnings("unchecked")
	public Pair<Integer, String> getHistoricaT(){
		Pair<Integer, String> p = null;
		if(!historial.isEmpty())
			try {
				p = (Pair<Integer, String>) historial.getLast();
			} catch (EmptyDequeException e) {
				e.printStackTrace();
			}
		return p;
	}

	public Pair<Integer, String> getCostosaT() throws EmptyPriorityQueueException {

		Entry<Integer, String> e= cola.min();
		Pair<Integer, String> p= new Pair<Integer, String>(null, null);
		
		p.setKey(e.getKey());
		p.setValue(e.getValue());
		return p;
	}

	public int getMonto(Pair<Integer, String> p) {
		return (int) p.getKey();
	}

	public String getDescripcion(Pair<Integer, String> p) {
		return (String) p.getValue();
	}

	public int getSaldo() {
		return saldo;
	}

	public void transaccion(int n) {
		saldo = saldo + n;
	}

	/**
	 * Validar codigo de acceso: valida que el codigo de acceso al sistema cumpla el siguiente formato AXA'A', siendo A un String, X un caracter x (asumimos que no es parte del String A), A' es A invertido
	 * @param cadena codigo de acceso a verificar
	 * @return boolean verdadero si es valida, caso contrario falso.
	 */
	public boolean validarCodigoDeAcceso(char[] pw) throws EmptyStackException, EmptyQueueException{
		boolean cumple=true;
		boolean encontreX=false;
		int pospw=0;
		char vchar;
		Stack<Character> p1=new PilaEnlaces<Character>();
		Stack<Character> p2=new PilaEnlaces<Character>();
		Queue<Character> c=new ColaConArregloCircular<Character>();
		while(pospw!=pw.length) {
			c.enqueue(pw[pospw]);
			pospw++;
		}

		if(c.size()>=4) {
			while(cumple && !encontreX) {
				vchar = c.dequeue();
				if(vchar=='x') 
					encontreX=true;
				else{
					p1.push(vchar);
					p2.push(vchar);

					if(c.size()==0 && !encontreX)
						cumple=false;
				}
			}

			while(cumple && !c.isEmpty() && !p1.isEmpty()) {
				cumple=c.dequeue() == p1.pop();
				if(c.isEmpty())
					cumple=false;
			}
			while(cumple && !c.isEmpty() && !p2.isEmpty()) 
				cumple=c.dequeue() == p2.pop();

			if(!c.isEmpty())
				cumple=false;
		}else
			cumple=false;

		return cumple;
	}


	@SuppressWarnings("unchecked")
	public void actualizar(int m, String d) {
		Pair<Integer, String> p = new Pair<Integer, String>(m, d); 

		try {
			historial.addFirst((E) p);
			bdd.insert(m, d);
			cola.insert(m, d);
		} catch (TDADiccionario.InvalidKeyException | TDAColaCP.InvalidKeyException e) {
			e.printStackTrace();
		}
	}

	public Iterable<TDADiccionario.Entry<Integer, String>> buscarBDD(int k) throws TDADiccionario.InvalidKeyException{
		return bdd.findAll(k);
	}

}


