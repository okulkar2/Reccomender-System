package recommender;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Driver {

	public static void main(String[] args) {

		int User = 944;
		int Item = 1683;
		FileProcessor fileProcessor=null;
		FileInputStream inputStream=null;
		FileOutputStream outputStream=null;
		Similarity similar = new Similarity(); 
		Prediction predict = new Prediction();
		int[][] recommenderMatrix = new int[User][Item];
		double[][] similarMatrix= new double[Item][Item];
		int[][] predictMatrix= new int[User][Item];
		
		try {	
			     
				inputStream = new FileInputStream(args[0]);
				outputStream = new FileOutputStream("output.txt");
				fileProcessor=new FileProcessor(inputStream,outputStream);
				
				int i,j,value;
				String line;
				
				//reading values for input matrix which contains 
				//ratings given by User to the Item
				while((line=fileProcessor.readLineFromFile())!=null) {	
						
					String[] tokens = line.split("[ \t]+");
					i = Integer.parseInt(tokens[0]);
					j = Integer.parseInt(tokens[1]);
					value = Integer.parseInt(tokens[2]);
					recommenderMatrix[i][j] = value;			
				}
				
				//Used to compute Similarity Matrix
				similar.calculateSimilarity(recommenderMatrix);
				similarMatrix = similar.getSimilar();
				
				//Used to predict the ratings for Items not rated
				predict.predictValues(recommenderMatrix, similarMatrix);
				predictMatrix = predict.getPredictMatrix();
				
				//Used to write the complete Input Matrix with Predicted
				//Values into output.txt
				System.out.println("Writing to output.txt");
				String result = null;
				for(i=1;i<User;i++){
					for(j=1;j<Item;j++){
						result = i+" "+j+" "+predictMatrix[i][j];
						fileProcessor.writeLineToFile(result);
					}
				}
				
				System.out.println("output.txt ready");
				
			
		} catch (FileNotFoundException e) {
			
			System.err.println("Error opening file. Please check if the file name is valid !!");
			System.exit(1);
		
		} catch (NumberFormatException e) {
			
			System.err.println("Check File !!");
		} finally { 
			
			if(fileProcessor!=null)
				fileProcessor.closeStream();
		}
	}

}
