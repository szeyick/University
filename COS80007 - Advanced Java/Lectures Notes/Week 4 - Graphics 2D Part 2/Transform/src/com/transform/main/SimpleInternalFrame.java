package com.transform.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class SimpleInternalFrame
{
	public static void main(String[] args)
	{
		JFrame frame = new InternalFrame();
		frame.setVisible(true);
	}
}

class InternalFrame extends JFrame
{
	static final long serialVersionUID = 1L;

	JButton b = new JButton("Add Frame");
	JDesktopPane desktopPane = new JDesktopPane();
	int windowCount = 1;
	JPanel p = new JPanel();

	public InternalFrame()
	{
		setTitle("SimpleInternalFrame");
		setSize(800,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		p.add(b);

		UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
		for (UIManager.LookAndFeelInfo info : infos)
			makeButton(info.getName(), info.getClassName());

		add(p, BorderLayout.NORTH);
		add(desktopPane, BorderLayout.CENTER);

		// JDesktopPane has a null layout by default
		desktopPane.setLayout(new FlowLayout());

		b.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				JInternalFrame jif = new JInternalFrame(
						"Internal Frame " + windowCount++, // title
						true,  // resizable
						true,  // closable
						true,  // maximizable
						true); // iconifiable

				jif.setPreferredSize(new Dimension(350, 100));
				desktopPane.add(jif);
				desktopPane.revalidate();
				jif.setVisible(true); // jdk1.3+ Internal Frames are invisible by default
			}
		});
	}

	/**
      Makes a button to change the pluggable look and feel.
      @param name the button name
      @param plafName the name of the look and feel class
	 */
	void makeButton(String name, final String plafName)
	{
		// add button to panel

		JButton button = new JButton(name);
		p.add(button);

		// set button action

		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				// button action: switch to the new look and feel
				try
				{
					UIManager.setLookAndFeel(plafName);
					SwingUtilities.updateComponentTreeUI(p);
					JInternalFrame [] jifArray = desktopPane.getAllFrames();
					for (int i = 0; i < jifArray.length; i++)
					{
						SwingUtilities.updateComponentTreeUI(jifArray[i]);
						desktopPane.revalidate();
					}

				}
				catch(Exception e) { e.printStackTrace(); }
			}
		});
	}
}
