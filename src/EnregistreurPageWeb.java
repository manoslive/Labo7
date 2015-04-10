/**
 * Created by Emmanuel Beloin & Shaun Cooper on 10/04/15 at 00:30 :D
 */

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class EnregistreurPageWeb {

    private void sendGet(String adresse, String fichierDeSorti){

        try {
            PrintWriter ecritureFichier = new PrintWriter(new BufferedWriter(new FileWriter(fichierDeSorti)));

            URL obj = new URL(adresse);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
                ecritureFichier.println(response.toString());
                response.delete(0, response.length());
            }
            in.close();
            ecritureFichier.close();
        }
        catch(UnknownHostException uhe)
        {
            System.err.println("Erreur: Site web inconnu / mal écrit!");
            System.exit(-1);
        }
        catch(MalformedURLException mue)
        {
            System.err.println("Erreur: Site de syntaxe de l'url!");
            System.exit(-2);
        }
        catch(IOException ioe)
        {
            System.err.println("Erreur: Fichier introuvable!");
        }
    }

    public static void main(String[] args) throws Exception {
        EnregistreurPageWeb http = new EnregistreurPageWeb();

        if (args.length == 1)
            http.sendGet(args[0], "page.html");
        else if(args.length == 2)
            http.sendGet(args[0], args[1]);
        else
            System.err.println("Veuiller entrer au moins l'adresse du site! Bin kin!");
    }
}