package seguridad.backend.Ciclo4Grupo2.MainSecurity.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import seguridad.backend.Ciclo4Grupo2.MainSecurity.modelos.Permiso;
import seguridad.backend.Ciclo4Grupo2.MainSecurity.modelos.PermisosRol;
import seguridad.backend.Ciclo4Grupo2.MainSecurity.modelos.Rol;
import seguridad.backend.Ciclo4Grupo2.MainSecurity.repositorios.RepositorioPermiso;
import seguridad.backend.Ciclo4Grupo2.MainSecurity.repositorios.RepositorioPermisosRoles;
import seguridad.backend.Ciclo4Grupo2.MainSecurity.repositorios.RepositorioRol;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/permisos-roles")
public class ControladorPermisosRoles {
    @Autowired
    private RepositorioPermisosRoles miRepositorioPermisosRoles;

    @Autowired
    private RepositorioPermiso miRepositorioPermiso;

    @Autowired
    private RepositorioRol miRepositorioRol;

    @GetMapping("")
    public List<PermisosRol> index(){
        return miRepositorioPermisosRoles.findAll();

    }
    /*

    Asignación del rol y permiso
    @param id_rol
    @param id_permiso
     */

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("rol/{id_rol}/permiso/{id_permiso}")
    public PermisosRol create(@PathVariable String id_rol, @PathVariable String id_permiso){
        PermisosRol nuevo = new PermisosRol();
        Rol elRol = miRepositorioRol.findById(id_rol).orElse(null);
        Permiso elPermiso =  miRepositorioPermiso.findById(id_permiso).orElse(null);
        if(elRol != null && elPermiso !=null){
            nuevo.setPermiso(elPermiso);
            nuevo.setRol(elRol);
            return miRepositorioPermisosRoles.save(nuevo);
        }else{
            return null;
        }
    }

    @GetMapping("{id}")
    public PermisosRol show(@PathVariable String id){
        PermisosRol permisoRolActual = miRepositorioPermisosRoles.findById(id).orElse(null);
        if(permisoRolActual != null){
            return permisoRolActual;
        }else{
            return null;
        }
    }

    @PutMapping("{id}/rol/{id_rol}/permiso/{id_permiso}")
    public PermisosRol update(@PathVariable String id,@PathVariable String id_rol, @PathVariable String id_permiso){
        PermisosRol permisosRolesActuales = miRepositorioPermisosRoles.findById(id).orElse(null);
        Rol elRol = miRepositorioRol.findById(id_rol).get();
        Permiso elPermiso = miRepositorioPermiso.findById(id_permiso).get();

        if(elRol != null && elPermiso != null){
            permisosRolesActuales.setRol(elRol);
            permisosRolesActuales.setPermiso(elPermiso);
            return miRepositorioPermisosRoles.save(permisosRolesActuales);
        }else{
            return null;
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}")
    public String delete(@PathVariable String id){
        PermisosRol permisoRolActual = miRepositorioPermisosRoles.findById(id).orElse(null);
        if(permisoRolActual != null){
            miRepositorioPermisosRoles.delete(permisoRolActual);
            return "Usuario Eliminado";
        }else{
            return "No fue posible eliminar el Usuario";
        }

    }

    @GetMapping("validar-permisos/rol/{id_rol}")
    public PermisosRol getPermisos(@PathVariable String id_rol,
                                   @RequestBody Permiso infoPermiso){
        Permiso actualPermiso = miRepositorioPermiso
                .getPermiso(infoPermiso.getUrl()
                        ,infoPermiso.getMetodo().get(0).toString());

        Rol elRol = miRepositorioRol.findById(id_rol).get();
        if(actualPermiso != null && elRol != null){
            return miRepositorioPermisosRoles.getPermisoRol(elRol.get_id(), actualPermiso.get_id());
        }else{
            return null;
        }
    }
}
