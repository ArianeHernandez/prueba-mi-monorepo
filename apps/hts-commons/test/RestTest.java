import co.htsoft.commons.net.CallPage;

public class RestTest {

	public static void main(String[] args) {

		CallPage cp = new CallPage();

		LoginRequest request = new LoginRequest();

		request.login = "EFPEREZ";
		request.password = "meconio3";

		LoginResponse resp = cp.callJSON("http://192.168.1.77:5050/botzioServer/rst/login", request, LoginResponse.class);

		System.out.println(resp.sessionID);

	}

	public static class LoginRequest {
		public String login, password;
	}

	public static class LoginResponse {
		public String sessionID;
	}

}
