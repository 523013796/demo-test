/**
 * User: zlin
 * Date: 2019/10/22
 * Time: 8:54
 **/

package com.zl.demotest.controller.community;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comm")
public class CommunityController {


    @RequestMapping("community")
    String toCommunity(){return "page/community";}
}
