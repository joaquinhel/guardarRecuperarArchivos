package sanchez_sanchez_joaquin_prog_tarea06;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

//Clase que se encarga de gardar objetos en el disco duro y posteriormente recuperarlos.
//Esta clase será útil tanto para este proyecto como para otros, sirve para guardar en disco cualqueir obejto.
public class Serializadora {

    //Declaramos los objetos I/O para leer y escribir los objetos que le pasemos en un fichero
    private ObjectOutputStream escritorDeObjetos; //Guardamos objetos en el disco duro
    private ObjectInputStream lectorDeObjetos; //Leemos objetos almacenados en el disco duro

    //El parámetro que recibe es tipo Object es decir un objeto.
    //Creamos el archivo clientes.dat que se guardará en el disco duro los objetos
    public void escribirObjeto(Object objeto) {
        try {
            escritorDeObjetos = new ObjectOutputStream(new FileOutputStream("clientes.dat"));
            escritorDeObjetos.writeObject(objeto);//Escribimos el objeto que se recibe por parámetro
            escritorDeObjetos.close();//Cerramos objeto que graba objetos en el disco duro

        } catch (IOException ex) {
            Logger.getLogger(Serializadora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Método que utilizaremos para leer objetos
    //Recibe como parámetro el nombre del archivo donde se guardan los objetos
    public Object leerObjeto(String nombreDelArchivo) {
        Object retorno = null;
        try {
            lectorDeObjetos = new ObjectInputStream(new FileInputStream(nombreDelArchivo));
            retorno = lectorDeObjetos.readObject(); //Retorna el objeto contenido en el archivo
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(Serializadora.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}//Fin de la clase
