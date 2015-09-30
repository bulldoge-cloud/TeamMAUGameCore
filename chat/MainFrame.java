import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{

	BufferedReader in;
    PrintWriter out;

	//JTextField textField = new JTextField(40);
    //JTextArea messageArea = new JTextArea(8, 40);
	
	JFrame frame = new JFrame("Tower Wars");
	JPanel cards = new JPanel(new CardLayout());
	
	JPanel start_panel = new JPanel();
	JPanel lobby_panel = new JPanel(new BorderLayout());
	
	// start_panel components
	JTextField ip_field = new JTextField(10);
	JTextField uname_field = new JTextField(10);
	JButton join_button = new JButton("CONNECT");
	JButton exit_button = new JButton("EXIT");
	JLabel uname_label = new JLabel("Username:");
	JLabel ip_label = new JLabel("Server IP Address:");
	
	// lobby_panel components
	JPanel chat_panel = new JPanel(new BorderLayout());
	JPanel setup_panel = new JPanel(new GridLayout(9,1));
	JPanel center_panel = new JPanel();
	
	// chat_panel components
	JTextField chatfield = new JTextField(30);
	JTextArea chatarea = new JTextArea(8, 30);
	
	// center_panel components
	String[] choices = { "WALL","ARCHER TOWER", "WIZARD TOWER","CANNON","MORTAR"};
	JLabel choose = new JLabel("Assign a type to each tower.");
	JLabel setup_label;
	JComboBox<String> cb;
	
	
	CardLayout cardLayout = (CardLayout) cards.getLayout();
	String address="", username="";
	
	public MainFrame(){
		
		start_panel.setLayout(null);
		
		start_panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		ip_field.setEditable(true);
		ip_label.setBounds(200,100,120,20);
		ip_field.setBounds(320,100, 150, 25);
		uname_label.setBounds(200,150,120,20);
		uname_field.setBounds(320,150, 150, 25);
		join_button.setBounds(220,210,100,25);
		exit_button.setBounds(350,210,100,25);

		uname_field.setEditable(true);
		frame.setResizable(false);
		frame.setVisible(true);
		
		chat_panel.add(chatfield, BorderLayout.NORTH);
		chat_panel.add(chatarea, BorderLayout.CENTER);
		
		center_panel.setLayout(null);
		choose.setBounds(20,40, 200, 20);
		center_panel.add(choose);
		
		for(int i=0, j=20; i<9; i++,j=j+20){
			setup_label = new JLabel("Tower "+i);
			cb = new JComboBox<String>(choices);
			setup_label.setBounds(20,50+j, 100, 20);
			cb.setBounds(70,50+j, 100, 20);
			center_panel.add(setup_label);
			center_panel.add(cb);
		}
		
		JLabel form_label = new JLabel("The setup below is the formation of the towers.");
		form_label.setBounds(270,40, 350, 20);
		center_panel.add(form_label);
		
		JButton btn1 = new JButton("Tower 1");
		btn1.setBounds(250,100, 100, 100);
		//btn1.setEnabled(false);
		center_panel.add(btn1);
		
		JButton btn2 = new JButton("Tower 2");
		btn2.setBounds(250,200, 100, 100);
		//btn2.setEnabled(false);
		center_panel.add(btn2);
		
		JButton btn3 = new JButton("Tower 3");
		btn3.setBounds(250,300, 100, 100);
		//btn3.setEnabled(false);
		center_panel.add(btn3);
		
		JButton btn4 = new JButton("Tower 4");
		btn4.setBounds(350,100, 100, 100);
		//btn4.setEnabled(false);
		center_panel.add(btn4);
		
		JButton btn5 = new JButton("Tower 5");
		btn5.setBounds(350,200, 100, 100);
		//btn5.setEnabled(false);
		center_panel.add(btn5);
		
		JButton btn6 = new JButton("Tower 6");
		btn6.setBounds(350,300, 100, 100);
		//btn6.setEnabled(false);
		center_panel.add(btn6);
		
		JButton btn7 = new JButton("Tower 7");
		btn7.setBounds(450,100, 100, 100);
		//btn7.setEnabled(false);
		center_panel.add(btn7);
		
		JButton btn8 = new JButton("Tower 8");
		btn8.setBounds(450,200, 100, 100);
		//btn8.setEnabled(false);
		center_panel.add(btn8);
		
		JButton btn9 = new JButton("Tower 9");
		btn9.setBounds(450,300, 100, 100);
		//btn9.setEnabled(false);
		center_panel.add(btn9);

		JButton start_button = new JButton("START");
		start_button.setBounds(0,600, 100, 100);
		center_panel.add(start_button);
		
	   //textField.setEditable(false);
       //messageArea.setEditable(false);
	   //lobby_panel.add(textField, "North");
       //lobby_panel.add(new JScrollPane(messageArea), "Center");
		
		join_button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				address = ip_field.getText();
				username = uname_field.getText();
				
				if(username != "" && address != ""){
					CardLayout cl = (CardLayout) cards.getLayout();
					cl.show(cards, "lobby");
				}
				
				try{
						//run();						
				}catch(Exception e){
					
				}
				/* For Testing Purposes
					System.out.println(address);
					System.out.println(username);
				*/
			}
		});
		
		exit_button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				System.exit(0);
			}
			
		});
		
		start_panel.add(ip_label);
		start_panel.add(ip_field);
		start_panel.add(uname_label);
		start_panel.add(uname_field);
		start_panel.add(join_button);
		start_panel.add(exit_button);
		
		lobby_panel.add(chat_panel, BorderLayout.NORTH);
		lobby_panel.add(center_panel, BorderLayout.CENTER);
		
		cards.add(start_panel, "home");
		cards.add(lobby_panel, "lobby");
		
		frame.add(cards);
	}
	
	public void run() throws IOException{

        // Make connection and initialize streams
        Socket socket = new Socket(address, 9001);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
		
        // Process all messages from server, according to the protocol.
        while(true){
            String line = in.readLine();
            if(line.startsWith("SUBMITNAME")){
                out.println(username);
            }else if (line.startsWith("NAMEACCEPTED")){
				//textField.setEditable(true);
            }else if (line.startsWith("MESSAGE")){
				//cardLayout.show(cards, "lobby");
                //messageArea.append(line.substring(8) + "\n");
            }
        }
    }
	
	public static void main(String args[])throws Exception{
	
		MainFrame game = new MainFrame();
		game.frame.setSize(700,700);
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setVisible(true);
	
	}
	
}

