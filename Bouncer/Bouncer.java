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
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Bouncer {

    private int t = 0;
    // UI components
    static private JFrame frame;
    static private JPanel masterPanel;

    private ArrayList<Ball> balls;

    public Bouncer() {
        // setup
        frame = new JFrame("Bouncer");
        frame.setVisible(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.pack();

        masterPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Point pos;
                int diameter = Settings.ballRadius * 2;
                int drawPosX, drawPosY;
                for (Ball ball : balls) {
                    g.setColor(ball.getColor());
                    pos = ball.getPosition();
                    drawPosX = (int)pos.getX() - Settings.ballRadius;
                    drawPosY = (int)pos.getY() - Settings.ballRadius;

                    g.fillOval(drawPosX, drawPosY, diameter, diameter);
                }
            }
        };
        masterPanel.setLayout(null);
        frame.setContentPane(masterPanel);

        balls = new ArrayList<Ball>();
        Ball ball;
        int initialVelocityBoundsX = 30;
        int initialVelocityBoundsY = 10;
        for (int num = 0; num < Settings.numBalls; num += 1) {
            // balls spawn in the middle of the screen
            ball = new Ball(masterPanel.getWidth() / 2, 3 * masterPanel.getHeight() / 4);

            // initial velocity
            ball.setVelocity(
                initialVelocityBoundsX * (2 * Math.random() - 1), 
                initialVelocityBoundsY * (2 * Math.random() - 1)
            );

            // subject to gravity
            ball.setAcceleration((int)Settings.gravity.getX(), (int)Settings.gravity.getY());

            // set random color
            ball.setColor(new Color((float)Math.random(), (float)Math.random(), (float)Math.random()));

            balls.add(ball);
        }

        Timer timer = new Timer(1000 / Settings.refreshRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                run();
            }
        });
        timer.setRepeats(true);
        timer.start();

        // display frame
        frame.setVisible(true);
        System.out.println("panel: " + frame.getWidth() + ", " + frame.getHeight());
    }

    /**
     * Updates that occur according to the set refresh rate
     */
    private void run() {
        // ball updates
        Point pos;
        for (Ball ball : balls) {
            // collision w/ frame bounds
            pos = ball.getPosition();
            if (pos.getX() <= Settings.ballRadius || pos.getX() >= masterPanel.getWidth() - Settings.ballRadius) {
                // System.out.println("bonk");
                double boundX = pos.getX();
                boundX = Math.min(boundX, masterPanel.getWidth() - Settings.ballRadius);
                boundX = Math.max(boundX, Settings.ballRadius);
                pos.setLocation(boundX, pos.getY());
                ball.setVelocity(
                    -1 * Settings.absorptionResistanceX * ball.getVelocity().getX(), // bounce
                    Settings.frictionResistanceY * ball.getVelocity().getY() // friction
                );
            }
            if (pos.getY() <= Settings.ballRadius || pos.getY() >= masterPanel.getHeight() - Settings.ballRadius) {
                // System.out.println("beep");
                double boundY = pos.getY();
                boundY = Math.min(boundY, masterPanel.getHeight() - Settings.ballRadius);
                boundY = Math.max(boundY, Settings.ballRadius);
                pos.setLocation(pos.getX(), boundY);
                ball.setVelocity(
                    Settings.frictionResistanceX * ball.getVelocity().getX(), // friction
                    -1 * Settings.absorptionResistanceY * ball.getVelocity().getY() // bounce
                );
            }
            ball.updateMotionVectors();
            // System.out.println(t++);
            // System.out.println("Position" + ball.getPosition());
            // System.out.println("Velocity" + ball.getVelocity());
            // System.out.println("Accel" + ball.getAcceleration());
        }
        masterPanel.repaint();
    }

    /**
     * Returns the master panel
     * 
     * @return
     *      The program's main content panel
     */
    public static JPanel getMasterPanel() {
        return masterPanel;
    }

    public static void main(String[] args) {
        new Bouncer();
    }



}