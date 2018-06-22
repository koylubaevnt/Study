package ru.intuit.deepjava.firstIndependentWork.main;

import org.eclipse.jetty.server.Server;

import ru.intuit.deepjava.firstIndependentWork.accountService.AccountServiceImpl;
import ru.intuit.deepjava.firstIndependentWork.base.AccountService;
import ru.intuit.deepjava.firstIndependentWork.base.FrontendService;
import ru.intuit.deepjava.firstIndependentWork.base.MessageSystem;
import ru.intuit.deepjava.firstIndependentWork.frontend.FrontendServiceImpl;
import ru.intuit.deepjava.firstIndependentWork.gameMehanics.GameMehanicsImpl;
import ru.intuit.deepjava.firstIndependentWork.gameMehanics.base.GameMehanics;
import ru.intuit.deepjava.firstIndependentWork.messageSystem.MessageSystemImpl;
import ru.intuit.deepjava.firstIndependentWork.resourceSystem.VirtualFileSystemImpl;
import ru.intuit.deepjava.firstIndependentWork.resourceSystem.base.VirtuaFileSystem;

public class HelloServer
{
	public static final int PORT = 8080;
	
	public static void main(String[] args) throws Exception {
		MessageSystem messageSystem = new MessageSystemImpl();
		VirtuaFileSystem virtuaFileSystem = new VirtualFileSystemImpl("");
		
		FrontendService frontend = new FrontendServiceImpl(messageSystem, virtuaFileSystem);
		AccountService accountService = new AccountServiceImpl(messageSystem);
		GameMehanics gameMehanics = new GameMehanicsImpl(messageSystem);
		
		new Thread(frontend).start();
		new Thread(accountService).start();
		new Thread(gameMehanics).start();
		
		Server server = new Server(PORT);
		server.setHandler((FrontendServiceImpl)frontend);
		server.start();
		server.join();

	}

}
