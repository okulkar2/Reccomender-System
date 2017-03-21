package recommender;

public class Prediction {
	
	private static int User = 944;
	private static int Item = 1683;
	private static int[][] predictMatrix = new int[User][Item];
	
	/**
	 * This function is used to calculate the prediction matrix
	 * for given input matrix & similarityMatrix
	 * 
	 * @param input matrix, similarity matrix .
	 * 
	 */
	public void predictValues(int[][] matrix, double[][] similarMatrix){

		double num1,num2;
		double denominator,numerator;
		double predict=0.0;
		
		System.out.println("Prediction Process Commences");
		for(int i=1;i<User;i++){
			for(int j=1;j<Item;j++){
				if(matrix[i][j]==0){
					num1=0.0;
					num2=0.0;
					denominator=0.0;
					numerator=0.0;
					
					for(int k=1;k<Item;k++){
						if(matrix[i][k]==0)
							continue;
						num1 = similarMatrix[j][k];
						num2 = matrix[i][k];
						
						numerator = numerator + (num1*num2);
						denominator = denominator + Math.abs(num1);
					}
					
					predict = numerator/denominator;
					predict = Math.round(predict);
					if(predict<1){
						predictMatrix[i][j] = 1;
					}
					else if(predict>5){
						predictMatrix[i][j] = 5;
					}
					else{
						predictMatrix[i][j] = (int)predict;
					}
					
				} else{
					predictMatrix[i][j] = matrix[i][j];
				}
					
			}
		}
	}

	/**
	 *This function returns the prediction matrix calculated
	 *in the predictValues() Function
	 *            .
	 * @return prediction matrix.
	 */
	public int[][] getPredictMatrix() {
		System.out.println("Prediction Process Completed");
		return predictMatrix;
	}

}

