SELECT v.* 
FROM alquiler as a , vehiculo as v 
WHERE a.fechaSalida IS null and a.idVehiculo = v.idVehiculo and v.placa like ?