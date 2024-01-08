import java.io.*;
import java.net.*;

public class TCPFileServer {
    public static void main(String[] args) throws IOException {
        int port = 6789;
        ServerSocket welcomeSocket = new ServerSocket(port);
        byte[] receiveData = new byte[1024];

        System.out.println("Server is running on port " + port);

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println("Client connected.");

            InputStream inputStream = connectionSocket.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream("received_file.txt");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            int bytesRead;
            while ((bytesRead = inputStream.read(receiveData)) != -1) {
                bufferedOutputStream.write(receiveData, 0, bytesRead);
            }

            bufferedOutputStream.close();
            inputStream.close();
            connectionSocket.close();

            System.out.println("File received and saved as 'received_file.txt'");
            break; 
        }

        welcomeSocket.close();
    }
}