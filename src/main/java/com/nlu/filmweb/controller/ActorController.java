package com.nlu.filmweb.controller;


import com.nlu.filmweb.payload.request.ActorRequest;
import com.nlu.filmweb.payload.response.ActorResponse;
import com.nlu.filmweb.payload.response.ActorDetailsResponse;
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
    public ResponseEntity<List<ActorResponse>> getAll(){
        return ResponseEntity.ok(actorService.getAll());
    }

    @PostMapping("")
    public ResponseEntity<ActorDetailsResponse> insert(@RequestBody ActorRequest actorRequest){
        var actorDetailDTO = actorService.insert(actorRequest);
        return ResponseEntity.ok(actorDetailDTO);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ActorDetailsResponse> getById(@PathVariable Long id){
        var actorDetailDTO = actorService.getById(id);
        return ResponseEntity.ok(actorDetailDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ActorDetailsResponse> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(actorService.deleteById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActorDetailsResponse> updateById(@RequestBody ActorRequest actorRequest, @PathVariable Long id){
        return ResponseEntity.ok(actorService.update(id, actorRequest));
    }


}
