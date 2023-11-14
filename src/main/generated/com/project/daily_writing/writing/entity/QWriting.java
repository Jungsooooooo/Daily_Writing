package com.project.daily_writing.writing.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWriting is a Querydsl query type for Writing
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWriting extends EntityPathBase<Writing> {

    private static final long serialVersionUID = -600484291L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWriting writing = new QWriting("writing");

    public final com.project.daily_writing.common.entity.QCommonUUID _super = new com.project.daily_writing.common.entity.QCommonUUID(this);

    public final StringPath context = createString("context");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath title = createString("title");

    public final com.project.daily_writing.types.QTypes types;

    public QWriting(String variable) {
        this(Writing.class, forVariable(variable), INITS);
    }

    public QWriting(Path<? extends Writing> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWriting(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWriting(PathMetadata metadata, PathInits inits) {
        this(Writing.class, metadata, inits);
    }

    public QWriting(Class<? extends Writing> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.types = inits.isInitialized("types") ? new com.project.daily_writing.types.QTypes(forProperty("types")) : null;
    }

}

