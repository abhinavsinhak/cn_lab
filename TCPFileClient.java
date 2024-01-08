import java.io.*;
import java.net.*;

public class TCPFileClient {
    public static void main(String[] args) throws IOException {
        String hostName = "localhost";
        int port = 6789;
        Socket clientSocket = new Socket(hostName, port);
        byte[] sendData = new byte[1024];
        String fileName = "sample.txt"; // Replace with the path to the actual file to send
        File file = new File(fileName);
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

        OutputStream outputStream = clientSocket.getOutputStream();

        System.out.println("Sending file: " + fileName);

        int bytesRead;
        while ((bytesRead = bufferedInputStream.read(sendData)) > 0) {
            outputStream.write(sendData, 0, bytesRead);
        }

        bufferedInputStream.close();
        outputStream.close();
        clientSocket.close();

        System.out.println("File transmission completed.");
    }
}