package cn.it.phw.ms.controller;

import cn.it.phw.ms.common.AppContext;
import cn.it.phw.ms.common.JsonResult;
import cn.it.phw.ms.pojo.LearningPlanForm;
import cn.it.phw.ms.pojo.LearningPlanFormExample;
import cn.it.phw.ms.service.BaseService;
import cn.it.phw.ms.service.LearningPlanFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    @GetMapping("/plan-form/owner")
    public JsonResult findLearningPlanFormByExample(HttpServletRequest request) {
        LearningPlanFormExample example = new LearningPlanFormExample();
        String uid = (String) request.getAttribute(AppContext.KEY_ID);
        example.or().andOwnerIdEqualTo(Integer.valueOf(uid));
        return learningPlanFormService.selectByExample(example);
    }

    @ResponseBody
    @GetMapping("/plan-forms")
    public JsonResult findAllLearningPlanForms() {
        return learningPlanFormService.selectByExample(null);
    }


}
