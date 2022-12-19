package Utilis;


import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;

import MNISTUtilis.MnistImage;

public class ImageHelper {
	
	public static ArrayList<Float> getImageDatafromDataURL(String dataUrl) throws IOException {
		int dataStartIndex = dataUrl.indexOf(",") + 1;
		String data = dataUrl.substring(dataStartIndex);
		data = data.replace(" ", "+");
		byte[] imageData = java.util.Base64.getDecoder().decode(data);
		FastRGB fastRGB = new FastRGB(ImageIO.read(new ByteArrayInputStream(imageData)));
		ArrayList<Float> image = new ArrayList<Float>();
		for (int w = 0; w < fastRGB.getWidth(); w++) {
			for (int h = 0; h < fastRGB.getHeight(); h++) {
				int rgb = fastRGB.getRGB(w, h);
				int r = (rgb >> 16) & 0xFF;
				int g = (rgb >> 8) & 0xFF;
				int b = (rgb & 0xFF);
				int gray = (r + g + b) / 3;
				//System.out.println(rgb);
				image.add(normalizeMINST(gray));
			}
		}
		return image;
	}
	
	public static ArrayList<Float> BufferMnistImage(ArrayList<Float> mnistData) throws IOException {
		System.out.println(mnistData);
		List<Float> floatList = mnistData;
		int[] intArray = new int[floatList.size()*3];
		int i = 0;

		for (Float f : floatList) {
			for (int j = 0; j < 3; j++) {
				//System.out.println("j: " + j + " | i: " + i);
				int p = i + j;
				intArray[p] = Math.round(f);
				//System.out.println("Punkt: "+p);
			}
			i++;
			i++;
			i++;
			//System.out.println("------");
		}
	
		BufferedImage img = getImageFromArray(intArray, 28, 28);
        byte imagebytearray[] = toByteArray(img, "png");
        String imagetobase64 = Base64.encodeBase64String(imagebytearray);
        String dataURL = "data:image/png;base64," + imagetobase64;
        System.out.println(dataURL);
        return getImageDatafromDataURL(dataURL);
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
