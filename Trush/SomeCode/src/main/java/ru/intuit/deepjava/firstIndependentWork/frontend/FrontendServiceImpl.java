package ru.intuit.deepjava.firstIndependentWork.frontend;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import ru.intuit.deepjava.firstIndependentWork.accountService.AccountServiceImpl;
import ru.intuit.deepjava.firstIndependentWork.base.Address;
import ru.intuit.deepjava.firstIndependentWork.base.FrontendService;
import ru.intuit.deepjava.firstIndependentWork.base.Message;
import ru.intuit.deepjava.firstIndependentWork.base.MessageSystem;
import ru.intuit.deepjava.firstIndependentWork.base.UserSession;
import ru.intuit.deepjava.firstIndependentWork.frontend.pages.AutorizationPageGeneration;
import ru.intuit.deepjava.firstIndependentWork.frontend.pages.FailedAutorizationPageGeneration;
import ru.intuit.deepjava.firstIndependentWork.frontend.pages.GamePageGeneration;
import ru.intuit.deepjava.firstIndependentWork.frontend.pages.MainPageGeneration;
import ru.intuit.deepjava.firstIndependentWork.frontend.pages.WaitGamePageGeneration;
import ru.intuit.deepjava.firstIndependentWork.frontend.pages.WaitPageGeneration;
import ru.intuit.deepjava.firstIndependentWork.gameMehanics.GameMehanicsImpl;
import ru.intuit.deepjava.firstIndependentWork.resourceSystem.base.VirtuaFileSystem;
import ru.intuit.deepjava.firstIndependentWork.utils.TimeHelper;

public class FrontendServiceImpl extends AbstractHandler implements FrontendService {

	private static final int TICK_TIME = 1000;

	private final Map<String, UserSession> sessionIdToUserSession;
	private final Map<String, UserSession> autorizedSessionIdToUserSession;
	private final List<String> freeUserSessions;
	
	
	//private static final String GAME_NAME = "/games/dotgame";
	
	private final MessageSystem messageSystem; 
	private final VirtuaFileSystem virtuaFileSystem; 
	private final Address address;
	
	AutorizationPageGeneration autorizationPageGeneration;
	WaitPageGeneration waitPageGeneration;
	MainPageGeneration mainPageGeneration;
	FailedAutorizationPageGeneration failedAutorizationPageGeneration;
	GamePageGeneration gamePageGeneration;
	WaitGamePageGeneration waitGamePageGeneration;
	
	public FrontendServiceImpl(MessageSystem messageSystem, VirtuaFileSystem virtuaFileSystem) {
		this.messageSystem = messageSystem;
		this.virtuaFileSystem = virtuaFileSystem;
		this.address = new Address();
		messageSystem.addService(this);
		this.sessionIdToUserSession = new HashMap<>();
		this.autorizedSessionIdToUserSession = new HashMap<>();
		this.freeUserSessions = new LinkedList<>();
		initializePageGenerators();
	}
	
