package com.estacionamientoceiba.estacionamientoceiba.aplicacion.comando.respuestas;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaListarAlquiler {
	private String placa;
	private int tipo;
	private Date fechaIngreso;
	private Date fechaSalida;
	private String puesto;
	private double pago;

}
