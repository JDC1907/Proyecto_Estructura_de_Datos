/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenda;

import tda.ArrayList;
import tda.LinkedList;
import tda.List;

/**
 *
 * @author nhale
 */
public class Sistema {
    static private ArrayList<Usuario> usuarios = new ArrayList<>();

    public static List<Contacto> contactos = new LinkedList();
    
    public static void inicializarSistema(){
    usuarios.addLast(new Usuario("admin","12345678","Ädministrador","ädmin"));
//    usuarios.addLast(new Usuario("Laura preciado","lau567","Persona","person"));
    
        contactos.addLast(new Persona("Nombre1", "123456"));
        contactos.addLast(new Persona("Nombre2", "087555"));
        contactos.addLast(new Persona("Nombre3", "023456"));
        contactos.addLast(new Persona("Nombre4", "123456"));
        contactos.addLast(new Persona("Nombre5", "123456"));
        contactos.addLast(new Persona("Nombre6", "123456"));
        contactos.addLast(new Persona("Nombre7", "123456"));
        contactos.addLast(new Persona("Nombre8", "123456"));
        contactos.addLast(new Persona("Nombre9", "123456"));
        contactos.addLast(new Persona("Nombre10", "123456"));
        contactos.addLast(new Persona("Nombre11", "123456"));
        Persona p = new Persona("Juan Carlos", "095555555");
        p.setAtributte("Usuario twitter", "@Twitter");
        p.setAtributte("Usuario fb", "@Facebook");
        p.setAtributte("Cumpleaños", "12/15/15");
        p.addPhoto("/imgpersonas/persona7.jpg");
        contactos.addLast(p);
    }

//    public static ArrayList<Usuario> getUsuarios() {
//        return usuarios;
//    }
//
//    public static void setUsuarios(ArrayList<Usuario> usuarios) {
//        Sistema.usuarios = usuarios;
//    }
//
//    public static List<Contacto> getContactos() {
//        return contactos;
//    }
//
//    public static void setContactos(List<Contacto> contactos) {
//        Sistema.contactos = contactos;
//    }

  
    
}
