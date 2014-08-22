package prog2.tp5Serialization;

import algoritmos.tpa3.palindromo.AnalizadorPalindromo;
import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: Martin
 * Date: 09/11/11
 * Time: 12:48
 * To change this template use File | Settings | File Templates.
 */
public class Dog implements Serializable{
    private String name;
    transient private boolean happy;
    private boolean hungry;

    public Dog(String name) {
        this.name = name;
        happy = true;
        hungry = false;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}