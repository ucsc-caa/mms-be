package org.ucsccaa.mms.services;


import org.ucsccaa.mms.domains.Authorization;

public interface AuthorService {
    Boolean checkAuthority(String level, String method, String uri);
}
