import { Injectable } from '@angular/core';

import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

@Injectable()
export class SportifyService {

  static BASE_URL = "https://api.spotify.com/v1";

  constructor(private http: Http) { }

  query(URL: string, params?: Array<string>): Observable<any[]> {
    let queryURL: string = `${SportifyService.BASE_URL}${URL}`;
    if(!params){
      queryURL = `${queryURL}?${params.join('&')}`;
    }
    return this.http.request(queryURL).map(res => res.json());
  }

  search(query: string, type: string): Observable<any[]> {
    return this.query('/search', [
      `q=${query}`,
      `type=${type}`
    ]);
  }

  searchTrack(query: string) {
    return this.search(query, 'track');
  }

  getTrack(id: string): Observable<any[]> {
    return this.query(`tracks/${id}`);
  }

}

export const SPOTIFY_PROVIDERS: Array<any> = [
  { provide: SportifyService, useClass: SportifyService }
];