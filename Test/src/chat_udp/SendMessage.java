package chat_udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SendMessage implements Runnable {
    private Thread thread;
    private DatagramSocket socket;
    private String id;
    private InetAddress ipAddress;
    private int port;

    public SendMessage(DatagramSocket socket, String id, InetAddress ipAddress, int port) {
        this.socket = socket;
        this.id = id;
        this.ipAddress = ipAddress;
        this.port = port;
    }

    @Override
    public void run() {

        String mess;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        byte[] sendData = new byte[1024];
        DatagramPacket sendPacket;

        while (true) {
            try {
                System.out.print(">> ");
                mess = bf.readLine();
                mess = id + ": " + mess;
                if (mess != null && this.socket != null) {
                    sendData = mess.getBytes();
                    sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
                    socket.send(sendPacket);
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