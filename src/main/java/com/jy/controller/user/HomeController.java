package com.jy.controller.user;

import com.jy.controller.feign.FeiginUrlController;
import com.jy.utils.ExcelAnalysisUtil;
import com.jy.utils.SimpleExcelResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yangyibo on 17/1/18.
 */
@Controller
public class HomeController {

    @Autowired
    private FeiginUrlController feigin;

    @RequestMapping("/main")
    public String index(Model model){
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model){
        return "login";
    }

    @RequestMapping("/")
    public String toLogin(Model model){
        return "login";
    }

//    @RequestMapping("/logout")
//    public String logout(HttpServletRequest request, HttpServletResponse response){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null){
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        return "redirect:/login?logout";
//    }


    @RequestMapping("/websocket")
    public String websocket(Model model){
        return "websocket";
    }


    @ResponseBody
    @RequestMapping("/test")
    public String test(Model model){
        ExcelAnalysisUtil.outPutToExcel("C:/Users/20625/Desktop/group/test.xlsx",new SimpleExcelResolver(),null);
        return "ok";
    }

}
