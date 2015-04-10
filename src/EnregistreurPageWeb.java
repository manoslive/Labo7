/**
 * Created by Emmanuel Beloin & Shaun Cooper on 2015-04-09.
 */
import java.io.*;
import java.net.*;

public class EnregistreurPageWeb {
    Socket monSocket;

    public void Enregistrer(String adresseWeb, String nom)
    {
        String ligne = new String();
        try
        {
            monSocket = new Socket(InetAddress.getByName(adresseWeb), 80);
            PrintWriter writer = new  PrintWriter( new OutputStreamWriter(monSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(monSocket.getInputStream()));
            PrintWriter ecritureFichier = new PrintWriter(new BufferedWriter( new FileWriter( nom) ) );
            // Affichage de l'entête
            writer.println("GET / HTTP/1.1");
            writer.println("Host: " + adresseWeb);
            writer.println("");
            writer.flush();

            // Ici on lit / écrit chaque ligne jusqu'à la dernière
            while((ligne = reader.readLine())!= null)
                ecritureFichier.println(ligne);
            ecritureFichier.close();
        }
        catch(UnknownHostException uhe)
        {
            System.err.println("Erreur: Le site web ne répond pas / n'existe pas");
            uhe.printStackTrace();
        }
        catch(IOException ioe)
        {

        }
    }
    public static void main(String[] args)
    {
        EnregistreurPageWeb lespion = new EnregistreurPageWeb();
        // Si l'utilisateur ne fourni pas de nom de fichier
        // on met le output dans un fichier par défaut (page.html)
        if(args.length == 1)
            lespion.Enregistrer(args[0],"page.html");
        else
            lespion.Enregistrer(args[0],args[1]);
    }
}
