package com.tech.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {
    String userId;
    String firstName;
    String lastName;
    int[] accountNumbers;
}
