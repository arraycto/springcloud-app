package com.wangsong.system.controller;

import com.wangsong.common.controller.BaseController;
import com.wangsong.common.model.CodeEnum;
import com.wangsong.common.model.Result;
import com.wangsong.system.model.User;
import com.wangsong.system.model.UserAddModel;
import com.wangsong.system.model.UserPage;
import com.wangsong.system.service.UserService;
import com.wangsong.system.vo.UserVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;


    @RequiresPermissions("/system/user/list")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(HttpServletRequest request, UserPage user) {
        return userService.findTByPage(user);
    }

    @RequiresPermissions("/system/user/add")
    @RequestMapping(value = "/add")
    @ResponseBody
    public Result add(UserAddModel user) {
        userService.insertUser(user);
        return new Result(CodeEnum.SUCCESS.getCode(), null);

    }


    @RequestMapping(value = "/selectByPrimaryKey")
    @ResponseBody
    public UserVO selectByPrimaryKey(String id) {
        return userService.selectByPrimaryKey(id);
    }

    @RequiresPermissions("/system/user/update")
    @RequestMapping(value = "/update")
    @ResponseBody
    public Result update(UserAddModel muser) {
        userService.updateUser(muser);
        return new Result(CodeEnum.SUCCESS.getCode(), null);

    }

    @RequiresPermissions("/system/user/delete")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Result delete(String[] id) {
        userService.deleteUser(id);
        return new Result(CodeEnum.SUCCESS.getCode(), null);

    }

    @RequestMapping(value = "/findUserByUser")
    @ResponseBody
    public Result findUserByUser(User user) {
        User tByT = userService.findTByT(user);
        String r = tByT == null ? CodeEnum.SUCCESS.getCode() : CodeEnum.NO_NULL.getCode();
        return new Result(r, null);

    }

    @RequestMapping(value = "/toUpdatePassword")
    @ResponseBody
    public User toUpdatePassword() {
        return userService.selectByPrimaryKey();
    }

    @RequestMapping(value = "/updatePassword")
    @ResponseBody
    public Result updatePassword(UserAddModel muser) {
        userService.updatePassword(muser);
        return new Result(CodeEnum.SUCCESS.getCode(), null);

    }

}
