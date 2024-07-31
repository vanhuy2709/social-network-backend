package com.application.social_platform.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MetaResponse {
    int current;
    int pageSize;
    long pages;
    int total;
}
