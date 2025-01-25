/**
 * The Settings class holds configurable variables for the program
 * 
 * @author Paul Enrade
 * email: penrade43@gmail.com
 */

import java.awt.Point;

public class Settings {
    public static final int refreshRate = 60; // in Hertz (iterations per second)
    public static final int drawRefreshRate = 60; // in Hertz (iterations per second)
    public static final int ballRadius = 10; // in pixels
    // public static final Point gravity = new Point(0, 20);
    public static final float gravityX = 0;
    public static final float gravityY = 1100;
    public static final int initialVelocityBoundsX = 200;
    public static final int initialVelocityBoundsY = 100;
    public static final int randomVelocityBoundsX = 1000;
    public static final int randomVelocityBoundsY = 1000;
    public static final float frictionResistanceX = (float) 0.95; // percent of x velocity retained upon contact with horizontal bounds
    public static final float frictionResistanceY = (float)0.9; // percent of y velocity retained upon contact with vertical bounds
    private static final float absorptionResistanceX = (float)0.75; // percent of x velocity retained upon contact with vertical bounds
    private static final float absorptionResistanceY = (float)0.75; // percent of x velocity retained upon contact with horizontal bounds
    private static final float lowVAbsorptionResistanceX = (float)0.01; // percent of x velocity retained upon contact with vertical bounds
    private static final float lowVAbsorptionResistanceY = (float)0.01; // percent of x velocity retained upon contact with horizontal bounds
    public static final int numBalls = 100;

    /**
     * Returns the percent of velocity retained after a ball hits
     * a horizontal bound
     * 
     * @return
     *      A float in range [0, absorptionResistanceX]
     */
    public static float getAbsorptionResistanceX(float velocity) {
        return Math.min(absorptionResistanceX, 5 * Math.abs(velocity) / (gravityX == 0 ? 1 : gravityX));
    }

    /**
     * Returns the percent of velocity retained after a ball hits
     * a vertical bound
     * 
     * @return
     *      A float in range [0, absorptionResistanceY]
     */
    public static float getAbsorptionResistanceY(float velocity) {
        return Math.min(absorptionResistanceY, 5 * Math.abs(velocity) / (gravityY == 0 ? 1 : gravityY));
    }
}