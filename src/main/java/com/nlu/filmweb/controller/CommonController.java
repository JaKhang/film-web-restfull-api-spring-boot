package com.nlu.filmweb.controller;

import com.nlu.filmweb.payload.request.CommonRequest;
import com.nlu.filmweb.payload.response.CommonResponse;
import com.nlu.filmweb.entity.CommonEntity;
import com.nlu.filmweb.service.CommonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonController<T extends CommonEntity> {
    protected CommonService<T> commonService;

    public ResponseEntity<List<CommonResponse>> getAll() {
        return ResponseEntity.ok(commonService.getAll());
    }

    public ResponseEntity<CommonResponse> insert(@RequestBody CommonRequest commonRequest) {
        CommonResponse commonResponse = commonService.insert(commonRequest);
        return ResponseEntity.ok(commonResponse);
    }

    public ResponseEntity<CommonResponse> deleteById(@PathVariable Long id){
        var commonDTO = commonService.deleteById(id);
        return ResponseEntity.ok(commonDTO);
    }

    public ResponseEntity<CommonResponse> updateByID(@PathVariable Long id, @RequestBody CommonRequest commonRequest){
        CommonResponse commonResponse = commonService.update(id , commonRequest);
        return ResponseEntity.ok(commonResponse);
    }

    public ResponseEntity<List<CommonResponse>> insertAll(@RequestBody List<CommonRequest> commonRequests) {
        List<CommonResponse> commonResponses = new ArrayList<>();
        for (var commonCreationDTO: commonRequests) {
            commonResponses.add(commonService.insert(commonCreationDTO));
        }
        return ResponseEntity.ok(commonResponses);
    }

    public void setCommonService(CommonService<T> commonService) {
        this.commonService = commonService;
    }
}
