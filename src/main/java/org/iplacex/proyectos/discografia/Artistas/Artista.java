package org.iplacex.proyectos.discografia.Artistas;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Document("Artistas")
public class Artista {
    @Id
    public String _id;
    public String nombre;
    public List<String> estilos;

    @Field(name = "AñoDeFundacion", targetType = FieldType.INT32)
    public int anioFundacion;
    public boolean estaActivo;
}
