package com.nlu.filmweb.controller;

import com.nlu.filmweb.dto.CommonCreationDTO;
import com.nlu.filmweb.dto.CommonDTO;
import com.nlu.filmweb.entity.CommonEntity;
import com.nlu.filmweb.service.CommonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonController<T extends CommonEntity> {
    protected CommonService<T> commonService;

    public ResponseEntity<List<CommonDTO>> getAll() {
        return ResponseEntity.ok(commonService.getAll());
    }

    public ResponseEntity<CommonDTO> insert(@RequestBody CommonCreationDTO commonCreationDTO) {
        CommonDTO commonDTO = commonService.insert(commonCreationDTO);
        return ResponseEntity.ok(commonDTO);
    }

    public ResponseEntity<CommonDTO> deleteById(@PathVariable Long id){
        var commonDTO = commonService.deleteById(id);
        return ResponseEntity.ok(commonDTO);
    }

    public ResponseEntity<CommonDTO> updateByID(@PathVariable Long id, @RequestBody CommonCreationDTO commonCreationDTO){
        CommonDTO commonDTO = commonService.update(id , commonCreationDTO);
        return ResponseEntity.ok(commonDTO);
    }

    public ResponseEntity<List<CommonDTO>> insertAll(@RequestBody List<CommonCreationDTO> commonCreationDTOs) {
        List<CommonDTO> commonDTOS = new ArrayList<>();
        for (var commonCreationDTO: commonCreationDTOs) {
            commonDTOS.add(commonService.insert(commonCreationDTO));
        }
        return ResponseEntity.ok(commonDTOS);
    }

    public void setCommonService(CommonService<T> commonService) {
        this.commonService = commonService;
    }
}
