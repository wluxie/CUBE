import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//Collision Mech bug --> Need to fix - Nov 15th // FIXED!!!
//Shooting mech need fixing

public class Character extends Actor
{
    private final int g = 1; //Gravity
    private final int movementspeed = 5; //Movement Speed
    private int v; //Velocity
    private int jumpFrameR = 2;
    private boolean keyDown;
    public Character()
    {
        v = 0; // Set velocity to 0 for character
    }
    // Action main method for cube
    public void act()
    {
        fall();
        if(Greenfoot.isKeyDown("W") && isOnSolidGround() == true)
        {
            jump();
        }
        shoot();
        move(); //We're still able to move mid air ==> Added Move mechnanic
    }  
    // Fall mechanic
    public void fall()
    {
        setLocation(getX(), getY() + v);
        if (isOnSolidGround() == true || Collision() == true) 
        {
            v = 0;
            //Fix Cube being sucked into platform - Nov 13rd
            while (isOnSolidGround() || Collision()) 
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
        v = -17; //Jump speed, [16-18]
    }
    //Movement 
    public void move()
    {
        int x = getX(); //Get X coordinates 
        int y = getY(); //Get Y coordinates
        //Fixed cube able to double jump onto walls - Nov 13rd 
        if(Greenfoot.isKeyDown("D") && CanMoveRight()) {x += movementspeed;}
        if(Greenfoot.isKeyDown("A") && CanMoveLeft()) {x -= movementspeed;}
        setLocation(x,y); // Nov 6th
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

    public boolean Collision() //Collision w/ Red Cube
    {
        boolean flag = false;
        int imagew = getImage().getWidth();
        int imageh = getImage().getHeight();

        if(getOneObjectAtOffset(imagew/-2,imageh/2, Enemy.class) != null || getOneObjectAtOffset(imagew/2,imageh/2, Enemy.class) != null)
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
        if (getOneObjectAtOffset(imagew/-2 - 3,imageh/-2, Ground.class) != null || getOneObjectAtOffset(imagew/-2 - 3,imageh/2 - 1, Ground.class) != null
        || getOneObjectAtOffset(imagew/-2 - 3,imageh/-2, Enemy.class) != null || getOneObjectAtOffset(imagew/-2 - 3,imageh/2 - 1, Enemy.class) != null)
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
        if (getOneObjectAtOffset(imagew/2 + 3,imageh/-2, Ground.class) != null || getOneObjectAtOffset(imagew/2 + 3,imageh/2 - 1, Ground.class) != null
        || getOneObjectAtOffset(imagew/2 + 3,imageh/-2, Enemy.class) != null || getOneObjectAtOffset(imagew/2 + 3,imageh/2 - 1, Enemy.class) != null)
        {
            wallright = false;
        } // Offset cube when hit a wall to avoid double jump

        return wallright;
    }
    
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
    
}
