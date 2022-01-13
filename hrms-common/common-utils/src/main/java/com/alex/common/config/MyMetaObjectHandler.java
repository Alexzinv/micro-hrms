package com.alex.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * @Author _Alexzinv_
 * @Date 2021/9/23
 * @Description mybatis在插入和更新时自动填充时间
 *
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
	@Override
	public void insertFill(MetaObject metaObject) {
		this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
		this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
	}
}
