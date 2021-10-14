package edu.eci.cvds.test;

//import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;

import com.google.inject.Inject;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class ServiciosAlquilerTest {

    @Inject
    private final ServiciosAlquiler serviciosAlquiler;

    public ServiciosAlquilerTest() {
        serviciosAlquiler = ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
       
    }

    @Before
    public void setUp() {
    }
        /** Cliente no registrado **/
        @Test
        public void registrarAlquilerCliente(){
            Item i1=new Item(null, 4, "Los 4 Fantasticos", "Los 4 Fantásticos  es una película de superhéroes  basada en la serie de cómic homónima de Marvel.", java.sql.Date.valueOf("2005-06-08"), 2000, "DVD", "Ciencia Ficcion");
    
            try{
                serviciosAlquiler.registrarAlquilerCliente(java.sql.Date.valueOf("2007-09-08"),0,i1,5);
                fail();
            }catch(ExcepcionServiciosAlquiler e){
                Assert.assertTrue(true);
            }
        }

 

        /** Cliente no registrado **/
        @Test
        public void noDeberiaConsultarItemsSincliente(){
    
            try{
                serviciosAlquiler.consultarItemsCliente(0);
                fail();
            }catch(ExcepcionServiciosAlquiler e){
                Assert.assertTrue(true);
            }
        }
    /** 
     Cliente a vetar no existe
      **/
    @Test
    public void deberiaNoVetarClienteInexistente(){
        try{
            serviciosAlquiler.vetarCliente(0, true);
            fail();
        }catch(ExcepcionServiciosAlquiler e){
            Assert.assertTrue(true);
        }
    }
               /** Cliente ya existe
     *  **/
    @Test
    public void deberiaNoRegistrarClienteExistente(){
        Cliente c1=new Cliente("Oscar Alba", 1026585664, "6788952", "KRA 109#34-C30", "oscar@hotmail.com", false,null);
        try{
            serviciosAlquiler.registrarCliente(c1);
            fail();
        }catch(ExcepcionServiciosAlquiler e){
            Assert.assertTrue(true);
        }
    }

        /** item no disponible **/
        @Test
        public void NoDebeConsultarCostoDeItemNoDisponible(){
            try{
                serviciosAlquiler.consultarCostoAlquiler(1,5);
                fail();
            }catch(ExcepcionServiciosAlquiler e){
                Assert.assertTrue(true);
            }
        }

    /** 
     * Item no existe
     */
    @Test
    public void deberiaNoActualizarItemInexistente(){
        try{
            serviciosAlquiler.actualizarTarifaItem(0, 0);
            fail();
        }catch(ExcepcionServiciosAlquiler e){
            Assert.assertTrue(true);
        }
    }
    /** Tipo item no existe **/
    @Test
    public void deberiaNoConsultartipoItemInexistente(){
 
        try{
            serviciosAlquiler.consultarTipoItem(0);
            fail();
        }catch(ExcepcionServiciosAlquiler e){
            Assert.assertTrue(true);
        }
    }
    @Test
    /** Item no rentado **/
    public void noDebeTenerMultaUnItemNoAlquilado(){
   
        try{
            serviciosAlquiler.consultarMultaAlquiler(4,java.sql.Date.valueOf("2005-06-08"));
            fail();
        }catch(ExcepcionServiciosAlquiler e){
            Assert.assertTrue(true);
        }
    }
        /** Item ya registrado **/
        @Test
        public void deberiaNoRegistrarItemMasDeUnaVez(){
            Item i1=new Item(null, 4, "Los 4 Fantasticos", "Los 4 Fantásticos  es una película de superhéroes  basada en la serie de cómic homónima de Marvel.", java.sql.Date.valueOf("2005-06-08"), 2000, "DVD", "Ciencia Ficcion");
            try{
                serviciosAlquiler.registrarItem(i1);
                fail();
            }catch(ExcepcionServiciosAlquiler e){
                Assert.assertTrue(true);
            }
        }


    /** Item no registrado **/
    @Test
    public void deberiaNoConsultarItemInexistente(){
         try{
            serviciosAlquiler.consultarItem(0);
            fail();
        }catch(ExcepcionServiciosAlquiler e){
            Assert.assertTrue(true);
        }
    }
          
}