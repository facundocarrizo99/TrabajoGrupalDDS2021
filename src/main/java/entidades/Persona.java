package entidades;

import entidades.caracteristicas.Caracteristica;
import entidades.db.EntidadPersistente;
import entidades.mascota.Mascota;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

//IMPORTANTE! Agregar un atributo con medio de notificacion preferido

@Entity
@Table(name = "persona")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "rescatista")
public class Persona extends EntidadPersistente {

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private LocalDate fechaDenacimiento;

    @Transient
    private String nroDeDocumento;

    @Transient
    private TipoDoc tipo;

    @Transient
    private ArrayList<Contacto> mediosDeComunicacion;

    @Column
    private ArrayList<Mascota> mascotas;          //Sacar esto

    @Transient
    private IHallazgo tipoDeAviso;

    @Transient
    private Notificable notificable;

    @Column
    private String telefono;

    @Column
    private String email;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn (name = "direccion_id")
    protected Direccion direccion;



    //////////////  Constructors  ////////////


    public Persona() {}

    public Persona(String nombre, String apellido, LocalDate fechaDenacimiento, String nroDeDocumento, TipoDoc tipo, List<Contacto> contactos) {
        this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
        this.apellido = Objects.requireNonNull(apellido, "El apellido no puede ser nulo");
        this.fechaDenacimiento = Objects.requireNonNull(fechaDenacimiento, "La fecha de nacimiento no puede ser nulo");
        this.nroDeDocumento = Objects.requireNonNull(nroDeDocumento, "El documento no puede ser nulo");
        this.tipo = Objects.requireNonNull(tipo, "El tipo de documento no puede ser nulo");
        this.mediosDeComunicacion = new ArrayList<Contacto>();
        this.mediosDeComunicacion.addAll(contactos);
        this.mascotas = new ArrayList<Mascota>();
    }

    public Persona(String nombre, String apellido, LocalDate fechaDenacimiento, String nroDeDocumento,TipoDoc tipo, Contacto contacto, Direccion direccion) {
        this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
        this.apellido = Objects.requireNonNull(apellido, "El apellido no puede ser nulo");
        this.direccion = Objects.requireNonNull(direccion, "la direccion no puede ser nula");
        this.fechaDenacimiento = Objects.requireNonNull(fechaDenacimiento, "La fecha de nacimiento no puede ser nulo");
        this.nroDeDocumento = Objects.requireNonNull(nroDeDocumento, "El documento no puede ser nulo");
        this.tipo = Objects.requireNonNull(tipo, "El tipo de documento no puede ser nulo");
        this.mediosDeComunicacion = new ArrayList<Contacto>();
        this.mediosDeComunicacion.add(contacto);
        //this.mascotas = new ArrayList<Mascota>();
    }


    //////////////  Getters y setters   ////////////

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaDenacimiento() {
        return fechaDenacimiento;
    }

    public String getNroDeDocumento() {
        return nroDeDocumento;
    }

    public void setNroDeDocumento(String nroDeDocumento) {
        this.nroDeDocumento = nroDeDocumento;
    }

    public TipoDoc getTipo() {
        return tipo;
    }

    public void setTipo(TipoDoc tipo) {
        this.tipo = tipo;
    }

    public List<Contacto> getMediosDeComunicacion() {
        return mediosDeComunicacion;
    }

    public void setMediosDeComunicacion(ArrayList<Contacto> mediosDeComunicacion) { this.mediosDeComunicacion = mediosDeComunicacion; }

    public void setFechaDenacimiento(LocalDate fechaDenacimiento) { this.fechaDenacimiento = fechaDenacimiento; }

    public Direccion getDireccion() { return direccion; }

    public void setDireccion(Direccion direccion) { this.direccion = direccion; }

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public IHallazgo getTipoDeAviso() {
        return tipoDeAviso;
    }

    public void setTipoDeAviso(IHallazgo tipoDeAviso) {
        this.tipoDeAviso = tipoDeAviso;
    }

    public Notificable getNotificable() {
        return notificable;
    }

    public void setNotificable(Notificable notificable) {
        this.notificable = notificable;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    ////////////////////


    public String notificar(String mensaje) {
        return notificable.notificar(mensaje, this);
    }

    public void addMascota(Mascota mascota){
        this.mascotas.add(mascota);
    }

    public void nuevoHallazgo(IHallazgo ihallazgo) {
       // ihallazgo.completarFormulario();
    }

    public String imprimirMediosDeCom(){
        String s = this.mediosDeComunicacion.stream().map(e -> e.toString()).reduce("", String::concat);
        return s;
    }

    public PublicacionBuscaAdopcion generarPublicacionBuscaAdopcion(List<Caracteristica> preferencias, List<Comodidad> comodidades){
        return new PublicacionBuscaAdopcion(preferencias,comodidades,this);
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaDenacimiento=" + fechaDenacimiento +
                ", nroDeDocumento='" + nroDeDocumento + '\'' +
                ", tipo=" + tipo +
                ", mascotas=" + mascotas +
                ", tipoDeAviso=" + tipoDeAviso +
                ", notificable=" + notificable +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", direccion=" + direccion +
                '}';
    }

    /*
    public void quererAdoptarMascota() {}

    public void adoptarMascota(mascota mascota) {}

    public void cambiarModoDeDarAvisoAMascotaEncontrada(IHallazgo tipoDeAviso) {}

    public void buscarHogarDeTransito(mascota mascota) {}

    public void encontrarDueno(mascota mascota) {}
    */


}
