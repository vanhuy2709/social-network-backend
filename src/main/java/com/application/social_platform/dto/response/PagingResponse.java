package com.application.social_platform.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PACKAGE)
public class PagingResponse<T> {
    MetaResponse meta;
    T data;
}
