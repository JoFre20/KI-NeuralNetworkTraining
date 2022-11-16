package lib;

import NeuralNetwork.Layer;

public class NeuralNetworkSave {
	
	private int SAMPLE;
	private int ITERATIONS;
	private Layer[] NETWORK;
	private long TIME;
	private String CREATED;
	
	public NeuralNetworkSave(int sample, int iterations, Layer[] network, long time, String created) {
		this.SAMPLE = sample;
		this.ITERATIONS = iterations;
		this.NETWORK = network;
		this.TIME = time;
		this.CREATED = created;
	}
	
	public int getSAMPLE() {
		return SAMPLE;
	}
	
	public int getITERATIONS() {
		return ITERATIONS;
	}
	
	public Layer[] getNETWORK() {
		return NETWORK;
	}
	
	public long getTIME() {
		return TIME;
	}

}
