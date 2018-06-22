import { TestBed, inject } from '@angular/core/testing';

import { InMemmoryDataService } from './in-memmory-data.service';

describe('InMemmoryDataService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [InMemmoryDataService]
    });
  });

  it('should be created', inject([InMemmoryDataService], (service: InMemmoryDataService) => {
    expect(service).toBeTruthy();
  }));
});
