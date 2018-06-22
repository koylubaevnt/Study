import { Injectable } from '@angular/core';

import { Message } from './message.model';
import { Subject, BehaviorSubject, Observable } from 'rxjs';
import { User } from '../user/user.model';
import { Thread } from '../thread/thread.model';

const initialMessages: Message[] = [];

@Injectable()
export class MessagesService {

  newMessages: Subject<Message> = new Subject<Message>();
  messages: Observable<Message[]>;
  updates: Subject<any> = new Subject<any>();
  
  //action streams
  create: Subject<Message> = new Subject<Message>();
  markThreadAsRead: Subject<any> = new Subject<any>();

  constructor() { 
    this.messages = this.updates
      .scan((messages: Message[], operation: IMessageOperation) => {
        return operation(messages);
      }, initialMessages)
      .publishReplay(1)
      .refCount();

    this.create
      .map(function(message: Message): IMessageOperation {
        return (messages: Message[]) => {
          return messages.concat(message);
        };
      })
      .subscribe(this.updates);

    this.newMessages
      .subscribe(this.create);

    this.markThreadAsRead
      .map((thread : Thread) => {
        return (messages: Message[]) => {
          return messages.map( (message: Message) => {
            if (message.thread.id === thread.id) {
              message.isRead = true
            }
            return message;
          });
        };
      })
      .subscribe(this.updates);

  }

  addMessage(message: Message): void {
    this.newMessages.next(message);
  }

  messagesForThreadUser(thread: Thread, user: User): Observable<Message> {
    return this.newMessages
      .filter((message: Message) => {
        return (message.thread.id === thread.id) &&
              (message.author.id !== user.id);
      })
  }
}

interface IMessageOperation extends Function {
  (messages: Message[]): Message[];
}

export const messagesServiceInjectables: Array<any> = [
  MessagesService
];