import { Component, OnInit, Input } from '@angular/core';

import { Observable } from 'rxjs';

import { UsersService } from '../user/users.service';
import { ThreadsService } from '../thread/threads.service';
import { MessagesService } from '../message/messages.service';

import { User } from '../user/user.model';
import { Message } from '../message/message.model';
import { Thread } from '../thread/thread.model';


@Component({
  selector: 'chat-message',
  templateUrl: './chat-message.component.html',
  styleUrls: ['./chat-message.component.css']
})
export class ChatMessageComponent implements OnInit {

  @Input() message: Message;
  currentUser: User;
  incoming: boolean;

  constructor(public usersService: UsersService) { }

  ngOnInit() {
    this.usersService.currentUser
      .subscribe(
        (user: User) => {
          this.currentUser = user;
          if(this.message.author && user) {
            this.incoming = this.message.author.id !== user.id;
          }
        }
      );
  }

}
