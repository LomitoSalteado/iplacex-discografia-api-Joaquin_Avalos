package org.iplacex.proyectos.discografia.Discos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "Discos")
public class Discos {
    @Id
    public String _id;
    public String idArtista;
    public String nombre;
    public int anioLanzamiento;
    public List<String> canciones;
}
