import java.io.*;
import java.net.*;

public class UDPFileServer {
    public static void main(String args[]) throws IOException {
        int port = 9876;
        DatagramSocket serverSocket = new DatagramSocket(port);
        byte[] receiveData = new byte[1024];
        FileOutputStream fileOutputStream = new FileOutputStream("received_file.txt");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

        System.out.println("Server is running on port " + port);

        boolean endOfFile = false;
        while (!endOfFile) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String line = new String(receivePacket.getData(), 0, receivePacket.getLength());
            if ("END".equals(line.trim())) {
                endOfFile = true;
            } else {
                bufferedOutputStream.write(receivePacket.getData(), 0, receivePacket.getLength());
            }
        }

        bufferedOutputStream.close();
        serverSocket.close();
        System.out.println("File received and saved as 'received_file.txt'");
    }
}