import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;


public class Janela1 extends JFrame{
	
	
	private Container cont;
	private JPanel pp,p1,p2,p3,p4;
	private JLabel lbl1,lbl2,lbl3;
	private JTextField t1,t2,t3;
	private JButton btn1,btn2,btn3;
	private JScrollPane sp;
	protected PrintWriter output;
	private Date data;
	
	public Janela1(String endereco,String nome,int port) {
		super("Chat TCP");
		cont=getContentPane();
		
		try {
			
			Socket socket = new Socket(endereco,port);
            //Ler o Input do Sservidor
            BufferedReader input = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            
          
            //retorna o input para o servidor
            PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
		
		

		 
        
        
		
		pp=new JPanel(new GridLayout(3,1));
	       
	       p1=new JPanel(new GridLayout(1,2));
	       
	           
	       lbl1=new JLabel();
	       lbl1.setHorizontalTextPosition(SwingConstants.CENTER);
	       lbl1.setHorizontalAlignment(SwingConstants.CENTER);
	       
	       sp=new JScrollPane(lbl1);
	       pp.add(sp);
	       ClientRunnable clientRun = new ClientRunnable(socket,this.lbl1);
	       
	      
        
        
	       lbl2=new JLabel("Introduza a mensagem");
	       lbl2.setHorizontalAlignment(SwingConstants.CENTER);
	       lbl2.setHorizontalTextPosition(SwingConstants.CENTER);
	       p1.add(lbl2);
	       
	       t1=new JTextField(5);
	       t1.setColumns(10);
	       
	       p1.add(t1);
	       
	       pp.add(p1);
	       
	        
	        p2=new JPanel(new FlowLayout());
	        
	        btn1=new JButton("Enviar");
	        
	        
	        new Thread(clientRun).start();
			
	        data=new Date();
			 
			 btn1.addActionListener(new ActionListener() {

		 			
		 			public void actionPerformed(ActionEvent e) {
		 				
		 				String message = (data.getHours()+":"+ data.getMinutes()+" "+ "[" + nome + "]"  );
		 				output.println(message + "-> " + t1.getText());
		 				
		 					}
		        	 
		         });
			 
	        

	        
	          
	          p2.add(btn1);
	          
	          
	          btn2=new JButton("Sair");
	          
	          btn2.addActionListener(new ActionListener() {

	   			
	   			public void actionPerformed(ActionEvent e) {
	   			    
	   				output.println(nome+" Saiu do chat!");
	   				try {
						socket.close();
					} catch (IOException e1) {
					
						System.out.println(e1.getMessage());
					}
	   				System.exit(0);
	   					}
	          	 
	           });
	          
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	          
	          p2.add(btn2);
	          pp.add(p2);
	          cont.add(pp);
	          setSize(800,350);
	          setVisible(true);
	          
	}
	

}
class ClientRunnable implements Runnable {

    private Socket socket;
    private BufferedReader input;
    private JLabel l1; 
    String acum="";
    // private PrintWriter output;

    public ClientRunnable(Socket s,JLabel l1) throws IOException {
        this.socket = s;
        this.l1=l1;
        this.input = new BufferedReader( new InputStreamReader(socket.getInputStream()));
        // this.output = new PrintWriter(socket.getOutputStream(),true);
    }
    @Override
    public void run() {
        
            try {
                while(true) {
                    String response = input.readLine();
                    acum=acum+"<br>"+response;
                    l1.setText("<html>"+acum+"</html>");
                    
                    System.out.println(response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    input.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }
    
}
