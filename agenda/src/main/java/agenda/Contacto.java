
package agenda;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import tda.CircularList;
import tda.DoublyLinkedList;
import tda.LinkedList;
import tda.List;


public abstract class Contacto{
    private LinkedHashSet tags;
    private LinkedHashMap<String, String> atributos;
    private CircularList<String> photos;
    private String name, number;
    private List<Date> dates;
    private LinkedHashSet<Contacto> contactosRelacionados;
    private boolean favorito;
    private static int id = 0;
    private int idContacto;
    private String userName;


    public Contacto(String name, String number) {
        this.tags = new LinkedHashSet<String>();
        this.tags.add("Todo");
        this.atributos = new LinkedHashMap<>();
        this.photos = new DoublyLinkedList();
        this.number = number;
        this.name = name;
        this.contactosRelacionados = new LinkedHashSet();
        this.dates = new LinkedList();
        this.favorito = false;
        id++;
        idContacto = id;
    }
    
    public int getId() {
        return idContacto;
    }
    
    public boolean addContactoRelacionado(Contacto contacto){
        if(contacto == null || this.contactosRelacionados.contains(contacto)){
            return false;
        }
        this.contactosRelacionados.add(contacto);
        contacto.addContactoRelacionado(this);
        return true;
    }
    
    public boolean removeContactoRelacionado(Contacto contacto){
        if(contacto == null || !this.contactosRelacionados.contains(contacto)){
            return false;
        }
        this.contactosRelacionados.remove(contacto);
        contacto.removeContactoRelacionado(this);
        return true;
    }
    
    public boolean addTag(String tag){
        if(!tag.equals("") && tag != null){
            tags.add(tag);
            return true;
        }
        return false;
    }
    
    public boolean removeTag(String tag){
        if(tags.contains(tag)){
            tags.remove(tag);
            return true;
        }
        return false;
    }
    public boolean setTag(String oldTag, String newTag){
        if(!removeTag(oldTag)){
            return false;
        }
        return addTag(newTag);
    }
    
    public LinkedHashSet<Contacto> getContactosRelacionados(){
        return contactosRelacionados;
    }
    
    public LinkedHashSet<String> getTags(){
        return tags;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    
    public boolean hasAtributte(String key){
        return this.atributos.containsKey(key);
    }
    
    public boolean setAtributte(String key, String valor){
        if(key.equals("") || key == null){
            return false;
        }
        if(valor == null){
            return false;
        }

        if(atributos.containsKey(key)){
            this.atributos.put(key, valor);
            return true;
        }
        return false;
    }
    
    public boolean putAtributte(String key, String valor){
        if(key.equals("") || key == null){
            return false;
        }
        if( valor == null){
            return false;
        }
        
        this.atributos.put(key, valor);
        return true;
    }
    
    public boolean updateKeyAtributte(String oldKey, String newKey){
        if(this.hasAtributte(oldKey) && !this.hasAtributte(newKey)){
            this.putAtributte(newKey, this.getValorAtributte(oldKey));
            this.atributos.remove(oldKey);
            return true;
        }
        return false;
    }
    
    public String getValorAtributte(String key){
        if (hasAtributte(key)){
            return this.atributos.get(key);
        }
        return null;
    }
    
    public Set<String> getKeysAtributtes(){
        return this.atributos.keySet();
    }
    
    public boolean removeKeyAtributte(String key){
        if(atributos.containsKey(key)){
            atributos.remove(key);
            return true;
        }
        return false;
    }
    
    public CircularList<String> getPhotos(){
        return photos;
    }
    
    public String getFirstPhoto(){
        return photos.get(0);
    }
    
    public void addPhoto(String photo){
        photos.addLast(photo);
    }
    
    public boolean hasPhotos(){
        return photos.size() > 0;
    }
    
    public int sizePhotos(){
        return photos.size();
    }
    
    public boolean removePhoto(String photo){
//        System.out.println(photo);
//        System.out.println(photos);
//        if(photo == null || !photos.contains(photo)){
//            return false;
//        }
        photos.remove(photo);
        
        return true;
    }
    
    @Override
    public String toString(){
        return this.name;
    }

    public boolean isFavorite() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }
    
    
}
