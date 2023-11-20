/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenda;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringJoiner;
import tda.ArrayList;
import tda.CircularList;
import tda.DoublyLinkedList;
import tda.List;

/**
 *
 * @author nhale
 */
public class Sistema {
    static private String usr;
    static private List<Usuario> usuarios = new ArrayList<>();
    
    public static Usuario usuario;
    public static CircularList<Contacto> contactos = new DoublyLinkedList();
    
    //public static List<Contacto> contactos = new LinkedList();
public static int comprobarUsuario(String usuario, String contrasena){
        int indice = 0;
        for (int i = 0; i<usuarios.size(); i++) {
            if (usuarios.get(i).nombreUsr.equals(usuario) && usuarios.get(i).contrasena.equals(contrasena)) {
                //System.out.println("Bienvenido " + usuarios.get(i).getNombreUsr() + " usted ha ingresado como: " + usuarios.get(i).tipo);
                usr = usuarios.get(i).getNombreUsr();
                indice = i;
                switch (usuarios.get(indice).tipo) {
                    case "admin":
                        return 1;
                    case "persona":
                        return 2;
                    case "empresa":
                        return 3;
                }
            }
        }
        return 0;
}
    public static void inicializarSistema(){
    usuarios.addLast(new Usuario("admin1","123","Ädministrador","admin"));
//    usuarios.addLast(new Usuario("Laura preciado","lau567","Persona","person"));
    iniciarSesion("admin1","123");
    //cargarContactos(usuario);
    //contactos = new DoublyLinkedList();
    cargarContactos();
    //guardarContactos(usuario);
    }
    
    public static void iniciarSesion(String nombreUsuario, String password){
       String nombre = "Nombre";
       String tipo = "tipo";
       usuario = new Usuario(nombreUsuario, password, nombre, tipo);
       
       //cargarContactos();
    }
    
