package net.darkhax.bookshelf.lib;

public enum PhysicalSide {
    
    CLIENT,
    
    SERVER;
    
    boolean isClient() {
        
        return this == CLIENT;
    }
    
    boolean isServer() {
        
        return this == SERVER;
    }
}