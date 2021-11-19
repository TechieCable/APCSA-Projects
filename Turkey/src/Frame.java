import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener {

	public void paint(Graphics g) {
		Color brown = new Color(0x591b00);
		Color light_brown = new Color(0x852800);
		Color[] wings = {new Color(0x990000), new Color(0x996e00), new Color(0x992e00)};
		g.setColor(wings[2]);
		g.fillOval(325, 100, 100, 400);
		g.setColor(wings[1]);
		g.fillOval(375, 100, 100, 400);
		g.setColor(wings[0]);
		g.fillOval(425, 100, 100, 400);
		g.setColor(wings[1]);
		g.fillOval(475, 100, 100, 400);
		g.setColor(wings[2]);
		g.fillOval(525, 100, 100, 400);
		g.setColor(wings[1]);
		g.fillOval(575, 100, 100, 400);
		g.setColor(light_brown);
		g.fillOval(200, 300, 300, 200);
		g.fillOval(500, 300, 300, 200);
		g.setColor(brown);
		g.fillOval(400, 150, 200, 200);
		g.fillOval(325, 300, 350, 350);
		g.setColor(Color.blue);
		g.fillOval(450, 230, 20, 20);
		g.fillOval(500, 230, 20, 20);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString("I am thankful that I know so many incredible people.", 125, 900);
		g.drawLine(420, 630, 330, 780);
		g.drawLine(600 - 30, 630, 690 - 30, 780);

	}

	public static void main(String[] arg) {
		Frame d = new Frame();
	}

	public Frame() {
		JFrame frame = new JFrame("Turkey");
		frame.setSize(1000, 1000);
		frame.add(this);

		// this part makes sure the x button closes the program
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.addMouseListener(this);
		t.start();
		// make the frame show up
		frame.setVisible(true);

	}

	Timer t = new Timer(16, this);

	public void actionPerformed(ActionEvent m) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent m) {
		System.out.println(m.getX() + ", " + m.getY());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}

/*
 * import java.awt.Graphics; import javax.swing.JFrame; import
 * javax.swing.JPanel;
 * 
 * public class Drawing extends JPanel { public void paint(Graphics g) {
 * g.fillRect(0, 0, 50, 50); }
 * 
 * public static void main(String[] args) { // TODO Auto-generated method stub
 * Drawing d = new Drawing(); }
 * 
 * public Drawing() { JFrame f = new JFrame("Have a restful break!");
 * f.setSize(800,600); f.setResizable(false); f.add(this);
 * f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); f.setVisible(true); }
 * 
 * }
 */
