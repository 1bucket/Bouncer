/**
 * The Bouncer class houses the main environment for the program, allowing
 * for user interaction with the Ball instance on the screen via mouse clicks.
 * 
 * @author Paul Enrade
 * email: penrade43@gmail.com
 */

import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Bouncer extends JPanel implements MouseListener, MouseMotionListener {


    // public static Timer timer;
    private int frameCount = 0;
    private int t = 0;
    // UI components
    private JFrame frame;
    private boolean mouseDown, locked;
    private int mouseX, mouseY;

    // private JPanel masterPanel;

    private ArrayList<Ball> balls;

    public Bouncer() {
        super();
        // setup
        frame = new JFrame("Bouncer");
        frame.setVisible(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setPreferredSize(screenSize);
        frame.pack();

        // masterPanel = new JPanel() {
        //     @Override
        //     protected void paintComponent(Graphics g) {
        //         super.paintComponent(g);
        //         Point pos;
        //         int diameter = Settings.ballRadius * 2;
        //         int drawPosX, drawPosY;
        //         for (Ball ball : balls) {
        //             g.setColor(ball.getColor());
        //             drawPosX = (int)ball.getXPos() - Settings.ballRadius;
        //             drawPosY = (int)ball.getYPos() - Settings.ballRadius;

        //             g.fillOval(drawPosX, drawPosY, diameter, diameter);
        //         }
        //     }
        // };
        // masterPanel.setLayout(null);
        // masterPanel.setBackground(Color.BLACK);
        // frame.setContentPane(masterPanel);
        setLayout(null);
        setBackground(Color.BLACK);
        frame.setContentPane(this);

        balls = new ArrayList<Ball>();
        Ball ball;
        int initialVelocityBoundsX = 1000;
        int initialVelocityBoundsY = 1000;
        for (int num = 0; num < Settings.numBalls; num += 1) {
            // balls spawn in the middle of the screen
            ball = new Ball((int)screenSize.getWidth() / 2, 3 * (int)screenSize.getHeight() / 4);

            // initial velocity
            ball.setVelocity(
                initialVelocityBoundsX * inflate(2 * (float)Math.random() - 1), 
                // 100,
                initialVelocityBoundsY * inflate(2 * (float)Math.random() - 1)
            );
            // System.out.println(ball.getXVel());

            // subject to gravity
            ball.setAcceleration((int)Settings.gravityX, (int)Settings.gravityY);

            // set random color
            ball.setColor(new Color((float)Math.random(), (float)Math.random(), (float)Math.random()));
            // to avoid colors blending in with the black background
            ball.setColor(ball.getColor().brighter());

            balls.add(ball);
        }

        Timer timer = new Timer(1000 / Settings.refreshRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                run();
            }
        });
        timer.setRepeats(true);
        timer.start();

        Timer test = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // System.out.println("toamto " + t);
            }
        });
        test.setRepeats(true);
        test.start();

        addMouseListener(this);
        addMouseMotionListener(this);
        mouseDown = false;
        locked = false;

        // display frame
        frame.setVisible(true);
        // System.out.println("panel: " + frame.getWidth() + ", " + frame.getHeight());
    }

    /**
     * Inflation helper method for setting initial velocity
     * 
     * @return
     *      An inflated value of x
     */
    private float inflate(float x) {
        return 1 - (float)Math.pow(x - 1, 2);
    }

    /**
     * Updates that occur according to the set refresh rate
     */
    private void run() {
        // ball updates
        float x, y;
        for (Ball ball : balls) {
            ball.updateMotionVectors();
            // collision w/ frame bounds
            x = ball.getXPos();
            y = ball.getYPos();


            if (x <= Settings.ballRadius || x >= getWidth() - Settings.ballRadius) {
                // System.out.println("bonk");
                ball.setVelocity(
                    -1 * Settings.getAbsorptionResistanceX(ball.getXVel()) * ball.getXVel(), // bounce
                    Settings.getFrictionResistanceY() * ball.getYVel() // friction
                );
                float boundX = x;
                boundX = Math.min(boundX, getWidth() - Settings.ballRadius);
                boundX = Math.max(boundX, Settings.ballRadius);
                ball.setPosition(boundX, y);
            }
            if (y <= Settings.ballRadius || y >= getHeight() - Settings.ballRadius) {
                // System.out.println("beep");
                // System.out.println(ball.getYVel());
                float former = ball.getYVel();
                ball.setVelocity(
                    Settings.getFrictionResistanceX() * ball.getXVel(), // friction
                    -1 * Settings.getAbsorptionResistanceY(ball.getYVel()) * ball.getYVel() // bounce
                );
                // System.out.println(Math.abs(former) + " to " + Math.abs(ball.getYVel()));
                float boundY = y;
                boundY = Math.min(boundY, getHeight() - Settings.ballRadius);
                boundY = Math.max(boundY, Settings.ballRadius);
                ball.setPosition(x, boundY);
            }

            // mouse activities
            if (mouseDown) {
                mouseGravity(ball);
            }
            else {
                ball.setAcceleration(Settings.gravityX, Settings.gravityY);
            }


            // System.out.println("yvel: " + ball.getYVel());
            // System.out.println("Position" + ball.getPosition());
            // System.out.println("Velocity" + ball.getVelocity());
            // System.out.println("Accel" + ball.getAcceleration());
        }
        // System.out.println(t++);
        t++;
        int frameMultiple = Settings.refreshRate / Settings.drawRefreshRate;
        if (frameCount % frameMultiple == 0) {
            repaint();
        }
        frameCount = (frameCount + 1) % Settings.refreshRate;
    }

    /**
     * Gravitates ball towards mouse position by manipulating its acceleration vector
     * 
     * @param ball
     *      The Ball whose acceleration vector is modified
     */
    private void mouseGravity(Ball ball) {
        float accelModifier = (float)0, displacementModifier = 5;
        int displacementX = mouseX - (int) ball.getXPos();
        int displacementY = mouseY - (int) ball.getYPos();
        ball.setAcceleration(
            accelModifier * ball.getXAccel() + displacementModifier * displacementX,
            accelModifier * ball.getYAccel() + displacementModifier * displacementY
        );
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int diameter = Settings.ballRadius * 2;
        int drawPosX, drawPosY;
        for (Ball ball : balls) {
            g.setColor(ball.getColor());
            drawPosX = (int)ball.getXPos() - Settings.ballRadius;
            drawPosY = (int)ball.getYPos() - Settings.ballRadius;

            g.fillOval(drawPosX, drawPosY, diameter, diameter);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (locked) {
            locked = false;
            return;
        }
        mousePressed(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // System.out.println("enter");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // System.out.println("exit");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // System.out.println("pressed");
        mouseDragged(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // System.out.println("release");
        locked = true;
        // for (Ball ball : balls) {
        //     ball.setAcceleration(Settings.gravityX, Settings.gravityY);
        // }
        mouseDown = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // System.out.println("drag");
        mouseX = e.getX();
        mouseY = e.getY();
        mouseDown = true;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // System.out.println("move");
    }

    public static void main(String[] args) {
        new Bouncer();
    }



}