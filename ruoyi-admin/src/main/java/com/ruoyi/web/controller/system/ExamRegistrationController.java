package com.ruoyi.web.controller.system;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.ExamRegistration;
import com.ruoyi.system.service.IExamRegistrationService;

/**
 * 考试报名Controller
 */
@RestController
@RequestMapping("/system/exam/registration")
public class ExamRegistrationController extends BaseController
{
    @Autowired
    private IExamRegistrationService registrationService;

    /**
     * 获取我的报名列表
     */
    @GetMapping("/my")
    public AjaxResult myList()
    {
        List<ExamRegistration> list = registrationService.selectMyRegistrationList(getUserId());
        return success(list);
    }

    /**
     * 新增报名
     */
    @PostMapping
    public AjaxResult add(@RequestBody ExamRegistration registration)
    {
        registration.setUserId(getUserId());
        registration.setStatus("0");
        registration.setCreateBy(getUsername());
        return toAjax(registrationService.insertExamRegistration(registration));
    }

    /**
     * 确认报名
     */
    @PutMapping("/confirm/{registrationId}")
    public AjaxResult confirm(@PathVariable Long registrationId)
    {
        ExamRegistration registration = new ExamRegistration();
        registration.setRegistrationId(registrationId);
        registration.setStatus("1");
        return toAjax(registrationService.updateExamRegistration(registration));
    }

    /**
     * 取消报名
     */
    @DeleteMapping("/{registrationId}")
    public AjaxResult cancel(@PathVariable Long registrationId)
    {
        return toAjax(registrationService.deleteExamRegistrationById(registrationId));
    }
}
