package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Cliente;


public interface ClienteMapper {
    
    public Cliente consultarCliente(@Param("idcli") int id); 
    

    public void agregarItemRentadoACliente(@Param("id") int id, 
            @Param("idit") int idit, 
            @Param("fechaInicio") Date fechainicio,
            @Param("fechaFin") Date fechafin);


    public List<Cliente> consultarClientes();
    
        
    public void insertarCliente(@Param("cliente") Cliente cl);


}
