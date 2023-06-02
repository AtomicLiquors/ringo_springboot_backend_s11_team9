package com.project.ringo.controller;


import java.net.URI;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ringo.model.dto.User;
import com.project.ringo.model.service.JwtService;
import com.project.ringo.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequestMapping("/api/users")
@RestController
@Api("사용자 컨트롤러  API V1")
public class UserController{
	
	@Autowired
	UserService userService;
	
	@Autowired
	JwtService jwtService;
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@GetMapping()
	protected ResponseEntity<List<User>> getUserList() throws Exception{
		return new ResponseEntity<List<User>>(userService.getUserList(), HttpStatus.OK);
	}

	@GetMapping("/{user_id}")
	protected ResponseEntity<User> getUserById(@PathVariable String user_id) throws Exception {
		User user = userService.getUserById(user_id);
		if(user!=null) {
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	//05.18 ID 중복조회
	@GetMapping("/check/{user_id}")
	protected ResponseEntity<Boolean> checkDuplicateId(@PathVariable String user_id) throws Exception {
		User user = userService.getUserById(user_id);
		if(user!=null) {
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		}else {
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}
	}
	
	//05.21 패스워드 조회
	@PostMapping("/check/pwcheck")
	public ResponseEntity<Boolean> passwordCheck(@RequestBody User user) throws Exception{
		boolean check = false;
		User userInfo = userService.login(user);

		if(userInfo != null) {
			check = true;
		}
		return new ResponseEntity<Boolean>(check, HttpStatus.OK);
	}
	
	//05.22 회원정보 수정
	@PutMapping("/{user_id}")
	public ResponseEntity<?> updateUser(@RequestBody User user) throws Exception{
		userService.updateUser(user);
		return ResponseEntity.ok().build();
	}
	
		
	
	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) User userDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			User loginUser = userService.login(userDto);
			System.out.println(loginUser.getUser_regTime());
			if (loginUser != null) {
				String accessToken = jwtService.createAccessToken("user_id", loginUser.getUser_id());// key, data
				String refreshToken = jwtService.createRefreshToken("user_id", loginUser.getUser_id());// key, data
				userService.saveRefreshToken(userDto.getUser_id(), refreshToken);
				logger.debug("로그인 accessToken 정보 : {}", accessToken);
				logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{user_id}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("user_id") @ApiParam(value = "인증할 회원의 아이디.", required = true) String user_id,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				User userDto = userService.getUserById(user_id);
				resultMap.put("userInfo", userDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	
	@ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거한다.", response = Map.class)
	@GetMapping("/logout/{user_id}")
	public ResponseEntity<?> removeToken(@PathVariable("user_id") String user_id) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			System.out.println(user_id);
			userService.deleRefreshToken(user_id);
			System.out.println(user_id);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급받는다.", response = Map.class)
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody User userDto, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refresh-token");
		logger.debug("token : {}, userDto : {}", token, userDto);
		if (jwtService.checkToken(token)) {
			if (token.equals(userService.getRefreshToken(userDto.getUser_id()))) {
				String accessToken = jwtService.createAccessToken("user_id", userDto.getUser_id());
				logger.debug("token : {}", accessToken);
				logger.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
		} else {
			logger.debug("리프레쉬토큰도 사용불!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
//	@PostMapping("/login")
//	public ResponseEntity<?> login(@RequestBody User loginUser) throws SQLException {
//	    User user = userService.getUserById(loginUser.getUser_id());
//	    if(user == null || !loginUser.getUser_pw().equals(user.getUser_pw())) {
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//	    }
//
//	    String token = jwtService.generateToken(loginUser.getUser_id());
//	    return ResponseEntity.ok(token);
//	}
	
//	@PostMapping("/login")
//    public ResponseEntity<?> login(@RequestParam String user_id, @RequestParam String user_pw) throws SQLException {
//        User user = userService.getUserByIdAndPw(user_id, user_pw);
//        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else {
//            String token = jwtService.generateToken(user.getUser_id(), user.getUser_pw());
//            return ResponseEntity.ok().header("Authorization", "Bearer " + token).body(user);
//        }
//    }
	
	@PostMapping
	protected ResponseEntity<?> registUser(@RequestBody User user) throws Exception {
		
		boolean flag = userService.registUser(user);
		if(flag) {
			return ResponseEntity.created(URI.create("/api/users/"+user.getUser_id())).build();
			//new ResponseEntity(HttpStatus.CREATED);
		}else {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping
	protected ResponseEntity<?> modifyUser(@RequestBody User user) throws Exception {
		logger.debug("신규 가입 회원", user);
		userService.updateUser(user);
		return ResponseEntity.ok().build();
		//아이디 중복 체크 필요.
		/*
		 * if (sUserNo == null || sUserNo.trim().length() == 0 || dname == null ||
		 * dname.trim().length() == 0 || loc == null || loc.trim().length() == 0) {
		 * request.setAttribute("errorMsg", "입력데이터 값에 문제가 있습니다. 올바르게 입력해주세요");
		 * request.setAttribute("dept", new User(Integer.parseInt(sUserNo),dname,loc));
		 * return new PageInfo("/detail.jsp"); } // 유효성 검사
		 */
	}

	@DeleteMapping("/{user_no}")
	protected ResponseEntity<?> deleteUser(@PathVariable int user_no) throws Exception {
		User user = userService.getUserByNo(user_no);
		if(user!=null) {
			userService.deleteUser(user_no);
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/*
	@GetMapping
	protected ResponseEntity<List<User>> searchUser(@RequestParam Map<String,String> map) throws Exception{
		return new ResponseEntity<List<User>>(userService.getUsersByName(map), HttpStatus.OK);
	}*/
	
	
	/*
	@PostMapping
	protected PageInfo logIn(String userId, String userPw) throws Exception {
		
		//유효성 검증
		User user = userService.login(userId, userPw);
		if(user!=null) {
			
			System.out.printf("login Successful : ID %s, name %s\n", userId, user.getUser_name());
			
			//세션은 어떻게?
			
			 HttpSession session = request.getSession(); session.setAttribute("user",
			 user); session.setAttribute("regdateString", new
			 SimpleDateFormat("yyyy.MM.dd.").format(user.getUser_regTime()));
			 
			
		}else {
			throw new Exception("회원정보가 일치하지 않습니다.");
		}
		
		return new PageInfo(false, "/index.jsp");
		//현재 페이지로 돌아올 방법?
	}
	
	protected PageInfo logOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.invalidate();
		return new PageInfo(false, "/index.jsp");
		//현재 페이지로 돌아올 방법?
	}
	
	protected PageInfo join(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("joinId");
		String pw = request.getParameter("joinPw");
		String name = request.getParameter("joinName");
		String email = request.getParameter("joinEmail");
		String addressBasic = request.getParameter("joinAddressBasic");
		String addressDetail = request.getParameter("joinAddressDetail");
		String image = request.getParameter("joinImage");
		
		User user = new User(id, pw, name, email, addressBasic, addressDetail, image);
		Boolean flag = userService.join(user);
		
		return new PageInfo(false, "/index.do");
		//현재 페이지로 돌아올 방법?
	}
	
	protected PageInfo update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("myPageId");
		String currPw = request.getParameter("myPageCurrPw"); 
		
		String name = request.getParameter("myPageName");
		String newPw = request.getParameter("myPageNewPw");
		
		String email = request.getParameter("myPageEmail");
		String addressBasic = request.getParameter("myPageAddressBasic");
		String addressDetail = request.getParameter("myPageAddressDetail");
		String image = request.getParameter("myPageImage");
		
		System.out.println(newPw);
		if(newPw.trim().equals("")) {
			newPw = currPw;
		}
		
		
		User user = new User(id, newPw, name, email, addressBasic, addressDetail, image);
		Boolean flag = userService.update(user, currPw);
		
		
		if(flag) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			return new PageInfo(false, "/index.do");
		}else {
			throw new Exception("비밀번호를 잘못 입력했거나, 수정 중 예기치 못한 에러가 발생했습니다.");
		}
		
		
	}
	
	protected PageInfo quit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("myPageId");
		
		userService.quit(id);
		
		HttpSession session = request.getSession();
		session.invalidate();
		return new PageInfo(false, "/index.jsp");
	}
	
	protected PageInfo getUserById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("findId");
		User user = userService.getUserById(id);
		
		if(user!=null) {
			request.setAttribute("user", user);
			request.setAttribute("regdateString", new SimpleDateFormat("yyyyMMdd").format(user.getUser_regTime()));
		}
		
		return new PageInfo("/user/findpw.jsp");
		//현재 페이지로 돌아올 방법?
	}*/
}

