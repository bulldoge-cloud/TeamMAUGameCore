import javax.swing.JFrame;

public class Main{
	
	public static void main(String args[]){
	
		JFrame frame = new JFrame("Clans Vs Towers");
		frame.setSize(800, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new GameFrame());
		frame.setResizable(false);
		frame.setVisible(true);
	
	}
}