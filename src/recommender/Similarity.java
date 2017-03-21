package recommender;

public class Similarity {

	private static int User = 944;
	private static int Item = 1683;
	private static double[][] similar = new double[Item][Item];
	private static double[] meanvalue = new double[User];
	
	/**
	 * This function is used to calculate the similarity matrix
	 * from the given input matrix
	 * 
	 * @param matrix - The input matrix containing Ratings for
	 * 				   Items by Users
	 *            .
	 */
	public void calculateSimilarity(int[][] matrix){
		
		double mean;
		double num1,num2,numerator;
		double deno1,deno2,denominator;
		int Items = 0;
		
		System.out.println("Similarity Process Commences");
		for(int i=1; i<User; i++){
			mean=0.0;
			for(int j=1; j<Item; j++){
				if(matrix[i][j]>0){
					mean = mean + matrix[i][j];
					Items++;
				}
			}
			meanvalue[i] = mean/Items;
		}
		
		for(int i=1; i<Item; i++){
	
			for(int j=i+1;j<Item;j++){
				
				numerator = 0.0;
				denominator = 0.0;
				deno1 = 0.0;
				deno2 = 0.0;
				num1 = 0.0;
				num2 = 0.0;
				
				for(int k=1; k<User; k++){
					num1 = matrix[k][i] - meanvalue[k];
					num2 = matrix[k][j] - meanvalue[k];
					numerator = numerator + (num1*num2); 
					deno1 = deno1 + Math.pow(num1, 2);
					deno2 = deno2 + Math.pow(num2, 2);
				}
				
				deno1 = Math.sqrt(deno1);
				deno2 = Math.sqrt(deno2);
								
				denominator = deno1 * deno2;
				
				similar[i][j] = numerator/denominator;
				similar[j][i] = similar[i][j];
			
			}
	
		}
		
	}

	/**
	 * This function returns the similarity matrix calculated
	 * in the calculateSimilarity() Function
	 *            .
	 * @return similarity matrix.
	 */
	public double[][] getSimilar() {
		System.out.println("Similarity Process Completed");
		return similar;
	}

}
