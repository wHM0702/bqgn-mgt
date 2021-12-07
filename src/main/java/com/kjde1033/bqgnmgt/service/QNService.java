package com.kjde1033.bqgnmgt.service;

import java.io.File;
import java.io.InputStream;
import java.util.Map;


public interface QNService {

    Map uploadFile(File file) throws Exception;

    Map uploadInput(InputStream inputStream) throws Exception;

}
