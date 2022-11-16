package NeuralNetwork;

public class TrainingData {
	
    float[] data;
    float[] expectedOutput;
    float Output;
   
    public TrainingData(float[] data, float expectedoutput) {
        this.data = data;
        if(expectedoutput == 0) {
    		this.expectedOutput = new float[] {1,0,0,0,0,0,0,0,0,0};
    	} else if(expectedoutput == 1) {
    		this.expectedOutput = new float[] {0,1,0,0,0,0,0,0,0,0};
    	} else if(expectedoutput == 2) {
    		this.expectedOutput = new float[] {0,0,1,0,0,0,0,0,0,0};
    	} else if(expectedoutput == 3) {
    		this.expectedOutput = new float[] {0,0,0,1,0,0,0,0,0,0};
    	} else if(expectedoutput == 4) {
    		this.expectedOutput = new float[] {0,0,0,0,1,0,0,0,0,0};
    	} else if(expectedoutput == 5) {
    		this.expectedOutput = new float[] {0,0,0,0,0,1,0,0,0,0};
    	} else if(expectedoutput == 6) {
    		this.expectedOutput = new float[] {0,0,0,0,0,0,1,0,0,0};
    	} else if(expectedoutput == 7) {
    		this.expectedOutput = new float[] {0,0,0,0,0,0,0,1,0,0};
    	} else if(expectedoutput == 8) {
    		this.expectedOutput = new float[] {0,0,0,0,0,0,0,0,1,0};
    	} else if(expectedoutput == 9) {
    		this.expectedOutput = new float[] {0,0,0,0,0,0,0,0,0,1};
    	}
        this.Output = expectedoutput;
    		
    }
}
