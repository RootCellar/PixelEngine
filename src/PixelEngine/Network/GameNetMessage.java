/*
 *
 * This class contains some basic message types. Upon constructing a server/client,
 * these will be placed into the MessageTypes class.
 * These are just some general message types, if you don't want to use them then
 * you definitely don't have to.
 *
 * The advantage to using an enum and a registry of sorts to keep track of message types
 * is that the numbers (IDs for message types) do not have to be hard coded, meaning that things
 * can be changed either here or in the user's code and they won't have to manually update ID numbers
 * in their code. The engine keeps track of the numbers, so the user only has to know the "name".
 *
 * Note though that if you define your own message
 * type with the same name as one of these, it will not be placed into the list
 * because a type already exists with that name (From here).
 *
*/

package PixelEngine.Network;

public enum GameNetMessage
{
    //Net Handling
    PING_CHECK,

    //Strings
    NAME_SET,
    CHAT,

    //Player Handling
    SELF_SET,

    //Entities
    ENTITY_SPAWN,
    ENTITY_REMOVE,

    //Mobs
    MOB_SPAWN,
    MOB_REMOVE,
    MOB_RESIZE,
    MOB_POS,

    //Mob Stats
    MOB_HP,
    MOB_STAMINA,
    MOB_HUNGER,
    MOB_THIRST,
    MOB_INV_ADD,
    MOB_INV_DEL,

    //Items
    ITEM_SPAWN,
    ITEM_REMOVE,

    //Tiles
    TILE_PLACE,
    TILE_REMOVE,

    //Projectiles
    PROJ_SPAWN,
    PROJ_REMOVE,
    PROJ_MOVE,

    //Input Keys
    KEY_W,
    KEY_A,
    KEY_S,
    KEY_D,
    KEY_SPACE,

    //Mouse Input
    MOUSE_POS,
    ;

    String name;
    short id;

    GameNetMessage() {
        name = name();
        id = (short) ordinal();

        //System.out.println( "GAME NET: " + getId() + " " + getName() );
    }

    public String getName() { return name; }
    public int getId() { return id; }
}
