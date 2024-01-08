import java.io.*;
import java.net.*;
public class Tcp_client {
  public static void main(String[] args) throws IOException{
    int port = 8000;
    Socket socket = new Socket("localhost",port);
    byte[] sendData = new byte[1024];

    String fileName = "sample.txt";
    File file = new File(fileName);
    FileInputStream fileInputStream = new FileInputStream(file);
    BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
    OutputStream outputStream = socket.getOutputStream();
    System.out.println("Sending file"+fileName);

    int bytesRead;
    while((bytesRead = bufferedInputStream.read(sendData))>0){
        outputStream.write(sendData,0,bytesRead);
    }
    bufferedInputStream.close();
    outputStream.close();
    socket.close();

    System.out.println("File transmission Completed");
  }
    
    
}