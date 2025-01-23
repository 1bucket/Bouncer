/**
 * The Ball class represents a circle which bounces around in the
 * program window.
 * 
 * @author Paul Enrade
 * email: penrade43@gmail.com
 */

import java.awt.Point;
import java.awt.Color;

public class Ball {

    private Point position; // components in pixels; position of the center of the ball
    private Point velocity; // components in pixels/second
    private Point acceleration; // components in pixel/second/second
    private Color color; 

    /**
     * Returns an instance of Ball
     * 
     * @param x
     *      The initial x position of the Ball
     * 
     * @param y
     *      The initial y position of the ball
     */
    public Ball(int x, int y) {
        position = new Point(x, y);
        velocity = new Point(0, 0);
        acceleration = new Point(0, 0);
        color = Color.RED;
    }

    /**
     * Updates this Ball's position and velocity vectors
     */
    public void updateMotionVectors() {
        // System.out.println("upsate");
        applyVelocity();
        applyAcceleration();
    }

    /**
     * Applies this Ball's velocity to its position.
     */
    private void applyVelocity() {
        double newX = position.getX() + velocity.getX() / Settings.refreshRate;
        double newY = position.getY() + velocity.getY() / Settings.refreshRate;
        // System.out.println("newX: " + newX + "newY: " + newY);
        setPosition(newX, newY);
    }

    /**
     * Applies this Ball's acceleration to its velocity.
     */
    private void applyAcceleration() {
        double newVX = velocity.getX() + acceleration.getX() / Settings.refreshRate;
        double newVY = velocity.getY() + acceleration.getY() / Settings.refreshRate;
        setVelocity(newVX, newVY);
    }

    /**
     * Returns this Ball's position
     * 
     * @return
     *      A Point representing the position of this Ball
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Returns this Ball's velocity
     * 
     * @return
     *      A Point representing the velocity of this ball
     */
    public Point getVelocity() {
        return velocity;
    }

    /**
     * Returns this Ball's acceleration
     * 
     * @return
     *      A Point representing the acceleration of this ball
     */
    public Point getAcceleration() {
        return acceleration;
    }

    /**
     * Returns this Ball's color
     * 
     * @return
     *      The Color object representing this Balls' color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets this Ball's position to the specified position
     * 
     * @param x
     *      The new x position of this Ball
     * 
     * @param y
     *      The new y position of this Ball
     */
    public void setPosition(double x, double y) {
        position.setLocation(x, y);
    }

    /**
     * Sets this Ball's velocity to the specified velocity
     * 
     * @param x
     *      The new x velocity of this Ball
     * 
     * @param y
     *      The new y velocity of this Ball
     */
    public void setVelocity(double x, double y) {
        velocity.setLocation(x, y);
    }

    /**
     * Sets this Ball's acceleration to the specified acceleration
     * 
     * @param x
     *      The new x acceleration of this Ball
     * 
     * @param y
     *      The new y acceleration of this Ball
     */
    public void setAcceleration(double x, double y) {
        acceleration.setLocation(x, y);
    }

    /**
     * Sets this Ball's color to the specified Color object
     * 
     * @param newColor
     *      The new Color object
     */
    public void setColor(Color newColor) {
        color = newColor;
    }
}