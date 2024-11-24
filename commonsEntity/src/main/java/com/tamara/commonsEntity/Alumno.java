package com.tamara.commonsEntity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "alumno")
public class Alumno {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String apellido;
    private String email;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    public Alumno() {}

    public Alumno(Integer id, String nombre, String apellido, String email, Date createAt) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.createAt = createAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj){
            return true;
        }
        if(!(obj instanceof Alumno)){
            return false;
        }
        Alumno a = (Alumno) obj;

        return this.id != null && this.id.equals(a.getId());
    }

    @PrePersist
    private void prePersiste() {
        this.createAt = new Date();
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", createAt=" + createAt +
                '}';
    }

}
