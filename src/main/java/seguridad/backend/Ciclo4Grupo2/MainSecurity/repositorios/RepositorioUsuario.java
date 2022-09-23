package seguridad.backend.Ciclo4Grupo2.MainSecurity.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import seguridad.backend.Ciclo4Grupo2.MainSecurity.modelos.Usuario;

public interface RepositorioUsuario extends MongoRepository<Usuario, String> {
    @Query("{'correo':?0}")
    public Usuario getUserByMail(String correo);
}
