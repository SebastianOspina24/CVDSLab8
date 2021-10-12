package edu.eci.cvds.sampleprj.dao.mybatis;


import java.util.Date;
import java.util.List;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Cliente;

public class MyBATISClienteDAO implements ClienteDAO{

    @Inject
    private ClienteMapper cliente;   

        public void save(Cliente cl) throws PersistenceException{
            try{
                cliente.insertarCliente(cl);
            }
            catch(org.apache.ibatis.exceptions.PersistenceException e){
                throw new PersistenceException("Error al registrar el cliente",e);
            } 
        }

        @Override
        public void agregarItemRentadoACliente(long id,int idit, Date fechainicio, Date fechafin) throws PersistenceException {
            try{
                cliente.agregarItemRentadoACliente(id,idit,fechainicio,fechafin);
            }
            catch(org.apache.ibatis.exceptions.PersistenceException e){
                throw new PersistenceException("Error al agregar item a "+id,e);
            }        
          
        }

        @Override
        public Cliente load(int documento) throws PersistenceException {
            try{
                return cliente.consultarCliente(documento);
            }
            catch(org.apache.ibatis.exceptions.PersistenceException e){
                throw new PersistenceException("Error al consultar el item "+documento,e);
            }
          
          
        }
        
        @Override
        public List<Cliente> consultarClientes() throws PersistenceException{
            try {
                return cliente.consultarClientes();
            } catch(org.apache.ibatis.exceptions.PersistenceException e){
                throw new PersistenceException("Error al consultar los clientes",e);
            }
        }

}
