/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pipecode.topshop.configuration;

//import java.util.HashMap;
import com.google.gson.Gson;
//import com.pipecode.topshop.configuration.domain.Acceso;
//import com.pipecode.topshop.configuration.domain.UsuarioDTO;
import com.pipecode.topshop.configuration.service.UsuarioService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author deadlock
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final String ORIGIN = "Origin";
    private static final String AC_REQUEST_METHOD = "Access-Control-Request-Method";
    private static final String AC_REQUEST_HEADERS = "Access-Control-Request-Headers";

    private static final String AC_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    private static final String AC_ALLOW_METHODS = "Access-Control-Allow-Methods";
    private static final String AC_ALLOW_HEADERS = "Access-Control-Allow-Headers";

    private CorsData corsData;

    private String origin;
    private String allowMethods;
    private String allowHeaders;

    @Autowired
    private UsuarioService usuarioServiceImpl;

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setAllowMethods(String allowMethods) {
        this.allowMethods = allowMethods;
    }

    public void setAllowHeaders(String allowHeaders) {
        this.allowHeaders = allowHeaders;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        this.corsData = new CorsData(request);

        if (this.corsData.isPreflighted()) {
            response.setHeader(AC_ALLOW_ORIGIN, origin);
            response.setHeader(AC_ALLOW_METHODS, allowMethods);
            response.setHeader(AC_ALLOW_HEADERS, allowHeaders);

            return false;
        }
//        boolean salida = false;
        boolean salida = true;
        String token = request.getHeader("Authorization");
        String ipAcceso = request.getRemoteAddr();
        // En caso de querer activar la seguridad  basica solo controla si el token esta vencido 
        if ((token != null && !token.equals("") )) {
            try {
                String tokenAux = token.replace("Bearer", "");
                Claims claims = Jwts.parser().setSigningKey("clave_Propia").parseClaimsJws(tokenAux).getBody();
                String jsonString = new Gson().toJson(claims.get("usuario"), Integer.class);
////                Gson gson = new Gson();
////                UsuarioDTO usuario = gson.fromJson(jsonString, UsuarioDTO.class);
                request.setAttribute("customAttribute", jsonString.toString());
//                salida = true;
            } catch (Exception ex) {
//                if (ex.getMessage().contains("expire")) {
//                } else {
//                }
            }
        }
        if (!salida) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        return salida;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (this.corsData.isSimple()) {
            response.setHeader(AC_ALLOW_ORIGIN, origin);
        }
    }

    class CorsData {

        private String origin;
        private String requestMethods;
        private String requestHeaders;

        CorsData(HttpServletRequest request) {
            this.origin = request.getHeader(ORIGIN);
            this.requestMethods = request.getHeader(AC_REQUEST_METHOD);
            this.requestHeaders = request.getHeader(AC_REQUEST_HEADERS);
        }

        public boolean hasOrigin() {
            return origin != null && !origin.isEmpty();
        }

        public boolean hasRequestMethods() {
            return requestMethods != null && !requestMethods.isEmpty();
        }

        public boolean hasRequestHeaders() {
            return requestHeaders != null && !requestHeaders.isEmpty();
        }

        public String getOrigin() {
            return origin;
        }

        public String getRequestMethods() {
            return requestMethods;
        }

        public String getRequestHeaders() {
            return requestHeaders;
        }

        public boolean isPreflighted() {
            return hasOrigin() && hasRequestHeaders() && hasRequestMethods();
        }

        public boolean isSimple() {
            return hasOrigin() && !hasRequestHeaders();
        }
    }
}
