/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

/**
 *
 * @author mathe
 */
import java.net.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class BroadcastClient extends Thread {

    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[256];
    private String received;
    public Object notifiObj;

    public BroadcastClient() {
        try {
            socket = new DatagramSocket(4445);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void run() {
        running = true;
        System.out.println("Waiting for a broadcast...");

        while (running) {
            try {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);

                ByteArrayInputStream bis = new ByteArrayInputStream(buf);
                ObjectInput in = null;
                try {
                    in = new ObjectInputStream(bis);
                    Object notifiObj = in.readObject();
                    this.notifiObj = notifiObj;
                } finally {
                    try {
                        if (in != null) {
                            in.close();
                        }
                    } catch (IOException ex) {
                        // ignore close exception
                    }
                }
                buf = new byte[256];
                socket.send(packet);
            } catch (Exception ex) {
                //System.out.println(ex);
            }
        }
        socket.close();
    }

    public String getReceived() {
        return notifiObj.toString();
    }

    public static void main(String[] args) {
        BroadcastClient client = new BroadcastClient();
        client.start();
    }
}
