package org.iplacex.proyectos.discografia.Discos;

import org.iplacex.proyectos.discografia.Artistas.IArtistaRepository;
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
public class DiscoController {

    @Autowired
    private IDiscoRepository discoRepository;

    @Autowired
    private IArtistaRepository artistaRepository;

    @PostMapping(
        value = "/discos",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Discos> handlePostDiscoRequest(@RequestBody Discos disco) {
        if (!artistaRepository.existsById(disco.idArtista)) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        Discos temp = discoRepository.save(disco);
        return new ResponseEntity<>(temp, null, HttpStatus.CREATED);
    }

    @GetMapping(
        value = "/discos",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Discos>> handleGetDiscosRequest() {
        List<Discos> discos = discoRepository.findAll();
        return new ResponseEntity<>(discos, null, HttpStatus.OK);
    }

    @GetMapping(
        value = "/discos/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Discos> handleGetDiscoRequest(@PathVariable("id") String id) {
        Optional<Discos> temp = discoRepository.findById(id);

        if (!temp.isPresent()) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(temp.get(), null, HttpStatus.OK);
    }

    @GetMapping(
        value = "/artista/{id}/discos",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Discos>> handleGetDiscosByArtistaRequest(@PathVariable("id") String idArtista) {
        List<Discos> discos = discoRepository.findDiscosByIdArtista(idArtista);
        return new ResponseEntity<>(discos, null, HttpStatus.OK);
    }
}
