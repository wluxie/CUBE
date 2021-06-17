import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MyWorld extends World
{
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        prepare();
    }

    private void prepare()
    {
        //Added Ground & cube (Character)
        Character character = new Character();
        addObject(character,289,196);
        Ground ground1 = new Ground(700,25);
        addObject(ground1, 289, 392);
        //Platform
        Ground ground2 = new Ground(120,25);
        addObject(ground2,290,250);
        Ground ground3 = new Ground();
        addObject(ground3,100,156);
        Ground ground4 = new Ground();
        addObject(ground4,480,156);
        //Enemy
        Enemy enemy = new Enemy();
        addObject(enemy,478,105);
        Enemy enemy2 = new Enemy();
        addObject(enemy2,93,105);
        Enemy enemy3 = new Enemy();
        addObject(enemy3,290,319);
        
    }
    
    public void act()
    {
        if (getObjects(Enemy.class).isEmpty()) //If enemy is removed, tranfer into a new world
        {
            Greenfoot.setWorld(new MyWorld2());
        }else if (getObjects(Character.class).isEmpty()) //Restart if Blue cube dies
        {
            Greenfoot.setWorld(new Menu());
        }
    }
}
