package p2p;

import javax.swing.*;

import java.net.*;
import java.io.*;

class Receiver implements Runnable {

	Thread activity = new Thread(this);
	MulticastSocket so;
	JTextArea txt;

	Receiver(MulticastSocket sock, JTextArea txtAr) {
		so = sock;
		txt = txtAr;
		activity.start();
	}

	public void run() {
		byte[] data = new byte[1024];
		while (true)
			try {
				DatagramPacket packet = new DatagramPacket(data, data.length);
				so.receive(packet);
				String mess = new String(data, 0, packet.getLength());
				txt.append(mess + "\n");
			} catch (IOException e) {
				break;
			}
	}
}
