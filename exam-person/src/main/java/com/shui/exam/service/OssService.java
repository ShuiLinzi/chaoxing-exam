package com.shui.exam.service;

import java.io.InputStream;

public interface OssService {
     String upload(InputStream inputStream, String module, String filename);
}
