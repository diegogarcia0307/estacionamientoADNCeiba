package com.estacionamientoceiba.estacionamientoceiba.infraestructura.comun.sql;

public class Consulta {
	public static final String QUERY_BUSCAR_VEHICULO = "SELECT * FROM vehiculo WHERE placa LIKE ?";
	public static final String QUERY_BUSCAR_ALQUILER = "SELECT a.* FROM alquiler as a, vehiculo as v WHERE a.idVehiculo = v.idVehiculo and v.placa like ?";
	public static final String QUERY_DISPONIBILIDAD_CARROS = "SELECT COUNT(a.idAlquiler) as conteo FROM alquiler as a, vehiculo as v WHERE a.fechaSalida IS null and a.idVehiculo = v.idVehiculo and v.tipo = 1";
	public static final String QUERY_DISPONIBILIDAD_MOTOS = "SELECT COUNT(a.idAlquiler) as conteo FROM alquiler as a, vehiculo as v WHERE a.fechaSalida IS null and a.idVehiculo = v.idVehiculo and v.tipo = 2";
	public static final String QUERY_PERMANENCIA_VEHICULO = "SELECT v.* FROM alquiler as a , vehiculo as v WHERE a.fechaSalida IS null and a.idVehiculo = v.idVehiculo and v.placa like ?";
	public static final String QUERY_SALIDA_VEHICULO = "UPDATE alquiler SET fechaSalida = :fechaSalida, pago = :pago WHERE idAlquiler = :idAlquiler";
	public static final String QUERY_LISTAR_ALQUILERES_EN_USO = "SELECT * FROM alquiler AS a WHERE a.fechaSalida IS NULL AND a.pago = 0";
	public static final String QUERY_LISTAR_VEHICULOS_EN_PARQUEADERO = "SELECT v.* FROM vehiculo as v, alquiler as a WHERE a.fechaSalida IS NULL AND a.pago = 0 AND a.idVehiculo = v.idVehiculo";
	public static final String QUERY_LISTAR_TODO_VEHICULO = "SELECT * FROM vehiculo";
	public static final String QUERY_LISTAR_TODO_ALQUILER = "SELECT * FROM alquiler";
}
