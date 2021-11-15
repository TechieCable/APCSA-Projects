import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import core.data.DataSource;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {

	// CREATE THE OBJECT (STEP 1)
	Background bg = new Background(0, 0);

	// Scoring variables
	int score = 0;

	Observation ob1;

	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

	Font large = new Font("Comic Sans MS", Font.PLAIN, 50);
	Font medium = new Font("Comic Sans MS", Font.PLAIN, 20);
	Font small = new Font("Comic Sans MS", Font.PLAIN, 10);

	public void weather() {
		String id1 = "KNKX";
		// https://w1.weather.gov/xml/current_obs/KNKX.xml
		DataSource ds1 = DataSource.connect("https://w1.weather.gov/xml/current_obs/" + id1 + ".xml");
		ds1.setCacheTimeout(15 * 60);
		ds1.load();
		// ds1.printUsageString();

		ob1 = ds1.fetch("Observation", "temp_f", "wind_degrees", "weather", "location", "wind_string", "wind_mph", "visibility_mi");
		System.out.println(ob1);
		// float temp, int windDir, String description, String location, String windDesc, Double windSpeed, int visibility
	}

	public void paint(Graphics g) {
		super.paintComponent(g);

		bg.paint(g);

		// reduces how frequently the weather updates
		if (System.currentTimeMillis() % 10000 < 1000) {
			weather();
		}

		Date date = new Date();

		g.setColor(Color.white);
		g.setFont(large);
		g.drawString("" + dateFormat.format(date), 50, 150);
		g.drawString("" + timeFormat.format(date), 80, 200);
		g.drawString(ob1.temp + "", 120, 300);

		g.setFont(medium);
		g.drawString(ob1.description + "", 150, 325);
		
		g.drawString("Wind", 150, 375);


		g.drawString(ob1.windDesc + "", 150, 400);
		g.drawString(ob1.windSpeed + " mph", 140, 425);

		g.setFont(small);
		g.drawString("Location: " + ob1.location, 25, 550);

		if (ob1.description.contains("Sunny")) {
			System.out.println("it's sunny!");
			bg.changePicture("sunny.jpg");
		} else if (ob1.description.contains("Cloud")) {
			System.out.println("it's cloudy!");
			bg.changePicture("cloudy.jpg");
		} else if (ob1.description.contains("Overcast")) {
			System.out.println("it's overcast!");
			bg.changePicture("overcast.jpg");
		} else if (ob1.description.contains("Fog")) {
			System.out.println("it's foggy!");
			 bg.changePicture("fog.jpg");
		} else if (ob1.description.contains("Fair")) {
			System.out.println("it's fair!");
			 bg.changePicture("fair.jpg");
		} else if (ob1.description.contains("xxxxx")) {
			System.out.println("it's xxxx!");
			// bg.changePicture("xxxx.jpg");
		}
	}

	public static void main(String[] arg) {
		Frame f = new Frame();
	}

	public Frame() {
		JFrame f = new JFrame("Weather App");
		f.setSize(new Dimension(400, 600));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1, 2));
		f.addMouseListener(this);
		f.addKeyListener(this);

		weather();

		Timer t = new Timer(1000, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
