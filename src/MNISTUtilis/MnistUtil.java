package MNISTUtilis;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;

public class MnistUtil {
	
	private static MnistMatrix[] mnistMatrixs;
	
	public MnistUtil() throws IOException {
		mnistMatrixs = new MnistDataReader().readData("mnistdata/train-images.idx3-ubyte", "mnistdata/train-labels.idx1-ubyte");
	}
	
	public static ArrayList<MnistImage> getImages(int count) throws IOException {
		ArrayList<MnistImage> outputarray = new ArrayList<MnistImage>();
		List<MnistMatrix> list =Arrays.asList(mnistMatrixs);
		Collections.shuffle(list);
		for (int j = 0; j < count; j++) {
			MnistMatrix matrix = list.get(j);
			ArrayList<Float> image = new ArrayList<Float>();
			for (int r = 0; r < matrix.getNumberOfRows(); r++) {
				for (int c = 0; c < matrix.getNumberOfColumns(); c++) {
					image.add((float) (matrix.getValue(r, c)));
				}
			}
			outputarray.add(new MnistImage(image,matrix.getLabel()));
		}
		return outputarray;
	}
	
    private static float normalizeMINST(float value) {
    	return value / 255;
    }
    
	public static BufferedImage getImageFromArray(int[] pixels, int w, int h) {
	    BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    WritableRaster raster = image.getRaster();
	    raster.setPixels(0,0,w,h,pixels);
	    return image;
	}
	
    public static byte[] toByteArray(BufferedImage bi, String format)
            throws IOException {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, format, baos);
            byte[] bytes = baos.toByteArray();
            return bytes;

    }

}
