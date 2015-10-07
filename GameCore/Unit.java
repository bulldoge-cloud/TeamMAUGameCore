import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import java.awt.Rectangle; 

public class Unit extends Entity{

	private int startX;
	int life = 1;
	
	public Unit(int x, int y){
	
		super(x,y);
		startX = x;
	}
	
	public void update(){
	
		x += 1;
		checkCollisions();
		checkOffScreen();
	}
	
	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics2D g2d){
	
		g2d.drawImage(getUnitImg(), x, y, null);
		//g2d.draw(getBounds());
	
	}

	public Image getUnitImg(){
	
		ImageIcon ic = new ImageIcon("enemy.png");
		return ic.getImage();
	
	}
	
	public void checkCollisions(){
	
	
	
	}
	
	public void checkOffScreen(){
	
		if(x >= 600){
		
			x = startX;
		
		}
	
	}
	
	public Rectangle getBounds(){
	
		return new Rectangle(x, y, getUnitImg().getWidth(null), getUnitImg().getHeight(null));
	
	}
	
}