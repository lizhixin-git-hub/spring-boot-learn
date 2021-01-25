package com.lzx.distributed.lock.download;

import com.lzx.distributed.lock.ext.ByteArrayResponseExtractor;
import com.lzx.distributed.lock.support.DownloadProgressPrinter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * 简单的文件下载器
 * 这里使用的是restTemplate调用execute, 先文件获取到字节数组, 再将字节数组直接写到目标文件。
 * 这里我们需要注意的点是: 这种方式会将文件的字节数组全部放入内存中, 及其消耗资源；我们来看看如何实现
 */
public class SimpleDownloader extends AbstractDownloader {

    public SimpleDownloader(DownloadProgressPrinter downloadProgressPrinter) {
        super(downloadProgressPrinter);
    }

    public SimpleDownloader() {
        super(DownloadProgressPrinter.defaultDownloadProgressPrinter());
    }

    @Override
    protected void doDownload(String fileURL, String dir, String fileName, HttpHeaders headers) throws IOException {
        String filePath = dir + File.separator + fileName;
        byte[] body = restTemplate.execute(fileURL, HttpMethod.GET, null,
                new ByteArrayResponseExtractor(downloadProgressPrinter));
        Files.write(Paths.get(filePath), Objects.requireNonNull(body));
    }

}

