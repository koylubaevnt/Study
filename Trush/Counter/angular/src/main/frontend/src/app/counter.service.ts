import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Counter } from './counter';

import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { catchError, map, tap } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class CounterService {

  private counterUrl = '/api/counters';  // URL to web api

  constructor(private http: HttpClient) { }

  addCounter(max?: number): Observable<Counter> {
    const url = `${this.counterUrl}/add`;
    const counter: Counter = new Counter();
    counter.max = max;
    return this.http.post<Counter>(url, counter).pipe(
      catchError(this.handleError('addCounter', null))
    );
  }

  deleteCounter(id: string): Observable<Counter> {
    const url = `${this.counterUrl}/${id}`;
    return this.http.delete<Counter>(url).pipe(
      catchError(this.handleError('deleteCounter', null))
    );
  }

  getCounterList(): Observable<Counter[]> {
    const url = `${this.counterUrl}/`;
    return this.http.get<Counter[]>(url).pipe(
      catchError(this.handleError('getCounterList', null)
    ));
  }

  getCounter(id: string): Observable<Counter> {
    const url = `${this.counterUrl}/${id}`;
    return this.http.get<Counter>(url).pipe(
      catchError(this.handleError('getCounter', null)
    ));
  }

  start(id: string): Observable<Counter> {
    const counter: Counter = new Counter();
    counter.id = id;
    const url = `${this.counterUrl}/start`;
    return this.http.post<Counter>(url, counter).pipe(
      catchError(this.handleError('start', null))
    );
  }

  stop(id: string): Observable<Counter> {
    const counter: Counter = new Counter();
    counter.id = id;
    const url = `${this.counterUrl}/stop`;
    return this.http.post<Counter>(url, counter).pipe(
      catchError(this.handleError('stop', null))
    );
  }

  pause(id: string): Observable<Counter> {
    const counter: Counter = new Counter();
    counter.id = id;
    const url = `${this.counterUrl}/pause`;
    return this.http.post<Counter>(url, counter).pipe(
      catchError(this.handleError('pause', null))
    );
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error);
      return of(result as T);
    };
  }
}
