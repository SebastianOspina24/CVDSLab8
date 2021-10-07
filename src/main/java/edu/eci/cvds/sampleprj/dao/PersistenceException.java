package edu.eci.cvds.sampleprj.dao;

public class PersistenceException extends Exception {
    public PersistenceException(String mensage,Exception e){
        super( mensage, e);
    }
}
