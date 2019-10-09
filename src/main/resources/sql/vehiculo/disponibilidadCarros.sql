SELECT COUNT(a.idAlquiler) as conteo 
FROM alquiler as a, vehiculo as v 
WHERE a.fechaSalida IS null and a.idVehiculo = v.idVehiculo and v.tipo = 1