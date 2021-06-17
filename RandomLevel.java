import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class RandomLevel extends World
{
    public RandomLevel()
    {    
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
        //Platform //Spawn in a rang between (x ~ 10-590, y ~ 120~220)
        Ground ground2 = new Ground();
        addObject(ground2,Greenfoot.getRandomNumber(580)+10,Greenfoot.getRandomNumber(200)+120); 
        Ground ground3 = new Ground();
        addObject(ground3,Greenfoot.getRandomNumber(580)+10,Greenfoot.getRandomNumber(200)+120);
        Ground ground4 = new Ground();
        addObject(ground4,Greenfoot.getRandomNumber(580)+10,Greenfoot.getRandomNumber(200)+120);
        if(Greenfoot.getRandomNumber(5) == 1) //20% chance of 4th platform
        {
            Ground ground5 = new Ground();
            addObject(ground5,Greenfoot.getRandomNumber(580)+10,Greenfoot.getRandomNumber(200)+120);
        }
        //Enemy //Spawn in a rang between (x ~ 10-590, y ~ 10~390)
        Enemy enemy = new Enemy();
        addObject(enemy,Greenfoot.getRandomNumber(580)+10,Greenfoot.getRandomNumber(380)+10);
        Enemy enemy2 = new Enemy();
        addObject(enemy2,Greenfoot.getRandomNumber(580)+10,Greenfoot.getRandomNumber(380)+10);
        Enemy enemy3 = new Enemy();
        addObject(enemy3,Greenfoot.getRandomNumber(580)+10,Greenfoot.getRandomNumber(380)+10);
        
        if(Greenfoot.getRandomNumber(5) == 1) //20% chance of fighting a 4th enemy
        {
            Enemy enemy4 = new Enemy();
            addObject(enemy4,Greenfoot.getRandomNumber(580)+10,Greenfoot.getRandomNumber(380)+10);
        }
        if(Greenfoot.getRandomNumber(10) == 1) // 10% chance of fighting a 5th enemy
        {
            Enemy enemy5 = new Enemy();
            addObject(enemy5,Greenfoot.getRandomNumber(580)+10,Greenfoot.getRandomNumber(380)+10);
        }
        if(Greenfoot.getRandomNumber(20) == 1) // 5% chance of fighting a 6th enemy
        {
            Enemy enemy6 = new Enemy();
            addObject(enemy6,Greenfoot.getRandomNumber(580)+10,Greenfoot.getRandomNumber(380)+10);
        }
    }
    public void act()
    {
        if (getObjects(Enemy.class).isEmpty()) //If enemy is removed, tranfer into a new world
        {
            Greenfoot.setWorld(new RandomLevel());
        }else if (getObjects(Character.class).isEmpty()) //Restart if Blue cube dies
        {
            Greenfoot.setWorld(new Menu());
        }
    }
}
