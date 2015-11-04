package com.transform.main;

import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.picking.PickCanvas;
import com.sun.j3d.utils.picking.PickResult;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class Pick extends MouseAdapter {

private PickCanvas pickCanvas;


public Pick()

{

    Frame frame = new Frame("Box and Sphere");

    GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();

    Canvas3D canvas = new Canvas3D(config);

    canvas.setSize(400, 400);

    SimpleUniverse universe = new SimpleUniverse(canvas);

    BranchGroup group = new BranchGroup();


    // create a color cube


    Vector3f vector = new Vector3f(-0.3f, 0.0f, 0.0f);

    Transform3D transform = new Transform3D();

    transform.setTranslation(vector);

    TransformGroup transformGroup = new TransformGroup(transform);

    ColorCube cube = new ColorCube(0.3);

    transformGroup.addChild(cube);

    group.addChild(transformGroup);


    //create a sphere


    Vector3f vector2 = new Vector3f(+0.3f, 0.0f, 0.0f);

    Transform3D transform2 = new Transform3D();

    transform2.setTranslation(vector2);

    TransformGroup transformGroup2 = new TransformGroup(transform2);

    Appearance appearance = new Appearance();

    appearance.setPolygonAttributes(

       new PolygonAttributes(PolygonAttributes.POLYGON_LINE,

       PolygonAttributes.CULL_BACK,0.0f));

    Sphere sphere = new Sphere(0.3f,appearance);

    transformGroup2.addChild(sphere);

    group.addChild(transformGroup2);


    universe.getViewingPlatform().setNominalViewingTransform();

    universe.addBranchGraph(group);

    frame.addWindowListener(new WindowAdapter() {

       public void windowClosing(WindowEvent winEvent) {

          System.exit(0);

       }

   	});

    frame.add(canvas);

    pickCanvas = new PickCanvas(canvas, group);

    pickCanvas.setMode(PickCanvas.BOUNDS);

    canvas.addMouseListener(this);

    frame.pack();

    frame.show();

}


public static void main( String[] args ) {

    new Pick();

}


public void mouseClicked(MouseEvent e)

{

    pickCanvas.setShapeLocation(e);

    PickResult result = pickCanvas.pickClosest();

    if (result == null) {

       System.out.println("Nothing picked");

    } else {

       Primitive p = (Primitive)result.getNode(PickResult.PRIMITIVE);

       Shape3D s = (Shape3D)result.getNode(PickResult.SHAPE3D);

       if (p != null) {

          System.out.println(p.getClass().getName());

       } else if (s != null) {

             System.out.println(s.getClass().getName());

       } else{

          System.out.println("null");

       }

    }

}

} // end of class Pick
