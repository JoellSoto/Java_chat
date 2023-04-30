import java.net.*;
import java.util.*;
import java.io.*;
public class Server{
	
    public static void main(String args[]){
    
        
        Connection con;
        //lista para adicionar todas as threads
        ArrayList<Connection> threadList = new ArrayList<>();
        try{
            int serverPort = 6000;
            System.out.println("A Escuta no Porto 6000");
            ServerSocket listenSocket = new ServerSocket(serverPort);
            System.out.println("LISTEN SOCKET="+listenSocket);
            while(true) {
                Socket clientSocket = listenSocket.accept(); // BLOQUEANTE
                System.out.println("CLIENT_SOCKET (created at accept())="+clientSocket);
                
               
                
           
              con= new Connection(clientSocket,threadList);
                
              threadList.add(con); 
              con.start();  
              //coloca na lista a thread atual
              
            }
        }catch(IOException e)
        {System.out.println("Listen3:" + e.getMessage());}
    }
    
    
}


// Thread para tratar de cada canal de comunicação com um cliente
class Connection extends Thread {
	  private Socket socket;
	  private ArrayList<Connection> threadList;
	  private PrintWriter output;

    
    
  
    public Connection (Socket aClientSocket, ArrayList<Connection> threadList) {
    	
    	this.socket = aClientSocket;
        this.threadList = threadList;
    }
    //=============================
    
    @Override
    public void run() {
        try {
            //Ler o input do cliente
            BufferedReader input = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            
            
             output = new PrintWriter(socket.getOutputStream(),true);


            //loop infinito para o servidor
            while(true) {
                String outputString = input.readLine();
                //se o ususario quiser sair
                if(outputString.equalsIgnoreCase("Sair")) {
                    break;
                }
                todosClientes(outputString);
                //output.println("Server diz " + outputString);
                System.out.println("Server recebeu " + outputString);

            }


        } catch (Exception e) {
            System.out.println("Error occured " +e.getMessage());
        }
    }

    private void todosClientes(String outputString) {
        for( Connection sT: threadList) {
            sT.output.println(outputString);
        }

    }
}