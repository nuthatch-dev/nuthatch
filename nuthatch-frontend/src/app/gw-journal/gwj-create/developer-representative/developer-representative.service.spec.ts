import { TestBed } from '@angular/core/testing';

import { DeveloperRepresentativeService } from './developer-representative.service';

describe('DeveloperRepresentativeService', () => {
  let service: DeveloperRepresentativeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DeveloperRepresentativeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
