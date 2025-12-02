package hello.login.web.session;

import hello.login.domain.member.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SessionManagerTest {
   SessionManager sessionManager = new SessionManager();

   @Test
    void sessionTest() {

       // 세션 생성
       // 서버에서 응답 전송
       MockHttpServletResponse response = new MockHttpServletResponse();
       Member member = new Member();

       sessionManager.createSession(member, response);

       // 요청 응답 쿠키 저장
       // 클라이언트에서 서버로
       MockHttpServletRequest request = new MockHttpServletRequest();
       request.setCookies(response.getCookies());

       Object result = sessionManager.getSession(request);

       sessionManager.expire(request);
      Object expired = sessionManager.getSession(request);
      assertNull(expired);
   }
}
