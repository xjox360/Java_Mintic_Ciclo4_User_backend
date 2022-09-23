package seguridad.backend.Ciclo4Grupo2.MainSecurity.modelos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document

public class Usuario {
    @Id
    private String _id;
    private String pseudonimo;
    private String correo;
    private String contrasena;
    @DBRef
    private Rol rol;
    public Usuario(String pseudonimo, String correo, String contrasena){
        this.pseudonimo = pseudonimo;
        this.correo = correo;
        this.contrasena = contrasena;

    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {

        return this.contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getPseudonimo() {
        return pseudonimo;
    }

    public void setPseudonimo(String pseudonimo) {
        this.pseudonimo = pseudonimo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
