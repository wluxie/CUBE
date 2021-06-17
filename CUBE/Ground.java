import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Ground extends Actor
{
    public Ground() //Ground's properties
    {
        this(100,25);
    }
    public Ground(int width, int height) 
    {
        GreenfootImage image = getImage();
        image.scale(width,height); //Set scale of ground
        setImage(image);
    }
}
