package chat_udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReadMessage implements Runnable {
    private Thread thread;
    private DatagramSocket socket;

    public ReadMessage(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        String mess;
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket;

        while (true) {
            try {
                if (this.socket != null) {
                    receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    socket.receive(receivePacket);
                    mess = new String(receivePacket.getData());
                    System.out.println(mess);
                    System.out.print(">> ");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }
}