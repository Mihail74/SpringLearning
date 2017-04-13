package ru.kichenko.test;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WebApplicationControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserDetailsService userDetailsService;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    public void testPostWithCsrfWorks() throws Exception {
        mvc.perform(post("/").with(csrf())).andExpect(status().isFound());
    }

    @Test
    public void testPostWithNoCsrfForbidden() throws Exception {
        mvc.perform(post("/")).andExpect(status().isForbidden());
    }

    @Test
    public void tesRrequiresAuthentication() throws Exception {
        mvc.perform(get("/")).andExpect(status().isFound());
    }

    @Test
    public void testAuthenticationFailed() throws Exception {
        mvc.perform(formLogin().user("user").password("invalid")).andExpect(status().isFound())
                .andExpect(redirectedUrl("/login?error")).andExpect(unauthenticated());
    }

    @Test
    public void testAuthenticationSuccess() throws Exception {
        mvc.perform(formLogin().user("kichenko").password("qwerty")).andExpect(status().isFound())
                .andExpect(redirectedUrl("/")).andExpect(authenticated().withUsername("kichenko"));
    }

    @Test
    public void testRequestProtectedUrlWithAdmin() throws Exception {
        UserDetails userDetails = userDetailsService.loadUserByUsername("kichenko");
        mvc.perform(get("/profile/edit").with(user(userDetails))).andExpect(status().isOk())
                .andExpect(authenticated().withAuthenticationPrincipal(userDetails));
    }
}
