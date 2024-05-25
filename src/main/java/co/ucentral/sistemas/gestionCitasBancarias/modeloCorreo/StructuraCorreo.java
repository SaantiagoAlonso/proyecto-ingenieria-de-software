package co.ucentral.sistemas.gestionCitasBancarias.modeloCorreo;

import lombok.Data;

@Data
public class StructuraCorreo {

    private String asunto = "mensaje desde Spring";

    private String mensaje = "este es el cooreo enviado";
}
