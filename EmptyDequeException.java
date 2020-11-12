package TDADeque;

/**
 * La clase EmptyDequeException extiende a la clase Exception y es utilizada para lanzar una excepción en caso de que
 * una posicion no sea válida.
 * @author Clarke Francisco, Ruiz Francisco
 */
public class EmptyDequeException extends Exception{
		/**
		 * Lanza el mensaje de error correspondiente
		 * @param msg mensaje a mostrar
		 */
		public EmptyDequeException(String msg) {
			super(msg);
		}

	}

