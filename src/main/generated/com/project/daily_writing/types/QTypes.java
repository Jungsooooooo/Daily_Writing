package com.project.daily_writing.types;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTypes is a Querydsl query type for Types
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTypes extends EntityPathBase<Types> {

    private static final long serialVersionUID = 1427215786L;

    public static final QTypes types = new QTypes("types");

    public final com.project.daily_writing.common.entity.QCommonUUID _super = new com.project.daily_writing.common.entity.QCommonUUID(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public QTypes(String variable) {
        super(Types.class, forVariable(variable));
    }

    public QTypes(Path<? extends Types> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTypes(PathMetadata metadata) {
        super(Types.class, metadata);
    }

}

