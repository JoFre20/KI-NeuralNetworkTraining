package MNISTUtilis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MnistUtil {
	
	private static MnistMatrix[] mnistMatrixs;
	
	public MnistUtil() throws IOException {
		mnistMatrixs = new MnistDataReader().readData("mnistdata/train-images.idx3-ubyte", "mnistdata/train-labels.idx1-ubyte");
	}
	
	public static ArrayList<MnistImage> getImages(int count) {
		ArrayList<MnistImage> outputarray = new ArrayList<MnistImage>();
		List<MnistMatrix> list =Arrays.asList(mnistMatrixs);
		Collections.shuffle(list);
		for (int j = 0; j < count; j++) {
			MnistMatrix matrix = list.get(j);
			ArrayList<Float> image = new ArrayList<Float>();
			for (int r = 0; r < matrix.getNumberOfRows(); r++) {
				for (int c = 0; c < matrix.getNumberOfColumns(); c++) {
					image.add(normalizeMINST(matrix.getValue(r, c)));
				}
			}
			outputarray.add(new MnistImage(image,matrix.getLabel()));
		}
		return outputarray;
	}
	
    private static float normalizeMINST(float value) {
    	return value / 255;
    }

}
