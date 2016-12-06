package sanchez_sanchez_joaquin_prog_tarea06;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.ListIterator;

public class Sanchez_sanchez_joaquin_PROG_Tarea06 {

    ArrayList listaDeObjetosCliente = new ArrayList(); // Creamos un arrayList poder mostrar todos los clientes guardados en disco duro. 
    Serializadora serializar = new Serializadora(); //Declaro un objeto de la clase Serializar.
    DatosClientes registroCliente; //Instancio un objeto de la clase DatosCliente, lo inicializo en el switch.
    static int opcion = 0;

    //Método para escribir el ArrayList en un archivo
    public void escribirFichero() {
        serializar.escribirObjeto(listaDeObjetosCliente);
    }

    //Método para escribir el ArrayList usando los datos almacenados en el fichero.
    public void comprobarLeerFichero() {
        //Creamos el objeto clientes que recibirá como parámetro el archivo creado por la clase Serializadora.
        File clientes = new File("clientes.dat");
        if (clientes.exists()) {
            //Casting para forzar que el objeto se convierta en un ArrayList
            listaDeObjetosCliente = (ArrayList) serializar.leerObjeto("clientes.dat");
        } else {
            System.out.println("Antes de leer el fichero debe crearlo");
        }
    }

    /*Método cuya función es mostrar la opciones del menu que se muestra a la entrada
     a la aplicación*/
    public void menuOpciones() {
        System.out.println("INTRODUZCA UNA DE LAS SIGUIENTES OPCIONES");
        System.out.println("-------------------------------------------");
        System.out.println("1- Añadir clientes.");
        System.out.println("2- Listar clientes.");
        System.out.println("3- Buscar clientes.");
        System.out.println("4- Borrar cliente.");
        System.out.println("5- Borrar fichero de clientes por completo.");
        System.out.println("6- Salir de la aplicación.");
    }

    /*Método en el que solicitamos la entrada de un número para el funcionamiento de switch
     que controlará la aplicación.*/
    public int elegirOpcion() {
        boolean error = false;
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        do {
            error = false;
            try {
                String numero = teclado.readLine();
                opcion = Integer.parseInt(numero); //Nos aseguramos de que la entrada no sea una letra
                if (opcion < 1 || opcion > 6) {
                    System.out.println("Solo se aceptan número entre 1 y 6");
                    error = true;
                }
            } catch (IOException e) {
                System.out.println("Error en la entrada de datos");
                error = true;
            } catch (NumberFormatException ex) {
                System.out.println("No se aceptan letras en este campo");
                error = true;
            }
        } while (error == true);
        return opcion;
    }

