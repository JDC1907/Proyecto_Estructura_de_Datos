/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenda;

import java.util.HashSet;
import tda.ArrayList;
import tda.CircularList;
import tda.DoublyLinkedList;
import tda.LinkedList;
import tda.List;

/**
 *
 * @author nhale
 */
public class Sistema {
    static private ArrayList<Usuario> usuarios = new ArrayList<>();

    public static CircularList<Contacto> contactos = new DoublyLinkedList();
    
    //public static List<Contacto> contactos = new LinkedList();
    public static HashSet<String> tags = new HashSet();
    
    public static void inicializarSistema(){
    usuarios.addLast(new Usuario("admin","12345678","Ädministrador","ädmin"));
//    usuarios.addLast(new Usuario("Laura preciado","lau567","Persona","person"));
    
        contactos.addLast(new Persona("Nombre1", "123456"){
            {addTag("Todo");
            }
        });
        contactos.addLast(new Persona("Nombre2", "087555"){
            {addTag("Todo");
            }
        });
        contactos.addLast(new Persona("Nombre3", "023456"){
            {addTag("Todo");
            }
        });
        contactos.addLast(new Persona("Nombre4", "123456"){
            {addTag("Todo");
            }
        });
        contactos.addLast(new Persona("Nombre5", "123456"){
            {addTag("Todo");
            }
        });
        contactos.addLast(new Persona("Nombre6", "123456"){
            {addTag("Todo");
            }
        });
        contactos.addLast(new Persona("Nombre7", "123456"){
            {addTag("Todo");
            }
        });
        contactos.addLast(new Persona("Nombre8", "123456"){
            {addTag("Todo");
            }
        });
        contactos.addLast(new Persona("Nombre9", "123456"){
            {addTag("Todo");
            }
        });
        contactos.addLast(new Persona("Nombre10", "123456"){
            {addTag("Todo");
            }
        });
        contactos.addLast(new Persona("Nombre11", "123456"){
            {addTag("Todo");
            }
        });
        Persona p = new Persona("Juan Carlos", "095555555");
        p.putAtributte("Usuario twitter", "@Twitter");
        p.putAtributte("Usuario fb", "@Facebook");
        p.putAtributte("Cumpleaños", "12/15/15");
        p.addPhoto("/imgpersonas/persona7.jpg");
        p.addTag("Familia");
        p.addTag("Amigos");
        p.addTag("Todo");
        contactos.addLast(p);
        
        Persona p2 = new Persona("Jean Carlos", "095555555");
        p2.putAtributte("Usuario twitter", "@Twitter");
        p2.putAtributte("Usuario fb", "@Facebook");
        p2.putAtributte("Cumpleaños", "12/15/15");
        p2.addPhoto("/imgpersonas/persona5.jpg");
        p2.addPhoto("/imgpersonas/persona6.jpg");
        p2.addTag("Familia");
        p2.addTag("Amigos");
        p2.addTag("Trabajo");
        p2.addTag("Escuella");
        p2.addTag("Todo");
        contactos.addLast(p2);
        
        for(Contacto contacto: contactos){
            tags.addAll(contacto.getTags());
        }
        
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
