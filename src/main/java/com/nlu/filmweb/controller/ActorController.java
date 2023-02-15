package com.nlu.filmweb.controller;


import com.nlu.filmweb.dto.ActorCreationDTO;
import com.nlu.filmweb.dto.ActorDTO;
import com.nlu.filmweb.dto.ActorDetailDTO;
import com.nlu.filmweb.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/actors")
@RequiredArgsConstructor
@Controller
public class ActorController {
    private final ActorService actorService;

    @GetMapping("")
    public ResponseEntity<List<ActorDTO>> getAll(){
        return ResponseEntity.ok(actorService.getAll());
    }

    @PostMapping("")
    public ResponseEntity<ActorDetailDTO> insert(@RequestBody ActorCreationDTO actorCreationDTO){
        var actorDetailDTO = actorService.insert(actorCreationDTO);
        return ResponseEntity.ok(actorDetailDTO);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ActorDetailDTO> getById(@PathVariable Long id){
        var actorDetailDTO = actorService.getById(id);
        return ResponseEntity.ok(actorDetailDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ActorDetailDTO> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(actorService.deleteById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActorDetailDTO> updateById(@RequestBody ActorCreationDTO actorCreationDTO, @PathVariable Long id){
        return ResponseEntity.ok(actorService.update(id, actorCreationDTO));
    }


}