    /*Método encargado de realizar la búsqueda de un cliente por su nif y en caso
     de que exista en el fichero devolver sus datos */
    public void buscarNif(String nif) {
        boolean encontrado = false; //Controlará el flujo del método
        ListIterator iterator = listaDeObjetosCliente.listIterator(); //Permite entrar secuencialmente a los objetos de una colección
        while (iterator.hasNext()) { //Mientras siga habiendo registros en la lista
            registroCliente = (DatosClientes) iterator.next(); //Nos lo va devolviendo haciendo un down-casting
            if (registroCliente.getNif().equals(nif)) { //compara el objeto almacenado (nif) con el que introducimos y dice si son iguales o no
                System.out.println("El DNI introducido se ha encontrado, se corresponde con el cliente : ");
                System.out.println("Nombre: " + registroCliente.getNombre());
                System.out.println("Dirección: " + registroCliente.getDireccion());
                System.out.println("Teléfono: " + registroCliente.getTelefono());
                System.out.println("Deuda : " + registroCliente.getDeuda() + "\n");
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se ha encontrado el nif " + nif);
        }
    }

    /*Método cuya función es borrar el usuario con el nif introducido por el usuario. */
    public void borrarNif(String nif) throws IOException {
        boolean nifExistente = false;
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        String seleccion = null;
        ListIterator iterator = listaDeObjetosCliente.listIterator(); //permite entrar secuencialmente a los objetos de una colección
        while (iterator.hasNext()) { //Mientras siga habiendo registros en la lista
            registroCliente = (DatosClientes) iterator.next(); //Down-Casting para "rescatar" los objetos del archivo

            if (registroCliente.getNif().equals(nif)) { //compara el objeto almacenado (nif) con el que introducimos y dice si son iguales o no
                System.out.println("El DNI introducido se ha encontrado, se corresponde con el cliente : ");
                System.out.println("Nombre: " + registroCliente.getNombre());
                System.out.println("Dirección: " + registroCliente.getDireccion());
                System.out.println("Teléfono: " + registroCliente.getTelefono());
                System.out.println("Deuda : " + registroCliente.getDeuda() + "\n");
                System.out.println("¿Desea borrar el registro?");
                System.out.println("Escriba ( \"SI\"  \"NO\" y pulse intro)");
                seleccion = teclado.readLine();
                if (seleccion.equalsIgnoreCase("si")) {
                    iterator.remove();//borramos el registro en el que estamos en este momento
                    System.out.println("El registo ha sido borrado.");
                } else {
                    System.out.println("El registro no ha sido borrado");
                }
                System.out.println("Registro borrado.");
                nifExistente = true;
            }
        }
        if (!nifExistente) {
            System.out.println("No se ha encontrado el nif " + nif);
        }
    }

    /*Método genérico para borrar cualquier fichero. Recibe como parámetro el nombre del 
     fichero. Comprueba si el fichero existe y en caso afirmativo lo elimina previa confirmación
     por parte del usuario*/
    public void borrarFichero(String nombreFichero) throws IOException {
        String seleccion = null;
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        // creamos el objeto fichero que hemos pasado como parámetro
        File fichero = new File(nombreFichero);
        if (!fichero.exists()) {
            System.out.println("El fichero " + nombreFichero + " no existe.");
        } else {
            System.out.println("¿Desea borrar el fichero " + nombreFichero + "?");
            System.out.println("Escriba ( \"SI\"  \"NO\" y pulse intro)");
            seleccion = teclado.readLine();

            if (seleccion.equalsIgnoreCase("si")) {
                fichero.delete();// borramos el fichero
                listaDeObjetosCliente.clear();//Borramos los objetos de la lista
                System.out.println("El fichero " + nombreFichero + " ha sido borrado.");
            } else {
                System.out.println("El fichero " + nombreFichero + " no se ha borrado.");
            }
        }
    }

    /*Método que recogera las opciones del Switch*/
    public void SwitchOpciones(int opcion) throws IOException {

        switch (opcion) {
            case 0: //Opción que se carga por defecto para que la aplicación funcione de forma adecuada.
                escribirFichero(); //Al entrar al switch lo primero que tengo que hacer es recuperar el fichero.
                /*La siguiente línea debe ir en el switch si lo declaro de forma global
                 al imprimir los clientes todos los objetos tienen los mismos datos*/
                registroCliente = new DatosClientes();
                break;

            case 1: //Introducción de datos
                registroCliente.setNombre(registroCliente.pedirNombre());
                registroCliente.setDireccion(registroCliente.pedirDireccion());
                registroCliente.setNif(registroCliente.pedirNif());
                registroCliente.setTelefono(registroCliente.pedirTelefono());
                registroCliente.setDeuda(registroCliente.pedirDeuda());
                listaDeObjetosCliente.add(registroCliente);//Añado un nuevo objeto al ArrayList
                //serializar.escribirObjeto(clientes); //Graba un nuevo registro en el fichero(no es correcto)
                break;

            case 2: //Muestra todos los datos guardados en el fichero
                ListIterator iterator = listaDeObjetosCliente.listIterator(); //permite entrar secuencialmente a los objetos de una colección
                while (iterator.hasNext()) { //Mientras siga habiendo registros en la lista
                    registroCliente = (DatosClientes) iterator.next(); //Down-Casting para "rescatar" los objetos del archivo
                    System.out.println("Nombre: " + registroCliente.getNombre());
                    System.out.println("Dirección: " + registroCliente.getDireccion());
                    System.out.println("NIF: " + registroCliente.getNif());
                    System.out.println("Teléfono: " + registroCliente.getTelefono());
                    System.out.println("Deuda: " + registroCliente.getDeuda());
                    System.out.println(); //Salto de linea despues de los datos de cada cliente
                }
                break;

            /*El parámetro de entrada a buscarNif será un nif que entraremos por
             teclado haciendo uso del método pedirNif() */
            case 3:
                buscarNif(registroCliente.pedirNif());
                break;

            /*El parámetro de entrada a buscarNif será un nif que entraremos por
             teclado haciendo uso del método pedirNif()*/
            case 4:
                borrarNif(registroCliente.pedirNif());
                break;

            case 5: //Borramos el fichero que pasemos como parámetro
                borrarFichero("clientes.dat"); //Llamada al método borrarFichero
                break;

            case 6:
                break;
        }
    }

    public static void main(String[] args) throws IOException {

        Sanchez_sanchez_joaquin_PROG_Tarea06 objeto1 = new Sanchez_sanchez_joaquin_PROG_Tarea06();
        objeto1.comprobarLeerFichero();

//Creamos un bucle del que solo saldremos al pulsar el 6.
        do {
            objeto1.menuOpciones();
            objeto1.elegirOpcion();
            objeto1.SwitchOpciones(0); //Para que se cargue la opción 0 y no tener el error NULLPOINTEREXCEPTION
            objeto1.SwitchOpciones(opcion);
        } while (opcion != 6); //Para salir de la aplicación cuando pulsemos sobre el 6
    }
}
