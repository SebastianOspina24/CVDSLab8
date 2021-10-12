package edu.eci.cvds.sampleprj.dao.mybatis;
import edu.eci.cvds.sampleprj.dao.ItemRentadoDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemRentadoMapper;
import edu.eci.cvds.samples.entities.ItemRentado;
import java.util.List;
import com.google.inject.Inject;

public class MyBATISItemRentadoDAO implements ItemRentadoDAO{
    @Inject
    private ItemRentadoMapper itemRentadoMapper;

    @Override
    public List<ItemRentado> consultarItemsCliente(long idCliente) throws PersistenceException {
        try{
            return itemRentadoMapper.consultarItemsCliente(idCliente);
        }catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar los items rentados del cliente "+idCliente,e);
        }
    }

    @Override
    public ItemRentado consultarItemRentado(int iditem) throws PersistenceException {
        try{
            return itemRentadoMapper.consultarItemRentado(iditem);
        }catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el item rentado "+iditem,e);
        }
    }


}
