import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Recursion extends JPanel implements ActionListener {

	static int screenH = 1000, screenW = 1000;
	static final double pi = Math.PI;
	int panelHue = 0;
	int cStartA = 0;

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
		return new Color((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF);
	}

	public double rad(int degrees) {
		return degrees * pi / 180;
	}

	/**
	 * draws concentric circles
	 * 
	 * @param g
	 * @param width
	 * @param x
	 * @param y
	 */
	public void concentricCircles(Graphics g, int width, int x, int y, int delta, int hue) {
		if (width < 1) {
			return;
		}

		g.setColor(hsv2C(hue, 100, 100));
		g.drawOval(x, y, width, width);

		concentricCircles(g, width - delta, x + delta / 2, y + delta / 2, delta, hue + 30);
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
	public void clover(Graphics g, int x, int y, int width, int hue) {
		if (width < 1)
			return;

		g.setColor(hsv2C(hue, 100, 100));

		g.drawOval(x - width, y - width, width, width);
		g.drawOval(x, y - width, width, width);
		g.drawOval(x, y, width, width);
		g.drawOval(x - width, y, width, width);

		clover(g, x, y, width - 5, hue + 30);
	}

	public void flake(Graphics g, int x, int y, double length, int hue) {
		if (length < 1)
			return;
		if (hue > 360)
			hue %= 360;

		for (int i = 1; i < 7; i++) {
			int x2 = x + (int) (length * Math.cos(pi * i / 3));
			int y2 = y + (int) (length * Math.sin(pi * i / 3));

			g.setColor(hsv2C(hue, 100, 100));
			g.drawLine(x, y, x2, y2);

			flake(g, x2, y2, length / 2.6, hue + 30);
		}
	}

	public void tree(Graphics g, int x, int y, int length, int hue) {
		// x, y value is base of tree

		// 3 segments per branch - improves coloring
		g.setColor(hsv2C(panelHue - 20, 100, 100));
		g.drawLine(x, y, x, y - (length / 3));

		g.setColor(hsv2C(panelHue - 10, 100, 100));
		g.drawLine(x, y - (length / 3), x, y - (length * 2 / 3));

		g.setColor(hsv2C(panelHue, 100, 100));
		g.drawLine(x, y - (length * 2 / 3), x, y - (length));

		treeBranch(g, x, y - length, length / 2, pi / 2, hue + 30);
	}

	@SuppressWarnings("unused")
	public void treeBranch(Graphics g, int x, int y, int length, double angle, int hue) {
		if (length < 1)
			return;

		// -3pi/12, -1pi/12, 1pi/12, 3pi/12

		// i goes from -3 to 3
		for (int i = -3; i < 5; i += 2) {
			double angleX = length * Math.cos(angle + i * pi / 12);
			double angleY = length * Math.sin(angle + i * pi / 12);

			if (length > 10) {
				g.setColor(hsv2C(hue - 20, 100, 100));
				g.drawLine(x, y, x + (int) (angleX / 3), y - (int) (angleY / 3));

				g.setColor(hsv2C(hue - 10, 100, 100));
				g.drawLine(x + (int) (angleX / 3), y - (int) (angleY / 3), x + (int) (angleX * 2 / 3),
						y - (int) (angleY * 2 / 3));

				g.setColor(hsv2C(hue, 100, 100));
				g.drawLine(x + (int) (angleX * 2 / 3), y - (int) (angleY * 2 / 3), x + (int) (angleX),
						y - (int) (angleY));
			} else {
				g.setColor(hsv2C(hue, 100, 100));
				g.drawLine(x, y, x + (int) (angleX), y - (int) (angleY));
			}

			treeBranch(g, x + (int) angleX, y - (int) angleY, length / 2, angle + i * pi / 12, hue + 30);
		}
	}

	public void fancyFlower(Graphics g, int x, int y, int length, double angle, int hue) {
		if (length < 1)
			return;

		// -3pi/12, -1pi/12, 1pi/12, 3pi/12

		// i goes from -1 to 1
		for (int i = -3; i < 7; i += 2) {
			int x2 = x + (int) (length * Math.cos(angle + i * pi / 5));
			int y2 = y - (int) (length * Math.sin(angle + i * pi / 5));
			g.setColor(hsv2C(hue, 100, 100));
			g.drawLine(x, y, x2, y2);
			fancyFlower(g, x2, y2, length / 2, angle + i * pi / 5, hue + 30);
		}

	}

	// uses angles in degrees for greater accuracy
	public void circleOSquares(Graphics g, int x, int y, int a, int hue) {
		if (a > 360)
			return;

		g.setColor(Color.WHITE);
		g.drawRect(x + (int) (150 * Math.cos(rad(a))), y + (int) (150 * Math.sin(rad(a))), 300, 300);
		circleOSquares(g, x, y, a + 10, hue + 10);
	}

	// uses angles in degrees for greater accuracy
	public void magicCircle(Graphics g, int x, int y, int a) {
		if (a > 360)
			return;

		int dx = (int) (150 * Math.cos(rad(a))), dy = (int) (150 * Math.sin(rad(a)));

		g.setColor(Color.RED);
		g.drawOval(x + 150 - a * 5 / 6, y + 150 - a * 5 / 6, a * 5 / 3, a * 5 / 3);

		g.setColor(Color.MAGENTA);
		g.drawLine(x + 150, y + 150, x + 150 + 2 * dx, y + 150 + 2 * dy);

		g.setColor(Color.CYAN);
		g.drawOval(x + dx, y + dy, 300, 300);

		magicCircle(g, x, y, a + 5);
	}

	/**
	 * draws a circle with its x,y values as its center
	 * 
	 * @param g
	 * @param x
	 * @param y
	 * @param radius
	 */
	public void centeredCircle(Graphics g, int x, int y, int radius) {
		g.drawOval(x - radius / 2, y - radius / 2, radius, radius);
	}

	public void expandingCircle(Graphics g, double x, double y, double radius, int hue) {
		if (radius < 5) { // 5, 10
			return;
		}

		g.setColor(hsv2C(hue, 100, 100));
		centeredCircle(g, (int) x, (int) y, (int) radius);

		// increments at values of 2pi / a
		int a = 6;
		// dR of 0.75 makes all circles touching
		// try dR 1.5, 1.25
		double dR = 0.75;
		for (int i = 0; i < a; i++) {
			expandingCircle(g, x + ((radius * dR) * Math.cos(i * pi / (a / 2) + cStartA)),
					y + ((radius * dR) * Math.sin(i * pi / (a / 2) + cStartA)), radius / 2.0, hue + 30);
		}
	}

	public void curlingTree(Graphics g, int x, int y, int length, int hue) {
		g.setColor(hsv2C(hue - 20, 100, 100));
		g.drawLine(x, y, x, y - 50);

		g.setColor(hsv2C(hue - 10, 100, 100));
		g.drawLine(x, y - 50, x, y - 100);

		g.setColor(hsv2C(hue, 100, 100));
		g.drawLine(x, y - 100, x, y - 150);

		curlingTreeBranch(g, x, y - 150, length, 90, hue + 30);
	}

	public void curlingTreeBranch(Graphics g, int x, int y, double l, int a, int hue) {
		if (l < 2) // optimal at 2
			return;
		if (a > 360)
			a %= 360;
		if (hue > 360)
			hue %= 360;

		// RIGHT ARM

		double angleX = l * Math.cos(rad(a - 25));
		double angleY = l * Math.sin(rad(a - 25));

		if (l > 10) {
			g.setColor(hsv2C(hue - 20, 100, 100));
			g.drawLine(x, y, x + (int) (angleX / 3), y - (int) (angleY / 3));

			g.setColor(hsv2C(hue - 10, 100, 100));
			g.drawLine(x + (int) (angleX / 3), y - (int) (angleY / 3), x + (int) (angleX * 2 / 3),
					y - (int) (angleY * 2 / 3));

			g.setColor(hsv2C(hue, 100, 100));
			g.drawLine(x + (int) (angleX * 2 / 3), y - (int) (angleY * 2 / 3), x + (int) (angleX), y - (int) (angleY));
		} else {
			g.setColor(hsv2C(hue, 100, 100));
			g.drawLine(x, y, x + (int) (angleX), y - (int) (angleY));
		}

		curlingTreeBranch(g, x + (int) (angleX), y - (int) (angleY), l * 0.85, a - 25, hue + 30);

		// RIGHT ARM

		angleX = l * Math.cos(rad(a + 20));
		angleY = l * Math.sin(rad(a + 20));

		if (l > 10) {
			g.setColor(hsv2C(hue - 20, 100, 100));
			g.drawLine(x, y, x + (int) (angleX / 3), y - (int) (angleY / 3));

			g.setColor(hsv2C(hue - 10, 100, 100));
			g.drawLine(x + (int) (angleX / 3), y - (int) (angleY / 3), x + (int) (angleX * 2 / 3),
					y - (int) (angleY * 2 / 3));

			g.setColor(hsv2C(hue, 100, 100));
			g.drawLine(x + (int) (angleX * 2 / 3), y - (int) (angleY * 2 / 3), x + (int) (angleX), y - (int) (angleY));
		} else {
			g.setColor(hsv2C(hue, 100, 100));
			g.drawLine(x, y, x + (int) (angleX), y - (int) (angleY));
		}

		curlingTreeBranch(g, x + (int) (angleX), y - (int) (angleY), l * 0.58, a + 20, hue + 30);
	}

	public void paint(Graphics g) {
		screenH = this.getHeight();
		screenW = this.getWidth();

		g.setColor(Color.black);
		g.fillRect(0, 0, screenW, screenH);
		g.setColor(Color.WHITE);

//		concentricCircles(g, 1000, screenW / 2 - 500, screenH / 2 - 500, 10, panelHue);

//		squares(g, screenW / 2 - 150, screenH / 2 - 150, 300);

//		clover(g, screenW / 2, screenH / 2, 500, panelHue);

//		flake(g, screenW / 2, screenH / 2, 400, panelHue);

//		tree(g, screenW / 2, screenH - 10, 500, panelHue);
//		fancyFlower(g, screenW / 2, screenH - 400, 150, pi / 2, panelHue + 30);

//		circleOSquares(g, screenW / 2, screenH / 2, 0, 0);

//		magicCircle(g, screenW / 2, screenH / 2, 0);

//		expandingCircle(g, screenW / 2, screenH / 2, 200, panelHue);

		curlingTree(g, screenW / 2 - 350, screenH - 10, 270, panelHue);

		cStartA++;
		if (cStartA > 360) {
			cStartA %= 360;
		}

		panelHue += 5;
		if (panelHue > 360) {
			panelHue %= 360;
		}
	}

	public static void main(String[] arg) {
		Recursion m = new Recursion();
	}

	public Recursion() {
		JFrame f = new JFrame("Recursive Methods");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1920, 1080);
		f.add(this);
		t.start();

		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setUndecorated(true);

		f.setVisible(true);
	}

	Timer t = new Timer(16, this);

	public void actionPerformed(ActionEvent e) {
		repaint();
	}

}
