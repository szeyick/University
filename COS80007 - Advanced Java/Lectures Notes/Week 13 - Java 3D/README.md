## Java3D

Java3D programs use a data structure called a **scene graph** that is used to describe and organise the graphics that will be drawn onto the users display. This scene graph is otherwise called the **virtual world**.

The virtual world contains everything that will be displayed including the lights.

- **GraphicsConfiguration** 

Describes the graphics destination (screen, printer). It means that there can be many GraphicsConfiguration objects associated to a graphics device.

```
// Return the appropriate GraphicsConfiguration object
GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration(); 
```

After this we use the configuration object to create a **Canvas3D**. It provides a drawing surface for 3D rendering.

- **VirtualUniverse** 

Creates a VirtualUniverse object, this is the container that we need to hold our scene in. It creates a **Locale**, **Viewing Platform** and **Viewer** that allows to create a default scene.

SimpleUniverse is a child class of VirtualUniverse.

### Java3D Outline

Almost every Java3D program is assembled from objects that are in the Java3D class hierachy. 

The virtual universe is created from a **scene graph**, the scene graph is created using instances of Java3D classes. The objects in the scene graph are assembled from objects that define the geometry, sound, lights, location, orientation, and appearance.

A scene graph is constructed from a bunch of **Node** objects that form a tree. A scene graph is formed from the treees rooted at the Locale objects. Only a single path exists from the root to any leaf.

### Locale Object

The VirtualUniverse object is the object that holds everything for the scene. It can contain a list of **Locale Objects**. These objects provide a reference point in the virtual world, in other words it is a landmark to determine the location of a visual object in the universe.

### BranchGroup Object

Is the root of a subgraph or branch, there are two categories of BranchGroups

- Content Branch Graph - specifies the contents of the virual universe, apperance, geometry, behaviour, location and lights.
- View Branch Graph - specifies the viewing parameters, such as viewing location and direction.

### Creating A Universe

There are generally 7 steps of creating a Virtual Universe or scene

- Create a Canvas3D object

- Create a VirtualUniverse object

- Create a Locale object, and attach it to the VirtualUniverse

- Constructor a View Branch Graph

Here we create a View, ViewPlatform, PhysicalBody, PhysicalEnvironment. We attach all that and the Canvas3D to the View

- Construct a Scene Branch Graph

Here we create the scene and an optional background.

- Compile the two branch graphs

- Insert the graphics into the Locale

### Java3D Coordinates

The View Branch Graph introduces the concept of a **image plate**.  It is an imaginary rectangle where the scene content is projected to form the rendered image. In this case, the Canvas3D object that is displayed onto the screen can be thought of that.

The Canvas3D object, that sort of works like a viewport that we as the user look through to see the scene on the other side.

By default, the image plate is centered at the origin of the Universe (0,0,0). 

We can think of the default Z-axis as looking down at the z-axis, meaning that if we have a positive z-axis we are coming towards the screen, whereas negative-z axis is going into it.

The X-axis in here is a horizontal line where the positive values are to the right.

The Y-axis is the vertical line through the centre, where positive values go up.

### Java3D Geometry

Shapes are the objects in the virtual world. The basic node for shape data is the Shape3D class.

A shape is comprised of 2 parts, the **geometry** which specifies the position of the vertices that provide the outline of the object, and also the **appearance**.

A default shape will have a wireframe appearance.

The shape can be added to the virtual world by -

- Creating them from individual vertices.
- Creating them using higher level classes such as spheres or loading them from files. 

This is similar to how we would create a shape in Graphics2D, specifying a predefined shape or using the GeneralPath object.

### Geometry Array

The basic types of geometry such as points, lines, polygones are defined using the **GeometryArray**.

For these shapes, you need to explicitly set each vertex, making complex shapes difficult. 

### Line Array

For each vertex in a LineArray object, the x,y,z coordinates need to be defined along with the colour. Again if we have positive z values, it will give the appearance that the vertex is closer to the eye or "image plate"

### Polygons 

If we are defining polygons, there are 2 important things to remember -

- The order of the vertices is important in defining the orientation

The reason for this is because a polygon will have a front and back side. The order of the vertices will define which is the back and front.

The front of the polygon is defined as the side where the vertices form a anti-clockwise loop. Thus if the vertices are in a clockwise order, it means we are looking at the back of it.

- The vertices must join and form a polygon.

### Vertices

All shapes are built from vertices, and all vertices have data associated with them.

At the most basic, all vertices need a coordinate, the colours, textures and all that do not need to be defined.

**Normals** indicate the orientation of the geometry at the vertex. It is used for lighting, if the surface faces the light it is more brightly lit and vice versa.

### Textures

Texture coordinates are used when an image or texture is attached to the surface.

### GeometryInfo

Is a class that provides support for creating complex polygons when the points can be determined by an algorithm.

We can create a GeometryInfo object as a polygon array

```
GeometryInfo geometryInfo = new GeometryInfo(GeometryInfo.POLYGON_ARRAY);

// Initialise the geometry of the shape here
Points3f[] pts = new Point3f[8];
pts[0] = new Point3f(40.0f, -19.9f, 40.0f);
pts[1] = new Point3f(30.0f, -29.9f, 80.0f);
pts[2] = new Point3f(60.0f, -39.9f, 50.0f);
...
pts[7] = new Point3f(10.0f, -79.9f, 80.0f);
```

We can do the same by defining the colours at the same vertex, then we add it to the GeometryInfo object

```
geometryInfo.setCoordinates(pts);
geometryInfo.setColors(clrs);
```

### Updating Scenes

Once a scene has been compiled and active, it is not possible to do things with the nodes unless the **capability bit** has been set.

### Bounds

Display, behaviour and lights only work within a certain predefined region. It does this because it improves performance by avoiding actions outside of these boundaries.

### Lighting

There are three types of lighting 

- Ambient Light - General lighting applied to the whole virtual universe.
- Point Light - Light that is coming out from a single point in the 3D space.
- Directional Light - Parallel ligth coming from a source

The lights can have properties to it, such as a colour and is bound to a region that it has effect.

### Behaviour

Mouse events can be added to the Branch Group. This means that any shape that is added to the BranchGroup will have the same transformation.

Generally we create a class that extends **Behaviour** and add it to the Branch Group. In Java3D, the behaviour defined will respond when it satisfies the **wake up criteria**.

The following defines the series of events and notifications used -

- Create a Behaviour and register its WakeUpCondition
- Java3D enters its behaviour processing loop
- Java3D checks whether the current WakeUpCondition is satisfied.
- If the WakeUpCondition is met, it calls processStimulus on the Behaviour object.
- The Behaviour sets its next WakeUpCondition
- The loop continues

### Translation and Rotation

Translation involves getting the current x,y,z transformation and applying the new values.

Rotation requires translating the point of rotation back to the origin (0,0,0), rotate then translate it back.

### Interpolators

These types of classes allow an objects current state to be interpolated between a set of defined states such as time.
