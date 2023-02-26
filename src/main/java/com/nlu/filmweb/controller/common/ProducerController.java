package com.nlu.filmweb.controller.common;

import com.nlu.filmweb.controller.CommonController;
import com.nlu.filmweb.payload.request.CommonRequest;
import com.nlu.filmweb.payload.response.CommonResponse;
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
    @GetMapping("")
    public ResponseEntity<List<CommonResponse>> getAll() {
        return super.getAll();
    }

    @Override
    @PostMapping("")
    public ResponseEntity<CommonResponse> insert(@RequestBody CommonRequest commonRequest) {
        return super.insert(commonRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> deleteById(@PathVariable Long id) {
        return super.deleteById(id);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse> updateByID(@PathVariable Long id, CommonRequest commonRequest) {
        return super.updateByID(id, commonRequest);
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<CommonResponse>> insertAll(@RequestBody List<CommonRequest> commonRequests) {
        return super.insertAll(commonRequests);
    }

    @Autowired
    public void setCommonService(CommonService<Producer> commonService) {
        super.setCommonService(commonService);
    }
}
