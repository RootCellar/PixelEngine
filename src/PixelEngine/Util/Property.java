package PixelEngine.Util;

import PixelEngine.Logging.*;

public class Property
{
    
    private static Logger logger = new Logger("Property", "Log");
    
    String name;
    String value;
    
    public Property(String n, String v) {
        name = n;
        value = v;
    }
    
    public void setValue(String s) {
        value = s;
    }
    
    public void setName(String s) {
        name = s;
    }
    
    public String getName() {
        return name;
    }
    
    public String getValue() {
        return value;
    }
    
    public boolean toBool() {
        if( value.equals("true") ) return true;
        else return false;
    }
    
    public int toInt() {
        
        try{
            return Integer.parseInt(value);
        }catch(Exception e) {
            
        }
        return Integer.MAX_VALUE;
    }
}