	private void initializePageGenerators() {
		this.autorizationPageGeneration = new AutorizationPageGeneration();
		this.waitPageGeneration = new WaitPageGeneration();
		this.mainPageGeneration = new MainPageGeneration();
		this.failedAutorizationPageGeneration = new FailedAutorizationPageGeneration();
		this.gamePageGeneration = new GamePageGeneration();
		this.waitGamePageGeneration = new WaitGamePageGeneration();
	}
	
	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException {
			response.setContentType("text/html;charset=utf-8");
			response.setStatus(HttpServletResponse.SC_OK);
			baseRequest.setHandled(true);
			if(request.getRequestURI().startsWith("/static")) {
				int filePathStartIndex = request.getRequestURI().indexOf("/static");
				if (filePathStartIndex >= 0) {			
					try {
						String filePath = request.getRequestURI().substring(filePathStartIndex + 1);
						byte[] outBuff = virtuaFileSystem.getBytes(filePath);
						response.getOutputStream().write(outBuff, 0, outBuff.length);
						response.setContentType("image/png;charset=utf-8");
						response.setStatus(HttpServletResponse.SC_OK);				
					} catch (IOException e) {
						response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					}			
				} else {
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				}
				return;
			}
			String page;
			UserSession userSession;
			String sessionId = request.getParameter("sessionId");
			String userName = request.getParameter("userName");
			
			if(sessionId == null || sessionId.isEmpty()) {
				sessionId = UUID.randomUUID().toString();
				userSession = new UserSession(sessionId);
				sessionIdToUserSession.put(sessionId, userSession);
				page = autorizationPageGeneration.getPage(userSession);
			} else {
				userSession = sessionIdToUserSession.get(sessionId);
				if(userSession.getUserId() == null) {
					if(!userSession.isAuthorizationIs()) { 
						//пользователь не авторизован...
						page = waitPageGeneration.getPage(userSession);
						autentificatedUser(sessionId, userName);
						userSession.setAuthorizationIs(true);
					} else { 
						if(userSession.isErrorAutorization()) {
							//Ошибка авторизации
							userSession.setAuthorizationIs(false);
							userSession.setErrorAutorization(false);
							page = failedAutorizationPageGeneration.getPage(userSession);
						} else {
							//Ожидание аторизации
							page = waitPageGeneration.getPage(userSession);
						}
					}
				} else {
					//пользователь авторизован
					if (!freeUserSessions.contains(sessionId)){
						//Если сессии нет в списке свободных, то значит пользователь играет в игру 
						page = gamePageGeneration.getPage(userSession);
					} else {
						//Посмотрим есть ли достаточное количесвто пользователей для старта игры 
						if(target.equals("/dotgames")) {
							if(freeUserSessions.size() >= 2) {
								startNewGameSession(sessionId);
							}
							page = waitGamePageGeneration.getPage(userSession);
						} else {
							page = mainPageGeneration.getPage(userSession);
						}
					}
				}
			}
			response.getWriter().write(page);
			//переделать на правильное распознавание событий
			
			/*
			if(!target.equals(GAME_NAME)) {
				return;
			}
			
			
			String page;
			//Integer sessionId и объект userSession
			String sessionId = request.getParameter("sessionId");//при первом запросе, этот параметр пустой
			UserSession userSession;
			PrintWriter printWriter = response.getWriter();
			if(sessionId == null) {
				//Вернем пользователю форму для ввода имени...
				sessionId = UUID.randomUUID().toString();
				userSession = new UserSession(sessionId, null, null);
				sessionIdToUserSession.put(sessionId, userSession);
				page = autorizationPageGeneration.getPage(userSession);
			} else {
				userSession = sessionIdToUserSession.get(sessionId);
				if(userSession.getUserName() != null && !userSession.getUserName().equals(userName)) {
					//Вводим данные о новом пользователе
					userSession.setUserId(null);
					userSession.setUserName(userName);
				} else if(userSession.getUserName() == null || userSession.getUserName().isEmpty()) {
					userSession.setUserName(userName);
				}
				if(userSession.getUserId() != null && userSession.getUserId().equals(USER_NOT_FOUND)) {
					//Нет такого пользователя, попросим ввести другие данные
					page = failedAutorizationPageGeneration.getPage(userSession);
				} else {
					if(userSession.getUserId() == null || userSession.getUserId().equals(USER_NOT_FOUND)) {
						//Пользователь не авторизован, авторизуем...
						//PageGenerator.getAutorizationWait(printWriter, userSession);
						page = waitPageGeneration.getPage(userSession);
						//Тут надо проверить, что запрос на авторизацию у пользователя уже был, чтобы 2 задачу не делать для AccountService
						Address addressAccountService = messageSystem.getAddressService().getAddress(AccountServiceImpl.class);
						Message message = new MessageGetUserId(getAddress(), addressAccountService, sessionId, userName);
						messageSystem.sendMessage(message);
					} else {
						//Пользователь авторизован
						page = mainPageGeneration.getPage(userSession);
						//Начинаем ИГРУ???
					}
				}
			}
			response.getWriter().write(page);*/
	}

	private void autentificatedUser(String sessionId, String name) {
		Address addressAccountService = messageSystem.getAddressService().getAddress(AccountServiceImpl.class);
		Message message = new MessageGetUserId(getAddress(), addressAccountService, sessionId, name);
		messageSystem.sendMessage(message);
	}
	
	private void startNewGameSession(String sessionId) {
		freeUserSessions.remove(sessionId);
		String sessionEnemy = freeUserSessions.get(0);
		freeUserSessions.remove(sessionEnemy);
		Address addressGameMehanicsService = messageSystem.getAddressService().getAddress(GameMehanicsImpl.class);
		Message message = new MessageStartGameSession(
				this.getAddress(), addressGameMehanicsService, 
				sessionIdToUserSession.get(sessionId), sessionIdToUserSession.get(sessionEnemy));
		messageSystem.sendMessage(message);
		
	}

	@Override
	public void run() {
		while(true) {
			messageSystem.execForAbonent(this);
			//removeDeadUsers();
			TimeHelper.sleep(TICK_TIME);
		}
		
	}

	@Override
	public Address getAddress() {
		return address;
	}

	@Override
	public MessageSystem getMessageSystem() {
		return messageSystem;
	}

	@Override
	public void updateUser(String sessionId, Integer userId, String name) {
		UserSession userSession = sessionIdToUserSession.get(sessionId);
		if(userId != null) {
			if (userSession.getUserId() == null) {
				userSession.setUserName(name);
				userSession.setUserId(userId);
			}
			freeUserSessions.add(sessionId);
		} else {
			userSession.setErrorAutorization(true);
			userSession.setUserName(null);
		}
		
	}
	
}
