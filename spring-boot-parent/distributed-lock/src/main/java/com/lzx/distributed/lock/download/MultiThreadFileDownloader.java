package com.lzx.distributed.lock.download;

import com.lzx.distributed.lock.ext.FileResponseExtractor;
import com.lzx.distributed.lock.support.DownloadProgressPrinter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RequestCallback;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多线程文件下载
 * 如果服务器不限速的话，通常能够把自己本地的带宽给跑满，那么使用单线程下载就够了，但是如果遇到服务器限速，下载速度远小于自己本地的带宽，那么可以考虑使用多线程下载。多线程我们使用CompletableFuture（可以参考文章 CompletableFuture让你的代码免受阻塞之苦）。
 *
 * 实现多线程文件下载的基本流程：
 *
 * 1、首先我们通过Http协议的Head方法获取到文件的总大小
 * 2、然后根据设置的线程数均分文件的大小，计算每个线程的下载的字节数据开始位置和结束位置
 * 3、开启线程，设置HTTP请求头Range信息，开始下载数据到临时文件
 * 4、下载完成后把每个线程下载完成的临时文件合并成一个文件
 */
public class MultiThreadFileDownloader extends AbstractDownloader {
    private int threadNum;

    public MultiThreadFileDownloader(int threadNum, DownloadProgressPrinter downloadProgressPrinter) {
        super(downloadProgressPrinter);
        this.threadNum = threadNum;
    }

    public MultiThreadFileDownloader(int threadNum) {
        super(DownloadProgressPrinter.defaultDownloadProgressPrinter());
        this.threadNum = threadNum;
    }

    @Override
    protected void doDownload(String fileURL, String dir, String fileName, HttpHeaders headers) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        long contentLength = headers.getContentLength();
        downloadProgressPrinter.setContentLength(contentLength);

        //均分文件的大小
        long step = contentLength / threadNum;

        List<CompletableFuture<File>> futures = new ArrayList<>();
        for (int index = 0; index < threadNum; index++) {
            //计算出每个线程的下载开始位置和结束位置
            String start = step * index + "";
            String end = index == threadNum - 1 ? "" : (step * (index + 1) - 1) + "";

            String tempFilePath = dir + File.separator + "." + fileName + ".download." + index;
            FileResponseExtractor extractor = new FileResponseExtractor(index, tempFilePath, downloadProgressPrinter);

            CompletableFuture<File> future = CompletableFuture.supplyAsync(() -> {
                RequestCallback callback = request -> {
                    //设置HTTP请求头Range信息，开始下载到临时文件
                    request.getHeaders().add(HttpHeaders.RANGE, "bytes=" + start + "-" + end);
                };
                return restTemplate.execute(fileURL, HttpMethod.GET, callback, extractor);
            }, executorService).exceptionally(e -> {
                e.printStackTrace();
                return null;
            });
            futures.add(future);
        }

        //创建最终文件
        String tmpFilePath = dir + File.separator + fileName + ".download";
        File file = new File(tmpFilePath);
        FileChannel outChannel = new FileOutputStream(file).getChannel();

        futures.forEach(future -> {
            try {
                File tmpFile = future.get();
                FileChannel tmpIn = new FileInputStream(tmpFile).getChannel();
                //合并每个临时文件
                outChannel.transferFrom(tmpIn, outChannel.size(), tmpIn.size());
                tmpIn.close();
                tmpFile.delete(); //合并完成后删除临时文件
            } catch (InterruptedException | ExecutionException | IOException e) {
                e.printStackTrace();
            }
        });
        outChannel.close();
        executorService.shutdown();

        file.renameTo(new File(dir + File.separator + fileName));
    }

}
