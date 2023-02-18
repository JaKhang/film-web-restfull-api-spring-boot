package com.nlu.filmweb.controller.common;


import com.nlu.filmweb.controller.CommonController;
import com.nlu.filmweb.payload.request.CommonRequest;
import com.nlu.filmweb.payload.response.CommonResponse;
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
    public ResponseEntity<List<CommonResponse>> getAll() {
        return super.getAll();
    }

    @PostMapping("")
    public ResponseEntity<CommonResponse> insert(@RequestBody CommonRequest commonRequest) {
        return super.insert(commonRequest);
    }

    @PostMapping("/all")
    public ResponseEntity<List<CommonResponse>> insertAll(@RequestBody List<CommonRequest> commonRequests) {
        return super.insertAll(commonRequests);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> deleteById(@PathVariable Long id){
        return super.deleteById(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse> updateByID(@PathVariable Long id, @RequestBody CommonRequest commonRequest){
        return super.updateByID(id, commonRequest);
    }
}
