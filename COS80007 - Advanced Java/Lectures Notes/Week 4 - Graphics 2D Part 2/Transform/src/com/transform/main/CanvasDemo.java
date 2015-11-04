package com.transform.main;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.Label;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class CanvasDemo extends Applet {

	public CanvasDemo() {


		setLayout(new BorderLayout());

		GraphicsConfiguration config =

				SimpleUniverse.getPreferredConfiguration();

		Canvas3D canvas = new Canvas3D(config);

		add("North",new Label("This is the top"));

		add("Center", canvas);

		add("South",new Label("This is the bottom"));

		BranchGroup contents = new BranchGroup();

		contents.addChild(new ColorCube(0.3));

		SimpleUniverse universe = new SimpleUniverse(canvas);

		universe.getViewingPlatform().setNominalViewingTransform();

		universe.addBranchGraph(contents);

	}

	public static void main( String[] args ) {

		CanvasDemo demo = new CanvasDemo();

		new MainFrame(demo,400,400);

	}

}
