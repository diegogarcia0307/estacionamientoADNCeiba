package com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.manejador;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JsonRespuestaCrear {

	String placa;
	String tipoVehiculo;
	boolean estadoOperacion;
}
