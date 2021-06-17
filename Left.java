import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

public class Left extends Actor
{
    private final int g = 1; //Gravity
    private final int movementspeed = 5; //Movement Speed
    private int v; //Velocity
    private int life = 15; //Bullet life span
    public void move(double distance)
    {
        int x = getX();
        int y = getY();
        life--;
        if(life != 0)
        {
            x  -= movementspeed; //Move 5 pixels every seconds
            y -= 15; // Fall 15 pixels every seconds
        }
        setLocation(x, y + v); //Set location of th bullet
        v += g;
    }
}
