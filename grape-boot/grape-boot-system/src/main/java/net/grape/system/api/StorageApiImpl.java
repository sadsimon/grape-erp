package net.grape.system.api;

import lombok.AllArgsConstructor;
import net.grape.api.module.system.StorageApi;
import net.grape.storage.service.StorageService;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * 存储服务Api
 *
 * @author 1161095164@qq.com
 *  
 */
@Component
@AllArgsConstructor
public class StorageApiImpl implements StorageApi {
    private final StorageService storageService;
    
    @Override
    public String getNewFileName(String fileName) {
        return storageService.getNewFileName(fileName);
    }

    @Override
    public String getPath() {
        return storageService.getPath();
    }

    @Override
    public String getPath(String fileName) {
        return storageService.getPath(fileName);
    }

    @Override
    public String upload(byte[] data, String path) {
        return storageService.upload(data, path);
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        return storageService.upload(inputStream, path);
    }
}
