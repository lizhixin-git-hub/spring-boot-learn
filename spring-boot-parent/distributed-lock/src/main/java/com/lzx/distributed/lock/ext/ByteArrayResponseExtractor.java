package com.lzx.distributed.lock.ext;

import com.lzx.distributed.lock.support.DownloadProgressPrinter;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 简单的文件下载器
 * 这里使用的是restTemplate调用execute, 先文件获取到字节数组, 再将字节数组直接写到目标文件。
 * 这里我们需要注意的点是: 这种方式会将文件的字节数组全部放入内存中, 及其消耗资源；我们来看看如何实现
 */
public class ByteArrayResponseExtractor extends AbstractDownloadProgressMonitorResponseExtractor<byte[]> {
    private long byteCount; //保存已经下载的字节数

    public ByteArrayResponseExtractor() {
    }

    public ByteArrayResponseExtractor(DownloadProgressPrinter downloadProgressPrinter) {
        super(downloadProgressPrinter);
    }

    @Override
    protected byte[] doExtractData(ClientHttpResponse response) throws IOException {
        long contentLength = response.getHeaders().getContentLength();
        ByteArrayOutputStream out =
                new ByteArrayOutputStream(contentLength >= 0 ? (int) contentLength : StreamUtils.BUFFER_SIZE);
        InputStream in = response.getBody();
        int bytesRead;
        //循环读取数据到字节数组中，记录以及下载的字节数
        for (byte[] buffer = new byte[4096]; (bytesRead = in.read(buffer)) != -1; byteCount += bytesRead) {
            out.write(buffer, 0, bytesRead);
        }
        out.flush();
        return out.toByteArray();
    }

    //返回已经下载的字节数
    @Override
    public long getAlreadyDownloadLength() {
        return byteCount;
    }

}

