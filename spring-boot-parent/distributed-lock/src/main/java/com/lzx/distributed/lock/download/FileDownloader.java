package com.lzx.distributed.lock.download;

import com.lzx.distributed.lock.ext.FileResponseExtractor;
import com.lzx.distributed.lock.support.DownloadProgressPrinter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.io.File;
import java.io.IOException;

/**
 * 单线程大文件下载
 * 上面的方式只能下载小的文件，那大文件的下载我们该用什么方式呢？我们可以把流输出到文件而不是内存中。接下来我们来实现我们大文件的下载。
 */
public class FileDownloader extends AbstractDownloader {

    public FileDownloader(DownloadProgressPrinter downloadProgressPrinter) {
        super(downloadProgressPrinter);
    }

    public FileDownloader() {
        super(DownloadProgressPrinter.defaultDownloadProgressPrinter());
    }

    @Override
    protected void doDownload(String fileURL, String dir, String fileName, HttpHeaders headers) throws IOException {
        String filePath = dir + File.separator + fileName;
        FileResponseExtractor extractor = new FileResponseExtractor(filePath + ".download", downloadProgressPrinter); //创建临时下载文件
        File tmpFile = restTemplate.execute(fileURL, HttpMethod.GET, null, extractor);
        assert tmpFile != null;
        tmpFile.renameTo(new File(filePath)); //修改临时下载文件名称
    }

}

