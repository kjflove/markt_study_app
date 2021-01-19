package markt_study.configuration;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("direction")) {
            httpServletResponse.sendRedirect("direction/home_direction");
        } else  if (roles.contains("supervisor")){
            httpServletResponse.sendRedirect("supervision/home_supervision");
        } else  if (roles.contains("management")){
            httpServletResponse.sendRedirect("management/home_management");
        }
	     else  if (roles.contains("admin")){
	        httpServletResponse.sendRedirect("all/home_admin");
	    }
    }
}
