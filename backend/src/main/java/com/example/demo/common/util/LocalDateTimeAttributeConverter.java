package com.example.demo.common.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;

//AttributeConverter 인터페이스로 DB 어트리뷰트를 변환할 수 있다.
//첫 번째 타입 파라미터는 자바쪽의 프로퍼티 타입을, 두 번째 타입 파라미터는 DB의 컬럼 타입을 의미한다.

//원래는 도메인의 특정 컬럼에
//@Convert 애노테이션을 사용해서 값을 변환대상 프로퍼티에 컨버터를 지정해주면 된다.
//하지만 @Converter(autoApply = true)를 하면 해당 속성은 무조건 이 컨버팅이 되어서 DB로 들어간다.

public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp>{
   @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime locDateTime) {
        return (locDateTime == null ? null : Timestamp.valueOf(locDateTime));
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp sqlTimestamp) {
        return (sqlTimestamp == null ? null : sqlTimestamp.toLocalDateTime());
    }
}