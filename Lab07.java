
/*[CS2302 Spring 2015]
 * Lab07
 * Omar Mejia
 * Professor: Dr. Olac Fuentes
 * TA: Jesus Medrano 
 * Last modified April May 5, 2015
 * 
 * In this lab a program was to simulate a 0-1 knapsack and fractional knapsack problem.  
 * This was to demonstrate the proper execution for 0-1 knapsack problem recursivley and 
 * fractional knapsack through an iterative method.
 */
public class Lab07 {

	public static void main(String [] args){
		int [] weight1 = {10, 8, 12, 3};
		int [] value1 = {15, 10, 14, 6};
		int W1 = 12;//weight goal
		int V1 = 0;//value goal

		int [] weight2 = {1, 2, 4, 5};
		int [] value2 = {2, 3, 4, 6};
		double W2 = 7;// weight goal
		
		System.out.println("0-1 Knapsack list of items' weight with respective values: ");
		for(int i = 0; i < weight1.length; i++)
			System.out.println("Item: " + i + "\n weight=" + weight1[i] + ", value=" + value1[i] );
		System.out.println("Highest-Value load for 0-1 knapsack: "+ knapsack(value1, weight1, W1, V1, value1.length-1));

		System.out.println("\nFractional knapsack list:");
		knap [] list = fractionArray(weight2, value2);
		for(int i = 0; i< list.length; i++)
			System.out.println("Item: " + i + "\n value=" + list[i].value+ " , weight=" + list[i].weight + ", value/weight= " +list[i].ratio );
		System.out.println();
		insertionSort(list, list.length);
		System.out.println("Total value for fractional knapsack:" + fractional(list,W2 , list.length-1));
	}//end main

	public static double fractional(knap [] list, double weightGoal, int n){
		double value = 0;
		int i = 0;
		for(i = n; i >= 0; i--){
			if(weightGoal > 0){
				if(list[i].weight <= weightGoal){
					weightGoal -= list[i].weight;
					value += list[i].value;
				}
				else{
					double fraction = (weightGoal/list[i].weight);
					weightGoal -= fraction*list[i].weight;
					value += fraction*list[i].value;
				}
			}
		}
		return value;
	}

	//0-1 knapsack method
	public static int knapsack(int [] v, int [] w, int Wgoal, int Vgoal, int n){
		if(n < 0)
			return Vgoal;
		int value1 = 0;
		int value2 = 0;
		if(Wgoal - w[n] >= 0){
			value1 = knapsack(v, w, Wgoal-w[n], Vgoal+v[n], n-1);
		}
		value2 = knapsack(v, w, Wgoal, Vgoal, n-1);
		if(value1 > value2)
			return value1;
		return value2;
	}
	
	//This method stores the value for (v/w) array 
	public static knap[] fractionArray(int[] weight, int[] value){
		knap [] array = new knap [weight.length];
		for(int i = 0; i < weight.length  && i < value.length; i++){
			array[i] = new knap(weight[i], value[i]);
		}
		return array;
	}

	//insertion sort method used to sort the fraction array
	public static void insertionSort(knap[] array, int size){
		for(int i = 1; i < array.length; i++){
			double current = array[i].ratio;
			knap GGG = array[i];
			int k;
			for(k = i-1; k >= 0 && array[k].ratio > current; k--){
				array[k+1] = array[k];
			}//end for
			array[k+1] = GGG;
		}//end for
		System.out.println("(V/W) array after Insertion Sort");
		for(int i = 0; i < array.length; i++)
			System.out.print(array[i].ratio + ", ");
		System.out.println();
	}//end insertionSort
}//end class
