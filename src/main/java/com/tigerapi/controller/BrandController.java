package com.tigerapi.controller;

import com.tigerapi.common.core.controller.BaseController;
import com.tigerapi.common.core.domain.AjaxResult;
import com.tigerapi.common.core.page.TableDataInfo;
import com.tigerapi.entity.Brand;
import com.tigerapi.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Brand)表控制层
 *
 * @author makejava
 * @since 2022-01-28 10:01:35
 */
@RestController
@RequestMapping("brand")
public class BrandController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private BrandService brandService;

//    /**
//     * 分页查询
//     *
//     * @param brand 筛选条件
//     * @param pageRequest      分页对象
//     * @return 查询结果
//     */
//    @GetMapping
//    public ResponseEntity<Page<Brand>> queryByPage(Brand brand, PageRequest pageRequest) {
//        return ResponseEntity.ok(this.brandService.queryByPage(brand, pageRequest));
//    }

    @GetMapping("/findAll")
    public TableDataInfo findAll(){
        startPage();
        List<Brand> list = brandService.findAll();
        return getDataTable(list);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Brand> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.brandService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param brand 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public ResponseEntity<Brand> add(Brand brand) {
        return ResponseEntity.ok(this.brandService.insert(brand));
    }

    /**
     * 编辑数据
     *
     * @param brand 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Brand> edit(Brand brand) {
        return ResponseEntity.ok(this.brandService.update(brand));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.brandService.deleteById(id));
    }

}

