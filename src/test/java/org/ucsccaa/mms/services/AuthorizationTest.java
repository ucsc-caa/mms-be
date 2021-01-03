package org.ucsccaa.mms.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.ucsccaa.mms.domains.Authorization;
import org.ucsccaa.mms.repositories.AuthorizeRepository;
import org.ucsccaa.mms.services.Implements.AuthorServiceImpl;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class AuthorizationTest {
    @Mock
    private AuthorizeRepository authorizeRepository;
    @InjectMocks
    private AuthorServiceImpl authorService;

    private final Authorization.LEVEL level = Authorization.LEVEL.LEVEL_1;
    private final ArrayList<Authorization.Authorities> authorityList = new ArrayList<Authorization.Authorities>() {
        {
            add(Authorization.Authorities.READ_STAFF_DEPT);
            add(Authorization.Authorities.READ_STAFF_POSITION);
        }
    };
    private Authorization expectedAuthor = new Authorization(1L, level, authorityList);
    private final String stringLevel = level.toString();
    private final String[] checkURI = {"GET", "STAFF", "EMAIL"};
    private final Boolean expectedResult = true;

    @Before
    public void configuration() {
        Mockito.when(authorizeRepository.findByLevel(Mockito.eq(level))).thenReturn(expectedAuthor);
    }


    @Test
    public void testCheckAuthority() {
        Boolean actualResult = authorService.checkAuthority(stringLevel, checkURI);
        Assert.assertEquals(expectedResult, actualResult);
    }
    @Test
    public void testGetByLevel() {
        Authorization actualAuthor = authorService.getByLevel(level);
        Assert.assertEquals(expectedAuthor, actualAuthor);
        Assert.assertEquals(expectedAuthor.getAuthorityList(), actualAuthor.getAuthorityList());
    }
    @Test(expected = RuntimeException.class)
    public void testGetByLevel_invalidArgument() {
        authorService.getByLevel(null);
    }
}
