package com.treesAndTables.main;

import java.awt.Font;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.treesAndTables.frame.MyJFrame;

/**
 * The BasicTreeDemo.
 * <p>
 * This class is a demonstration of how a tree
 * is to be implemented in Java.
 * <p>
 * @author szeyick
 * @version 0.1
 */
public class BasicTreeDemo {

	/**
	 * The frame that all components will be added to.
	 */
	private static JFrame frame;
	
	/**
	 * The program main.
	 * @param args - Command line arguments.
	 */
	public static void main(String[] args) {
		frame = new MyJFrame("Basic Tree Demo");
		createTree();
		frame.setVisible(true);
	}
	
	/**
	 * Create the nodes to the tree.
	 */
	private static void createTree() {
		// TreeNode represent individual nodes in the tree.
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Tree Root");
		DefaultMutableTreeNode childNode = new DefaultMutableTreeNode("Child Node");
		DefaultMutableTreeNode grandChildNode = new DefaultMutableTreeNode("Grand Child Node");
		DefaultMutableTreeNode anotherChildNode = new DefaultMutableTreeNode("Another Child Node");
		
		// Define the structure of the tree by adding nodes to each other where appropriate
		childNode.add(grandChildNode);
		rootNode.add(anotherChildNode);
		rootNode.add(childNode);
		
		// The root node needs to be added to the tree, which is the component to be displayed.
		JTree tree = new JTree(rootNode);
		tree.addTreeSelectionListener(new MyTreeListener());
	    tree.putClientProperty("JTree.lineStyle","Angled");
	    tree.setFont(new Font("Helvetica", Font.BOLD, 20));
	    
	    // Print the list of nodes from the root in some sort of traversal order.
	    Enumeration treeEnumeration = rootNode.breadthFirstEnumeration();
	    while (treeEnumeration.hasMoreElements()) {
	    	DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeEnumeration.nextElement();
	    	System.out.println(node.getUserObject());
	    }
		frame.add(tree);
	}
	
	/**
	 * The MyTreeListener.
	 * <p>
	 * This class is responsible for responding to events triggered from selecting
	 * nodes in the tree.
	 * <p>
	 * @author szeyick
	 *
	 */
	private static class MyTreeListener implements TreeSelectionListener {

		/**
		 * Method called when a tree node is selected and deselected.
		 */
		@Override
		public void valueChanged(TreeSelectionEvent e) {
			JTree tree = (JTree) e.getSource();
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			if (selectedNode != null) {
				System.out.println(selectedNode.getUserObject());
			}
		}
	}
}
