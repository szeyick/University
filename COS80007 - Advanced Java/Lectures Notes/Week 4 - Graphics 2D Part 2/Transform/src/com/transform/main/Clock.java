package com.transform.main;

import java.awt.*;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Clock extends JPanel
{
	static final long serialVersionUID = 1L;
	private RunClock c;
	private JButton startB;
	private JButton stopB;

	public Clock()
	{
		System.out.println("thread running Clock() is " +
				Thread.currentThread().getName());
		c = new RunClock();
		startB = new JButton("Start");
		stopB = new JButton("Stop");

		stopB.setEnabled(false);  // begin with this disabled

		startB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("thread intercepting Start Button Event is " +
						Thread.currentThread().getName());
				// disable to stop more "start" requests
				startB.setEnabled(false);

				// Thread to run the counter
				Thread clockThread = new Thread(c,"Clock");
				clockThread.start();

				stopB.setEnabled(true);
				stopB.requestFocus();
			}
		});

		stopB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("thread intercepting Stop Button Event is " +
						Thread.currentThread().getName());
				stopB.setEnabled(false);
				c.stopClock();
				startB.setEnabled(true);
				startB.requestFocus();
			}
		});

		JPanel innerButtonP = new JPanel();
		innerButtonP.setLayout(new GridLayout(0, 1, 0, 3));
		innerButtonP.add(startB);
		innerButtonP.add(stopB);

		JPanel buttonP = new JPanel();
		buttonP.setLayout(new BorderLayout());
		buttonP.add(innerButtonP, BorderLayout.NORTH);

		this.setLayout(new BorderLayout(10, 10));
		this.setBorder(new EmptyBorder(20, 20, 20, 20));
		this.add(buttonP, BorderLayout.WEST);
		this.add(c, BorderLayout.CENTER);
	}

	public static void main(String[] args)
	{
		System.out.println("thread running main() is " +
				Thread.currentThread().getName());
		Clock c = new Clock();
		JFrame f = new JFrame("Clock");
		f.setContentPane(c);
		f.setSize(320, 200);
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}

class RunClock extends JComponent
implements Runnable
{
	static final long serialVersionUID = 2L;
	private volatile boolean keepRunning;
	private Font paintFont;
	private volatile String timeMsg;
	private volatile int arcLen;

	public RunClock()
	{
		paintFont = new Font("SansSerif", Font.BOLD, 14);
		timeMsg = "never started";
		arcLen = 0;
	}

	public void run()
	{
		runClock();
	}

	public void runClock()
	{
		System.out.println("thread running RunClock() is " +
				Thread.currentThread().getName());

		DecimalFormat fmt = new DecimalFormat("0.000");
		long normalSleepTime = 10;

		long startTime = System.currentTimeMillis();
		long timeLapsedSinceLastUpdate = 0;
		
		keepRunning = true;

		while ( keepRunning )
		{
			try
			{
				Thread.sleep(normalSleepTime);
			} catch ( InterruptedException x )
			{
				// ignore
			}

			double elapsedSecs = (System.currentTimeMillis() - startTime)/1000.0;
			timeMsg = fmt.format(elapsedSecs);
//			arcLen = ( ( ( int ) elapsedSecs ) % 60 ) * 360 / 60;
			repaint();
		}
	}

	public void stopClock()
	{
		keepRunning = false;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;

		System.out.println("thread that invoked paint() is " +
				Thread.currentThread().getName());

		g2D.setPaint(Color.black);
		g2D.setFont(paintFont);
		g2D.drawString(timeMsg, 0, 15);

		g2D.setStroke(new BasicStroke(2.0F));
		g2D.draw(new Ellipse2D.Double(0, 20, 100, 100));  // black border

		g2D.setPaint(Color.white);
		g2D.draw(new Ellipse2D.Double(3, 23, 94, 94));  // white for unused portion

		g2D.setPaint(Color.blue);  // blue for used portion
		g2D.fill(new Arc2D.Double(2, 22, 96, 96, 90, -arcLen,Arc2D.PIE));
	}
}
