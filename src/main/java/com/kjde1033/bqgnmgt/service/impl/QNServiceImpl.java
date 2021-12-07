package com.kjde1033.bqgnmgt.service.impl;

import com.google.gson.Gson;
import com.kjde1033.bqgnmgt.service.QNService;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class QNServiceImpl implements QNService {

    @Autowired
    private UploadManager uploadManager;

    @Autowired
    private Auth auth;

    @Autowired
    private Gson gson;

    @Value("${qiniu.bucket}")
    private String bucket;

    @Override
    public Map uploadFile(File file) throws Exception {

        Map map = new HashMap();
        Response response = this.uploadManager.put(file,null,this.auth.uploadToken(bucket));
        DefaultPutRet putRet = this.gson.fromJson(response.bodyString(),DefaultPutRet.class);
        String fileName = putRet.hash;
        map.put("response",response);
        map.put("fileName",fileName);
        return map;
    }

    @Override
    public Map uploadInput(InputStream inputStream) throws Exception {
        Map map = new HashMap();
        Response response = this.uploadManager.put(inputStream,null,this.auth.uploadToken(bucket),null,null);
        DefaultPutRet putRet = this.gson.fromJson(response.bodyString(),DefaultPutRet.class);
        String fileName = putRet.hash;
        map.put("response",response);
        map.put("fileName",fileName);
        return map;
    }
}
