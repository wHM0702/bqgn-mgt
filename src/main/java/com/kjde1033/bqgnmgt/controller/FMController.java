package com.kjde1033.bqgnmgt.controller;

import com.kjde1033.bqgnmgt.dao.UserDao;
import com.kjde1033.bqgnmgt.dao.ZiyuanDao;
import com.kjde1033.bqgnmgt.model.entity.User;
import com.kjde1033.bqgnmgt.model.entity.UserDemo;
import com.kjde1033.bqgnmgt.model.entity.Ziyuan;
import com.kjde1033.bqgnmgt.service.QNService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/*
1. Controller, RestController的共同点

都是用来表示Spring某个类的是否可以接收HTTP请求

2.  Controller, RestController的不同点

@Controller标识一个Spring类是Spring MVC controller处理器

@RestController：  a convenience annotation that does nothing more than adding the@Controller and@ResponseBody annotations。
 @RestController是@Controller和@ResponseBody的结合体，两个标注合并起来的作用。

3、如果只是使用@RestController注解Controller，则Controller中的方法无法返回jsp页面，配置的视图解析器InternalResourceViewResolver不起作用，返回的内容就是Return 里的内容。

例如：本来应该到success.jsp页面的，则其显示success.

4、如果需要返回到指定页面，则需要用 @Controller配合视图解析器InternalResourceViewResolver才行。

5、如果需要返回JSON，XML或自定义mediaType内容到页面，则需要在对应的方法上加上@ResponseBody注解。
 */
@Controller
public class FMController {

    @Resource
    private ZiyuanDao ziyuanDao;
    @Resource
    private UserDao userDao;
    @Resource
    private QNService qnService;
    @Autowired
     private UserDemo userDemo;

    @Value("${qiniu.path}")
    private String qnPath;


    @RequestMapping("/")
    public String index(){
        userDemo.m1();
        return "index";
    }

    @RequestMapping("/upload")
    public String upload(int uid, @RequestParam("tx")MultipartFile tx, HttpServletRequest request){
        //判断图片是否为空
        if(tx.isEmpty()){
            request.setAttribute("info","你没传图片");
            return "index";
        }
        //file.getOriginalFilename()是得到上传时的文件名。
        String fileName=tx.getOriginalFilename();
        //重命名
        fileName=uid+fileName;
        //获得图片大小
        int size = (int)tx.getSize();
        System.out.println(fileName+" "+size);

        //指定路径
        String path="E:/IdeaProjects/KJDE1033/bqgn-mgt-resoure/pic";
        //创建文件流，文件的存储路径和文件名
        File dest = new File(path+"/"+fileName);

        //如果文件不存在，则创建，exists:存在
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        //数据转移
        /*
            在文件上传的时候，MultipartFile中的transferTo(dest)方法只能使用一次
            并且使用transferTo方法之后不可以在使用getInputStream()方法
            否则会报错，IllegalStateException
            使用transferTo(dest)方法将上传文件写到服务器上指定的文件
            原因文件流只可以接收读取一次，传输完毕后则关闭流
         */
        try {
            tx.transferTo(dest);
            request.setAttribute("info","上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("info","上传失败");
        }

        return "index";
    }

    @RequestMapping("/mutiUpload")
    public String mutiUpload(HttpServletRequest request){
        //转换成request，解析出request中的文件
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("kc");
        if(files.isEmpty()){
            request.setAttribute("info","你什么资料都没传");
            return "index";
        }
        String path = "E:/IdeaProjects/KJDE1033/bqgn-mgt-resoure/file";
        int times = 0;

        for (MultipartFile file : files){
           //判断传了几个文件
            if(file.isEmpty()){
                continue;
            }
            String pathName = path+"/"+file.getOriginalFilename();
            File dest = new File(pathName);
            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdir();
            }
            try {
                file.transferTo(dest);
                ziyuanDao.addZiyuan(file.getOriginalFilename(),pathName);
                times++;
            } catch (IOException e) {
                e.printStackTrace();
                request.setAttribute("info","上传故障，已经上传"+times+"个文件");
                return "index";
            }
        }
        request.setAttribute("info","上传成功，共"+times+"个文件");
        return "index";
    }

    @RequestMapping("/initZiyuan")
    public String initZiyuan(HttpServletRequest request){
        List<Ziyuan> ziyuanList  = ziyuanDao.getAllZiyuan();
        request.setAttribute("ziyuanList",ziyuanList);
        return "ziyuan";
    }

    @RequestMapping("/dload")
    public String dload(HttpServletResponse response,String pathName,String rname) throws IOException {

        File file = new File(pathName);

        if(file.exists()){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/*;charset=UTF-8");
            response.setHeader("Content-Disposition","attachment;fileName="+ URLEncoder.encode(rname,"UTF-8"));

            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            OutputStream os = null;

            os = response.getOutputStream();
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            int i = bis.read(buffer);
            while (i>0){
                os.write(buffer);
                i = bis.read(buffer);
            }
            bis.close();
            fis.close();
            return "开始下载";

        }else{
            return "下载地址失效";
        }
    }

    @RequestMapping("/log")
    public String log(String phone,String pwd,HttpServletRequest request){
       User user= userDao.getUser(phone,pwd);
       request.setAttribute("user",user);
       if(user.getType()==1){
            return "leader";
       }else if(user.getType()==4){
           return "master";
       }else {
           return "teacher";
       }
    }

    @RequestMapping("/uploadQN")
    public String uploadQN(MultipartFile mufile,HttpServletRequest request) throws Exception{
        String path = "C:/Users/Administrator/Desktop/file/";
        File file = new File(path+mufile.getOriginalFilename());
        mufile.transferTo(file);
        Map map = qnService.uploadFile(file);
        String fname = (String) map.get("fileName");
        String link = "http://"+qnPath+"/"+fname;
        request.setAttribute("link",link);
        return "qiniu";
    }

    @RequestMapping("/uploadQN2")
    public String uploadQN2(MultipartFile mufile,HttpServletRequest request) throws Exception{
        byte[] bytes = mufile.getBytes();
        InputStream inputStream = new ByteArrayInputStream(bytes);
        Map map = qnService.uploadInput(inputStream);
        String fname = (String) map.get("fileName");
        String link = "http://"+qnPath+"/"+fname;
        request.setAttribute("link",link);
        return "qiniu";
    }

}
