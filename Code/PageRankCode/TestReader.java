import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe de lectures
 * 
 * @author decarvalhobo
 * @version April 2016
 */
public class TestReader
{
    public static int length;

    /**
     * METHOD READ_MATRIX
     * 
     * --> Lis le fichier et crée la matrice
     *
     * @param file fichier contenant la matrice
     */
    public static double [][] read_matrix(String file )
    {
        ArrayList <String[]> list = new ArrayList<String[]>(); //Liste dynamique qui contiendra les elements de la matrices
        BufferedReader fichier = null;

        try
        {
            fichier = new BufferedReader(new FileReader(file));   //Ouverture du flux de fichier
            String ligne = fichier.readLine();  //Lecture de la premiere ligne du fichier
            while( ligne != null )
            {
                list.add(ligne.split(",")); //On prend chaque élément séparé par une virgule 
                ligne = fichier.readLine(); //On lit la ligne suivante du fichier
            }
        }
        catch (FileNotFoundException exception) //Gestion de l'erreur du au fait de ne pas trouver le fichier 
        {
            System.err.println("Le fichier " + file +" n'a pas été trouvé :" + exception.getMessage());
            PageRank.initialisation();  //retour à la demande du nom de fichier
        }
        catch (IOException exception)   //Gestion de l'erreur du à la lecture du fichier
        {
            System.err.println ("Erreur lors de la lecture : " + exception.getMessage());
            PageRank.initialisation(); //retour à la demande du nom de fichier
        }
        finally //Exécuté dans tout les cas
        {
            try
            {
                fichier.close();    //Fermeture du flux de fichier
            }
            catch(IOException exception)    //Gestion de l'erreur du à la fermeture du flux de fichier
            {
                System.err.println ("Erreur lors de la fermeture du flux de fichier : " + exception.getMessage());
            }
        }

        String [][] temporaire = list.toArray(new String[list.size()][]);   //Resultat de la lecture dans une matrice temporaire
        if (temporaire.length != temporaire[0].length)  //Gestion erreur matrice non NxN
        {
            System.err.println("La largeur de la matrice n'est pas égale à la hauteur. Nous ne garantissons pas le bon fonctionement du programme.\n ATTENTION : Une matrice pageRank est de type NxN");
        }

        /* Construction de la matrice */
        double [][] matrice = new double[temporaire.length][temporaire.length];    //Création de la matrice
        for (int runner_1 = 0 ; runner_1 < temporaire.length ; runner_1++)  //Parcours du tableau 
        {
            for (int runner_2 = 0 ; runner_2 < temporaire.length ; runner_2++) 
            {
                matrice[runner_1][runner_2] = Double.parseDouble(temporaire[runner_1][runner_2]); //Conversion et stockage
            }
        }
        length = matrice.length;
        System.out.println("\nLe fichier " + file + " contient la matrice suivante : \n");
        PageRank.print_a_matrix(matrice);
        return matrice;
    }

    /**
     * METHOD READ_VECTOR
     * 
     * --> Lis le fichier et crée le vecteur
     *
     * @param file fichier contenant le vecteur
     */
    public static double [] read_vector(String file )
    {
        String temp [] =new String [length];
        BufferedReader fichier = null;

        try
        {
            fichier = new BufferedReader(new FileReader(file));   //Ouverture du flux de fichier
            String ligne = fichier.readLine();  //Lecture de la premiere ligne du fichier
            while( ligne != null )
            {
                temp = ligne.split(","); //On prend chaque élément séparé par une virgule 
                ligne = fichier.readLine(); //On lit la ligne suivante du fichier
            }
        }
        catch (FileNotFoundException exception) //Gestion de l'erreur du au fait de ne pas trouver le fichier 
        {
            System.err.println("Le fichier " + file +" n'a pas été trouvé :" + exception.getMessage());
            PageRank.initialisation();  //retour à la demande du nom de fichier
        }
        catch (IOException exception)   //Gestion de l'erreur du à la lecture du fichier
        {
            System.err.println ("Erreur lors de la lecture : " + exception.getMessage());
            PageRank.initialisation(); //retour à la demande du nom de fichier
        }
        finally //Exécuté dans tout les cas
        {
            try
            {
                fichier.close();    //Fermeture du flux de fichier
            }
            catch(IOException exception)    //Gestion de l'erreur du à la fermeture du flux de fichier
            {
                System.err.println ("Erreur lors de la fermeture du flux de fichier : " + exception.getMessage());
            }
        }

        double vector [] = new double[temp.length];

        for (int runner_1 = 0 ; runner_1 < length ; runner_1++) 
        {
            vector[runner_1] = Double.parseDouble(temp[runner_1]); //Conversion et stockage
        }
        System.out.println("\nLe fichier " + file + " contient le vecteur de personnalisation suivant : \n");
        PageRank.print_a_vector(vector);
        return vector;
    }

}
