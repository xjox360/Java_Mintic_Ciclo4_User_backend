package seguridad.backend.Ciclo4Grupo2.MainSecurity.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import seguridad.backend.Ciclo4Grupo2.MainSecurity.modelos.Permiso;
import seguridad.backend.Ciclo4Grupo2.MainSecurity.modelos.PermisosRol;

public interface RepositorioPermiso extends MongoRepository<Permiso, String> {

    @Query("{'url':?0 , 'metodo':?1}")
    Permiso getPermiso(String url, String metodo);

}
