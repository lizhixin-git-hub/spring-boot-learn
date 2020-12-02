package com.dynamic.data.source.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dynamic.data.source.entity.Good;

import java.util.List;

public interface IGoodService extends IService<Good> {

    List<Good> list();

}
