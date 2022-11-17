package src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import src.Conexion;

public class Cliente extends Conexion
{
    public Cliente() throws IOException{super("cliente");} //Se usa el constructor para cliente de Conexion

    public void startClient(int request, String vector) //Método para iniciar el cliente
    {
        try
        {
            //Flujo de datos hacia el servidor
            salidaServidor = new DataOutputStream(cs.getOutputStream());

            salidaServidor.writeUTF(String.valueOf(request));
            salidaServidor.writeUTF(vector);

            DataInputStream entrada = new DataInputStream(cs.getInputStream());

            System.out.println(entrada.readUTF());

            System.out.printf("Resultado: %s%n", entrada.readUTF());

            System.out.printf("Tiempo empleado: %.2f%n", Float.parseFloat(entrada.readUTF()));
            
            cs.close();//Fin de la conexión

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
