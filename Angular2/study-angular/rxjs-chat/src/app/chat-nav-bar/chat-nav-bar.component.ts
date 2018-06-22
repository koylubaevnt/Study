import { Component, OnInit, Inject } from '@angular/core';

import * as _ from 'lodash';

import { ThreadsService } from '../thread/threads.service';
import { MessagesService } from '../message/messages.service';

import { Message } from '../message/message.model';
import { Thread } from '../thread/thread.model';


@Component({
  selector: 'chat-nav-bar',
  templateUrl: './chat-nav-bar.component.html',
  styleUrls: ['./chat-nav-bar.component.css']
})
export class ChatNavBarComponent implements OnInit {

  unreadMessagesCount: number;

  constructor(public messagesService: MessagesService,
              public threadsService: ThreadsService) { }

  ngOnInit() {
    this.messagesService.messages
      .combineLatest(
        this.threadsService.currentThread,
        (messages: Message[], currentThread: Thread) => 
          [currentThread, messages])
        .subscribe(([currentThread, messages]: [Thread, Message[]]) => {
        this.unreadMessagesCount =
          _.reduce(
            messages,
            (sum: number, m: Message) => {
              const messageIsInCurrentThread: boolean = m.thread && currentThread &&
                (currentThread.id === m.thread.id);
              if(m && !m.isRead && !messageIsInCurrentThread) {
                sum = sum + 1;
              }
              return sum;
            },
            0);
    });
  }

}
