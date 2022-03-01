import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Recursion extends JPanel implements ActionListener {

	static int screenL = 1000;
	static final double pi = Math.PI;
	int branchHue = 0;

	/**
	 * converts HSV to Color object
	 * 
	 * - hue should be 0-360
	 * 
	 * - saturation should be 0-100
	 * 
	 * - value should be 0-100
	 * 
	 * @param h
	 * @param s
	 * @param v
	 * @return
	 */
	public Color hsv2C(int h, int s, int v) {
		int rgb = Color.HSBtoRGB((float) (h / 360.0), (float) (s / 100.0), (float) (v / 100.0));
		int r = (rgb >> 16) & 0xFF, g = (rgb >> 8) & 0xFF, b = rgb & 0xFF;
		return new Color(r, g, b);
	}

	/**
	 * draws concentric circles
	 * 
	 * @param g
	 * @param width
	 * @param x
	 * @param y
	 */
	public void concentricCircles(Graphics g, int width, int x, int y, int delta) {
		if (width < 1) {
			return;
		}

		g.setColor(Color.BLUE);
		g.drawOval(x, y, width, width);

		concentricCircles(g, width - delta, x + delta / 2, y + delta / 2, delta);
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
		if (width < 1) {
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

	/**
	 * draw clover figure
	 * 
	 * @param g
	 * @param x
	 * @param y
	 * @param width
	 */
	public void clover(Graphics g, int x, int y, int width) {
		if (width < 1) {
			return;
		}
		g.setColor(Color.GREEN);

		g.drawOval(x - width, y - width, width, width);
		g.drawOval(x, y - width, width, width);
		g.drawOval(x, y, width, width);
		g.drawOval(x - width, y, width, width);

		clover(g, x, y, width - 5);

	}

	public void flake(Graphics g, int x, int y, double length, int hue) {
		if (length < 1) {
			return;
		}
		if (hue > 360) {
			hue = 0;
		}

		for (int i = 1; i < 7; i++) {
			int x2 = x + (int) (length * Math.cos(pi * i / 3));
			int y2 = y + (int) (length * Math.sin(pi * i / 3));

			g.setColor(hsv2C(hue, 100, 100));
			g.drawLine(x, y, x2, y2);

			flake(g, x2, y2, length / 2.6, hue + 30);
		}
	}

	public void treeBranch(Graphics g, int x, int y, int length, double angle, int hue) {
		if (length < 1) {
			return;
		}

		// 3pi/12, 5pi/12, 7pi/12, 9pi/12
		// -3pi/12, -1pi/12, 1pi/12, 3pi/12

		// i goes from -3 to 3
		for (int i = -3; i < 5; i += 2) {
			int x2 = x + (int) (length * Math.cos(angle + i * pi / 12));
			int y2 = y - (int) (length * Math.sin(angle + i * pi / 12));
			g.setColor(hsv2C(hue, 100, 100));
			g.drawLine(x, y, x2, y2);
			treeBranch(g, x2, y2, length / 2, angle + i * pi / 12, hue + 30);
		}

	}

	public void paint(Graphics g) {
		screenL = this.getHeight();

		g.setColor(Color.black);
		g.fillRect(0, 0, 1000, 1000);

		g.setColor(Color.WHITE);

//		concentricCircles(g, 500, screenL / 2 - 250, screenL / 2 - 250, 10);

//		g.setColor(Color.ORANGE);
//		squares(g, screenL / 2 - 150, screenL / 2 - 150, 300);

//		clover(g, 500, 500, 500);

//		flake(g, screenL / 2, screenL / 2, 400, 0);

		g.setColor(hsv2C(branchHue, 100, 100));
		g.drawLine(screenL / 2, screenL - 50, screenL / 2, screenL - 350);
		treeBranch(g, screenL / 2, screenL - 350, 150, pi / 2, branchHue + 30);

		branchHue++;
	}

	public static void main(String[] arg) {
		Recursion m = new Recursion();

	}

	public Recursion() {
		JFrame f = new JFrame("Recursive Methods");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1000, 1000);
		f.add(this);
		t.start();

		f.setVisible(true);

	}

	Timer t = new Timer(16, this);

	public void actionPerformed(ActionEvent e) {
		repaint();
	}

}
