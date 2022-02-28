import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Recursion extends JPanel implements ActionListener {

	int CCdelta = 10;

	/**
	 * draws concentric circles
	 * 
	 * @param g
	 * @param width
	 * @param x
	 * @param y
	 */
	public void concentricCircles(Graphics g, int width, int x, int y) {
		if (width < 1) {
			return;
		}

		// each method call draws a circle
		// then call itself to draw a different circle
		g.setColor(Color.BLUE);
		g.drawOval(x, y, width, width);

		concentricCircles(g, width - CCdelta, x + CCdelta / 2, y + CCdelta / 2);

	}

	/**
	 * draws squares
	 * 
	 * @param g
	 * @param x
	 * @param y
	 * @param width
	 */
	public void squares(Graphics g, int x, int y, int width) {
		if (width < 10) {
			return;
		}
		int seg = width / 3;

		g.fillRect(x, y, width, width);

		g.setColor(Color.MAGENTA);
		squares(g, x - seg * 2, y - seg * 2, seg);

		g.setColor(Color.RED);
		squares(g, x + seg, y - seg * 2, seg);

		g.setColor(Color.WHITE);
		squares(g, x + seg * 4, y - seg * 2, seg);

		g.setColor(Color.GREEN);
		squares(g, x - seg * 2, y + seg, seg);

		g.setColor(Color.BLUE);
		squares(g, x + seg * 4, y + seg, seg);

		g.setColor(Color.PINK);
		squares(g, x - seg * 2, y + seg * 4, seg);

		g.setColor(Color.YELLOW);
		squares(g, x + seg, y + seg * 4, seg);

		g.setColor(Color.CYAN);
		squares(g, x + seg * 4, y + seg * 4, seg);

	}

	public void clover(Graphics g, int x, int y, int width) {
		if (width < 1) {
			return;
		}
		g.setColor(Color.WHITE);

		g.drawOval(x - width, y - width, width, width);
		g.drawOval(x, y - width, width, width);
		g.drawOval(x, y, width, width);
//		g.drawOval(width - x, width - y, width, width);

		clover(g, x, y, width - 10);

	}

	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, 1000, 1000);

		g.setColor(Color.WHITE);

//		concentricCircles(g, 500, 100, 20);

//		g.setColor(Color.ORANGE);
//		squares(g, 350, 350, 300);

		clover(g, 500, 500, 500);

	}

	public static void main(String[] arg) {
		Recursion m = new Recursion();

	}

	public Recursion() {
		JFrame f = new JFrame("Recursive Methods");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1000, 1000);
		f.add(this);
		f.setVisible(true);

	}

	Timer t = new Timer(16, this);

	public void actionPerformed(ActionEvent e) {
		repaint();
	}

}