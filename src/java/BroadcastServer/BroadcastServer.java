/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BroadcastServer;

/**
 *
 * @author mathe
 */
import java.net.*;
import java.io.*;
import java.util.*;

public class BroadcastServer {

    private DatagramSocket socket;
    private InetAddress address;
    Scanner input = new Scanner(System.in);
    private byte[] buf;

    public BroadcastServer() {
        try {
            socket = new DatagramSocket();
            address = InetAddress.getByName("255.255.255.255");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public String sendEcho(String msg) {
        try {
            buf = msg.getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
            socket.setBroadcast(true);
            socket.send(packet);
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            socket.setSoTimeout(10000);
            String received = new String(packet.getData(), 0, packet.getLength());
            return "Success";
        } catch (Exception ex) {
            System.out.println(ex);
            return "Error";
        }
    }

    public void close() {
        socket.close();
    }

    public static void main(String[] args) {
        BroadcastServer server = new BroadcastServer();
    }
}