    public static void cerrarSesion(String nombreUsuario){
        usuario = null;
        contactos = null;
        
    }
    public static void cargarContactos(Usuario usuario){
        contactos.addLast(new Persona("Vicente Gómez", "+593 802-24-3198"){
            {addTag("Amigos");
            addPhoto("/imgpersonas/persona8.jpg");
            }
        });
        contactos.addLast(new Persona("Hugo Muñoz", "+593 005-84-6765"){
            {addTag("Familia");
            addPhoto("/imgpersonas/persona9.jpg");
            }
        });
        contactos.addLast(new Persona("Adrián Suárez", "+593 961-40-2387"){
            {//addTag("Tag3");
                addPhoto("/imgpersonas/persona10.jpg");
            }
        });
        contactos.addLast(new Persona("Alaja Filo", "+593 727-39-3931"){
            {//addTag("Tag4");
                addPhoto("/imgpersonas/persona11.jpg");
            }
        });
        contactos.addLast(new Persona("Clara Álvarez", "+593 567-84-4712"){
            {//addTag("Tag5");
                addPhoto("/imgpersonas/persona12.jpg");
            }
        });
        contactos.addLast(new Persona("Cris Gómez", "+593 557-95-1014"){
            {//addTag("Tag6");
                addPhoto("/imgpersonas/persona13.jpg");
            }
        });
        contactos.addLast(new Persona("Mario Ruiz", "+593 969-34-1666"){
            {addTag("Amigos");
            }
        });
        contactos.addLast(new Persona("Santiago Gutiérrez", "+593 783-89-5317"){
            {addTag("Amigos");
            }
        });
        contactos.addLast(new Persona("Ricardo Ruiz", "+593 996-41-2072"){
            {addTag("Trabajo");
            }
        });
        contactos.addLast(new Persona("Daniel Álvarez", "+593 069-01-2194"){
            {addTag("Trabajo");
            }
        });
        contactos.addLast(new Persona("Manuel Martínez", "+593 293-40-4674"){
            {addTag("Escuela");
            }
        });
        
        contactos.addLast(new Empresa("NVidea", "+593 412-56-4455"){
            {addTag("Empresa");
            addTag("Todo");
            }
        });
        
        contactos.addLast(new Empresa("Dell", "+593 777-54-3265"){
            {addTag("Empresa");
            addTag("Todo");
            }
        });
        
        contactos.addLast(new Empresa("FaceBook", "+593 455-74-6837"){
            {addTag("Empresa");
            addTag("Todo");
            }
        });
        
        Empresa empre = new Empresa("CocaCola","+593 451-47-4521");
        empre.putAtributte("Usuario twitter", "@CocaSA");
        empre.putAtributte("Usuario fb", "CocaCola SA");
        empre.putAtributte("Fecha", "05/01/98");
        empre.putAtributte("Direccion", "Avda. Alameda Sundheim 59");
        empre.addPhoto("/imgpersonas/Cocacola.jpg");
        empre.addTag("Empresa");
        empre.addTag("Todo");
        contactos.addLast(empre);
        
        Empresa empre1 = new Empresa("Ferrari","+593 787-45-1445");
        empre1.putAtributte("Usuario twitter", "@Ferrari");
        empre1.putAtributte("Usuario fb", "FerrariSA");
        empre1.putAtributte("Fecha", "15/07/75");
        empre1.putAtributte("Direccion", "Rosa de los Vientos 9");
        empre1.addPhoto("/imgpersonas/Ferrari-badge.jpg");
        empre1.addTag("Empresa");
        empre1.addTag("Todo");
        contactos.addLast(empre1);
        
        Empresa empre2 = new Empresa("Samsung","+593 652-78-8541");
        empre2.putAtributte("Usuario twitter", "@Samsung");
        empre2.putAtributte("Usuario fb", "SamsungSa");
        empre2.putAtributte("Fecha", "30/12/45");
        empre2.putAtributte("Direccion", "Plaza de España 83");
        empre2.addPhoto("/imgpersonas/Samsung.jpg");
        empre2.addTag("Empresa");
        empre2.addTag("Todo");
        contactos.addLast(empre2);
        
        Empresa empre3 = new Empresa("iphone","+593 744-85-7456");
        empre3.putAtributte("Usuario twitter", "@iphone");
        empre3.putAtributte("Usuario fb", "iphoneSa");
        empre3.putAtributte("Fecha", "04/12/45");
        empre3.putAtributte("Direccion", "Herrería 94");
        empre3.addPhoto("/imgpersonas/iPhone-logo.png");
        empre3.addTag("Empresa");
        empre3.addTag("Todo");
        contactos.addLast(empre3);
        
        
        
        Persona p = new Persona("Juan Carlos", "+593 605-07-8066");
        
        p.putAtributte("Apellido", "Carrazco Figo");
        p.putAtributte("Usuario twitter", "@Twitter");
        p.putAtributte("Usuario fb", "@Facebook");
        p.putAtributte("Cumpleaños", "05/01/98");
        p.addPhoto("/imgpersonas/persona7.jpg");
        p.addTag("Familia");
        p.addTag("Amigos");
        p.addTag("Todo");
        contactos.addLast(p);
        //crear otro contacto
        Persona p2 = new Persona("Alex Felix", "+593 772-74-8902");
        p2.putAtributte("Apellido", "Sirelio Maximos");
        p2.putAtributte("Usuario twitter", "@Twitter");
        p2.putAtributte("Usuario fb", "@Facebook");
        p2.putAtributte("Cumpleaños", "15/06/99");
        p2.addPhoto("/imgpersonas/persona5.jpg");
        p2.addPhoto("/imgpersonas/persona6.jpg");
        p2.addPhoto("/imgpersonas/persona2.jpg");
//        p2.removePhoto("/imgpersonas/persona5.jpg");
//        p2.removePhoto("/imgpersonas/persona6.jpg");
//        p2.removePhoto("/imgpersonas/persona2.jpg");
        p2.addTag("Familia");
        p2.addTag("Amigos");
        p2.addTag("Trabajo");
        p2.addTag("Escuela");
        p2.addTag("Todo");
        contactos.addLast(p2);
        p2.addContactoRelacionado(p);
        p2.addContactoRelacionado(contactos.get(2));
        
        Persona p3 = new Persona("Emanuel Maclaren", "+593 681-70-0726");
        p3.putAtributte("Apellido", "Saslovi Rifo");
        p3.putAtributte("Usuario twitter", "@Twitter");
        p3.putAtributte("Usuario fb", "@Facebook");
        p3.putAtributte("Cumpleaños", "29/11/96");
        p3.addTag("Familia");
        p3.addTag("Amigos");
        p3.addTag("Todo");
        contactos.addLast(p3);
        
        
    }
    
    public static Set<String> getTags(){
        Set<String> tags = new LinkedHashSet();
        tags.add("Todo");
        tags.add("Favorito");
        for(Contacto contacto: contactos){
            tags.addAll(contacto.getTags());
        }
        return tags;
    }
    
