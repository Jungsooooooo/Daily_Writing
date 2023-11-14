package com.project.daily_writing.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommonUUID is a Querydsl query type for CommonUUID
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommonUUID extends EntityPathBase<CommonUUID> {

    private static final long serialVersionUID = -1519879000L;

    public static final QCommonUUID commonUUID = new QCommonUUID("commonUUID");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QCommonUUID(String variable) {
        super(CommonUUID.class, forVariable(variable));
    }

    public QCommonUUID(Path<? extends CommonUUID> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommonUUID(PathMetadata metadata) {
        super(CommonUUID.class, metadata);
    }

}

