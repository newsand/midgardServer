package Valhalla.MidgardWebserver.api;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Valhalla.MidgardWebserver.database.DatabaseConnector;
import Valhalla.MidgardWebserver.model.TemperatureRegister;

@Path("temperature")
public class TemperatureController {

    public DatabaseConnector m_mongoConnector = new DatabaseConnector("heroku_0lpfwdhp", "TemperatureRegistry");

    /**
     * GET methods    
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "GOOD SHIAAT!!";
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public long Count() {
        return m_mongoConnector.Count();
    }

    @GET
    @Path("/getLast")
    @Produces(MediaType.APPLICATION_JSON)
    public TemperatureRegister getLast() {
        TemperatureRegister a = m_mongoConnector.GetLast();
        System.out.println(a.getTemperature() + "" + a.getRegisterTime() + " " + a.getHardwareId() + " " + a.getSensorId());
        return a;
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TemperatureRegister> getAll() {
    	List<TemperatureRegister> v_result = m_mongoConnector.GetAll();
        return v_result;
    }

    /**
     * POST methods    
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String addTest() {

        System.out.println("inserindo ai");
        m_mongoConnector.InsertFirst();
        return "{\"aaa veiPost works!!\":\"olar\"}";
    }

    @POST
    @Path("/insert")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TemperatureRegister addOne(TemperatureRegister p_register) {
        System.out.println("to add");
        System.out.println(p_register.toString());
        TemperatureRegister v_register = m_mongoConnector.InsertOne(p_register);
        return v_register;
    }
}
