import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe de lecture & création de matrice
 * 
 * @author decarvalhobo
 * @version April 2016
 */
public class TestReader
{
    public static void main(String [] args)
    {
        ArrayList <String[]> list = new ArrayList<String[]>(); //Liste dynamique qui contiendra les elements de la matrices
        BufferedReader fichier = null;

        try
        {
            fichier = new BufferedReader(new FileReader(args[0]));   //Ouverture du flux de fichier
            String ligne = fichier.readLine();  //Lecture de la premiere ligne du fichier
            while( ligne != null )
            {
                list.add(ligne.split(",")); //On prend chaque élément séparé par une virgule 
                ligne = fichier.readLine(); //On lit la ligne suivante du fichier
            }
        }
        catch (FileNotFoundException exception) //Gestion de l'erreur du au fait de ne pas trouver le fichier 
        {
            System.err.println("Le fichier " + args[0] +" n'a pas été trouvé :" + exception.getMessage());
        }
        catch (IOException exception)   //Gestion de l'erreur du à la lecture du fichier
        {
            System.err.println ("Erreur lors de la lecture : " + exception.getMessage());
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
        int [][] matrice = new int[temporaire.length][temporaire.length];    //Création de la matrice
        for (int runner_1 = 0 ; runner_1 < temporaire.length ; runner_1++)  //Parcours du tableau 
        {
            for (int runner_2 = 0 ; runner_2 < temporaire.length ; runner_2++) 
            {
                matrice[runner_1][runner_2] = Integer.parseInt(temporaire[runner_1][runner_2]); //Conversion et stockage
            }
        }
        print_a_matrix(matrice);
    }


    /** METHOD PRINT_A_MATRIX
     * 
     * --> Imprime la matrice
     *
     * @param matrice La matrice à imprimer
     */
    public static void print_a_matrix(int[][] matrice)
    {
        for(int runner = 0 ; runner < matrice.length ; runner++) //Parcours
        {
            for(int runner2 = 0 ; runner2 < matrice.length ; runner2++) //Parcours
            {
                System.out.print(" " + matrice[runner][runner2]);
            }
            System.out.println("");
        }
        System.out.println("\n");
    }
}
