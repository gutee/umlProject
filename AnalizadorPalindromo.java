package algoritmos.tpa3.palindromo;

import algoritmos.estructuras.cola.ColaE;
import algoritmos.estructuras.pila.PilaE;

/**
 * Created by IntelliJ IDEA.
 * User: Martin
 * Date: 27/03/12
 * Time: 13:17
 * To change this template use File | Settings | File Templates.
 */
public class AnalizadorPalindromo {
    public boolean analizadorPalindromo(String s) {
        PilaE palabra = new PilaE();
        ColaE palabra2 = new ColaE();
        for (int i = 0; i < s.length(); i++) {
            palabra.apilar(s.charAt(i));
            palabra2.encolar(s.charAt(i));
        }
        for (int i = 0; i < s.length(); i++) {
            if (palabra.verTope() != palabra2.desencolar()) {
                return false;
            }
            palabra.desapilar();
        }
        return true;
    }
}