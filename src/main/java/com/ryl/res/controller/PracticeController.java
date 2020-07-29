package com.ryl.res.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ryl.ea.meeting.entity.ReJuan;
import com.ryl.ea.meeting.service.TestService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: ryl
 * @description:
 * @date: 2019-07-25 10:59:06
 */
@RestController
public class PracticeController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TestService testService;


    /**
     * POST请求存放参数的Map必须是MultiValueMap,Map和HashMap都不行,被调用方参数接收会是null
     * GET请求存放参数的Map是HashMap,
     * 如果使用MultiValueMap,参数会加上[],比如username传ryl,被调用方接收到的参数为[ryl];
     * 注意POST和GET的参数顺序
     */

    @PostMapping("/import")
    public void importExcel() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook("上海有色.xlsx");
        XSSFSheet sheet = workbook.getSheetAt(0);
        List<ReJuan> reJuanList = new ArrayList<>();
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i+1);
            if (row != null) {
                ReJuan reJuan = new ReJuan();
                //名称
                String name = getCellValue(row,0);
                //材质
                String material = getCellValue(row,1);
                //规格
                String spec = getCellValue(row,2);
                //产地
                String place = getCellValue(row,3);
                //价格
                String _price = getCellValue(row,4);
                BigDecimal price = new BigDecimal(_price);
                //涨跌
                String _trend = getCellValue(row,5);
                Integer trend = Integer.valueOf(_trend);
                //日期
                String _date = getCellValue(row,6);
                LocalDate date = LocalDate.parse(_date);

                reJuan.setName(name);
                reJuan.setMaterial(material);
                reJuan.setSpec(spec);
                reJuan.setPlace(place);
                reJuan.setPrice(price);
                reJuan.setTrend(trend);
                reJuan.setDate(date);
                reJuanList.add(reJuan);
            }
        }
        testService.insertEmployee(reJuanList);
    }

    private String getCellValue(Row row,int columnIndex) {
        Cell cell = row.getCell(columnIndex);
        if (cell != null) {
            return cell.getStringCellValue();
        }
        return "";
    }

    @GetMapping("/login")
    public String login(){
        String str = postForObjectWithHeader1();
        JSONObject jsonObject = JSON.parseObject(str);
        JSONArray arr = (JSONArray) jsonObject.get("data");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("组织机构");
        String[] header = {"姓名","工号","性别","岗位名称","邮箱","手机","电话"};
        XSSFRow row0 = sheet.createRow(0);
        for (int i = 0; i< header.length; i++) {
            row0.createCell(i).setCellValue(header[i]);
        }
        int j =1;
        for (Object o : arr) {
            String employeenamecn = ((String) ((JSONObject) o).get("employeenamecn"));
            String employeecode = ((String) ((JSONObject) o).get("employeecode"));
            String sexLable = ((String) ((JSONObject) o).get("sexLable"));
            String stationLable = ((String) ((JSONObject) o).get("stationLable"));
            String email = ((String) ((JSONObject) o).get("email"));
            String mobile = ((String) ((JSONObject) o).get("mobile"));
            String telephone = ((String) ((JSONObject) o).get("telephone"));

            XSSFRow row = sheet.createRow(j);
            row.createCell(0).setCellValue(employeenamecn);
            row.createCell(1).setCellValue(employeecode);
            row.createCell(2).setCellValue(sexLable);
            row.createCell(3).setCellValue(stationLable);
            row.createCell(4).setCellValue(email);
            row.createCell(5).setCellValue(mobile);
            row.createCell(6).setCellValue(telephone);

            j++;
        }
        try {
            FileOutputStream out = new FileOutputStream("组织机构.xlsx");
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

    /**
     ***************************************GET开始*********************************************
     */
    private String getForObject(){
        Map<String,String> params = new HashMap<>();
        params.put("username","ryl");
        params.put("password","123456");
        //GET请求必须要把参数拼在url上并使用占位符
        String url = "http://127.0.0.1:8088/user/login?username={username}&password={password}";
        String urls = restTemplate.getForObject(url, String.class, params);
        return urls;
    }

    private String getForEntityWithHeader(){
        //header
        HttpHeaders headerParam = new HttpHeaders();
        headerParam.add("authorization","Bearer safaff");
        //参数
        Map<String,String> params = new HashMap<>();
        params.put("username","ryl");
        params.put("password","123456");

        HttpEntity request = new HttpEntity(params,headerParam);
        //GET请求必须要把参数拼在url上并使用占位符
        String url = "http://127.0.0.1:8088/user/login?username={username}&password={password}";
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, String.class, params);
        HttpHeaders headerRes = responseEntity.getHeaders();
        String urls = responseEntity.getBody();
        return urls;
    }

    private String getForEntity(){
        Map<String,String> params = new HashMap<>();
        params.put("username","ryl");
        params.put("password","123456");
        //GET请求必须要把参数拼在url上并使用占位符
//        String url = "http://127.0.0.1:8088/user/login?username={username}&password={password}";
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, params);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://127.0.0.1:8088/user/login")
                .queryParam("username",  "ryl")
                .queryParam("password",  "123456");
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(builder.toUriString(), String.class);
        HttpHeaders headers = responseEntity.getHeaders();
        String urls = responseEntity.getBody();
        return urls;
    }


    /**
     ***************************************POST开始*********************************************
     */
    private String postForObject(){
        //参数
        MultiValueMap params = new LinkedMultiValueMap();
        params.add("username","ryl");
        params.add("password","123456");
        String url = "http://127.0.0.1:8088/user/login";
        //如果不传header第二个参数可以直接放MultiValueMap
        String urls = restTemplate.postForObject(url,params, String.class);
        return urls;
    }

    private String postForObjectWithHeader(){
        //header
        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization","Bearer safaff");
        //参数
        MultiValueMap params = new LinkedMultiValueMap();
        params.add("username","ryl");
        params.add("password","123456");
        //url
        String url = "http://127.0.0.1:8088/user/login";
        HttpEntity request = new HttpEntity(params,headers);
        String urls = restTemplate.postForObject(url, request, String.class);
        return urls;
    }

    private String postForObjectWithHeader1(){
        //header
        HttpHeaders headers = new HttpHeaders();
        String token = "ea:user:tk:eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMDIwLTA1LTA5IiwiaWF0IjoxNTg5MDA0ODM4LCJzdWIiOiJ7XCJpZFwiOjI3ODQyNyxcIm5hbWVcIjpcIuS7u-awuOS6rlwiLFwicmVudElkXCI6MixcInVzZXJuYW1lXCI6XCJ5b25nbGlhbmcucmVuXCJ9IiwiZXhwIjoxNTg5MDMzNjM4fQ.Paop-FXeH7nodQxkUvY4LosqpjcTulruaLD8WwjE3OI";
        headers.add("token",token);
        headers.add("version","1.0.0");
        //参数
        MultiValueMap params = new LinkedMultiValueMap();
        params.add("nodeno",59537);
        params.add("pageNo",1);
        params.add("pageSize",265);
        //url
        String url = "http://ehr.eascs.com/department/list.json";
        HttpEntity request = new HttpEntity(params,headers);
        String urls = restTemplate.postForObject(url, request, String.class);
        return urls;
    }

    private String postForEntity(){
        //参数
        MultiValueMap params = new LinkedMultiValueMap();
        params.add("username","ryl");
        params.add("password","123456");
        String url = "http://127.0.0.1:8088/user/login";
        //如果不传header第二个参数可以直接放MultiValueMap
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, params, String.class);
        //postForEntity返回的结果包含了头部信息
        HttpHeaders headers = responseEntity.getHeaders();
        String urls = responseEntity.getBody();
        return urls;
    }

    private String postForEntityWithHeader(){
        //header
        HttpHeaders headerParams = new HttpHeaders();
        headerParams.add("authorization","Bearer safaff");
        //参数
        MultiValueMap params = new LinkedMultiValueMap();
        params.add("username","ryl");
        params.add("password","123456");
        //url
        String url = "http://127.0.0.1:8088/user/login";
        HttpEntity request = new HttpEntity(params,headerParams);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
        //postForEntity返回的结果包含了头部信息
        HttpHeaders headerRes = responseEntity.getHeaders();
        String urls = responseEntity.getBody();
        return urls;
    }


}
