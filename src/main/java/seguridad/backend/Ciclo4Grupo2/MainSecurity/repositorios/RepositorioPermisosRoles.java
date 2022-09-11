package seguridad.backend.Ciclo4Grupo2.MainSecurity.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import seguridad.backend.Ciclo4Grupo2.MainSecurity.modelos.PermisosRol;

public interface RepositorioPermisosRoles extends MongoRepository<PermisosRol, String> {
}
