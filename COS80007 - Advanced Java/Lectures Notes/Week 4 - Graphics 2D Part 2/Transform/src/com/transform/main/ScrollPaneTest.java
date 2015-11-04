package com.transform.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ScrollPaneTest {

	public static void main(String[] args) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JTextArea(20, 30), BorderLayout.CENTER);
		panel.add(new JPanel(new GridLayout(1, 0)) {
			{
				add(new JButton("Foo"));
				add(new JButton("Bar"));
			}
		}, BorderLayout.PAGE_END);
		JScrollPane scrollPane = new JScrollPane(panel);

		JInternalFrame internalFrame = new JInternalFrame("InternalFrame", true,
				true);
		internalFrame.getContentPane().add(scrollPane);
		internalFrame.setPreferredSize(new Dimension(200, 200));
		internalFrame.setSize(internalFrame.getPreferredSize());
		internalFrame.setVisible(true);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setPreferredSize(new Dimension(400, 400));
		desktopPane.add(internalFrame);
		JOptionPane.showMessageDialog(null, desktopPane);
	}
}
