package com.boot.demo.weixin.controller;

import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.menu.WxMpGetSelfMenuInfoResult;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static me.chanjar.weixin.common.api.WxConsts.MenuButtonType;


/**
 * 微信公众号菜单
 *
 * @author liangfeihu
 * @since 2018/8/16 17:02.
 */
@RestController
@RequestMapping("/wechat/menu")
public class WxMenuController {

    @Autowired
    private WxMpService wxService;

    /**
     * 自定义菜单创建接口
     * POST方式：数据格式参考resources下wx_menu.json
     *
     * @param menu
     * @return 如果是个性化菜单，则返回menuid，否则返回null
     */
    @PostMapping("/create")
    public String menuCreate(@RequestBody WxMenu menu) throws WxErrorException {
        return this.wxService.getMenuService().menuCreate(menu);
    }

    /**
     * 自定义菜单创建接口
     * GET方式
     */
    @GetMapping("/create")
    public String menuCreateSample() throws WxErrorException {
        WxMenu menu = new WxMenu();

        WxMenuButton button1 = new WxMenuButton();
        button1.setType(MenuButtonType.VIEW);
        button1.setName("极速下款");
        button1.setUrl("https://h5.51ddhua.com/#/?series=friddhua");

        menu.getButtons().add(button1);

        return this.wxService.getMenuService().menuCreate(menu);
    }

    /**
     * 自定义菜单创建接口
     * GET方式 json
     *
     * @param json
     * @return 如果是个性化菜单，则返回menuid，否则返回null
     */
    @GetMapping("/create/{json}")
    public String menuCreate(@PathVariable String json) throws WxErrorException {
        return this.wxService.getMenuService().menuCreate(json);
    }


    /**
     * 自定义菜单查询接口
     */
    @GetMapping("/get")
    public WxMpMenu menuGet() throws WxErrorException {
        return this.wxService.getMenuService().menuGet();
    }


    /**
     * 自定义菜单删除接口
     */
    @GetMapping("/delete")
    public void menuDelete() throws WxErrorException {
        this.wxService.getMenuService().menuDelete();
    }

    /**
     * 删除个性化菜单接口
     *
     * @param menuId 个性化菜单的menuid
     */
    @GetMapping("/delete/{menuId}")
    public void menuDelete(@PathVariable String menuId) throws WxErrorException {
        this.wxService.getMenuService().menuDelete(menuId);
    }

    /**
     * 获取自定义菜单配置接口
     * 本接口将会提供公众号当前使用的自定义菜单的配置，
     * 如果公众号是通过API调用设置的菜单，则返回菜单的开发配置，
     * 而如果公众号是在公众平台官网通过网站功能发布菜单，则本接口返回运营者设置的菜单配置。
     */
    @GetMapping("/getSelfMenuInfo")
    public WxMpGetSelfMenuInfoResult getSelfMenuInfo() throws WxErrorException {
        return this.wxService.getMenuService().getSelfMenuInfo();
    }

}
