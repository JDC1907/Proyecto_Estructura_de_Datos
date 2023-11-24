/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenda;

import tda.CircularList;
import tda.DoublyLinkedList;

/**
 *
 * @author nhale
 */
public class Usuario {
  protected String nombreUsr;
  protected String contrasena;
  protected String nombre;
  protected String tipo;//enum
  
  public Usuario(String nombreUsr, String contrasena, String nombre, String tipo){
    this.nombreUsr = nombreUsr;
    this.contrasena = contrasena;
    this.nombre = nombre;
    this.tipo = tipo;
  }

  public String getNombreUsr() {
    return nombreUsr;
  }

  public String getContrasena() {
        return contrasena;
  }

  public String getNombre() {
    return nombre;
  }

  public String getTipo() {
    return tipo;
  }
  
  @Override
  public String toString(){
      return nombreUsr;
  }
}
