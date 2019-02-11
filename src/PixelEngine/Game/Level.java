/*
*
*   Where things take place
*   For singleplayer games, this class is very simple to used
*   For multiplayer games, this class gets a little bit more complicated
*
*/

package PixelEngine.Game;

import java.util.ArrayList;

import PixelEngine.Util.*;
import PixelEngine.Network.*;
import PixelEngine.Logging.*;

public class Level
{

    //May be more useful, some day... 2/1/19
    public enum State {
        NORMAL, //Normal, everything is good...
        FROZEN, //Level is frozen, will not tick, even if told to
        DEBUG, //Used to check things out, experiment, learn...
        ;
    }

    //Set to true if this level is being used for a multiplayer (online) game.
    public static boolean MODE_NET = false;

    public static Logger toLog = new Logger("LEVEL","LEVEL");

    //Lists of things that exist in the level
    public ArrayList<Entity> entities = new ArrayList<Entity>();
    public ArrayList<Team> teams = new ArrayList<Team>();
    public ArrayList<Tile> tiles = new ArrayList<Tile>();
    public ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

    //Things that we want to add or remove
    public ArrayList<Entity> pendingSpawns = new ArrayList<Entity>();
    public ArrayList<Entity> pendingDespawns = new ArrayList<Entity>();

    //Only used for multiplayer (online) games
    //Used to keep track of what has been added and removed,
    //which can be sent to connected clients to keep them up to date
    public ArrayList<Entity> additions = new ArrayList<Entity>();
    public ArrayList<Entity> removals = new ArrayList<Entity>();

    //Same as above, but for tiles
    public ArrayList<Tile> tileAdditions = new ArrayList<Tile>();
    public ArrayList<Tile> tileRemovals = new ArrayList<Tile>();

    //The bounds
    public double xBound = 1000;
    public double yBound = 1000;

    //Keeping track of how long things take to tick/simulate
    public int entityTickingTime = 0;
    public int teamTickingTime = 0;
    public int levelTickingTime = 0;

    //May be useful for multiplayer (online) games...
    private static int idPoint = 0;
    private int id = idPoint++;

    private String name = "LEVEL";

    State state;

    //Can be set to receive output from the level
    public static Outputter o;

    public Level() {
        out("Creating new level..." + (MODE_NET? "Net mode enabled!" : "") );

        setState(State.NORMAL);

        if(idPoint > 1000000000) idPoint = -1000000000;
    }

    public void clear() {
        while(entities.size() > 0) entities.remove(0);
        while(pendingSpawns.size() > 0) pendingSpawns.remove(0);
        while(pendingDespawns.size() > 0) pendingDespawns.remove(0);

        while(projectiles.size() > 0) projectiles.remove(0);
        while(teams.size() > 0) teams.remove(0);
        while(tiles.size() > 0) tiles.remove(0);
    }

    public void setName(String s) {
        name = s;
        out("Name set to " + s);
    }

    public void setState(State s) {
        state = s;

        out("Changing state: " + s.ordinal() + " " + s.name());
    }

    public ArrayList<Entity> getEntities() { return entities; }
    public ArrayList<Team> getTeams() { return teams; }

    public ArrayList<Player> getPlayers() {
        ArrayList<Player> toRet = new ArrayList<Player>();

        for(Entity e : entities) {
            if(e instanceof Player) toRet.add( (Player) e );
        }

        return toRet;
    }

    public void receiveUpdates(ArrayList<Message> receive) {
        for(Message m : receive) {

            if(m.getType() == GameNetMessage.TILE_PLACE.getId()) {
                Tile t = new Tile(m.getId());

                t.x = m.readShort();
                t.y = m.readShort();

                place(t);
            }
            else {
                for(Entity e : entities) {
                    if(m.getId() == e.id) e.passUpdate(m);
                }
            }

        }
    }

    //Useful for multiplayer games to help new clients get into things
    public ArrayList<Message> getSetupData() {
        ArrayList<Message> toRet = new ArrayList<Message>();

        for(Entity e : entities) {
            toRet.add(e.getSpawnMessage());
            e.forceUpdate();
        }

        for(Tile t : tiles) {
            toRet.add(t.getPlaceMessage());
        }

        return toRet;
    }

