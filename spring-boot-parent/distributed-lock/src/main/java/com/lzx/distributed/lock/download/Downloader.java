package com.lzx.distributed.lock.download;

import java.io.IOException;

public interface Downloader {

    void download(String fileURL, String dir) throws IOException;

}
