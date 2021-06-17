import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
//Need to be done: 
//Randomizer Move Mechanic // rand = (int)(Math.random()*100 + 1 );
//  1: Jump
//  2: Left
//  3: Right
//  4: Shoot

//Collision Mechanic
//Shoot
//Sounds --> Use Greenfoot.playSound("au.wav");
//Animation

public class Enemy extends Actor
{
    private final int g = 1; //Gravity
    private final int movementspeed = 5; //Movement Speed
    private int v; //Velocity
    private int rand;
    private int randDelay;
    //private boolean keyDown;
    
    public Enemy()
    {
        v = 0; // Set velocity to 0 for cube
    }
    // Action main method for cube
    public void act()
    {
       fall();
       jump();
       //shoot();
       move(); //Added Move mechnanic
       if(Hit())
       {
            removeTouching(Character.class);
       }
    }  
    
                                //--DO NOT TOUCH--//
    
    public void fall()
    {
        setLocation(getX(), getY() + v);
        if (isOnSolidGround() == true) 
        {
            v = 0;
            //Fix Cube being sucked into platform - Nov 13rd
            while (isOnSolidGround()) 
            {
                setLocation(getX(), getY() - 1);
            }
            setLocation(getX(), getY() + 1); 
        } else if(v < 0 && HeadBumpFix())
        {
            v = 0;
            //Head Bump recoil fix where cube is being sucked into the platform
            // Nov 13rd
            while (HeadBumpFix())
            {
                setLocation(getX(), getY() + 1);
            }
        }else 
        {
            v += g; // Gravity
        }
        
    }
    
    public void jump()
    {
       // Random chance enemy will jump
        if (Greenfoot.getRandomNumber(8) == 1 && isOnSolidGround() == true)
       {
            v = -17; 
       } //Jump speed, [16-18]
    }
    
                                //--DO NOT TOUCH--//
    
    //Movement 
    public void move()
    {
        int x = getX(); //Get X coordinates 
        int y = getY(); //Get Y coordinates
        if(Greenfoot.getRandomNumber(100) <= 10) // 10% of moving Right
        {
            x += Greenfoot.getRandomNumber(40); // Moving 0 to 40 unit
        }else if(Greenfoot.getRandomNumber(100) <= 10) // 10% of moving Left
        {
            x -= Greenfoot.getRandomNumber(40); // Moving 0 to 40 unit
        }
        setLocation(x,y);
    }
    
    public boolean Hit()
    {
        boolean flag = false;
        int imagew = getImage().getWidth();
        int imageh = getImage().getHeight();

        if(getOneObjectAtOffset(imagew/-2,imageh/2, Character.class) != null || getOneObjectAtOffset(imagew/2,imageh/2, Character.class) != null 
        || getOneObjectAtOffset(imagew/2,imageh/-2, Character.class) != null || getOneObjectAtOffset(imagew/-2,imageh/-2, Character.class) != null)
        {
            flag = true;
        }
        return flag;
    }
    
    // On solid Ground flag
    public boolean isOnSolidGround() 
    {
        boolean flag = false;
        
        if(getY() > getWorld().getHeight() - 25) //Floor is 25 unit high 
        {
            flag = true;
        }
        int imagew = getImage().getWidth();
        int imageh = getImage().getHeight();
        if (getOneObjectAtOffset(imagew/-2,imageh/2, Ground.class) != null || getOneObjectAtOffset(imagew/2,imageh/2, Ground.class) != null)
        {
            flag = true;
        }
        
        return flag;
    }
    
    // Head Bump problem Fixed - Bug - Nov 8th
    public boolean HeadBumpFix()
    {
        boolean head = false;
        
        int imagew = getImage().getWidth();
        int imageh = getImage().getHeight();
        if (getOneObjectAtOffset(imagew/-2,imageh/-2, Ground.class) != null || getOneObjectAtOffset(imagew/2,imageh/-2, Ground.class) != null)
        {
            head = true;
        } // Offset cube when interact with a platform to avoid getting sucked in
        
        return head;
    }
    
    //Double Jump fixed - Bug - Nov 13rd
    public boolean CanMoveLeft()
    {
        boolean wallleft = true;
        
        int imagew = getImage().getWidth();
        int imageh = getImage().getHeight();
        if (getOneObjectAtOffset(imagew/-2 - 3,imageh/-2, Ground.class) != null || getOneObjectAtOffset(imagew/-2 - 3,imageh/2 - 1, Ground.class) != null)
        {
            wallleft = false;
        } // Offset cube when hit a wall to avoid double jump
        
        return wallleft;
    }
    
    public boolean CanMoveRight()
    {
        boolean wallright = true;
        
        int imagew = getImage().getWidth();
        int imageh = getImage().getHeight();
        if (getOneObjectAtOffset(imagew/2 + 3,imageh/-2, Ground.class) != null || getOneObjectAtOffset(imagew/2 + 3,imageh/2 - 1, Ground.class) != null)
        {
            wallright = false;
        } // Offset cube when hit a wall to avoid double jump
        
        return wallright;
    }
    /*
    public void shoot()
    {
        
        if(!keyDown && Greenfoot.isKeyDown("right"))
        {
            bullet bullet = new bullet();
            getWorld().addObject(bullet,getX(),getY());
            keyDown = true;
        }
        if(!keyDown && Greenfoot.isKeyDown("left"))
        {
            bullet2 bullet2 = new bullet2();
            getWorld().addObject(bullet2,getX(),getY());
            keyDown = true;
        }
        if(keyDown && (!Greenfoot.isKeyDown("right") && !Greenfoot.isKeyDown("left")))
        {
            keyDown = false;
        }
    }
    */
}
