package sanchez_sanchez_joaquin_prog_tarea06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

/* Clase que implementa la interfaz seriarizable, y por lo tanto va a permitir 
 * guardar sus objetos en un fichero en el disco duro para posteriormente recuperarlos
 * y poder trabajar con ellos */
public class DatosClientes implements Serializable {
//Declaramos como privados los atributos de la clase

    private String nif;
    private String nombre;
    private String telefono;
    private String direccion;
    private float deuda;

    /*Para poder acceder a los atributos privados que hemos declarado, se necesitan
     los métodos get y set siguientes*/
    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNif() {
        return nif;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDeuda(float deuda) {
        this.deuda = deuda;
    }

    public float getDeuda() {
        return deuda;
    }

    /*Método cuya función es pedir al usuario de la aplicación la introducción de 
     datos por teclado. En este método controlamos que la entrada sea de 9 caracteres, 
     los 8 primeros serán númreros y el últmimo una letra.
     La letra no es una letra al azar, sino que es calculada, por lo que debemos 
     comprobar que sea la adecuada. Cuando todo sea correcto nos devuelve el nif. */
    public String pedirNif() {
        boolean error = false;
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        do {
            error = false;
            try {
                System.out.println("Introduzca los 9 dígitos del nif del cliente seguido de la letra sin espacios: ");
                nif = teclado.readLine();
                if (nif.length() != 9) {
                    System.out.println("La longitud de su nif es incorrecta.");
                    error = true;
                }

                String dniLetra = nif.substring(8); //Separamos el último caracter introducido que debe ser una letra
                String dniNumero = nif.substring(0, 8); //Separamos los 8 primeros caracter numericos introducidos

                int dniNum = Integer.parseInt(dniNumero); // Pasamos a int las cifras del dni

                //Con los siguientes tres pasos calculamos la letra correcta del dni
                int resto = dniNum % 23;
                String[] letras = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
                String letraCalculada = letras[resto];

                //Comparamos la letra calculada con la letra introducida
                if (letraCalculada.equalsIgnoreCase(dniLetra)) {
                    System.out.println("El DNI introducido es correcto");
                } else {
                    System.out.println("El DNI introducido es incorrecto.");
                    error = true;
                }
            } catch (Exception e) {
                System.out.println("Error en la entrada del NIF");
                error = true;
            }
        } while (error == true); //Mientras hay error no se sale del bucle
        return nif;
    }

    /*Método para la introducción del nombre. Controlamos su longitud. Cuando la entrada
     sea correcta nos devuelve el nombre*/
    public String pedirNombre() {
        boolean error = false;
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        do {
            error = false;
            try {
                System.out.println("Introduzca el nombre del cliente: ");
                nombre = teclado.readLine();
                if (nombre.length() < 3 || nombre.length() > 60) {
                    System.out.println("La longitud de su nombre es incorrecta.");
                    error = true;
                }
            } catch (IOException e) {
                System.out.println("Error en la entrada de datos");
                error = true;
            }
        } while (error == true); //Mientras hay error seguimos en el bucle
        return nombre;
    }

/*Método para la introducción de la dirección. Controlamos su longitud. Cuando la
    entrada sea correcta nos devuelve la dirección*/
    public String pedirDireccion() {
        boolean error = false;
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        do {
            error = false;
            try {
                System.out.println("Introduzca la direccion del cliente: ");
                direccion = teclado.readLine();
                if (direccion.length() < 7 || direccion.length() > 150) {
                    System.out.println("La longitud de su dirección es incorrecta.");
                    error = true;
                }
            } catch (IOException e) {
                System.out.println("Error en la entrada de datos");
                error = true;
            }
        } while (error == true);
        return direccion;
    }

    /*Método que se utiliza para la entrada del teléfono por parte del teclado.
    Controlamos que la entrada sea numerica y que la longitud sea de 9 dígitos que 
    es la longitud de los teléfonos.
    */
    public String pedirTelefono() {
        boolean error = false;
        int tel = 0;
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        do {
            error = false;
            try {
                System.out.println("Introduzca el telefono del cliente: ");
                telefono = teclado.readLine();
                if (telefono.length() != 9) {
                    System.out.println("La longitud de su telefono es incorrecta.");
                    error = true;
                } else {
                    tel = Integer.parseInt(telefono); //Nos aseguramos que la entrada sea numerica
                }
            } catch (IOException e) {
                System.out.println("Error en la entrada de datos");
                error = true;
            } catch (NumberFormatException ex) {
                System.out.println("No se aceptan letras en este campo");
                error = true;
            }
        } while (error == true);
        return telefono;
    }

    public float pedirDeuda() {
        boolean error = false;
        String saldo;
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        do {
            error = false;
            try {
                System.out.println("Introduzca la deuda del cliente: ");
                saldo = teclado.readLine();
                saldo = saldo.replace(",", "."); //Evitamos errores de formato en la entrada
                deuda = Float.parseFloat(saldo); // Nos aseguramos que la entrada sea numerica(float)
                if (deuda <= 0) { //Evitamos la entrada de números negativos
                    System.out.println("La deuda debe ser mayor a 0 €");
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
        return deuda;
    }

}//Final de la clase
