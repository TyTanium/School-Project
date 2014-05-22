package Space.online;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {

	private String name;
	private String address;
	private int port;

	private DatagramSocket socket;
	private InetAddress ip;

	public Client(String name, String address, int port) {
		this.name = name;
		this.address = address;
		this.port = port;
		boolean connect = openConnection(address, port);
		if (!connect) {
			System.err.println("Connection Failed!");
		}
	}

	private String recieve() {
		byte[] data = new byte[1024];
		DatagramPacket packet = new DatagramPacket(data, data.length);
		try {
			socket.receive(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String message = new String(packet.getData());
		return message;
	}

	private boolean openConnection(String address, int port) {
		try {
			socket = new DatagramSocket(port);
			ip = InetAddress.getByName(address);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (SocketException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
