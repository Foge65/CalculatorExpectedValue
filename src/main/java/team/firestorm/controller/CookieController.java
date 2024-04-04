package team.firestorm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import team.firestorm.service.UUIDService;

import java.util.Map;
import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping("/cookie")
public class CookieController {
    private static final String USER_KEY = "user";
    private UUIDService uuidService;

    @GetMapping("/setCookie")
    public ResponseEntity<?> setCookie(HttpServletRequest request, HttpServletResponse response) {
        String sessionId = request.getSession().getId();
        String uuid = uuidService.getUUID(sessionId);
        Cookie cookie;
        if (uuid != null && uuidService.isValidUUID(uuid)) {
            cookie = new Cookie(USER_KEY, uuid);
        } else {
            String newUUID = UUID.randomUUID().toString();
            uuidService.addUUID(sessionId, newUUID);
            cookie = new Cookie(USER_KEY, newUUID);
        }
        cookie.setPath("/");
        cookie.setMaxAge(86400);
        response.addCookie(cookie);
        response.setContentType("text/plan");
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping("/getCookie")
    public ResponseEntity<?> readCookie(@CookieValue(value = "user") String user) {
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/saveData")
    public ResponseEntity<?> saveData(@RequestBody Map<String, Object> data, HttpServletRequest request, HttpServletResponse response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonData = objectMapper.writeValueAsString(data);
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(USER_KEY)) {
                        cookie.setPath("/");
                        cookie.setMaxAge(86400);
                        response.addCookie(cookie);
                        return ResponseEntity.ok().body(HttpStatus.OK);
                    }
                }
            }
            Cookie dataCookie = new Cookie(USER_KEY, jsonData);
            dataCookie.setPath("/");
            dataCookie.setMaxAge(86400);
            response.addCookie(dataCookie);
            return ResponseEntity.ok().body(HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving data to cookie");
        }
    }

    @GetMapping("/getCookieValue")
    public ResponseEntity<?> getCookieValue(@RequestParam("cookieName") String cookieName, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return ResponseEntity.ok().body(cookie.getValue());
                }
            }
        }
        return ResponseEntity.notFound().build();
    }
}
