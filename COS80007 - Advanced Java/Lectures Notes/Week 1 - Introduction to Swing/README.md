## Key Facts and Terminology

- Native Peers

Are components that interact directly with the Operating System. AWT is built from the idea of native peers, meaning Java components written in AWT will inherit their look and feel directly from the operating system.

Swing components interact directly with the graphics card, requesting a point to draw on the screen. This allows for the look and feel to remain consistent across different platforms.

- Swing Rendering

Rendering in Swing is done through the paint() method. The method then calls paintComponent() which will draw the current component and after it is done will call paintChildren() which will iterate through all its components and call paint() again.

Painting in Swing is done in the order in which the components have been added.

- Swing Frames

Top level components in Swing will extend some AWT class, meaning that it has a native peer.

- JFrame

A JFrame is made up of many different layers. The main layer that we should be interested in is the JLayeredPane which contains the ContentPane. 

- ContentPane

The pane within the JFrame where most of the components (buttons, panels, fields) are added to.

- Layout Manager

Layout Managers are useful for laying out components within the ConcentPane or another component. It is significantly easier to do this than to specify the coordinates of each individual component.

Swing provides 2 different layout managers, a simple one that will just set components into default locations depending on the selected layout, and a more complex layout manager that allows for contraints to be set. Constraints are essentially properties for a component to abide by (alignment, size, fill etc).

- Grid Layout

A type of layout that can be used in a Swing component. It essentially allows you to set components in a grid form.

- Border Layout

Another type of layout that can be used, components are set to either NORTH, SOUTH, EAST, WEST and CENTER.

- Event Handling

Components in Swing can respond to events (mouse and keyboard events). Input events will cause AWT to generate an xxxEvent object where we can then inspect the object to find out where it came from and then choose what to do with it.

- Event Listening

Components in Swing can also listen to events (mouse and keyboard events). All components need to do is register to another component that they may be interested in, and when an event is triggered they will be notified.

- Inner Class

Is a class that is within another class. This inner class should only be visible to the outer class that it resides in. By being an inner class it has access to all the outer classes methods and instance variables.

- Anonymous Inner Class

The same as an inner class, without the **public class xxx** declaration and is not assigned to an instance variable. This can be useful if only a single instance of the class is required that will never be removed.

- Applets

A Java program that can be run from a web browser.
