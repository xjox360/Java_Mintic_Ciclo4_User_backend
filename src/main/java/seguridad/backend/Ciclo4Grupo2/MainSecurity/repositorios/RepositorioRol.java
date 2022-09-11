package seguridad.backend.Ciclo4Grupo2.MainSecurity.repositorios;

import seguridad.backend.Ciclo4Grupo2.MainSecurity.modelos.Rol;
import org.springframework.data.mongodb.repository.MongoRepository;
import seguridad.backend.Ciclo4Grupo2.MainSecurity.modelos.Rol;

public interface RepositorioRol extends MongoRepository<Rol, String> {
}
