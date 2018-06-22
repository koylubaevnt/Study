import { Component, OnInit, Inject, ElementRef } from '@angular/core';
import { Thread } from '../thread/thread.model';
import { User } from '../user/user.model';
import { AppStore } from '../app.store';
import { AppState, getCurrentThread, getCurrentUser } from '../app.reducer';
import * as Redux from 'redux';

import * as ThreadActions from '../thread/thread.actions';

@Component({
  selector: 'app-chat-window',
  templateUrl: './chat-window.component.html',
  styleUrls: ['./chat-window.component.css']
})
export class ChatWindowComponent implements OnInit {

  currentThread: Thread;
  draftMessage: { text: string };
  currentUser: User;

  constructor(@Inject(AppStore) private store: Redux.Store<AppState>, private el: ElementRef) {
    store.subscribe(() => this.updateState());
    this.updateState()
    this.draftMessage = { text: '' }; 
   }

   updateState():void {
    const state: AppState = this.store.getState();

    this.currentThread = getCurrentThread(state);
    this.currentUser = getCurrentUser(state);

    this.scrollToBottom();
   }

  ngOnInit() {
  }

  scrollToBottom(): void {
    const scrollPane: any = this.el
      .nativeElement.querySelector('.msg-container-base');
    if(scrollPane) {
      setTimeout(() => scrollPane.scrollTop = scrollPane.scrollHeight);
    }
  }

  sendMessage(): void {
    this.store.dispatch(ThreadActions.addMessage(
      this.currentThread,
      {
        author: this.currentUser,
        isRead: true,
        text: this.draftMessage.text
      }
    ));
    this.draftMessage = { text: '' };
  }

  onEnter(event: any) {
    this.sendMessage();
    event.preventDefault();
  }
}
