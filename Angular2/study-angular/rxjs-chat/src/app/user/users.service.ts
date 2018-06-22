import { Injectable } from '@angular/core';

import { User } from './user.model';
import { Subject, BehaviorSubject } from 'rxjs';


@Injectable()
export class UsersService {

  currentUser: Subject<User> = new BehaviorSubject<User>(null);

  constructor() { }

  public setCurrentUser(user: User): void {
    this.currentUser.next(user);
  }
}

export const usersServiceInjectables: Array<any> = [
  UsersService
];