    //Useful for multiplayer games to easily grab ALL updates and send them
    //to all clients.
    public ArrayList<Message> getUpdates() {
        ArrayList<Message> toRet = new ArrayList<Message>();

        for(Entity e : additions) {
            toRet.add(e.getSpawnMessage());
            additions.remove(e);
        }

        for(Entity e : removals) {
            toRet.add(e.getDespawnMessage());
            removals.remove(e);
        }

        for(Tile t : tileAdditions) {
            toRet.add(t.getPlaceMessage());
            tileAdditions.remove(t);
        }

        for(Tile t : tileRemovals) {
            toRet.add(t.getDestroyMessage());
            tileRemovals.remove(t);
        }

        for(Entity e : entities) {

            for(Message m : e.getUpdates()) {
                toRet.add(m);
            }

        }

        return toRet;
    }

    public void out(String s) {
        if(o != null) o.out("[LEVEL] " + id + " " + s);

        toLog.log(s);
    }

    public void debug(String s) {
        if(state == State.DEBUG) out("[DEBUG] " + s);
        else toLog.log("[DEBUG] " + id + " " + s);
    }

    public boolean has(Entity e) {
        for(int i=0; i<entities.size(); i++) {
            if( entities.get(i).equals(e) ) return true;
        }

        for(int i=0; i<pendingSpawns.size(); i++) {
            if( pendingSpawns.get(i).equals(e) ) return true;
        }

        return false;
    }

    public void remove(Entity e) {
        pendingDespawns.add(e);
    }

    public void place(Tile t) {
        tiles.add(t);
        if(MODE_NET) tileAdditions.add(t);
    }

    public void destroy(Tile t) {
        tiles.remove(t);
        if(MODE_NET) tileRemovals.add(t);
    }

    //Where all the things happen
    public void tick() {

        if(state == State.FROZEN) return; //If we are frozen, we refuse to simulate

        long start, end;

        long start2 = System.nanoTime();

        while(pendingSpawns.size()>0) {
            Entity toAdd = pendingSpawns.remove(0);

            if(toAdd instanceof Projectile) projectiles.add( (Projectile) toAdd);
            else entities.add( toAdd );

            if(MODE_NET) additions.add(toAdd);
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

            if(e.x < ( -1 * xBound ) ) e.x=-1 * xBound;
            if(e.y < ( -1 * yBound ) ) e.y=-1 * yBound;
            if(e.x > xBound) e.x=xBound;
            if(e.y > yBound) e.y=yBound;

            e.tick();

            if(e.x < ( -1 * xBound ) ) e.x=-1 * xBound;
            if(e.y < ( -1 * yBound ) ) e.y=-1 * yBound;
            if(e.x > xBound) e.x=xBound;
            if(e.y > yBound) e.y=yBound;

        }
        end = System.nanoTime();
        entityTickingTime = (int) (end-start);

        for(int i=0; i<projectiles.size(); i++) {
            Projectile p = projectiles.get(i);

            p.tick();

            if(p.x < ( -1 * xBound ) ) remove(p);
            if(p.y < ( -1 * yBound ) ) remove(p);
            if(p.x > xBound) remove(p);
            if(p.y > yBound) remove(p);
        }

        while(pendingDespawns.size()>0) {
            Entity toRem = pendingDespawns.remove(0);

            if(toRem instanceof Projectile) projectiles.remove(toRem);
            else entities.remove( toRem );

            if(MODE_NET) removals.add(toRem);
        }

        long end2 = System.nanoTime();
        levelTickingTime = (int) (end2-start2);
    }

    public void add(Projectile p) {
        //projectiles.add(p); //This is potentially problematic
        pendingSpawns.add(p);
        p.setLevel(this);
    }

    public void remove(Projectile p) {
        //projectiles.remove(p); //Always Problematic
        pendingDespawns.add(p);
    }

    public void add(Entity e) {
        pendingSpawns.add(e);
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
