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
    // public static final Point gravity = new Point(0, 20);
    public static final float gravityX = 0;
    public static final float gravityY = 550;
    public static final float frictionResistanceX = (float) 0.99; // percent of x velocity retained upon contact with horizontal bounds
    public static final float frictionResistanceY = (float)0.9; // percent of y velocity retained upon contact with vertical bounds
    private static final float absorptionResistanceX = (float)0.75; // percent of x velocity retained upon contact with vertical bounds
    private static final float absorptionResistanceY = (float)0.75; // percent of x velocity retained upon contact with horizontal bounds
    private static final float lowVAbsorptionResistanceX = (float)0.01; // percent of x velocity retained upon contact with vertical bounds
    private static final float lowVAbsorptionResistanceY = (float)0.01; // percent of x velocity retained upon contact with horizontal bounds
    public static final int numBalls = 1;

    public static float getAbsorptionResistanceX(float velocity) {
        velocity = Math.abs(velocity);
        if (velocity < 0.12 * gravityX) {
            return velocity / gravityX;
        }
        return absorptionResistanceX;
    }

    public static float getAbsorptionResistanceY(float velocity) {
        velocity = Math.abs(velocity);
        if (velocity < 0.12 * gravityY) {
            return velocity / gravityY;
        }
        return absorptionResistanceY;
    }
}