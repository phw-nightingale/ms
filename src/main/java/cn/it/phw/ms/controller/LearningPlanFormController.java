package cn.it.phw.ms.controller;

import cn.it.phw.ms.common.JsonResult;
import cn.it.phw.ms.pojo.LearningPlanForm;
import cn.it.phw.ms.service.BaseService;
import cn.it.phw.ms.service.LearningPlanFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ms")
public class LearningPlanFormController extends BaseController {

    @Autowired
    private LearningPlanFormService learningPlanFormService;

    @ResponseBody
    @PutMapping("/plan-form")
    public JsonResult insertLearningPlanForm(LearningPlanForm learningPlanForm) {
        return learningPlanFormService.insert(learningPlanForm);
    }

    @ResponseBody
    @DeleteMapping("/plan-form/{id}")
    public JsonResult deleteLearningPlanFormById(@PathVariable Integer id) {
        return learningPlanFormService.deleteByPrimaryKey(id);
    }

    @ResponseBody
    @PostMapping("/plan-form")
    public JsonResult updateLearningPlanFormById(LearningPlanForm learningPlanForm) {
        return learningPlanFormService.updateByPrimaryKey(learningPlanForm);
    }

    @ResponseBody
    @GetMapping("/plan-form/{id}")
    public JsonResult findLearningPlanFormById(@PathVariable Integer id) {
        return learningPlanFormService.selectByPrimaryKey(id);
    }

    @ResponseBody
    @GetMapping("/plan-form")
    public JsonResult findLearningPlanFormByExample(LearningPlanForm form) {
        return null;
    }

    @ResponseBody
    @GetMapping("/plan-forms")
    public JsonResult findAllLearningPlanForms() {
        return learningPlanFormService.selectByExample(null);
    }


}
