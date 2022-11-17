package src;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.crypto.Data;

import src.Conexion;
import src.algoritmos.QuickSortLeft;
import src.algoritmos.HeapSort;
import src.algoritmos.MergeSort;
import src.QuickSort;

public class Servidor extends Conexion //Se hereda de conexión para hacer uso de los sockets y demás
{
    public Servidor() throws IOException{super("servidor");} //Se usa el constructor para servidor de Conexion

    public void startServer()//Método para iniciar el servidor
    {
        try
        {
            System.out.println("Esperando..."); //Esperando conexión

            cs = ss.accept(); //Accept comienza el socket y espera una conexión desde un cliente

            System.out.println("Cliente en línea");

            //Se obtiene el flujo de salida del cliente para enviarle mensajes
            salidaCliente = new DataOutputStream(cs.getOutputStream());

            //Se le envía un mensaje al cliente usando su flujo de salida
            salidaCliente.writeUTF("Petición recibida y aceptada");

            //Se obtiene el flujo entrante desde el cliente
            DataInputStream entrada = new DataInputStream(cs.getInputStream());

            String request = "";

            while((mensajeServidor = entrada.readUTF()) != null) //Mientras haya mensajes desde el cliente
            {

                if (request.compareTo("") == 0) {

                    request = mensajeServidor;
                    continue;
                }

                String[] arr_str = mensajeServidor.split(",");
                int[] arr = new int[arr_str.length];

                for (int i = 0; i < arr.length; i++) {
                    
                    arr[i] = Integer.parseInt(arr_str[i]);
                }
                
                if (request.compareTo("1") == 0) {

                    QuickSortLeft qs = new QuickSortLeft();

                    long startTime = System.nanoTime();
                    
                    int[] result = qs.sort(arr);

                    long endTime = System.nanoTime();

                    String arr_r = "";

                    for (int i = 0; i < result.length; i++) {
                        
                        arr_r += String.valueOf(result[i]) + ",";

                    }

                    String arr_aux = arr_r.replaceAll(", $", "");

                    salidaCliente.writeUTF(arr_aux);
                    salidaCliente.writeUTF(String.valueOf(endTime - startTime));

                }

                else if (request.compareTo("2") == 0) {

                    QuickSort qs = new QuickSort();
                    
                    long startTime = System.nanoTime();

                    int[] result = qs.QuickSort(arr, arr.length); 

                    long endTime = System.nanoTime();

                    String arr_r = "";

                    for (int i = 0; i < result.length; i++) {
                        
                        arr_r += String.valueOf(result[i]) + ",";

                    }

                    String arr_aux = arr_r.replaceAll(", $", "");

                    salidaCliente.writeUTF(arr_aux);
                    salidaCliente.writeUTF(String.valueOf(endTime - startTime));

                }

                else if (request.compareTo("3") == 0) {

                    MergeSort ms = new MergeSort();
                    
                    long startTime = System.nanoTime();

                    int[] result = ms.sort(arr, 0, arr.length - 1);

                    long endTime = System.nanoTime();

                    String arr_r = "";

                    for (int i = 0; i < result.length; i++) {
                        
                        arr_r += String.valueOf(result[i]) + ",";

                    }

                    String arr_aux = arr_r.replaceAll(", $", "");

                    salidaCliente.writeUTF(arr_aux);
                    salidaCliente.writeUTF(String.valueOf(endTime - startTime));
                }

                else if (request.compareTo("4") == 0) {

                    HeapSort hs = new HeapSort();
                    
                    long startTime = System.nanoTime();

                    int[] result = hs.sort(arr);

                    long endTime = System.nanoTime();

                    String arr_r = "";

                    for (int i = 0; i < result.length; i++) {
                        
                        arr_r += String.valueOf(result[i]) + ",";

                    }

                    String arr_aux = arr_r.replaceAll(", $", "");

                    salidaCliente.writeUTF(arr_aux);
                    salidaCliente.writeUTF(String.valueOf(endTime - startTime));
                }

                

            }

            System.out.println("Fin de la conexión");

            ss.close();//Se finaliza la conexión con el cliente
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
