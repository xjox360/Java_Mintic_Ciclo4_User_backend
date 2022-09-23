package seguridad.backend.Ciclo4Grupo2.MainSecurity.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import seguridad.backend.Ciclo4Grupo2.MainSecurity.modelos.Rol;
import seguridad.backend.Ciclo4Grupo2.MainSecurity.modelos.Usuario;
import seguridad.backend.Ciclo4Grupo2.MainSecurity.repositorios.RepositorioRol;
import seguridad.backend.Ciclo4Grupo2.MainSecurity.repositorios.RepositorioUsuario;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class ControladorUsuario {
    @Autowired
    private RepositorioUsuario miRepositorioUsuario;
    @Autowired
    private RepositorioRol miRepositorioRol;

    @GetMapping("")
    public List<Usuario> index(){
        return this.miRepositorioUsuario.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Usuario create(@RequestBody Usuario infoUsuario){
        infoUsuario.setContrasena(convertirSHA256(infoUsuario.getContrasena()));
        return this.miRepositorioUsuario.save(infoUsuario);
    }

    @GetMapping("{id}")
    public Usuario show(@PathVariable String id){
        Usuario usuarioActual = miRepositorioUsuario.findById(id).orElse(null);
        return usuarioActual;
    }

    @PutMapping("{id}")
    public  Usuario update(@PathVariable String id, @RequestBody Usuario infoUsuario){
        Usuario usuarioActual = miRepositorioUsuario.findById(id).orElse(null);
        if(usuarioActual != null){
            usuarioActual.setPseudonimo(infoUsuario.getPseudonimo());
            usuarioActual.setCorreo(infoUsuario.getCorreo());
            usuarioActual.setContrasena(convertirSHA256(infoUsuario.getContrasena()));
            return miRepositorioUsuario.save(usuarioActual);
        }else{
            return null;
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}")
    public String
    delete(@PathVariable String id){
        Usuario usuarioActual = miRepositorioUsuario.findById(id).orElse(null);
        if(usuarioActual != null){
            miRepositorioUsuario.delete(usuarioActual);
            return "Usuario Eliminado";
        }else{
            return "No fue posible eliminar el Usuario";
        }
    }

    /*

    La Relación de unos a muchos (1 a n)
     */

    @PutMapping("{id}/rol/{id_rol}")
    public Usuario asignaRolUsuario(@PathVariable String id, @PathVariable String id_rol){
        Usuario usuarioActual = miRepositorioUsuario.findById(id).orElse(null);
        Rol rolActual = miRepositorioRol.findById(id_rol).orElse(null);
        if(usuarioActual != null && rolActual != null){
            usuarioActual.setRol(rolActual);
            return miRepositorioUsuario.save(usuarioActual);
        }else{
            return null;
        }
    }
    public String convertirSHA256(String password){
        MessageDigest m1 = null;
        try{
            m1 = MessageDigest.getInstance("SHA-256");
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
        byte[] hash = m1.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for(byte b : hash){
            sb.append(String.format("%02x",b));

        }
        return sb.toString();
    }

    @PostMapping("/validar")
    public Usuario validate(@RequestBody Usuario infoUsuario, final HttpServletResponse response) throws IOException {
        Usuario usuarioActual = miRepositorioUsuario.getUserByMail((infoUsuario.getCorreo()));

        if(usuarioActual != null && usuarioActual.getContrasena()
                .equals(convertirSHA256(infoUsuario.getContrasena()))){
            usuarioActual.setContrasena("");
            return usuarioActual;
        }else{

            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
    }


}

