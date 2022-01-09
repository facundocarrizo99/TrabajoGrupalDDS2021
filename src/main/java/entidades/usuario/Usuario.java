package entidades.usuario;

import entidades.db.EntidadPersistente;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")

public class Usuario extends EntidadPersistente {

    @Column
    private String usuario;

    @Column
    private String contrasena;

    @Column
    private Boolean bloqueado;

    @Column
    private Integer intentosInicioSesionFallidos;

    @Column
    private Boolean status;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime lastLogin;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime lastLogout;

    public Usuario(){}

    public Usuario (String usuario, String contrasena) {
        this.status = true;
        this.bloqueado = false;
        this.lastLogin = null;
        this.intentosInicioSesionFallidos = 0;
        this.usuario = Objects.requireNonNull(usuario, "El nombre no puede ser nulo");
        this.contrasena = Objects.requireNonNull(contrasena, "La contraseña no puede ser nula");
    }

    public Boolean getBloqueado() { return bloqueado; }

    public void setBloqueado(Boolean bloqueado) { this.bloqueado = bloqueado; }

    public Integer getIntentosInicioSesionFallidos() { return intentosInicioSesionFallidos; }

    public void setIntentosInicioSesionFallidos(Integer intentosInicioSesionFallidos) { this.intentosInicioSesionFallidos = intentosInicioSesionFallidos; }

    public Boolean getStatus() { return status; }

    public void setStatus(Boolean status) { this.status = status; }

    public LocalDateTime getLastLogin() { return lastLogin; }

    public void setLastLogin(LocalDateTime lastLogin) { this.lastLogin = lastLogin; }

    public void iniciarSesion() {
        this.lastLogin = java.time.LocalDateTime.now();
    }

    public void cerrarSesion() {
        this.lastLogout = java.time.LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "usuario='" + usuario + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", bloqueado=" + bloqueado +
                ", intentosInicioSesionFallidos=" + intentosInicioSesionFallidos +
                ", status=" + status +
                ", lastLogin=" + lastLogin +
                '}';
    }

    //////////////  Getters y setters   ////////////
    public String getUsuario() { return this.usuario; }

    public LocalDateTime getLastLogout() {
        return lastLogout;
    }

    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getContrasena() { return contrasena; }

    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

}