import java.util.*;
public class TravelingSalesPerson {
    /**fills a symmetric distance matrix with random values
     * between 0 and 1
     * @param size how big the matrix should be //banyak kota
     * @param seed where to start the random number generator //sekedar parameter, agar tetap, tidak random
     * @return the symmetric distance matrix
     */
    public double[][] fillDistanceMatrix(int size, int seed)
    {
        double[][] distances=new double[size][size];
        Random random=new Random(seed);//ini pencetus
        for(int i=0; i<size; i++)
            for(int j=i+1; j<size; j++)
            {
                distances[i][j]=random.nextDouble();
                distances[j][i]=distances[i][j];
//                System.out.println("jarak "+distances[i][j]);
            }
//        for(int i=0; i< distances.length; i++)
//                System.out.println(Arrays.toString(distances[i]));
//        System.out.println("jarak "+distances[5][8]);
        return distances;
    }

    /**swaps two cities in the tour and returns the
     * change in cost of the tour
     * @param tour the current tour
     * @param distances the symmetric distance matrix
     * @return the change in cost, negative indicating a
     * shorter new tour, and positive indicating an increase
     * in tour length
     */
    public double swap(int[] tour, int tourIndexA,  int tourIndexB,  double[][] distances)
    {

        double costAwal=0;
        double costAkhir=0;
        for (int i =0; i<tour.length-1; i++){
            costAwal+=distances[tour[i]][tour[i+1]];
        }
        System.out.println("Matriks awal "+Arrays.toString(tour));
        System.out.println("cost awal "+costAwal);


        int tempA = tour[tourIndexA];
        int tempB = tour[tourIndexB];

        tour[tourIndexA]= tempB;
        tour[tourIndexB]= tempA;

        for (int i =0; i<tour.length-1; i++){
                costAkhir+=distances[tour[i]][tour[i+1]];
        }
        System.out.println("cost akhir "+costAkhir);
        System.out.println("Matriks akhir "+Arrays.toString(tour));
        System.out.println();
        return 0 /*costAkhir-costAwal*/;
    }

    /**performs a hill climbing algorithm on the TSP with
     * random restart
     * @param tour where the best solution will be stored.
     * Initially the contents of this array are unspecified,
     * but it does include enough storage to keep the resulting
     * approximated optimal tour.
     * @param distances the symmetric distance matrix
     * @param restarts the number of times to start at a
     * new random solution
     * @param seed the seed for the random restart
     * @return the cost of the approximated optimal tour
     */
    public double getBestTour(int[] tour, double[][] distances, int restarts, int seed){
        //must use random to create new randomly ordered tours
        Random random=new Random(seed);
        Random rn=new Random();

        //bikin temp cost
        double tempCost=0;
        double tempCostTerbaik=0;


        int indexA = rn.nextInt(tour.length);
        int indexB = rn.nextInt(tour.length);

        for(int i=0; i<restarts; i++)
        {
            randomizeTour(tour, random);
            //put code here
            //swap disini
            for (int j = 0; j<2;j++){
                swap(tour, indexA, indexB, distances );

            }
            //masukkan value nya di array. nanti ada 20 isinya.
        }

//        System.out.println(Arrays.toString(tour));
        for (int i = 0; i<tour.length-1; i++){
            tempCostTerbaik+=distances[i][i+1];
        }
        /*double swapCost = tempCostTerbaik;
        if(swapCost<0){
            for (int i = 0; i<tour.length-1; i++){
                tempCost+=distances[tour[i]][tour[i+1]];

                tempCostTerbaik=tempCost;
            }
            System.out.println("temp cost " +tempCost);
//            System.out.println("acc " + swapCost);
        }*/
        return tempCostTerbaik;
        //return cost paling optimal
    }

    public void randomizeTour(int[] tour, Random random)
    {
        for(int i=0; i<tour.length; i++)
            tour[i]=i;
        //randomize the tour from here

        
    }

    public  void run()
    {
        int[] sizes={10};
        int[] seeds={1};
        int[] restarts={20};

        TravelingSalesPerson sales=new TravelingSalesPerson();
        for(int i=0; i<sizes.length; i++)
        {
            double[][] distances=sales.fillDistanceMatrix(sizes[i], seeds[i]);
            int[] tour=new int[sizes[i]];
            double cost=sales.getBestTour(tour, distances, restarts[i], seeds[i]);
            System.out.println("The following tour costs "+cost);
            for(int j=0; j<tour.length; j++)
                System.out.print(tour[j]+" ");
            System.out.println();
        }
    }
}
