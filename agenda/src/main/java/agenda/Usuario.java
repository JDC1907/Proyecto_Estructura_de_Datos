/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenda;

/**
 *
 * @author nhale
 */
public class Usuario {
    protected String nombreUsr;
  protected String contrasena;
  protected String nombre;
  protected String tipo;
  
  public Usuario(String nombreUsr, String contrasena, String nombre, String tipo){
    this.nombreUsr = nombreUsr;
    this.contrasena = contrasena;
    this.nombre = nombre;
    this.tipo = tipo;
  }

  public String getNombreUsr() {
    return nombreUsr;
  }

  public String getNombre() {
    return nombre;
  }

  public String getTipo() {
    return tipo;
  }
    
}
