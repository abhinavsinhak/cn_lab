import java.io.*;
import java.net.*;

public class UDPFileClient {
    public static void main(String args[]) throws IOException {
        String hostName = "localhost";
        int port = 9876;
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName(hostName);
        byte[] sendData = new byte[1024];
        String fileName = "sample.txt"; // Replace with the path to the actual file to send
        File file = new File(fileName);
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

        System.out.println("Sending file: " + fileName);

        int bytesRead;
        while ((bytesRead = bufferedInputStream.read(sendData)) > 0) {
            DatagramPacket sendPacket = new DatagramPacket(sendData, bytesRead, IPAddress, port);
            clientSocket.send(sendPacket);
        }

        // Send a message to server to indicate end of file transmission
        String endMessage = "END";
        sendData = endMessage.getBytes();
        DatagramPacket endPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
        clientSocket.send(endPacket);

        bufferedInputStream.close();
        clientSocket.close();
        System.out.println("File transmission completed.");
    }
}