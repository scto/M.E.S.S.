package jeu;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

public class Client extends Thread {

	private InetAddress ipAddress;
	private DatagramSocket socket;
	
	public Client(String ipAddress, EndlessMode endless) {
		super();
		try {
			this.ipAddress = InetAddress.getByName(ipAddress);
			this.socket = new DatagramSocket();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		}
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
	
	public void send() {
		byte[] data = new byte[1024];
//		data = Byte.parseByte("test");
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 2311);
		try {
			socket.send(packet);
		} catch (Exception e) {
		}
	}
	public void send(String s) {
		byte[] data = new byte[s.length()];
		data = s.getBytes();
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 2311);
		try {
			socket.send(packet);
		} catch (Exception e) {
		}
	}
	
}
