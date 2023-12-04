package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	@Autowired
	private UserRepository userR;

	@GetMapping("/login")
	public String Login(Model model) {
		model.addAttribute("user", new User());
		return "Login";
	}

	@GetMapping("/signup1")
	public String signup1() {
		return "signup1";
	}

	@GetMapping("/signup2")
	public String signup2(Model model) {
		model.addAttribute("user", new User());
		return "signup2";
	}

	@GetMapping("/ipsearch")
	public String ipsearch() {
		return "ipsearch";
	}

	@PostMapping("/signup")
	public String createuser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
		userR.save(user);
		return "redirect:/login";
	}

	@GetMapping("/checkUserIdAvailability")
	@ResponseBody
	public boolean checkUserIdAvailability(@RequestParam String id) {
		// 데이터베이스에서 해당 사용자 이름을 검색하여 결과 확인
		List<User> existingUser = userR.findByUserId(id);

		// 중복 여부에 따라 결과 반환
		return existingUser.isEmpty(); // true는 중복이 아님, false는 중복임
	}

	@GetMapping("/checkUseremailAvailability")
	@ResponseBody
	public boolean checkUseremailAvailability(@RequestParam String email) {
		// 데이터베이스에서 해당 사용자 이름을 검색하여 결과 확인
		List<User> existingUser = userR.findByemailList(email);

		// 중복 여부에 따라 결과 반환
		return existingUser.isEmpty(); // true는 중복이 아님, false는 중복임
	}
	
	@PostMapping("/login")
    public String loginUser(@RequestParam String id, @RequestParam String passwd, RedirectAttributes redirectAttributes, HttpSession session) {
        // 사용자 정보를 데이터베이스에서 조회합니다.
        List<User> users = userR.findByUserId(id);

        if (!users.isEmpty()) {
            User user = users.get(0); // 첫 번째 사용자를 선택합니다.
            if (passwd.equals(user.getPasswd())) {
                session.setAttribute("loggedInUserId", user.getId());
                return "redirect:/"; // 로그인 후 이동할 페이지
            }
        }
        redirectAttributes.addFlashAttribute("errorMessage", "아이디(로그인 전용 아이디) 또는 비밀번호를 잘못 입력했습니다.\r\n"
              + "입력하신 내용을 다시 확인해주세요.");
        return "redirect:/login"; // 로그인 실패 시 표시할 페이지
    }
	
	@GetMapping("/logout")
    public String logout(HttpSession session) {
        // 세션에서 사용자 정보 가져오기
        String loggedInUserId = (String) session.getAttribute("loggedInUserId");

        // 세션에 loggedInNickname가 있는지 확인
        if (loggedInUserId != null) {
            // 세션에 로그인 정보가 있을 경우, 세션 삭제 후 로그인 페이지로 이동
            session.removeAttribute("loggedInUserId");
            return "redirect:/";
        } else {
            // 세션에 로그인 정보가 없을 경우, signup 페이지로 이동
            return "redirect:/login";
        }
    }
}
