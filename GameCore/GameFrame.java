import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.*;

public class GameFrame extends JPanel implements MouseListener, MouseMotionListener{
	
	static ArrayList<Unit> units = new ArrayList<Unit>();
	public int mouseX=0, mouseY=0;
	Graphics g;
	Graphics2D g2d = (Graphics2D) g;
	int j=0;
	
	public GameFrame(){
	
		setFocusable(true);
		
		for(int i=0; i<10; i++){
			Unit unit = new Unit(0,0);
			units.add(unit);
		}
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
	}

	public void paint(Graphics g){
	
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		ImageIcon ic = new ImageIcon("bg.png");
		g2d.drawImage(ic.getImage(), 0, 0, null);
		
		for(int i=0; i<units.size(); i++){
			units.get(i).draw(g2d);
			units.get(i).update();
			repaint();	
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent event){
		mouseX = event.getX();
		mouseY = event.getY();
		
		if(j != units.size()){
			Unit unit = units.get(j);
			units.get(j).setXY(mouseX, mouseY);
			repaint();
			j++;
		}
		
	}
	
	@Override
	public void mousePressed(MouseEvent event){
		
	}
	
	public void mouseReleased(MouseEvent event){
		
	}
	
	public void mouseMoved(MouseEvent event){
		
	}
	
	public void mouseDragged(MouseEvent event){
		
	}
	
	public void mouseEntered(MouseEvent event){
	
	}
	
	public void mouseExited(MouseEvent event){
		
	}
	
	public static void addUnit(Unit u){
	
		units.add(u);
	
	}

	public static void removeUnit(Unit u){
	
		units.remove(u);
	
	}
	
	public static ArrayList<Unit> getUnitList(){
	
		return units;
	
	}
	
	
}