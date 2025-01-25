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

    private float xPos, yPos; // position in pixels
    private float xVel, yVel; // velocity in pixels per second
    private float xAccel, yAccel; // acceleration in pixels per second per second
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
        // position = new Point(x, y);
        // velocity = new Point(0, 0);
        // acceleration = new Point(0, 0);
        xPos = x;
        yPos = y;
        xVel = 0;
        yVel = 0;
        xAccel = 0;
        yAccel = 0;
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
        setPosition(
            xPos + xVel / Settings.refreshRate, 
            yPos + yVel / Settings.refreshRate
        );
    }

    /**
     * Applies this Ball's acceleration to its velocity.
     */
    private void applyAcceleration() {
        setVelocity(
            xVel + xAccel / Settings.refreshRate, 
            yVel + yAccel / Settings.refreshRate
        );
    }

    /**
     * Returns this Ball's x position
     * 
     * @return
     *      A float representing the x position of this Ball
     */
    public float getXPos() {
        return xPos;
    }

    /**
     * Returns this Ball's y position
     * 
     * @return
     *      A float representing the y position of this Ball
     */
    public float getYPos() {
        return yPos;
    }

    /**
     * Returns this Ball's x velocity
     * 
     * @return
     *      A float representing the x velocity of this Ball
     */
    public float getXVel() {
        return xVel;
    }

    /**
     * Returns this Ball's y velocity
     * 
     * @return
     *      A float representing the y velocity of this Ball
     */
    public float getYVel() {
        return yVel;
    }

    /**
     * Returns this Ball's x acceleration
     * 
     * @return
     *      A float representing the x acceleration of this Ball
     */
    public float getXAccel() {
        return xAccel;
    }

    /**
     * Returns this Ball's y acceleration
     * 
     * @return
     *      A float representing the y acceleration of this Ball
     */
    public float getYAccel() {
        return yAccel;
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
    public void setPosition(float x, float y) {
        // position.setLocation(x, y);
        xPos = x;
        yPos = y;
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
    public void setVelocity(float x, float y) {
        // velocity.setLocation(x, y);
        // System.out.println(x + " , " + y);
        xVel = x;
        yVel = y;
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
    public void setAcceleration(float x, float y) {
        // acceleration.setLocation(x, y);
        xAccel = x;
        yAccel = y;
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