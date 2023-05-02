package com.example.cumulusspringboot.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class assignCertifToUserReq {


        private long numUser;
        private long numCertif;
    }

