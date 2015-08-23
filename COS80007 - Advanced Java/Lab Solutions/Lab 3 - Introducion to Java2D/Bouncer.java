import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;

public class Bouncer extends JApplet
{
    static final long serialVersionUID = 1L;  // java 5

    private BallCanvas theCanvas = new BallCanvas();
    private JButton createButton = new JButton("Create");
    private JTextField x1Text = new JTextField("10", 10);
    private JTextField y1Text = new JTextField("0", 10);
    private JTextField x2Text = new JTextField("300", 10);
    private JTextField y2Text = new JTextField("300", 10);
	
    private int count = 0;

    public void init()
    {
        getContentPane().add("Center", theCanvas);
        theCanvas.setBackground(Color.white);
        JPanel p = new JPanel(new GridLayout(3,1));
        JPanel p1 =  new JPanel();
        JPanel p2 =  new JPanel();
        JPanel p3 =  new JPanel();
        p.add(p1);
        p.add(p2);
        p.add(p3);
        p1.add(new JLabel("  x1"));
        p1.add(x1Text);
        p1.add(new JLabel("  y1"));
        p1.add(y1Text);
        p2.add(new JLabel("  x2"));
        p2.add(x2Text);
        p2.add(new JLabel("  y2"));
        p2.add(y2Text);
        p3.add(createButton);
        getContentPane().add(p, "South");
	
        createButton.addActionListener(new ActionListener()
        {
            public void actionPerformed( ActionEvent event )
            {
                create();
            }
        });
        
    }

    /** if textfields are ok, create and start a ball.
     */
    public void create()
    {
        int x1 = Integer.parseInt(x1Text.getText().trim());
        int y1 = Integer.parseInt(y1Text.getText().trim());
        int x2 = Integer.parseInt(x2Text.getText().trim());
        int y2 = Integer.parseInt(y2Text.getText().trim());
        final Ball ball = new Ball(theCanvas, x1, y1, x2, y2);
        theCanvas.setBall(ball);  
        Timer t = new Timer(Ball.SLEEP_TIME, new ActionListener()
        {
            public void actionPerformed( ActionEvent event )
            {
                ball.moveOneStep(Ball.SLEEP_TIME*0.001);
                theCanvas.repaint();
            }
        });
        t.start();
    }


} //Mover

/**  Ball
 *  models a moving billiard ball.
 */
class Ball
{
    static final int SLEEP_TIME = 16;  // millisec, controls time step (This controls how often it is redrawn, the larger the time, the further the jumps are)
    private static final double SPEED = 25;  // pixels/sec
    private static final int RADIUS = 10;
    private static final int DIAMETER = RADIUS*2;
	private static final double LOSS_FACTOR = 0.95;
	private static final int HEIGHT = 300;
	private static final int GRAVITY = 9800; // The speed of gravity in cm/sec/sec (Reduce this number to slow the fall and bounce).
    private Point2D.Double centre;
    private double x2, y2;
    private double vX = 0, vY = 0;   // velocity, initially 0
    private Color myColor;
    private BallCanvas canvas;
    private boolean running;
        
    public Ball(BallCanvas c, int x1, int y1, int x2, int y2)
    {
        canvas = c;
        myColor = Color.red;
        centre = new Point2D.Double(x1,y1);
        double dist = Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        vX = (x2 - x1)/dist*SPEED;  // unit velocity
        vY = (y2 - y1)/dist*SPEED;
        //System.out.println("velocity:"+vX+", "+vY);
		this.x2 = vX;
		this.y2 = vY;
        running = true;
    }


    /**
     draws dot at 'centre' position on drawing surface accessed by g 
    */
    public void display(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(myColor);
        g2.fill(new Ellipse2D.Double(centre.getX() - RADIUS, centre.getY() - RADIUS,
                   DIAMETER, DIAMETER));
    }

    /** calculates new value for centre based on old position and
     * current velocity.  No change after it reaches (x2,y2) approximately.
     * @param dt time step in seconds
     */
    public void moveOneStep(double dt)
    {
        if (running)
        {
            //   System.out.print("move:"+centre.getX()+", "+centre.getY());
            double newX = centre.getX() + vX*dt;
			double newY = centre.getY() + vY * dt;
			
            centre.setLocation(newX, newY);
            //   System.out.println("  to:"+centre.getX()+", "+centre.getY());
			// Calculate when the bounce should occur, if it occurs we flip the vY.
			if (newY >= HEIGHT) {
				newY = HEIGHT;
				vY = -vY * LOSS_FACTOR;
			}
			else {
				vY = vY + 9800 * dt;
			}
			Rectangle rectangle = new Rectangle();
			rectangle.setBounds(HEIGHT, HEIGHT, 50, 50);
			if (rectangle.contains(newX, newY)) {
				running = false;
			}
        }
    }

    public void run()
    {
    }

} // Ball

/**  BallCanvas - displays one ball. 
 */
class BallCanvas extends JPanel
{
    static final long serialVersionUID = 1L;

    private Ball theBall;
    
    public void setBall(Ball b)
    {
        theBall = b;
    }

    /** draws the ball.
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (theBall != null)
           theBall.display(g);
    }
    
} // BallCanvas