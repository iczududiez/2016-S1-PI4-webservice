import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.senac.pi4.services.Database;

@Path("/teste")
public class TesteSelects  {
	
	public ResultSet testeFunc() throws Exception{
		Connection conn = null;
		PreparedStatement psta = null;
		ResultSet rs = null;
		try {
			conn = Database.get().conn();		
			psta = conn.prepareStatement("select * from Alternativa");
			//psta.setInt(1, codQuestao);
			
			rs = psta.executeQuery();
			
		}catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		} finally {
			if (psta != null)
				psta.close();
			if (conn != null)
				conn.close ();
		}
		
		return rs;
	}
	
	@GET
	@Path("/{t}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTeste() throws Exception{
		return Response.status(200).entity(testeFunc()).build();
	}
}
