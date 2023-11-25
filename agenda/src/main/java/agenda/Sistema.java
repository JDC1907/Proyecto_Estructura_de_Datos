package agenda;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
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
   public static Usuario usuario;
   public static List<Usuario> usuarios = new ArrayList<>();
   public static CircularList<Contacto> contactos = new DoublyLinkedList();
    
    //public static List<Contacto> contactos = new LinkedList();
public static boolean comprobarUsuario(String nombreUsuario, String contrasena){
        for (int i = 0; i<usuarios.size(); i++) {
            if (usuarios.get(i).nombreUsr.equals(nombreUsuario) && usuarios.get(i).contrasena.equals(contrasena)) {
                usuario = usuarios.get(i);
                return true;
            }
        }
        return false;
}
    public static void inicializarSistema(){
        cargarUsuarios();
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
        File file = new File("usuarios/"+nombreArchivo);
        
        try{
            FileWriter fw = new FileWriter(file);
            String linea = "";
            
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
            e.printStackTrace();
        }
    }
    
    public static void cargarContactos(Usuario usuario){
        File file = new File("usuarios/"+usuario.getNombreUsr()+".vCard");
        if(file.exists()){
            try{
                FileReader fr = new FileReader(file,StandardCharsets.UTF_8);
                BufferedReader bf = new BufferedReader(fr);
                String linea = bf.readLine();
                Contacto contacto = null; 
                HashMap<Contacto, List<Integer>> contactosDiccionario = new LinkedHashMap<>();
                while(linea != null){
                    if(linea != null){
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
                                String[] d = atributte.split(":",-1);
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
                e.printStackTrace();
            }
        }
    }

    public static void cargarUsuarios(){
        File file = new File("usuarios.txt");
        if(file.exists()){
            try{
                FileReader fr = new FileReader(file,StandardCharsets.UTF_8);
                BufferedReader bf = new BufferedReader(fr);
                String linea = bf.readLine();
                while(linea != null){
                    if(linea != null){
                        String[] datos = linea.split(",",-1);
                        int i = 0;
                        String nombreUsuario = datos[0];
                        String password = datos[1];
                        String nombre = datos[2];
                        String tipo = datos[3];
                        usuarios.addLast(new Usuario(nombreUsuario, password, nombre, tipo));
                    }
                    linea = bf.readLine();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public static void guardarUsuarios(){
        String nombreArchivo = "usuarios.txt";
        File file = new File(nombreArchivo);
        try{
            FileWriter fw = new FileWriter(file);
            String linea = "";
            
            for(Usuario u: usuarios){
                StringJoiner joinerLinea = new StringJoiner(",");

                joinerLinea.add(u.getNombreUsr());
                joinerLinea.add(u.getContrasena());
                joinerLinea.add(u.getNombre());
                joinerLinea.add(u.getTipo()+"\r\n");
                
                linea += joinerLinea.toString();
            }
            
            fw.write(linea);
            fw.close();
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
  
    
}
