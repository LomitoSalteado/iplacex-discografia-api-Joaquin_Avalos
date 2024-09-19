package org.iplacex.proyectos.discografia.Artistas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ArtistaController {

    @Autowired
    private IArtistaRepository artistaRepository;

    @PostMapping(
        value = "/artistas",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Artista> handleInsertArtistaRequest(@RequestBody Artista artista) {
        Artista temp = artistaRepository.save(artista);
        return new ResponseEntity<>(temp, null, HttpStatus.CREATED);
    }

    @GetMapping(
        value = "/artistas",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Artista>> handleGetArtistasRequest() {
        List<Artista> artistas = artistaRepository.findAll();
        return new ResponseEntity<>(artistas, HttpStatus.OK);
    }


    @GetMapping(
        value = "/artistas/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Artista> handleGetArtistaRequest(@PathVariable("id") String id) {
        Optional<Artista> temp = artistaRepository.findById(id);

        if (!temp.isPresent()) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(temp.get(), null, HttpStatus.OK);
    }

    @PutMapping(
        value = "/artistas/{id}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Artista> handleUpdateArtistaRequest(@PathVariable String id, @RequestBody Artista artista) {
        if (!artistaRepository.existsById(id)) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }

        artista._id = id;
        Artista temp = artistaRepository.save(artista);

        return new ResponseEntity<>(temp, null, HttpStatus.OK);
    }

    @DeleteMapping(
        value = "/artistas/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Artista> handleDeleteArtistaRequest(@PathVariable("id") String id) {
        if (!artistaRepository.existsById(id)) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        
        Artista temp = artistaRepository.findById(id).get();
        artistaRepository.deleteById(id);
        
        return new ResponseEntity<>(temp, null, HttpStatus.OK);
    }
}
