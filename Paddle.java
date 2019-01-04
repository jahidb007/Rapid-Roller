import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;


public class Paddle implements Runnable, ImageObserver{
	int x,y,paddleThread=10,count=1,pn;
	Rectangle paddle;
	Image paddleImage;
	boolean decreaseThread=true,gameStart;
	Paddle(int py,int pno){
		ImageIcon pi = new ImageIcon(this.getClass().getResource("paddle.gif"));
		paddleImage = pi.getImage();
		this.x=(int)(Math.random()*250);
		this.y=py;
		paddle = new Rectangle(x,y,60,10);
		this.pn=pno;
	}
	public void ThreadChecker(){
		if(count%1000==0){
			if(decreaseThread==true){
			paddleThread--;
			if(paddleThread<6){
				decreaseThread=false;
			}
			
			}
		}
	}
	void draw(Graphics g){
		g.drawImage(paddleImage, paddle.x, paddle.y, this);
		//g.drawString("paddle no "+pn, paddle.x, paddle.y);
	}
	public void reborn(){
		
		if(paddle.y==0){
			paddle.y=400;
			paddle.x=(int)(Math.random()*250);
		}
	}
	@Override
	public void run() {
		try {
			while(true){
				if (gameStart) {
					count++;
					paddle.y--;
					ThreadChecker();
					reborn();
				}
				Thread.sleep(paddleThread);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5) {
		// TODO Auto-generated method stub
		return false;
	}
	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();
		if(keycode==KeyEvent.VK_R){
			paddleThread=10;
			decreaseThread=true;
			
		}
		if(keycode==KeyEvent.VK_LEFT)
		gameStart=true;
		if(keycode==KeyEvent.VK_RIGHT)
			gameStart=true;
	}
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
