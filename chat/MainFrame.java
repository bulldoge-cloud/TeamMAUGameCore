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
	JPanel center_panel = new JPanel();
	JPanel game_panel = new JPanel();
	
	// chat_panel components
	JTextField chatfield = new JTextField(30);
	JTextArea chatarea = new JTextArea(8, 30);
	
	// center_panel components
	String[] choices = { "WALL","ARCHER TOWER", "WIZARD TOWER","CANNON","MORTAR"};
	JLabel choose = new JLabel("Assign a type to each tower.");
	JLabel setup_label1;
	JLabel setup_label2;
	JLabel setup_label3;
	JLabel setup_label4;
	JLabel setup_label5;
	JLabel setup_label6;
	JLabel setup_label7;
	JLabel setup_label8;
	JLabel setup_label9;
	JComboBox<String> cb1;
	JComboBox<String> cb2;
	JComboBox<String> cb3;
	JComboBox<String> cb4;
	JComboBox<String> cb5;
	JComboBox<String> cb6;
	JComboBox<String> cb7;
	JComboBox<String> cb8;
	JComboBox<String> cb9;
	
	
	
	CardLayout cardLayout = (CardLayout) cards.getLayout();
	String address="", username="";
	
	public MainFrame(){
		
		start_panel.setLayout(null);
		center_panel.setLayout(null);
		game_panel.setLayout(null);
		
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
		
		
		choose.setBounds(20,40, 200, 20);
		center_panel.add(choose);
		
		
		setup_label1 = new JLabel("Tower 1");
		cb1 = new JComboBox<String>(choices);
		setup_label1.setBounds(20,50+20, 100, 20);
		cb1.setBounds(70,50+20, 100, 20);
		center_panel.add(setup_label1);
		center_panel.add(cb1);
		
		setup_label2 = new JLabel("Tower 2");
		cb2 = new JComboBox<String>(choices);
		setup_label2.setBounds(20,70+20, 100, 20);
		cb2.setBounds(70,70+20, 100, 20);
		center_panel.add(setup_label2);
		center_panel.add(cb2);
		
		setup_label3 = new JLabel("Tower 3");
		cb3 = new JComboBox<String>(choices);
		setup_label3.setBounds(20,90+20, 100, 20);
		cb3.setBounds(70,90+20, 100, 20);
		center_panel.add(setup_label3);
		center_panel.add(cb3);
		
		setup_label4 = new JLabel("Tower 4");
		cb4 = new JComboBox<String>(choices);
		setup_label4.setBounds(20,110+20, 100, 20);
		cb4.setBounds(70,110+20, 100, 20);
		center_panel.add(setup_label4);
		center_panel.add(cb4);
		
		setup_label5 = new JLabel("Tower 5");
		cb5 = new JComboBox<String>(choices);
		setup_label5.setBounds(20,130+20, 100, 20);
		cb5.setBounds(70,130+20, 100, 20);
		center_panel.add(setup_label5);
		center_panel.add(cb5);
		
		setup_label6 = new JLabel("Tower 6");
		cb6 = new JComboBox<String>(choices);
		setup_label6.setBounds(20,150+20, 100, 20);
		cb6.setBounds(70,150+20, 100, 20);
		center_panel.add(setup_label6);
		center_panel.add(cb6);
		
		setup_label7 = new JLabel("Tower 7");
		cb7 = new JComboBox<String>(choices);
		setup_label7.setBounds(20,170+20, 100, 20);
		cb7.setBounds(70,170+20, 100, 20);
		center_panel.add(setup_label7);
		center_panel.add(cb7);
		
		setup_label8 = new JLabel("Tower 8");
		cb8 = new JComboBox<String>(choices);
		setup_label8.setBounds(20,190+20, 100, 20);
		cb8.setBounds(70,190+20, 100, 20);
		center_panel.add(setup_label8);
		center_panel.add(cb8);
		
		setup_label9 = new JLabel("Tower 9");
		cb9 = new JComboBox<String>(choices);
		setup_label9.setBounds(20,210+20, 100, 20);
		cb9.setBounds(70,210+20, 100, 20);
		center_panel.add(setup_label9);
		center_panel.add(cb9);
		
		
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
		start_button.setBounds(350, 430, 100, 30);
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
		
		cb1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				JComboBox comboBox = (JComboBox) ae.getSource();
				
				Object selected = comboBox.getSelectedItem();
				if(selected.toString().equals("WALL")){ 
					btn1.setText("WALL");
				}else if(selected.toString().equals("ARCHER TOWER")){
					btn1.setText("A-TOWER");
				}else if(selected.toString().equals("WIZARD TOWER")){
					btn1.setText("WIZTOWER");
				}else if(selected.toString().equals("CANNON")){
					btn1.setText("CANNON");
				}else if(selected.toString().equals("MORTAR")){
					btn1.setText("MORTAR");
				}
			}
			
		});
		
		cb2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				JComboBox comboBox = (JComboBox) ae.getSource();
				
				Object selected = comboBox.getSelectedItem();
				if(selected.toString().equals("WALL")){ 
					btn2.setText("WALL");
				}else if(selected.toString().equals("ARCHER TOWER")){
					btn2.setText("A-TOWER");
				}else if(selected.toString().equals("WIZARD TOWER")){
					btn2.setText("WIZTOWER");
				}else if(selected.toString().equals("CANNON")){
					btn2.setText("CANNON");
				}else if(selected.toString().equals("MORTAR")){
					btn2.setText("MORTAR");
				}
			}
			
		});
		
		cb3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				JComboBox comboBox = (JComboBox) ae.getSource();
				
				Object selected = comboBox.getSelectedItem();
				if(selected.toString().equals("WALL")){ 
					btn3.setText("WALL");
				}else if(selected.toString().equals("ARCHER TOWER")){
					btn3.setText("A-TOWER");
				}else if(selected.toString().equals("WIZARD TOWER")){
					btn3.setText("WIZTOWER");
				}else if(selected.toString().equals("CANNON")){
					btn3.setText("CANNON");
				}else if(selected.toString().equals("MORTAR")){
					btn3.setText("MORTAR");
				}
			}
			
		});
		
		cb4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				JComboBox comboBox = (JComboBox) ae.getSource();
				
				Object selected = comboBox.getSelectedItem();
				if(selected.toString().equals("WALL")){ 
					btn4.setText("WALL");
				}else if(selected.toString().equals("ARCHER TOWER")){
					btn4.setText("A-TOWER");
				}else if(selected.toString().equals("WIZARD TOWER")){
					btn4.setText("WIZTOWER");
				}else if(selected.toString().equals("CANNON")){
					btn4.setText("CANNON");
				}else if(selected.toString().equals("MORTAR")){
					btn4.setText("MORTAR");
				}
			}
			
		});
		
		cb5.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				JComboBox comboBox = (JComboBox) ae.getSource();
				
				Object selected = comboBox.getSelectedItem();
				if(selected.toString().equals("WALL")){ 
					btn5.setText("WALL");
				}else if(selected.toString().equals("ARCHER TOWER")){
					btn5.setText("A-TOWER");
				}else if(selected.toString().equals("WIZARD TOWER")){
					btn5.setText("WIZTOWER");
				}else if(selected.toString().equals("CANNON")){
					btn5.setText("CANNON");
				}else if(selected.toString().equals("MORTAR")){
					btn5.setText("MORTAR");
				}
			}
			
		});
		
		cb6.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				JComboBox comboBox = (JComboBox) ae.getSource();
				
				Object selected = comboBox.getSelectedItem();
				if(selected.toString().equals("WALL")){ 
					btn6.setText("WALL");
				}else if(selected.toString().equals("ARCHER TOWER")){
					btn6.setText("A-TOWER");
				}else if(selected.toString().equals("WIZARD TOWER")){
					btn6.setText("WIZTOWER");
				}else if(selected.toString().equals("CANNON")){
					btn6.setText("CANNON");
				}else if(selected.toString().equals("MORTAR")){
					btn6.setText("MORTAR");
				}
			}
			
		});
		
		cb7.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				JComboBox comboBox = (JComboBox) ae.getSource();
				
				Object selected = comboBox.getSelectedItem();
				if(selected.toString().equals("WALL")){ 
					btn7.setText("WALL");
				}else if(selected.toString().equals("ARCHER TOWER")){
					btn7.setText("A-TOWER");
				}else if(selected.toString().equals("WIZARD TOWER")){
					btn7.setText("WIZTOWER");
				}else if(selected.toString().equals("CANNON")){
					btn7.setText("CANNON");
				}else if(selected.toString().equals("MORTAR")){
					btn7.setText("MORTAR");
				}
			}
			
		});
		
		cb8.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				JComboBox comboBox = (JComboBox) ae.getSource();
				
				Object selected = comboBox.getSelectedItem();
				if(selected.toString().equals("WALL")){ 
					btn8.setText("WALL");
				}else if(selected.toString().equals("ARCHER TOWER")){
					btn8.setText("A-TOWER");
				}else if(selected.toString().equals("WIZARD TOWER")){
					btn8.setText("WIZTOWER");
				}else if(selected.toString().equals("CANNON")){
					btn8.setText("CANNON");
				}else if(selected.toString().equals("MORTAR")){
					btn8.setText("MORTAR");
				}
			}
			
		});
		
		cb9.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				JComboBox comboBox = (JComboBox) ae.getSource();
				
				Object selected = comboBox.getSelectedItem();
				if(selected.toString().equals("WALL")){ 
					btn9.setText("WALL");
				}else if(selected.toString().equals("ARCHER TOWER")){
					btn9.setText("A-TOWER");
				}else if(selected.toString().equals("WIZARD TOWER")){
					btn9.setText("WIZTOWER");
				}else if(selected.toString().equals("CANNON")){
					btn9.setText("CANNON");
				}else if(selected.toString().equals("MORTAR")){
					btn9.setText("MORTAR");
				}
			}
			
		});
		
		start_button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				CardLayout cl = (CardLayout) cards.getLayout();
				cl.show(cards, "game");
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
		cards.add(game_panel, "game");
		
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

