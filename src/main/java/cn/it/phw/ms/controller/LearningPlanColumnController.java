package cn.it.phw.ms.controller;

import cn.it.phw.ms.common.JsonResult;
import cn.it.phw.ms.pojo.LearningPlanColumn;
import cn.it.phw.ms.service.BaseService;
import cn.it.phw.ms.service.LearningPlanColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ms")
public class LearningPlanColumnController extends BaseController {

    @Autowired
    private LearningPlanColumnService learningPlanColumnService;

    @ResponseBody
    @GetMapping("/form-columns")
    public JsonResult getLearningPlanTemplate() {
        return learningPlanColumnService.selectByExample(null);
    }

    @ResponseBody
    @PostMapping("/form-column")
    public JsonResult updateLearningPlanColumnById(LearningPlanColumn column) {
        return learningPlanColumnService.updateByPrimaryKey(column);
    }

    @ResponseBody
    @DeleteMapping("/form-column/{id}")
    public JsonResult deleteLearningPlanColumnById(@PathVariable Integer id) {
        return learningPlanColumnService.deleteByPrimaryKey(id);
    }

    @ResponseBody
    @PutMapping("/form-column")
    public JsonResult insertLearningPlanColumn(LearningPlanColumn column) {
        return learningPlanColumnService.insert(column);
    }

}
