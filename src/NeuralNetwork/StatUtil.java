package NeuralNetwork;
import java.util.ArrayList;
import java.util.Map;

public class StatUtil {
	

	// Get a random numbers between min and max
    public static float RandomFloat(float min, float max) {
        float a = (float) Math.random();
        float num = min + (float) Math.random() * (max - min);
        if(a < 0.5)
            return num;
        else
            return -num;
    }
    
    // Sigmoid function
    public static float Sigmoid(float x) {
        return (float) (1.0/(1+Math.pow(Math.E, -x)));
    }
    
    // Derivative of the sigmoid function
    public static float SigmoidDerivative1(float x) {
        return Sigmoid(x)*(1-Sigmoid(x));
    }
    
    // Used for the backpropagation
    public static float squaredError(float output,float target) {
    	return (float) (0.5*Math.pow(2,(target-output)));
    }
    
    // Used to calculate the overall error rate (not yet used)
    public static float sumSquaredError(float[] outputs,float[] targets) {
    	float sum = 0;
    	for(int i=0;i<outputs.length;i++) {
    		sum += squaredError(outputs[i],targets[i]);
    	}
    	return sum;
    }
    
    public static float[] ArrayListtoFloatArray(ArrayList<Float> arraylist) {
    	float[] floatArray = new float[arraylist.size()];
    	int i = 0;

    	for (Float f : arraylist) {
    	    floatArray[i++] = (f != null ? f : Float.NaN); // Or whatever default you want.
    	}
    	return floatArray;
    }
    
    public static <K extends Comparable<K>, V> Map.Entry<K, V>
    getMaxEntryInMapBasedOnKey(Map<K, V> map)
    {
        // To store the result
        Map.Entry<K, V> entryWithMaxKey = null;
 
        // Iterate in the map to find the required entry
        for (Map.Entry<K, V> currentEntry : map.entrySet()) {
 
            if (
 
                // If this is the first entry,
                // set the result as this
                entryWithMaxKey == null
 
                // If this entry's key is more than the max key
                // Set this entry as the max
                || currentEntry.getKey()
                        .compareTo(entryWithMaxKey.getKey())
                    > 0) {
 
                entryWithMaxKey = currentEntry;
            }
        }
 
        // Return the entry with highest key
        return entryWithMaxKey;
    }
}
