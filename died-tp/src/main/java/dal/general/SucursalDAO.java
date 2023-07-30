package dal.general;

import java.sql.SQLException;
import java.util.Map;

import datos.Producto;
import datos.Sucursal;

public interface SucursalDAO extends DAO<Sucursal> {
	public abstract void setStock(Sucursal suc) throws SQLException;
	public abstract Map<Producto,Integer> getStock(Sucursal suc) throws SQLException;
}