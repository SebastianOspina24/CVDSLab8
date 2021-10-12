package edu.eci.cvds.sampleprj.dao.mybatis;

import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.TipoItemMapper;
import edu.eci.cvds.samples.entities.TipoItem;

public class MyBATISTipoItemDAO implements TipoItemDAO{

    @Inject
    private TipoItemMapper tiMapper;   
    

    @Override
    public void save(TipoItem ti) throws PersistenceException {
        try{
            tiMapper.insertarTipoItem(ti);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al registrar el item "+ti.toString(),e);
        }        
      
    }


    @Override
    public TipoItem load(int id) throws PersistenceException {
        try{
            return tiMapper.getTipoItem(id);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el item "+id,e);
        }
      
    }


    @Override
    public List<TipoItem> consultarTipos() throws PersistenceException {
        try{
            return tiMapper.getTiposItems();
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el los tipos de item",e);
        }
    }
    
}
