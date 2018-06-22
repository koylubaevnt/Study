import { TestBed, inject, fakeAsync, tick } from '@angular/core/testing';

import { MockBackend } from '@angular/http/testing';

import { Http, ConnectionBackend, BaseRequestOptions, Response, ResponseOptions } from '@angular/http';

import { SportifyService } from './sportify.service';

describe('SportifyService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        BaseRequestOptions,
        MockBackend,
        SportifyService,
        { provide: Http, useFactory:(backend: ConnectionBackend,
                                defaultOptions: BaseRequestOptions) => {
                                  return new Http(backend, defaultOptions);
                                }, deps: [MockBackend, BaseRequestOptions]},
      ]
    });
  });

  describe('getTrack', () => {
    it('')
  });

  it('should be created', inject([SportifyService], (service: SportifyService) => {
    expect(service).toBeTruthy();
  }));
});

