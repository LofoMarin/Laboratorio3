package src;

import java.io.IOException;
import src.Cliente;
import java.util.Scanner;


//Clase principal que hará uso del cliente
public class MainCliente
{
    public static void main(String[] args) throws IOException
    {
        Cliente cliente = new Cliente(); //Se crea el cliente

        System.out.println("Iniciando cliente\n");

        System.out.println("Escoja la acción a realizar:\n1. Quicksort left\n2. Quicksort right\n3. Mergesort\n4. Heapsort");

        Scanner leer = new Scanner(System.in);

        int opcion = 0;

        try {

            opcion = Integer.parseInt(leer.nextLine());

            while (opcion < 0 || opcion > 4) {

                System.out.println("Error: opcion invalida");

                opcion = Integer.parseInt(leer.nextLine());
            }

            
        } catch (Exception e) {

            System.out.println("Error: opcion inválida");
            System.exit(1);
        }

        System.out.println("Ingrese la longitud del arreglo a ingresar: ");

        int tamano = Integer.parseInt(leer.nextLine());

        String arr = "";

        for (int i = 0; i < tamano; i++) {
            
            System.out.printf("Ingrese el valor de la posicion %d: ", i);

            arr += leer.nextLine() + ",";
        }

        String arr_aux = arr.replaceAll(", $", "");

        cliente.startClient(opcion, arr_aux); //Se inicia el cliente
    }
}
