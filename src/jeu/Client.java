package jeu;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

public class Client extends Thread {

	private InetAddress ipAddress = null;
	private DatagramSocket socket = null;
	private String ip = "";
	
	public Client(String ipAddress) {
		ip = ipAddress;
//		try {
//			this.ipAddress = InetAddress.getByName(ipAddress);
//			this.socket = new DatagramSocket();
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		} catch (SocketException e) {
//			e.printStackTrace();
//		}
	}

	public void send(final String s) {
		System.out.println(s);
		new Thread( new Runnable() {
			@Override
			public void run() {
				System.out.println(ip);
				if (ipAddress == null || socket == null) {
					try {
						ipAddress = InetAddress.getByName(ip);
						socket = new DatagramSocket();
					} catch (UnknownHostException e) {
						e.printStackTrace();
					} catch (SocketException e) {
						e.printStackTrace();
					}
				}
				byte[] data = new byte[s.length()];
				data = s.getBytes();
				if (ipAddress != null && socket != null) {
					DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 2311);
					try {
						socket.send(packet);
					} catch (Exception e) {
					}
				}
			}
		}).run();
	}
}
