import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';

import {AppComponent} from './app.component';
import {RawDataComponent} from './components/rawdata/rawdata.component';
import {StatusComponent} from './components/status/status.component';
import {StompConfig, StompService} from '@stomp/ng2-stompjs';
import * as SockJs from 'sockjs-client';

const stompConfig: StompConfig = {
  // Which server?
  url: new SockJs('http://172.30.78.52:8080/pa-efm/questing'),

  // Headers
  // Typical keys: login, passcode, host
  headers: {
    login: '123456789',
    passcode: 'guest'
  },

  // How often to heartbeat?
  // Interval in milliseconds, set to 0 to disable
  heartbeat_in: 0, // Typical value 0 - disabled
  heartbeat_out: 20000, // Typical value 20000 - every 20 seconds

  // Wait in milliseconds before attempting auto reconnect
  // Set to 0 to disable
  // Typical value 5000 (5 seconds)
  reconnect_delay: 5000,

  // Will log diagnostics on console
  debug: true
};


@NgModule({
  declarations: [
    AppComponent,
    RawDataComponent,
    StatusComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [
    StompService,
    {
      provide: StompConfig,
      useValue: stompConfig
    }
  ],
  bootstrap: [AppComponent]
})

export class AppModule {
}