package PixelEngine.Game;

import java.util.ArrayList;
public class Level
{
    public ArrayList<Entity> entities = new ArrayList<Entity>();
    public ArrayList<Team> teams = new ArrayList<Team>();
    
    public ArrayList<Entity> pendingMobSpawns = new ArrayList<Entity>();
    
    public ArrayList<Entity> pendingMobDespawns = new ArrayList<Entity>();
    
    public double xBound = 1000;
    public double yBound = 1000;
    
    public int entityTickingTime = 0;
    public int teamTickingTime = 0;
    public int levelTickingTime = 0;
    
    public boolean has(Entity e) {
        for(int i=0; i<entities.size(); i++) {
            if( entities.get(i).equals(e) ) return true;
        }
        
        for(int i=0; i<pendingMobSpawns.size(); i++) {
            if( pendingMobSpawns.get(i).equals(e) ) return true;
        }
        
        return false;
    }

    public void remove(Entity e) {
        pendingMobDespawns.add(e);
    }

    public void tick() {
        long start, end;
        
        long start2 = System.nanoTime();
       
        while(pendingMobSpawns.size()>0) {
            entities.add( pendingMobSpawns.remove(0) );
        }
        
        start = System.nanoTime();
        for(int i=0; i<teams.size(); i++) {
            teams.get(i).tick();
        }
        end = System.nanoTime();
        teamTickingTime = (int) (end-start);
        
        start = System.nanoTime();
        for(int i=0; i<entities.size(); i++) {
            Entity e = entities.get(i);
            
            if(e.x<0) e.x=0;
            if(e.y<0) e.y=0;
            if(e.x>xBound) e.x=xBound;
            if(e.y>yBound) e.y=yBound;
            
            e.tick();
            
            if(e.x<0) e.x=0;
            if(e.y<0) e.y=0;
            if(e.x>xBound) e.x=xBound;
            if(e.y>yBound) e.y=yBound;

        }
        end = System.nanoTime();
        entityTickingTime = (int) (end-start);
        
        while(pendingMobDespawns.size()>0) {
            entities.remove( pendingMobDespawns.remove(0) );
        }
        
        long end2 = System.nanoTime();
        levelTickingTime = (int) (end2-start2);
    }

    public void add(Entity e) {

        pendingMobSpawns.add(e);
        e.setLevel(this);
    }

    public void addTeam(Team t) {
        t.setLevel(this);
        teams.add(t);
        t.setup();
    }

    public ArrayList<Mob> getInRange( double x, double y, double r) {
        ArrayList<Mob> list = new ArrayList<Mob>();

        for(int i=0; i<entities.size(); i++) {
            Entity e = entities.get(i);
            
            if(!(e instanceof Mob)) continue;
            
            if( getDistance( x, y, e.x, e.y) <= r + (double)e.size) {
                list.add((Mob)e);
            }

        }

        return list;
    }

    public static double getDistance(double x1, double y1, double x2, double y2) {
        double a = Math.abs( x1 - x2 );
        double b = Math.abs( y1 - y2 );
        a*=a;
        b*=b;
        return Math.sqrt( a + b );
    }
}