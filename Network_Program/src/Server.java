import java.io.*;
import java.net.*;
import java.util.Objects;

public class Server implements Runnable {
    private Socket socket;
    private final ServerSocket server;
    private DataInputStream in;
    private DataOutputStream out;

    private boolean serverWork;

    public Server(int port) throws IOException {
        server = new ServerSocket(port);
        serverWork = true;
        System.out.println("Server started!\n");
    }

    public void establishingConnection() {
        try {
            System.out.println("Waiting for a client ...");

            socket = server.accept();
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());

            System.out.println("Client accepted");
        }
        catch (IOException err) {
            err.printStackTrace();
        }
    }

    public void clientServerCommunication() {
        try {
            String message = in.readUTF();

            if(Objects.equals(message, "quit")) {
                System.out.println("\nServer is shutting down!\n");
                serverWork = false;
                return;
            }

            int delay = Integer.parseInt(in.readUTF());

            System.out.println("Message from client: " + message + ", it will be sent back in " + delay + " ms!");
            Thread.sleep(delay);

            out.writeUTF(message);
        }
        catch (IOException err) {
            err.printStackTrace();
        }
        catch (InterruptedException err) {
            throw new RuntimeException(err);
        }
    }

    public void closeEverything() {
        try {
            System.out.println("Closing connection!\n");

            in.close();
            out.close();
            socket.close();
        }
        catch (IOException err) {
            err.printStackTrace();
        }
    }

    public void run() {
        while(serverWork) {
            establishingConnection();
            clientServerCommunication();
            closeEverything();
        }

        try {
            server.close();
        }
        catch (IOException err) {
            throw new RuntimeException(err);
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(1234);
        Thread serverRunning = new Thread(server);
        serverRunning.start();
    }
}