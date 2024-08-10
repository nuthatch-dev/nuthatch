import { TestBed } from '@angular/core/testing';

import { SroService } from './sro.service';

describe('SroService', () => {
  let service: SroService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SroService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
