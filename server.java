import java.io.*;
import java.net.*;

public class server {
    public static void main(String[] args) throws Exception {
        System.out.println("server started");
        ServerSocket serverSocket = new ServerSocket(8000);
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println(serverSocket.getInetAddress());
            new Thread(new ClientHandler(socket)).start();
        }
        
    }
}

class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            String request = in.readLine();
            if (request.startsWith("UPLOAD")) {
                BufferedWriter writer = new BufferedWriter(new FileWriter("uploaded_text.txt"));
                String line;
                while (!(line = in.readLine()).equals("END")) {
                    writer.write(line);
                    writer.newLine();
                }
                writer.close();
                out.println("File uploaded successfully");
            } else if (request.startsWith("DOWNLOAD")) {
                BufferedReader reader = new BufferedReader(new FileReader("uploaded_text.txt"));
                String line;
                while ((line = reader.readLine()) != null) {
                    out.println(line);

                }
                reader.close();
            }
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}