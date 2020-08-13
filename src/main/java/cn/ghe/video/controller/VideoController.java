package cn.ghe.video.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Api(value="蒲公英云页面管理接口",description = "蒲公英云页面管理接口，提供页面的增、删、改、查")
public interface VideoController {
    final String API_PRE = "/dandelioncloud/page";
    @GetMapping(API_PRE+"/list/{page}/{size}")
    @ApiOperation("分页查询页面列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码",required=true,paramType="path",dataType="integer"),
            @ApiImplicitParam(name="size",value = "每页记录数",required=true,paramType="path",dataType="integer")
    })
    public ResponseEntity<Object> findList(@PathVariable("page") int page, @PathVariable("size") int size);
}