    public static void guardarContactos(Usuario usuario){
        String nombreArchivo = usuario.getNombreUsr() + ".vCard";
        File file = new File(nombreArchivo);
        try{
            FileWriter fw = new FileWriter(file);
            String linea = "";
            System.out.println(contactos.size());
            for(Contacto contacto: contactos){
                StringJoiner joinerLinea = new StringJoiner(",");
                int tipo = 0; //Es 0 si contacto es Persona
                if (contacto instanceof Empresa){
                    tipo = 1; // 1 si es Empresa
                }
                joinerLinea.add(String.valueOf(tipo));
                joinerLinea.add(String.valueOf(contacto.isFavorite()));
                joinerLinea.add(contacto.getName());
                joinerLinea.add(String.valueOf(contacto.getNumber()));
                
                StringJoiner joiner = new StringJoiner(";");
                for (String tag : contacto.getTags()) {
                    joiner.add(tag);
                }
                String tags = joiner.toString();
                joinerLinea.add(tags);
                
                joiner = new StringJoiner(";");
                for (String photo : contacto.getPhotos()) {
                    joiner.add(photo);
                }
                String photos = joiner.toString();
                joinerLinea.add(photos);
                
                joiner = new StringJoiner(";");
                for (String key : contacto.getKeysAtributtes()) {
                    joiner.add(key+":"+contacto.getValorAtributte(key));
                }
                String atributtes = joiner.toString();
                joinerLinea.add(atributtes);
                
                joiner = new StringJoiner(";");
                for (Contacto contactoRelacionado: contacto.getContactosRelacionados()) {
                    joiner.add(String.valueOf(contactoRelacionado.getId()));
                }
                String contactosRelacionados = joiner.toString();
                joinerLinea.add(contactosRelacionados+"\r\n");
                linea += joinerLinea.toString();
            }
            fw.write(linea);
            fw.close();
            
        }catch (Exception e){
            System.out.println(e);
        }
    }
    
    public static void cargarContactos(){
        File file = new File(usuario.getNombreUsr()+".vCard");
        if(file.exists()){
            try{
                FileReader fr = new FileReader(file);
                BufferedReader bf = new BufferedReader(fr);
                String linea = bf.readLine();
                Contacto contacto = null; 
                HashMap<Contacto, List<Integer>> contactosDiccionario = new LinkedHashMap<>();
                while(linea != null){
                    if(linea != null){
                        System.out.println(linea);
                        String[] datos = linea.split(",",-1);
                        int i = 0;
                        int tipo = Integer.parseInt(datos[0]);
                        boolean isFavorite = Boolean.parseBoolean(datos[1]);
                        String nombre = datos[2];
                        String numero = datos[3];
                        String[] tagsString = datos[4].split(";");
                        String[] photos = datos[5].split(";");

                        String[] atributtes = datos[6].split(";");
                        String[] contactosRelacionadosStringIDs = datos[7].split(";");

                        List<Integer> contactosRelacionadosIDs = new ArrayList();
                        for(String id: contactosRelacionadosStringIDs){
                            if(!id.equals("")){
                                contactosRelacionadosIDs.addLast(Integer.valueOf(id));
                            }
                        }
                        if(tipo == 0){
                            contacto = new Persona(nombre, numero);
                        }else if(tipo == 1){
                            contacto = new Empresa(nombre, numero);
                        }
                        contacto.setFavorito(isFavorite);
                        for(String photo: photos){
                            if(!photo.equals("")){
                                if(Files.exists(Paths.get(photo.split(":")[1]))){
                                    contacto.addPhoto(photo);
                                }
                            }
                        }
                        for(String tag: tagsString){
                            if(!tag.equals("")){
                                contacto.addTag(tag);
                            }
                        }
                        for(String atributte: atributtes){
                            if(!atributte.equals("")){
                                String[] d = atributte.split(":");
                                String key = d[0];
                                String value = d[1];
                                contacto.putAtributte(key, value);
                            }
                        }
                        contactosDiccionario.put(contacto, contactosRelacionadosIDs);
                        contactos.addLast(contacto);
                    }
                    linea = bf.readLine();
                }

                for (Contacto c1: contactosDiccionario.keySet()){
                    for (Contacto c2: contactosDiccionario.keySet()){
                        for(int id: contactosDiccionario.get(c1)){
                            if(c2.getId() == id){
                                c1.addContactoRelacionado(c2);
                            }
                        }
                    }
                }

            }catch (Exception e){
                System.out.println(e);
                System.out.println("ERRRORRRRRRRRRRR");
            }
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
