package MNISTUtilis;

import java.util.ArrayList;

public class MnistImage {
	
	private ArrayList<Float> imgdata;
	private int label;
	
	public MnistImage(ArrayList<Float> imgdata, int label) {
		this.imgdata = imgdata;
		this.label = label;
	}
	
	public ArrayList<Float> getImgdata() {
		return imgdata;
	}
	
	public int getLabel() {
		return label;
	}

}
