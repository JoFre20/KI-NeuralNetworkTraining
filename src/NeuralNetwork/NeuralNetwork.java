package NeuralNetwork;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import MNISTUtilis.MnistImage;
import MNISTUtilis.MnistUtil;
import Utilis.FileHelper;
import Utilis.JsonHelper;
import lib.NeuralNetworkSave;

public class NeuralNetwork {
	//Set Parameter
	final static int SAMPLE = 10000;
	final static int ITERATIONS = 10000;
	
	static FileHelper fileHelper;
	
	static MnistUtil MUtil;
	
	static Layer[] layers;
    
    
    static TrainingData[] tDataSet;
   
    
    public static void main(String[] args) throws IOException {
    	fileHelper = new FileHelper();
    	MUtil = new MnistUtil();
    	fileHelper.init();
        // Set the Min and Max weight value for all Neurons
    	Neuron.setRangeWeight(0,1);
    	
    	// Create the layers
    	layers = new Layer[4];
    	layers[0] = null; // Input Layer 784,16
    	layers[1] = new Layer(784,16); // Hidden Layer 16,16
    	layers[2] = new Layer(16,16); // Hidden Layer 16,16
    	layers[3] = new Layer(16,10);// Output Layer 16,10
        
    	// Create the training data
    	CreateTrainingData();
    	
        System.out.println("============");
        System.out.println("Output before training");
        System.out.println("============");
        System.out.println("");
        for(int i = 0; i < tDataSet.length; i++) {
            forward(tDataSet[i].data);
            System.out.println("============");
            System.out.println("0 -- " + layers[3].neurons[0].value);
            System.out.println("1 -- " + layers[3].neurons[1].value);
            System.out.println("2 -- " + layers[3].neurons[2].value);
            System.out.println("3 -- " + layers[3].neurons[3].value);
            System.out.println("4 -- " + layers[3].neurons[4].value);
            System.out.println("5 -- " + layers[3].neurons[5].value);
            System.out.println("6 -- " + layers[3].neurons[6].value);
            System.out.println("7 -- " + layers[3].neurons[7].value);
            System.out.println("8 -- " + layers[3].neurons[8].value);
            System.out.println("9 -- " + layers[3].neurons[9].value);
            System.out.println("expectedOutput: " + tDataSet[i].Output);
            System.out.println("============");
            System.out.println("");
        }
       
        train(ITERATIONS, 0.05f);

        System.out.println("============");
        System.out.println("Output after training");
        System.out.println("============");
        System.out.println("");
        for(int i = 0; i < 10; i++) {
        	HashMap<Float, String> outputs = new HashMap<Float, String>();
            forward(tDataSet[i].data);
            System.out.println("============");
            outputs.put(layers[3].neurons[0].value, "0");
            outputs.put(layers[3].neurons[1].value, "1");
            outputs.put(layers[3].neurons[2].value, "2");
            outputs.put(layers[3].neurons[3].value, "3");
            outputs.put(layers[3].neurons[4].value, "4");
            outputs.put(layers[3].neurons[5].value, "5");
            outputs.put(layers[3].neurons[6].value, "6");
            outputs.put(layers[3].neurons[7].value, "7");
            outputs.put(layers[3].neurons[8].value, "8");
            outputs.put(layers[3].neurons[9].value, "9");
            System.out.println("KI-Output: " + StatUtil.getMaxEntryInMapBasedOnKey(outputs).getValue());
            System.out.println("expectedOutput: " + tDataSet[i].Output);
            System.out.println("============");
            System.out.println("");
            outputs = null;
        }
    }
   
    public static void CreateTrainingData() {
    	tDataSet = new TrainingData[SAMPLE];
    	int i = 0;
		for (MnistImage mnistimg : MUtil.getImages(SAMPLE)) {
			tDataSet[i] = new TrainingData(StatUtil.ArrayListtoFloatArray(mnistimg.getImgdata()), (float) mnistimg.getLabel());
			i = i+1;
		}
		System.out.println(tDataSet);
    }
    
