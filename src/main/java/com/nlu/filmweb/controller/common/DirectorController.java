package com.nlu.filmweb.controller.common;


import com.nlu.filmweb.payload.DirectorPayload;
import com.nlu.filmweb.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/directors")
@RequiredArgsConstructor
public class DirectorController {
    private final DirectorService directorService;

    @GetMapping("")
    public ResponseEntity<List<DirectorPayload>> getAll() {
        return ResponseEntity.ok(directorService.getAll());
    }

    @PostMapping("")
    public ResponseEntity<DirectorPayload> insert(@RequestBody DirectorPayload directorCreationDTO) {
        DirectorPayload directorDTO = directorService.insert(directorCreationDTO);
        return ResponseEntity.ok(directorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DirectorPayload> deleteById(@PathVariable Long id){
        var directorDTO = directorService.deleteById(id);
        return ResponseEntity.ok(directorDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DirectorPayload> updateByID(@PathVariable Long id, @RequestBody DirectorPayload directorCreationDTO){
        DirectorPayload directorDTO = directorService.update(id , directorCreationDTO);
        return ResponseEntity.ok(directorDTO);
    }

    @PostMapping("/all")
    public ResponseEntity<List<DirectorPayload>> insertAll(@RequestBody List<DirectorPayload> directorCreationDTOs) {
        List<DirectorPayload> directorDTOS = new ArrayList<>();
        for (var directorCreationDTO: directorCreationDTOs) {
            directorDTOS.add(directorService.insert(directorCreationDTO));
        }
        return ResponseEntity.ok(directorDTOS);
    }

}
