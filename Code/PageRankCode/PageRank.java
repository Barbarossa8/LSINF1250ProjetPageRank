import java.util.*;
import java.io.*;

/**
 * Page Rank
 * 
 * @author Antoine & Antonio
 * @version Avril 2016
 */
public class PageRank
{

    /**
     * Alors les fonctions que l'on doit utiliser pour l'algorithme PageRank de la slide 135 sont implémentée
     * Il reste plus qu'à faire:
     *                              - la fonction principale càd retranscrire l'algo en code
     *                              - ecrire la main qui lira des fichiers et appellera la fonction de calcul                    
     */

    /**
     * Main utilisé pour des tests
     */
    public static void main()
    {
        double[][] matrice = { {0,1,0},{1,0,1},{1,1,0}};
        double[] vector = {1,0,0};
        double alpha = 0.9;
        double [] first_vector = multiply(vector,transition_matrix(matrice),alpha);
        double facteur = (1 - alpha)/matrice.length ;
        double [] second_vector = multiply(create_vector(matrice.length,1),facteur);
        print_a_vector( sum( first_vector , second_vector ) );
        System.out.println(sum(sum( first_vector , second_vector )));
    }

    public static void initialisation()
    {
        Scanner scan = new Scanner( System.in );
        System.out.print("Entrez le nom du fichier contenant la matrice: ");
        String fichier = scan.nextLine();
        
        scan.close();
    }

    //METHODES AVANCEE SUR MATRICES
    /**
     * METHOD TRANSITION_MATRIX
     *
     *  --> Calcule la matrice de transition
     *
     * @param matrice Une matrice NxN
     * @return La matrice de transition NxN
     */
    public static double [][] transition_matrix(double [][] matrice)
    {
        return divise(matrice,degre(matrice));
    }

    /**
     * Méthode qui calcule le score PageRank à partir de la matrice donnée en argument.
     * 
     * 
     * @pre: Une matrice d’adjacence A d’un graphe dirigé, pondéré 
     *       et régulier G ainsi qu’un paramètre de téléportation 
     *       α entre 0 et 1 (inutile de le vérifier) et un vecteur de personnalisation q.
     * @post: Un vecteur x contenant les scores d’importance des noeuds ordonnés 
     *        dans le même ordre que la matrice d’adjacence.
     */
    public double[] calcul (double matrice[][]){
        return null;

    }

    //METHODES DE BASES POUR LES MATRICES

    /**
     * METHODE SUM
     * 
     * --> Calcule la somme de deux vecteurs
     *
     * @param  vector_1     Un vecteur de taille N
     * @param  vector_2     Un vecteur de taille N
     * @return     La somme des deux vecteurs
     */
    public static double [] sum(double [] vector_1, double [] vector_2)
    {
        for( int runner=0 ; runner< vector_1.length;runner++)
        {
            vector_1[runner] += vector_2[runner];
        }
        return vector_1;
    }

    /**
     * METHOD DIVISE
     *
     * @param matrice Une matrice NxN
     * @param vector Un vecteur de longueur N
     * @return Retourne la division entre la matrice et le vecteur sous forme de matrice NxN
     */
    public static double [][] divise(double [][] matrice, double [] vector)
    {
        double divide_by;
        for(int runner = 0 ; runner < matrice.length ; runner++)
        {
            divide_by = vector[runner];
            if(divide_by > 1) //Si ca vaut la peine de diviser par divide_by
            {
                for(int runner2 = 0 ; runner2 < matrice.length ; runner2++)
                {
                    matrice[runner][runner2]=matrice[runner][runner2]/divide_by;
                }
            }
        }
        return matrice;
    }

    /**
     * METHOD TRANSPOSE
     * 
     * --> Transpose une matrice NxN
     * 
     * @param matrice La matrice que l'on veut transposer
     * @return La matrice transposée
     */
    public static double [][] transpose(double [][] matrice)
    {
        double [][] matrice_transpose = new double [matrice.length] [matrice.length]; 

        for(int runner = 0 ; runner < matrice.length ; runner++) //Parcours
        {
            for(int runner2 = 0 ; runner2 < matrice.length ; runner2++) //Parcours
            {
                matrice_transpose[runner2][runner] = matrice[runner][runner2]; //Inversion
            }
        }

        return matrice_transpose;
    }

    /**
     * METHOD MULTIPLY
     *
     * --> Multiplie une matrice avec un vecteur et un coefficient alpha
     *
     * @param matrice_A La matrice A 
     * @param matrice_B La matrice B
     * @return Le produit des deux matrices NxN sous forme de matrice NxN
     */
    public static double[] multiply(double[] vector, double[][] matrice, double alpha) 
    {   
        double[] vector_x_matrice = new double [vector.length];
        for( int runner_1 = 0 ; runner_1 != matrice.length ; runner_1++ ) //Parcours
        {
            for( int runner_2 = 0 ; runner_2 != matrice.length ; runner_2++ ) //Parcours
            {
                vector_x_matrice[runner_1] += vector[runner_2] * matrice[runner_2][runner_1];
            }
            if (alpha != 0)
            {
                vector_x_matrice[runner_1] = vector_x_matrice[runner_1]*alpha;
            }
        }
        return vector_x_matrice;
    }

    /**
     * METHOD MULTIPLY
     *
     * --> Multiplie un vecteur avec un facteur
     *
     * @return Le produit ...
     */
    public static double[] multiply(double[] vector, double facteur) 
    {   
        for( int runner = 0 ; runner != vector.length ; runner++ ) //Parcours
        {
            vector[runner] = vector[runner]*facteur;
        }
        return vector;
    }

    /**
     * METHOD DEGRE
     *
     * --> Calcule le vecteur de degré de la matrice
     *
     * @param matrice La matrice a analyser
     * @return Un vecteur contenant les degrés des lignes de la matrice
     */
    public static double[] degre(double[][] matrice)
    {
        double[] matrice_degre=new double [matrice.length]; //On cree la matrice qui contiendra les degrés
        for ( int runner_1=0 ; runner_1 < matrice.length ; runner_1++)
        {
            double sum=0; //variable qui stockera le degré de la ligne runner_1

            for ( int runner_2=0 ; runner_2 < matrice.length ; runner_2++)
            {
                sum+=matrice[runner_1][runner_2]; //Additionne la ligne
            }
            matrice_degre [runner_1] = sum; //Met le degré de la ligne N à la place N du vecteur
        }
        return matrice_degre;
    }

    //METHODES DIVERSE
    public static double [] create_vector(int longueur , int valeur_initialisation)
    {        
        double [] vector = new double [longueur];
        for( int runner=0 ; runner < longueur ; runner++ )
        {
            vector [runner]=valeur_initialisation;
        }
        return vector;
    }

    public static double sum(double[] vector)
    {
        double sum=0;
        for (int runner=0;runner<vector.length; runner++)
        {
            sum+=vector[runner];   
        }
        return sum;
    }

    //METHODES POUR IMPRIMER
    /**
     * METHOD PRINT_A_MATRIX
     * 
     * --> Imprime la matrice
     *
     * @param matrice La matrice à imprimer
     */
    public static void print_a_matrix(double[][] matrice)
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

    /**
     * METHOD PRINT_A_VECTOR
     * 
     * --> Imprime le vecteur
     *
     * @param vector Le vecteur à imprimer
     */
    public static void print_a_vector(double[] vector)
    {
        for(int runner = 0 ; runner < vector.length ; runner++) //Parcours
        {
            System.out.print(vector[runner]+" ");
        }
        System.out.println(" ");
    }

}
