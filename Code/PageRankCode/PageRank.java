import java.util.*;
import java.io.*;
import java.util.ArrayList;

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
     *                              - la fonction principale càd retranscrire l'algo en code (en cours)
     *                              - ecrire la main qui lira des fichiers et appellera la fonction de calcul  V                   
     */

    /**
     * Main utilisé pour des tests
     */
    public static void main()
    {
        /* Initialisation de toutes les données nécéssaire*/

        String [] valeur_initial = initialisation();  //Paramètrage de la fonction
        double [][] matrice = TestReader.read_file(valeur_initial[0]);  //Création de la matrice du graphe dirigé
        double alpha = Double.parseDouble(valeur_initial[1]);    //coefficient de téléportation
        double [] vector_xT = create_vector(matrice.length,1);  //vecteur xT
        double [][] matrice_proba_t = transition_matrix(matrice);   //matrice de probabilité de transition

        double nombre_page = matrice.length;    //nombre de page de la matrice
        double facteur = (1 - alpha)/nombre_page;   
        double [] vector_eT = create_vector(matrice.length,0);  //vecteur eT
        //double [] vector_vT = convert(valeur_initial[2]);

        /* Implémentation de la formule */

        double [] terme_1;  //contenir le 1er terme
        double [] terme_2;  //contenir le 2eme terme
        double [] vector_PR = create_vector(matrice.length,0);    //contenir le vecteur Page Rank

        while( condition(vector_xT, vector_PR) ) 
        {
            vector_PR = vector_xT;  
            terme_1 = multiply(vector_xT , matrice_proba_t , alpha);    //Premier terme de l'algorithme 
            terme_2 = multiply(vector_eT , facteur);    //Second terme de l'algorithme
            vector_xT = sum(terme_1 , terme_2); //Résultat
            print_a_vector(vector_xT);
        }
        vector_PR = vector_xT;
        print_a_vector(vector_PR);
    }
    
    public static double [] convert(String to_convert)
    {
        return null;
    }
    
    

    public static boolean condition(double [] first, double [] second)
    {
        double [] temp = minus(second, first);
        return ( temp[0] < 0.00000001 );
    }

    /**
     * METHODE MINUS
     * 
     * --> Calcule la différences de deux vecteurs
     *
     * @param  vector_1     Un vecteur de taille N
     * @param  vector_2     Un vecteur de taille N
     * @return     La différence entre les deux vecteurs
     */
    public static double [] minus(double [] vector_1, double [] vector_2)
    {
        for( int runner=0 ; runner< vector_1.length;runner++)
        {
            vector_1[runner] -= vector_2[runner];
        }
        return vector_1;
    }

    public static String [] initialisation()
    {
        Scanner scan_1 = new Scanner( System.in );    //Nom du fichier contenant la matrice
        System.out.print("Entrez le nom du fichier contenant la matrice: ");
        String fichier = scan_1.nextLine();
        scan_1.close();

        Scanner scan_2 = new Scanner( System.in );    //Valeur de alpha
        System.out.print("Entrez la valeur du coefficient alpha: ");
        String alpha = scan_2.nextLine();
        scan_2.close();

        Scanner scan_3 = new Scanner( System.in );    //Vecteur personalisation
        System.out.print("Entrez le vecteur de personalisation: ");
        String vector_personalisation = scan_3.nextLine();
        scan_3.close();

        String [] valeurs_initial = { fichier , alpha , vector_personalisation };

        return valeurs_initial;
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
     *   -> Pour le premier terme de la formule
     *  
     * @param vector Le vecteur xT
     * @param matrice La matrice de probabilité de transition
     * @param alpha Le coefficient alpha
     * 
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
    public static double [] create_vector(int longueur, int mode)
    {        
        double [] vector = new double [longueur];
        for( int runner=0 ; runner < longueur ; runner++ )
        {
            if( mode == 1 ) //mode xT (1, 0, 0, ...)
            {
                if(runner == 0)
                {
                    vector [runner]=1;
                }
                vector [runner]=0;
            }
            else    //mode eT ( 1, 1, 1 ...)
            {
                vector [runner] = 1;
            }
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
