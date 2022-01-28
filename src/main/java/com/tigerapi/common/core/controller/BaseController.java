package com.tigerapi.common.core.controller;

import com.github.pagehelper.PageInfo;
import com.tigerapi.common.constant.HttpStatus;
import com.tigerapi.common.core.domain.AjaxResult;
import com.tigerapi.common.core.page.TableDataInfo;
import com.tigerapi.common.utils.PageUtils;
import com.tigerapi.common.utils.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

/**
 * @ClassName BaseController
 * @Description
 * @Author jerry
 * @Since 2022/1/27
 */
public class BaseController {

    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        PageUtils.startPage();
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 返回成功
     */
    public AjaxResult success()
    {
        return AjaxResult.success();
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error()
    {
        return AjaxResult.error();
    }

    /**
     * 返回成功消息
     */
    public AjaxResult success(String message)
    {
        return AjaxResult.success(message);
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error(String message)
    {
        return AjaxResult.error(message);
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected AjaxResult toAjax(boolean result)
    {
        return result ? success() : error();
    }

    /**
     * 页面跳转
     */
    public String redirect(String url)
    {
        return StringUtils.format("redirect:{}", url);
    }

//    /**
//     * 获取用户缓存信息
//     */
//    public LoginUser getLoginUser()
//    {
//        return SecurityUtils.getLoginUser();
//    }
//
//    /**
//     * 获取登录用户id
//     */
//    public Long getUserId()
//    {
//        return getLoginUser().getUserId();
//    }
//
//    /**
//     * 获取登录部门id
//     */
//    public Long getDeptId()
//    {
//        return getLoginUser().getDeptId();
//    }
//
//    /**
//     * 获取登录用户名
//     */
//    public String getUsername()
//    {
//        return getLoginUser().getUsername();
//    }
}
