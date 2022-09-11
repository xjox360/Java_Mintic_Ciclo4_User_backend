package seguridad.backend.Ciclo4Grupo2.MainSecurity.modelos;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Data
@Document
public class Permiso {
    @Id
    private String _id;
    private String url;
    private ArrayList metodo;

    public Permiso(String url, ArrayList metodo){
        this.url = url;
        this.metodo = metodo;
    }

    public  String get_id(){
        return _id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList getMetodo() {
        return metodo;
    }

    public void setMetodo(ArrayList metodo) {
        this.metodo = metodo;
    }
}
