package seguridad.backend.Ciclo4Grupo2.MainSecurity.modelos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Rol {
    @Id
    private String _id;
    private String nombre;

    private String description;

    public Rol(String nombre, String description){
        this.nombre = nombre;
        this.description = description;
    }
    public String get_id(){
        return _id;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
}