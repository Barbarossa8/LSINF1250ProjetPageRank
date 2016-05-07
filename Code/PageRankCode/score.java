import java.util.*;
import java.io.*;

/**
 * La classe score qui va appelé les différentes fonctions et lire le fichier dans la main
 * 
 * @author Antoine Denauw & Antonio Decarvalho
 * @version 07/05/16
 */
public class score {
    /*
     * Méthode qui charge le fichier de données (la matrice d’adjacence) 
     * , qui appelle la procédure calculant les scores PageRank
     * et les imprime à l’écran.
     */
    public static void main(String[] args) {
        int lignes = 0;
        int largeur = 0;
        int [][]matrice = new int[100][100];
        String [][]fake = new String[100][100];

        //BufferedReader br; // hors du try pour etre accessible dans le finally
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("MatriceDeBase"));
            String str = br.readLine();
            while(str != null)
            {
                for (largeur = 0; str != null; largeur++)
                {
                    System.out.println(str);
                    str = br.readLine();
                    fake[lignes] = str.split(",");
                    lignes++;
                }
            }   //fin While

            for (int i = 0; i < largeur; i++)
            {
                for (int j = 0; j < lignes; j++)
                {
                    matrice[i][j] = Integer.parseInt(fake[i][j]);
                }
            }
            print_a_matrix(matrice);

            br.close();
        }
        catch (FileNotFoundException exception)
        {
            System.out.println ("Le fichier n'a pas été trouvé");

        }
        catch (IOException exception)
        {
            System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
        }
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

    /*
     * Méthode qui calcule le score PageRank à partir de la matrice donnée en argument.
     * 
     * 
     * @pre: Une matrice d’adjacence A d’un graphe dirigé, pondéré 
     * 		 et régulier G ainsi qu’un paramètre de téléportation 
     * 		 α entre 0 et 1 (inutile de le vérifier) et un vecteur de personnalisation q.
     * @post: Un vecteur x contenant les scores d’importance des noeuds ordonnés 
     * 		  dans le même ordre que la matrice d’adjacence.
     */
    public int[] calcul (int matrice[][]){
        return null;

    }
}
