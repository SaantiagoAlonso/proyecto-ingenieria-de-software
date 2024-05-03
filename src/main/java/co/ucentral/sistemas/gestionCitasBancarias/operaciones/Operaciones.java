package co.ucentral.sistemas.gestionCitasBancarias.operaciones;

import co.ucentral.sistemas.gestionCitasBancarias.dto.ClienteDto;

public interface Operaciones {
    int inicioSesion(ClienteDto cliente);
    public int ingresarCliente(ClienteDto cliente);
}
