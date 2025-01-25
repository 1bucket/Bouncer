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
    private static final float frictionResistanceX = (float) 0.95; // percent of x velocity retained upon contact with horizontal bounds
    private static final float frictionResistanceY = (float)0.9; // percent of y velocity retained upon contact with vertical bounds
    private static final float absorptionResistanceX = (float)0.75; // percent of x velocity retained upon contact with vertical bounds
    private static final float absorptionResistanceY = (float)0.75; // percent of x velocity retained upon contact with horizontal bounds
    private static final float lowVAbsorptionResistanceX = (float)0.01; // percent of x velocity retained upon contact with vertical bounds
    private static final float lowVAbsorptionResistanceY = (float)0.01; // percent of x velocity retained upon contact with horizontal bounds
    public static final int numBalls = 100;

    public static float getFrictionResistanceX() {
        return frictionResistanceX;
    }

    public static float getFrictionResistanceY() {
        return frictionResistanceY;
    }

    public static float getAbsorptionResistanceX(float velocity) {
        velocity = Math.abs(velocity);
        if (velocity < 0.105 * gravityX) {
            return velocity / (gravityX == 0 ? 1 : gravityX);
        }
        return absorptionResistanceX;
    }

    public static float getAbsorptionResistanceY(float velocity) {
        velocity = Math.abs(velocity);
        // if (velocity < 0.105 * gravityY) {
        //     return velocity / gravityY;
        // }
        // return absorptionResistanceY;
        float resistance = Math.min(absorptionResistanceY, 5 * velocity / (gravityY == 0 ? 1 : gravityY));
        // System.out.println(resistance);
        return resistance;
    }
}