package seguridad;

import entidades.usuario.Usuario;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.Date;

public class Sesion {

    @Column
    private Usuario username;

    @Column
    private LocalDateTime created;

    @Column
    private LocalDateTime closed;

    public Sesion() {
    }

    public Usuario getUsername() {
        return username;
    }

    public void setUsername(Usuario username) {
        this.username = username;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getClosed() {
        return closed;
    }

    public void setClosed(LocalDateTime closed) {
        this.closed = closed;
    }
}
