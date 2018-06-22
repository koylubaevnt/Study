import { Component, OnInit, OnDestroy, NgZone } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Message } from 'stompjs';

import { STOMPService } from '../../services/stomp';
import { ConfigService } from '../../services/config/config.service';

@Component({
  selector: 'app-rawdata',
  templateUrl: './rawdata.component.html',
  styleUrls: ['./rawdata.component.css'],
  providers: [STOMPService]
})
export class RawDataComponent implements OnInit, OnDestroy {

  // Stream of messages
  public messages: Observable<Message>;

  // Array of historic message (bodies)
  public mq: Array<string> = [];

  // A count of messages received
  public count = 0;

  private _counter = 1;

  /** Constructor */
  constructor(private _stompService: STOMPService,
    private _configService: ConfigService) { }

  ngOnInit() {
    // Get configuration from config service...
    /*this._configService.getConfig('api/config.json').then(
      config => {
        // ... then pass it to (and connect) STOMP:
        this._stompService.configure(config);
        this._stompService.try_connect().then(this.on_connect);
      }
    );
	*/
	
	this._stompService.configure({
		// Which server?
		  host: "172.30.78.52",
		  port: 8080,
		  ssl: false,

		  // What credentials?
		  user: "123456",
		  pass: "",

		  // Which queues?
		  publish: ["/app/hello"],
		  subscribe: ["/topic/greetings","/user/queue/horray"],

		  // How often to heartbeat?
		  heartbeat_in: 0,
		  heartbeat_out: 200,

		  // Enable client debugging?
		  debug: true
	});
     this._stompService.try_connect().then(this.on_connect);
	 
  }

  ngOnDestroy() {
    this._stompService.disconnect();
  }

  public onClick() {
    const _getRandomInt = (min, max) => {
      return Math.floor(Math.random() * (max - min + 1)) + min;
    };
    this._stompService.publish(`{ type: "Test Message", data: [ ${this._counter}, ${_getRandomInt(1, 100)}, ${_getRandomInt(1, 100)}] }`);

    this._counter++;
  }

  /** Callback on_connect to queue */
  public on_connect = () => {
	console.log("NgZone(on_connect): ", NgZone.isInAngularZone());
    // Store local reference to Observable
    // for use with template ( | async )
    this.messages = this._stompService.messages;

    // Subscribe a function to be run on_next message
    this.messages.subscribe(this.on_next);
  }

  /** Consume a message from the _stompService */
  public on_next = (message: Message) => {
console.log("NgZone(on_next): ", NgZone.isInAngularZone());
    // Store message in "historic messages" queue
    this.mq.push(message.body + '\n');

    // Count it
    this.count++;

    // Log it to the console
    console.log(this.messages);
  }
}
