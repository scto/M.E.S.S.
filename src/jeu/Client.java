package jeu;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client extends Thread {

	private InetAddress ipAddress;
	private DatagramSocket socket;
	private Endless endless;
	
	public Client(String ipAddress, Endless endless) {
		super();
		try {
			this.ipAddress = InetAddress.getByName(ipAddress);
			this.socket = new DatagramSocket();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		this.endless = endless;
	}
	
	@Override
	public void run() {
		byte[] data = new byte[1024];
		DatagramPacket packet = new DatagramPacket(data, data.length);
		try {
			socket.receive(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void send(String string) {
		byte[] data = new byte[1024];
		data = string.getBytes();
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 2311);
		try {
			socket.send(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
