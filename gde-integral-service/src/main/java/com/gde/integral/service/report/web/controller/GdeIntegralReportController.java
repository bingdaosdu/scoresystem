package com.gde.integral.service.report.web.controller;

import com.gde.integral.service.common.dao.IntegralAllDao;
import com.gde.integral.service.common.entity.Result;
import com.gde.integral.service.common.entity.StatusCode;
import com.gde.integral.service.common.pojo.IntegralAll;
import com.gde.integral.service.common.pojo.IntegralAlterInfo;
import com.gde.integral.service.common.util.IntegralDateUtils;
import com.gde.integral.service.dispose.service.IntegralAlterInfoService;
import com.gde.integral.service.report.service.GdeIntegralReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * GDE社区积分报表Controller类
 *
 * @author ~
 * @date 2019/6/27
 */
@RestController
@RequestMapping("/integralReport")
@CrossOrigin
@Api(tags = "积分数据表相关接口")
public class GdeIntegralReportController {

    private final GdeIntegralReportService gdeIntegralReportService;

    @Autowired
    public GdeIntegralReportController(GdeIntegralReportService gdeIntegralReportService, IntegralAllDao integralAllDao, IntegralAlterInfoService integralAlterInfoService) {
        this.gdeIntegralReportService = gdeIntegralReportService;
        this.integralAllDao = integralAllDao;
        this.integralAlterInfoService = integralAlterInfoService;
    }

    /**
     * 获取所有成员总计积分数据的表格
     *
     * @param request 请求
     * @param response 响应
     */
    @GetMapping("/integralAllExcel")
    public void integralAllExcel(HttpServletRequest request, HttpServletResponse response) {

    }


    private final IntegralAllDao integralAllDao;

    private final IntegralAlterInfoService integralAlterInfoService;

    /**
     * 测试下载excel文件
     */
    @GetMapping("/downloadExcel")
    public void downloadExcel(HttpServletResponse response) {

        // 获取所有总积分数据
        List<IntegralAll> integralAllList = integralAllDao.findByOrderByIntegralTotalDesc();

        Workbook wb = new XSSFWorkbook();

        // web对象
        Sheet sheet = wb.createSheet("test1");

        for (int i = 0; i < integralAllList.size(); i++) {

            Row row = sheet.createRow(i);

            Cell cell00 = row.createCell(0);
            Cell cell01 = row.createCell(1);
            Cell cell02 = row.createCell(2);
            Cell cell03 = row.createCell(3);

            if (i == 0) {
                cell00.setCellValue("姓名");
                cell01.setCellValue("工号");
                cell02.setCellValue("可用积分");
                cell03.setCellValue("变动积分");
                continue;
            }

            IntegralAll integralAll = integralAllList.get(i - 1);

            cell00.setCellValue(integralAll.getName());
            cell01.setCellValue(integralAll.getW3id());
            cell02.setCellValue(integralAll.getIntegralTotal());

        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + "GDEIntegralTotal_"+ IntegralDateUtils.appointDateStr(0) +".xlsx");

        try {
            response.flushBuffer();
            // 将wb中的内容写入输出流中
            wb.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 上传Excel文件
     *
     * @param excelFile excelFile
     * @return 返回是否成功
     */
    @ApiOperation("上传积分处理结果表格")
    @PostMapping(value = "/uploadExcel")
    @Transactional(rollbackFor = Exception.class)
    public Result uploadExcel(@RequestParam("excelFile") MultipartFile excelFile) {
        System.out.println("start...");
        String name = excelFile.getOriginalFilename();
        System.out.println(name);
        if (name != null && (name.length() < 6 || !".xlsx".equals(name.substring(name.length() - 5)))) {
            return new Result(false, StatusCode.ERROR, "文件格式错误");
        }

        List<IntegralAlterInfo> integralAlterInfoList = new ArrayList<>();

        // 解析excel文件
        try {
            InputStream inputStream = excelFile.getInputStream();

            List<Integer> list = new ArrayList<>();

            Workbook wb = WorkbookFactory.create(inputStream);
            inputStream.close();

            // 工作表对象
            Sheet sheet = wb.getSheetAt(0);
            // 总行数
            int rowLength = sheet.getLastRowNum() + 1;
            // 工作表的列
            Row row = sheet.getRow(0);
            // 总列数
            int colLength = row.getLastCellNum();
            // 得到指定的单元格
            Cell cell;

            for (int i = 1; i < rowLength; i ++) {
                row = sheet.getRow(i);

                IntegralAlterInfo integralAlterInfo = new IntegralAlterInfo();
                for (int j = 0; j < colLength; j++) {
                    cell = row.getCell(j);

                    if (cell != null) {

                        CellType cellType = cell.getCellType();

                        if (j == 0) {
                            String data = cell.getStringCellValue();
                            integralAlterInfo.setName(data.trim());
                        } else if (j == 1) {
                            String data = cell.getStringCellValue();
                            integralAlterInfo.setW3id(data.trim());
                        } else if (j == 3 && cellType.equals(CellType.NUMERIC)) {
                            double data = cell.getNumericCellValue();
                            integralAlterInfo.setAlterValue(data);
                        }
                        integralAlterInfo.setRecordTime(new Date());
                        integralAlterInfo.setAlterReason("excel批量导入");


                        if (cellType.equals(CellType.STRING)) {
                            String data = cell.getStringCellValue();
                            data = data.trim();
                            if (StringUtils.isNumeric(data)) {
                                list.add(Integer.parseInt(data));
                            }
//                            System.out.println(data);
                        } else if (cellType.equals(CellType.NUMERIC)) {
                            double data = cell.getNumericCellValue();
//                            System.out.println(data);
                        }
                    }
                }

                // 只有当alterValue不等于0时才添加到需要修改积分的集合对象
                if (integralAlterInfo.getAlterValue() != 0) {
                    integralAlterInfoList.add(integralAlterInfo);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        integralAlterInfoList.forEach(integralAlterInfo -> {
            integralAlterInfoService.integralAlter(integralAlterInfo.getName(),
                    integralAlterInfo.getW3id(),
                    integralAlterInfo.getAlterValue(),
                    integralAlterInfo.getAlterReason(),
                    IntegralDateUtils.getStartDateStrFromDate(integralAlterInfo.getRecordTime()),
                    integralAlterInfo.getRecordTime(), "TESTER");
            System.out.println(integralAlterInfo);
        });

        System.out.println("end...");

        return new Result(true, StatusCode.OK, "文件上传成功");
    }

}
