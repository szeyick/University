package com.treesAndTables.main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.treesAndTables.frame.MyJFrame;

/**
 * The EditableTree.
 * <p>
 * This is a demo class that displays how to add and remove
 * nodes from a tree through using the getLastSelectedPathComponent
 * method.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class EditableTree {

	/**
	 * The frame that all components will be added to.
	 */
	private static JFrame frame;
	
	/**
	 * The JTree to render our nodes.
	 */
	private static JTree tree;
	
	/**
	 * The program main.
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("Editable Tree Demo");
		createTreePanel();
		createButtonPanel();
		frame.setVisible(true);
	}

	/**
	 * Create the JTree and add it to the frame.
	 */
	private static void createTreePanel() {
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Root");
		tree = new JTree(rootNode);
		
		frame.add(tree, BorderLayout.CENTER);
	}
	
	/**
	 * Create a button panel to allow for us to add/remove nodes.
	 */
	private static void createButtonPanel() {
		JPanel buttonPanel = new JPanel();
		JButton addButton = new JButton("Add Node");
		JButton removeButton = new JButton("Remove Node");
		
		ButtonListener listener = new ButtonListener();
		addButton.addActionListener(listener);
		removeButton.addActionListener(listener);
		
		buttonPanel.add(addButton);
		buttonPanel.add(removeButton);
		
		frame.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Depending on the button selected, add or remove a node
	 * from the tree, so long as there is a node selected.
	 * @param event - The button event.
	 */
	private static void addOrRemoveNode(ActionEvent event) {
		String buttonName = event.getActionCommand();
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		switch (buttonName) {
		case "Add Node" :
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			// Only proceed if something was selected
			if (selectedNode != null) {
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("Node");
				model.insertNodeInto(newNode, selectedNode, selectedNode.getChildCount());

				// Display the new node.
			    TreeNode[] nodes = model.getPathToRoot(newNode);
			    TreePath path = new TreePath(nodes);
			    tree.scrollPathToVisible(path);
				
			}
			break;
		case "Remove Node" :
			DefaultMutableTreeNode nodeToRemove = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			// Only proceed if something was selected
			if (nodeToRemove != null) {
				// Do not remove the root node.
				if (nodeToRemove.getParent() != null) {
					model.removeNodeFromParent(nodeToRemove);
				}
			}
			break;
		default :
			break;
		}
	}
	
	/**
	 * The ButtonListener.
	 * <p>
	 * This class is responsible for listening to button events triggered
	 * by the user. Upon button selection, the actionPerformed() method is
	 * triggered.
	 * <p>
	 * @author szeyick
	 */
	private static class ButtonListener implements ActionListener {

		/**
		 * The method invoked when a button is pressed.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			addOrRemoveNode(e);
		}
		
	}
	
}
