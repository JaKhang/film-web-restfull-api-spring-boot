package com.nlu.filmweb.controller.common;


import com.nlu.filmweb.controller.CommonController;
import com.nlu.filmweb.dto.CommonCreationDTO;
import com.nlu.filmweb.dto.CommonDTO;
import com.nlu.filmweb.entity.Country;
import com.nlu.filmweb.entity.Status;
import com.nlu.filmweb.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/film-statuses")
public class StatusController extends CommonController<Status> {
    @Autowired
    public void setCommonService(CommonService<Status> commonService) {
        super.setCommonService(commonService);
    }
    @GetMapping("")
    public ResponseEntity<List<CommonDTO>> getAll() {
        return super.getAll();
    }

    @PostMapping("")
    public ResponseEntity<CommonDTO> insert(@RequestBody CommonCreationDTO commonCreationDTO) {
        return super.insert(commonCreationDTO);
    }

    @PostMapping("/all")
    public ResponseEntity<List<CommonDTO>> insertAll(@RequestBody List<CommonCreationDTO> commonCreationDTOs) {
        return super.insertAll(commonCreationDTOs);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CommonDTO> deleteById(@PathVariable Long id){
        return super.deleteById(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CommonDTO> updateByID(@PathVariable Long id, @RequestBody CommonCreationDTO commonCreationDTO){
        return super.updateByID(id, commonCreationDTO);
    }
}
