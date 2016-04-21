
/**
 * Page Rank
 * 
 * @author Antoine & Antonio
 * @version Avril 2016
 */
public class PageRank
{
    /**
     * Main utilisé pour des tests
     */
    public static void main()
    {
        float[][] matrice = { {1,2},{8,4}};
        float[][] matrice2 = { {5,6},{7,8} };
        print_a_matrix(matrice);    //Imprime la matrice
        print_a_matrix(transpose(matrice)); //Imprime la meme matrice mais en transpose
        print_a_vector(degre(matrice));// Imprime et calcule le degre de la matrice de base
    }

    /**
     * Méthode qui calcule le score PageRank à partir de la matrice donnée en argument.
     * 
     * 
     * @pre: Une matrice d’adjacence A d’un graphe dirigé, pondéré 
     * 		 et régulier G ainsi qu’un paramètre de téléportation 
     * 		 α entre 0 et 1 (inutile de le vérifier) et un vecteur de personnalisation q.
     * @post: Un vecteur x contenant les scores d’importance des noeuds ordonnés 
     * 		  dans le même ordre que la matrice d’adjacence.
     */
    public float[] calcul (float matrice[][]){
        return null;

    }

    /**
     * Method transpose
     *
     * @param matrice A parameter
     * @return The return value
     */
    public static float [][] transpose(float [][] matrice)
    {
        float [][] matrice_transpose = new float [matrice.length] [matrice.length]; 

        for(int runner = 0 ; runner < matrice.length ; runner++)
        {
            for(int runner2 = 0 ; runner2 < matrice.length ; runner2++)
            {
                matrice_transpose[runner2][runner] = matrice[runner][runner2];
            }
        }
        return matrice_transpose;
    }

    /**
     * Method multiply
     *
     * @param matrice_A A parameter
     * @param matrice_B A parameter
     * @return The return value
     */
    public static float[][] multiply(float[][] matrice_A, float[][] matrice_B) {
        int rows_A = matrice_A.length;
        int columns_A = matrice_A [0].length; 
        int columns_B = matrice_B[0].length;
        float[][] matrice_AB = new float[rows_A][columns_B];
        for (int i = 0; i < rows_A; i++) {
            for (int j = 0; j < columns_B; j++) {
                for (int k = 0; k < columns_A; k++) {
                    matrice_AB[i][j] = matrice_AB[i][j] + matrice_A[i][k] * matrice_B[k][j];
                }
            }
        }
        return matrice_AB;
    }

    /**
     * Method degre
     *
     * @param matrice A parameter
     * @return The return value
     */
    public static float[] degre(float[][] matrice)
    {
        float[] matrice_degre=new float [matrice.length]; //On cree la matrice qui contiendra les degre
        for ( int runner_1=0 ; runner_1 < matrice.length ; runner_1++)
        {
            float sum=0; //Stockera le degre de la ligne runner_1
            for ( int runner_2=0 ; runner_2 < matrice.length ; runner_2++)
            {
                sum+=matrice[runner_1][runner_2];
            }
            matrice_degre [runner_1] = sum;
        }
        return matrice_degre;
    }

    /**
     * Method print_a_matrix
     *
     * @param matrice A parameter
     */
    public static void print_a_matrix(float[][] matrice)
    {
        System.out.println("\n");
        for(int runner = 0 ; runner < matrice.length ; runner++)
        {
            for(int runner2 = 0 ; runner2 < matrice.length ; runner2++)
            {
                System.out.print(" " + matrice[runner][runner2]);
            }
            System.out.println("");
        }
    }

    /**
     * Method print_a_matrix
     *
     * @param matrice A parameter
     */
    public static void print_a_vector(float[] vector)
    {
        System.out.println("\n");
        for(int runner = 0 ; runner < vector.length ; runner++)
        {
            System.out.println(vector[runner]);
        }
    }

}
