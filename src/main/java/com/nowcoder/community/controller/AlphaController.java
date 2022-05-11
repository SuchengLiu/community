package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired//注入service，即controller依赖于service
    private AlphaService alphaService;

    @RequestMapping("/hello")//注解请求路径
    @ResponseBody
    public String sayHello() {
        return "Hello Spring Boot.";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData() {
        return alphaService.find();
    }

    //获取请求的底层方法
    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) {//通过response对象可以向浏览器返回任何值，不需要设置函数返回值
        // 获取请求数据
        System.out.println(request.getMethod());//获取请求方法
        System.out.println(request.getServletPath());//获取请求路径
        Enumeration<String> enumeration = request.getHeaderNames();//获取所有请求头的所有key
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();//请求头的key
            String value = request.getHeader(name);//请求头的value
            System.out.println(name + ": " + value);
        }
        System.out.println(request.getParameter("code"));//获取请求体中的参数

        // 返回响应数据
        response.setContentType("text/html;charset=utf-8");//设置返回值类型
        try (
                PrintWriter writer = response.getWriter();//获取输出流；写在try之后的()里可以自动关闭writer，不用写finally
        ) {
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // GET请求，日常使用的方法

    // /students?current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            //通过@RequestParam注解将请求对象中的参数绑定到控制器方法中参数，并可以做更详细的说明
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    // /student/123
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)//通过id查找学生
    @ResponseBody
    public String getStudent(@PathVariable("id") int id) {//通过注解@PathVariable将请求路径的参数id绑定到控制器的参数id
        System.out.println(id);
        return "a student";
    }

    // POST请求，常用于浏览器向服务器提交请求数据
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age) {//方法中的形参与请求参数的名字相同就可以直接获取参数值
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    // 响应HTML数据

    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    //不加@ResponseBody注解，默认返回html
    public ModelAndView getTeacher() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "张三");
        mav.addObject("age", 30);
        mav.setViewName("/demo/view");
        return mav;
    }

    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model) {//返回类型String表示view的模板路径，形参类型声明为Model，dispatcherservlet回自动传入model对象
        model.addAttribute("name", "北京大学");
        model.addAttribute("age", 80);
        return "/demo/view";
    }

    // 响应JSON数据(异步请求)
    // Java对象 -> JSON字符串 -> JS对象

    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody//加上该注解才能返回字符串，否则返回html
    public Map<String, Object> getEmp() {//dispatcherservlet检测到Map类型，会将Map对象自动转化为jason字符串
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 23);
        emp.put("salary", 8000.00);
        return emp;
    }

    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps() {//用List包装Map对象，返回一组对象并序列化
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 23);
        emp.put("salary", 8000.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "李四");
        emp.put("age", 24);
        emp.put("salary", 9000.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "王五");
        emp.put("age", 25);
        emp.put("salary", 10000.00);
        list.add(emp);

        return list;
    }
}
