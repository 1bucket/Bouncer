/**
 * The Settings class holds configurable variables for the program
 * 
 * @author Paul Enrade
 * email: penrade43@gmail.com
 */

import java.awt.Point;

public class Settings {
    public static final int refreshRate = 60; // in Hertz (iterations per second)
    public static final int ballRadius = 10; // in pixels
    public static final Point gravity = new Point(0, 10);
    public static final double frictionResistanceX = 0.99; // percent of x velocity retained upon contact with horizontal bounds
    public static final double frictionResistanceY = 0.9; // percent of y velocity retained upon contact with vertical bounds
    public static final double absorptionResistanceX = 0.85; // percent of x velocity retained upon contact with vertical bounds
    public static final double absorptionResistanceY = 0.85; // percent of x velocity retained upon contact with horizontal bounds
    public static final int numBalls = 1;
}