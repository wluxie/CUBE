import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class bullet2 extends Left
{
    private int life = 40;
    
    public void act() 
    {
        move(10.0);
        life--;
        if(life ==0)
        {
            getWorld().removeObject(this);
        }else if (isOnSolidGround() || Collision() || HeadBumpFix())
        {
            getWorld().removeObject(this);
        }
    }
    
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
    
    public boolean Collision()
    {
        boolean flag = false;
        int imagew = getImage().getWidth();
        int imageh = getImage().getHeight();
        
        if(getOneObjectAtOffset(imagew/-2,imageh/2, Enemy.class) != null || getOneObjectAtOffset(imagew/2,imageh/2, Enemy.class) != null 
        || getOneObjectAtOffset(imagew/2,imageh/-2, Enemy.class) != null || getOneObjectAtOffset(imagew/-2,imageh/-2, Enemy.class) != null)
        {
            flag = true;
            removeTouching(Enemy.class);
        }
        return flag;
    }

    public boolean HeadBumpFix()
    {
        boolean head = false;
        
        int imagew = getImage().getWidth();
        int imageh = getImage().getHeight();
        if (getOneObjectAtOffset(imagew/-2,imageh/-2, Ground.class) != null || getOneObjectAtOffset(imagew/2,imageh/-2, Ground.class) != null)
        {
            head = true;
        }
        
        return head;
    }
}