    public static void forward(float[] inputs) {
    	// First bring the inputs into the input layer layers[0]
    	layers[0] = new Layer(inputs);
    	
        for(int i = 1; i < layers.length; i++) {
        	for(int j = 0; j < layers[i].neurons.length; j++) {
        		float sum = 0;
        		for(int k = 0; k < layers[i-1].neurons.length; k++) {
        			sum += layers[i-1].neurons[k].value*layers[i].neurons[j].weights[k];
        		}
        		sum += layers[i].neurons[j].bias; // TODO add in the bias
        		layers[i].neurons[j].value = StatUtil.Sigmoid(sum);
        	}
        } 	
    }
    

    public static void backward(float learning_rate,TrainingData tData) {
    	
    	int number_layers = layers.length;
    	int out_index = number_layers-1;
    	
    	// Update the output layers 
    	// For each output
    	for(int i = 0; i < layers[out_index].neurons.length; i++) {
    		// and for each of their weights
    		float output = layers[out_index].neurons[i].value;
    		float target = tData.expectedOutput[i];
    		float derivative = output-target;
    		float delta = derivative*(output*(1-output));
    		layers[out_index].neurons[i].gradient = delta;
    		for(int j = 0; j < layers[out_index].neurons[i].weights.length;j++) { 
    			float previous_output = layers[out_index-1].neurons[j].value;
    			float error = delta*previous_output;
    			layers[out_index].neurons[i].cache_weights[j] = layers[out_index].neurons[i].weights[j] - learning_rate*error;
    		}
    	}
    	
    	//Update all the subsequent hidden layers
    	for(int i = out_index-1; i > 0; i--) {
    		// For all neurons in that layers
    		for(int j = 0; j < layers[i].neurons.length; j++) {
    			float output = layers[i].neurons[j].value;
    			float gradient_sum = sumGradient(j,i+1);
    			float delta = (gradient_sum)*(output*(1-output));
    			layers[i].neurons[j].gradient = delta;
    			// And for all their weights
    			for(int k = 0; k < layers[i].neurons[j].weights.length; k++) {
    				float previous_output = layers[i-1].neurons[k].value;
    				float error = delta*previous_output;
    				layers[i].neurons[j].cache_weights[k] = layers[i].neurons[j].weights[k] - learning_rate*error;
    			}
    		}
    	}
    	
    	// Here we do another pass where we update all the weights
    	for(int i = 0; i< layers.length;i++) {
    		for(int j = 0; j < layers[i].neurons.length;j++) {
    			layers[i].neurons[j].update_weight();
    		}
    	}
    	
    }
    
    // This function sums up all the gradient connecting a given neuron in a given layer
    public static float sumGradient(int n_index,int l_index) {
    	float gradient_sum = 0;
    	Layer current_layer = layers[l_index];
    	for(int i = 0; i < current_layer.neurons.length; i++) {
    		Neuron current_neuron = current_layer.neurons[i];
    		gradient_sum += current_neuron.weights[n_index]*current_neuron.gradient;
    	}
    	return gradient_sum;
    }
 
    
    // This function is used to train being forward and backward.
    public static void train(int training_iterations,float learning_rate) {
    	long begin = System.currentTimeMillis();
    	for(int i = 0; i < training_iterations; i++) {
    		System.out.println((i/(float) ITERATIONS)*100 + "% sind abgeschlossen");
    		for(int j = 0; j < tDataSet.length; j++) {
    			forward(tDataSet[j].data);
    			backward(learning_rate,tDataSet[j]);
    		}
    	}
    	long end = System.currentTimeMillis();
    	//Save Layers as Json
        SimpleDateFormat formatterdate = new SimpleDateFormat("dd.MM.yyyy-HH_mm_ss");
        Date date = new Date();
    	String saveDate = formatterdate.format(date);
    	String json = JsonHelper.ClassToJson(new NeuralNetworkSave(SAMPLE, ITERATIONS, layers, end - begin, saveDate));
    	System.out.println(json);
    	fileHelper.writeToDisk(FileHelper.MAIN_DIR + "/" + saveDate, json);
    }

}
