import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { ChatNavBarComponent } from './chat-nav-bar/chat-nav-bar.component';
import { ChatThreadsComponent } from './chat-threads/chat-threads.component';
import { ChatWindowComponent } from './chat-window/chat-window.component';
import { ChatThreadComponent } from './chat-thread/chat-thread.component';
import { ChatPageComponent } from './chat-page/chat-page.component';

import { messagesServiceInjectables } from './message/messages.service';
import { threadsServiceInjectables } from './thread/threads.service';
import { usersServiceInjectables } from './user/users.service';
import { ChatMessageComponent } from './chat-message/chat-message.component';

import { FromNowPipe } from './pipes/from-now.pipe';

@NgModule({
  declarations: [
    AppComponent,
    ChatNavBarComponent,
    ChatThreadsComponent,
    ChatWindowComponent,
    ChatThreadComponent,
    ChatPageComponent,
    ChatMessageComponent,
    FromNowPipe
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [
    messagesServiceInjectables,
    threadsServiceInjectables,
    usersServiceInjectables
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
