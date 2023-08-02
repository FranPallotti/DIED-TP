package logica;


import java.sql.SQLException;
import java.util.List;

import dal.general.FactoryDAO;
import dal.general.SucursalDAO;
import datos.Ruta;
import datos.Sucursal;
import datos.Operatividad;

public final class GestorRutas {
	/* falta gestionar responsabilidades para esta clase
	 * 
	 */

	private static GestorRutas gestor;
	
	public synchronized static GestorRutas getInstance() {
		if(gestor==null) {
			gestor=new GestorRutas();
		}
		return gestor;
	}
	
	private GestorRutas() {
		super();
	}
	
	public void altaRuta(Integer idSucursalOrigen, Integer idSucursalDestino ,Operatividad estado, Integer duracion, Float capacidad) throws SQLException, ClassNotFoundException{
		
		FactoryDAO fact= FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY);
		SucursalDAO auxDAOSucursal= fact.getSucursalDAO();
	/* Coloco un null en el constructor de ruta en la posicion del id, porque eso me lo va a 
	 * generar como serial la bdd 
	 */
		Ruta aux= new Ruta(auxDAOSucursal.getByID(idSucursalOrigen),auxDAOSucursal.getByID(idSucursalDestino),estado,duracion,capacidad);
		fact.getRutaDAO().insert(aux);
	}
	
	public void modificarRuta(Integer idRuta, Integer idSucursalOrigen, Integer idSucursalDestino ,Operatividad estado, Integer duracion, Float capacidad) throws ClassNotFoundException, SQLException {
		FactoryDAO fact= FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY);
		SucursalDAO auxDAOSucursal= fact.getSucursalDAO();
	/* Coloco un null en el constructor de ruta en la posicion del id, porque eso me lo va a 
	 * generar como serial la bdd 
	 */
		Ruta aux= new Ruta(idRuta,auxDAOSucursal.getByID(idSucursalOrigen),auxDAOSucursal.getByID(idSucursalDestino),estado,duracion,capacidad);
		fact.getRutaDAO().update(aux);
	}
	
	public List<Ruta> consultaPorAtributos(Integer idRuta, Sucursal origen, Sucursal destino,
			 Operatividad estado, Integer duracionDesde, Integer duracionHasta,
			 Float capacMaxDesde, Float capacMaxHasta) throws ClassNotFoundException, SQLException{
		
			FactoryDAO fact=FactoryDAO.getFactory(FactoryDAO.POSTGRE_FACTORY);
			
			return fact.getRutaDAO().searchByAttributes(idRuta, origen, destino, estado, duracionDesde, duracionHasta, capacMaxDesde, capacMaxHasta);
		
	}
	
	

}
