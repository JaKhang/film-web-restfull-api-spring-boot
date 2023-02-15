package com.nlu.filmweb.controller.common;

import com.nlu.filmweb.controller.CommonController;
import com.nlu.filmweb.dto.CommonCreationDTO;
import com.nlu.filmweb.dto.CommonDTO;
import com.nlu.filmweb.entity.Producer;
import com.nlu.filmweb.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/producers")
public class ProducerController extends CommonController<Producer> {



    @Override
    @PostMapping("")
    public ResponseEntity<List<CommonDTO>> getAll() {
        return super.getAll();
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<CommonDTO> insert(@RequestBody CommonCreationDTO commonCreationDTO) {
        return super.insert(commonCreationDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<CommonDTO> deleteById(@PathVariable Long id) {
        return super.deleteById(id);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<CommonDTO> updateByID(@PathVariable Long id, CommonCreationDTO commonCreationDTO) {
        return super.updateByID(id, commonCreationDTO);
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<CommonDTO>> insertAll(@RequestBody List<CommonCreationDTO> commonCreationDTOs) {
        return super.insertAll(commonCreationDTOs);
    }

    @Autowired
    public void setCommonService(CommonService<Producer> commonService) {
        super.setCommonService(commonService);
    }
}
