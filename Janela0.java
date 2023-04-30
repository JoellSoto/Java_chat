import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Janela0 extends JFrame{
	private Container cont;
	private JPanel pp,p1,p2,p3,p4;
	private JLabel lbl1,lbl2,lbl3;
	private JTextField t1,t2,t3;
	private JButton btn1,btn2,btn3;
	private String clientName = "Vazio";
	
	public Janela0() {
		super("HOME PAGE");
		
		cont=getContentPane();
		p1=new JPanel(new GridLayout(3,3));
		p2=new JPanel(new BorderLayout());
		
		
		
		
		
		lbl1=new JLabel("Introduza seu Nome ");
        p1.add(lbl1);
        t1=new JTextField(5);
        p1.add(t1);
        
        
        
        lbl2=new JLabel("introduza o endereco do Servidor");
        p1.add(lbl2);
        t2=new JTextField(5);
        p1.add(t2);
        
        
        
        lbl3=new JLabel("<html>introduza a porta<br> (padrão do servidor é 6000)</html>");
        p1.add(lbl3);
        t3=new JTextField(5);
        p1.add(t3);
        
        
        
         btn1=new JButton("Avançar");
         
         btn1.addActionListener(new ActionListener() {

 			
 			public void actionPerformed(ActionEvent e) {
 				
 				new Janela1(t2.getText(),t1.getText(),Integer.parseInt(t3.getText()));
 				dispose();
 				
 			
 			
 			}
        	 
         });
           
           p2.add(btn1,BorderLayout.SOUTH);
           
           pp=new JPanel(new GridLayout(2,1));
           pp.add(p1);
           pp.add(p2);
           cont.add(pp);
           
           
           setSize(425,300);
           setVisible(true);

        
        
	}
	
	public static void main(String args[]) {
		new Janela0();
	}
	
	
}
