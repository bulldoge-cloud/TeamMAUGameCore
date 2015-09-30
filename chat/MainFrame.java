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

	JTextField textField = new JTextField(40);
    JTextArea messageArea = new JTextArea(8, 40);
	
	JFrame frame = new JFrame("Tower Wars");
	JPanel cards = new JPanel(new CardLayout());
	
	JPanel start_panel = new JPanel(new FlowLayout());
	JPanel lobby_panel = new JPanel(new BorderLayout());
	
	JTextField ip_field = new JTextField(10);
	JTextField uname_field = new JTextField(10);
	JButton join_button = new JButton("CONNECT");
	JButton exit_button = new JButton("EXIT");
	JLabel uname_label = new JLabel("Username:");
	JLabel ip_label = new JLabel("Server IP Address:");
	
	CardLayout cardLayout = (CardLayout) cards.getLayout();
	String address="", username="";
	
	public MainFrame(){
		//start_panel.setLayout(new BoxLayout(start_panel, BoxLayout.PAGE_AXIS));
		//start_panel.add(Box.createRigidArea(new Dimension(5,5)));
		
		start_panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		ip_field.setEditable(true);
		ip_field.setPreferredSize(new Dimension(10,20));
		ip_label.setPreferredSize(new Dimension(120,20));
		uname_field.setEditable(true);
		uname_label.setPreferredSize(new Dimension(120,20));
		frame.setResizable(false);
		frame.setVisible(true);
		
		textField.setEditable(false);
        messageArea.setEditable(false);
		lobby_panel.add(textField, "North");
        lobby_panel.add(new JScrollPane(messageArea), "Center");
		
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
						run();						
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
		
		textField.addActionListener(new ActionListener(){
            /*
             Responds to pressing the enter key in the textfield by sending
             the contents of the text field to the server.    Then clear
             the text area in preparation for the next message.
            */
            public void actionPerformed(ActionEvent e){out.println(textField.getText());
                textField.setText("");
            }
        });
		
		start_panel.add(ip_label);
		start_panel.add(ip_field);
		start_panel.add(uname_label);
		start_panel.add(uname_field);
		start_panel.add(join_button);
		start_panel.add(exit_button);
		
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
				//System.out.println(address);
				//System.out.println(username);
            }else if (line.startsWith("NAMEACCEPTED")){
				textField.setEditable(true);
				//System.out.println("hello!");
            }else if (line.startsWith("MESSAGE")){
				//cardLayout.show(cards, "lobby");
				System.out.println("message");
                messageArea.append(line.substring(8) + "\n");
            }
        }
    }
	
	public static void main(String args[])throws Exception{
	
		MainFrame game = new MainFrame();
		game.frame.setSize(350,350);
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setVisible(true);
	
	}
	
}

