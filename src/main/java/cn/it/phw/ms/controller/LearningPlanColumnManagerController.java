package cn.it.phw.ms.controller;

import cn.it.phw.ms.common.JsonResult;
import cn.it.phw.ms.pojo.LearningPlanColumnManager;
import cn.it.phw.ms.pojo.LearningPlanColumnManagerExample;
import cn.it.phw.ms.service.LearningPlanColumnManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ms")
public class LearningPlanColumnManagerController extends BaseController {

    @Autowired
    private LearningPlanColumnManagerService learningPlanColumnManagerService;

    private LearningPlanColumnManagerExample example = new LearningPlanColumnManagerExample();

    @ResponseBody
    @GetMapping("/column-manager")
    public JsonResult findLearningPlanColumnManagerByFormId(@RequestParam Integer userId) {
        LearningPlanColumnManagerExample.Criteria criteria = example.or();
        criteria.andLearningPlanFormIdEqualTo(userId);
        return learningPlanColumnManagerService.selectByExample(example);
    }

    @ResponseBody
    @DeleteMapping("/column-manager/{id}")
    public JsonResult deleteLearningPlanFormManagerById(@PathVariable Integer id) {
        return learningPlanColumnManagerService.deleteByPrimaryKey(id);
    }

    @ResponseBody
    @PostMapping("/column-manager")
    public JsonResult updateLearningPlanFormManagerById(LearningPlanColumnManager manager) {
        return learningPlanColumnManagerService.updateByPrimaryKey(manager);
    }

    @ResponseBody
    @PutMapping("/column-manager")
    public JsonResult insertLearningPlanFormManager(LearningPlanColumnManager manager) {
        return learningPlanColumnManagerService.insert(manager);
    }

}
