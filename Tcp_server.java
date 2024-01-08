import java.io.*;
import java.net.*;

public class Tcp_server{
    public static void main(String[] args) throws IOException{
        int port = 8000;
        ServerSocket serverSocket = new ServerSocket(port);
        byte[] receiveData = new byte[1024];

        System.out.println("Server is running on port"+port);
        while(true){
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            InputStream inputStream = socket.getInputStream();
            FileOutputStream fileInputStream = new FileOutputStream("received_file.txt");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileInputStream);
            int byteData;
            while((byteData = inputStream.read(receiveData)) != -1){
                bufferedOutputStream.write(receiveData, 0, byteData);
            }
            bufferedOutputStream.close();
            fileInputStream.close();
            socket.close();

            System.out.println("File received and saved");
            break;
        }
        serverSocket.close();
    }
}