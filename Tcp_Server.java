import java.io.*;
import java.net.*;

public class Tcp_Server {
    public static void main(String[] args) throws Exception {
        int port = 8000;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server is started on the port" + port);
        byte[] receiveData = new byte[1024];
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client Connected");
            
            InputStream inputStream = socket.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream("uploaded_text.txt");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            int byteData;
            while((byteData = inputStream.read(receiveData))!= -1){
                bufferedOutputStream.write(receiveData,0,byteData);
            }
            bufferedOutputStream.close();
            inputStream.close();
            socket.close();
           
        }
    }
}