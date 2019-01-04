import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class RapidRoller extends JFrame implements Runnable,KeyListener,MouseListener,MouseMotionListener{
	Image dbImage,backGround,play;
	Graphics dbg;
	static Ball b = new Ball();
	int mouseX,mouseY,px,py;
	boolean startPlay;
	Rectangle pl = new Rectangle(144,160,160,70);
	Rectangle mr = new Rectangle(mouseX,mouseY,1,1);
	RapidRoller(){
			ImageIcon ic = new ImageIcon(this.getClass().getResource("background.png"));
			backGround = ic.getImage();
			ImageIcon i = new ImageIcon(this.getClass().getResource("play.png"));
			play=i.getImage();
			setTitle("Rapid Roller");
			setSize(440,400);
			this.setLocation(300,100);
			setVisible(true);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			addKeyListener(this);
			addMouseMotionListener(this);
			addMouseListener(this);
	}
		
		public void checkStart(){
			mr.x=mouseX;
			mr.y=mouseY;
			if(mr.intersects(pl)){
				startPlay=true;
			}
		}
		public void run() {
		try {
			while (true) {
				checkStart();
				if (startPlay==true) {
					py--;
				}
				if(py<-600){
					startPlay=false;
				}
				Thread.sleep(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		
		public void paint(Graphics g){
			dbImage=createImage(getWidth(),getHeight());
			dbg=dbImage.getGraphics();
			draw(dbg);
			g.drawImage(dbImage, 0, 0, this);
		}
		public void draw(Graphics g){
			Graphics2D g2d = (Graphics2D)g;
			g.drawImage(backGround, 0, 0, this);
			b.draw(g);
			b.p1.draw(g);
			b.p2.draw(g);
			b.p3.draw(g);
			b.p4.draw(g);///*
			g.drawImage(play, px, py, this);
			g.setColor(Color.black);
			g.fillRect(mr.x, mr.y, mr.width, mr.height);
			g.setColor(Color.BLACK);//*/
			repaint();
		}
			public static void main(String[] args) {
		RapidRoller rl = new RapidRoller();
			Thread rr = new Thread(rl);
			rr.start();
			Thread bl = new Thread(b);
			Thread pdl1 = new Thread(b.p1);
			Thread pdl2 = new Thread(b.p2);
			Thread pdl3 = new Thread(b.p3);
			Thread pdl4 = new Thread(b.p4);
			pdl1.start();
			pdl2.start();
			pdl3.start();
			pdl4.start();
			bl.start();
		
	}


			@Override
			public void keyPressed(KeyEvent e) {
				b.keyPressed(e);
				b.p1.keyPressed(e);
				b.p2.keyPressed(e);
				b.p3.keyPressed(e);
				b.p4.keyPressed(e);
			}


			@Override
			public void keyReleased(KeyEvent e) {
			b.keyReleased(e);
			}


			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}


			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}


			@Override
			public void mouseMoved(MouseEvent e) {
				b.mouseMoved(e);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				mouseX=e.getX();
				mouseY=e.getY();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

}
